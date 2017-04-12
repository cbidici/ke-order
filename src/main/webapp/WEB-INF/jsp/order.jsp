<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="header.jsp"%>
<%@ include file="taglibs.jsp"%>
<style>
.wrapper {
	/*border: 2px solid #000;*/
	overflow: hidden;
}

.wrapper div {
	/*min-height: 200px;*/
	padding: 10px;
}

#one {
	/*background-color: gray;*/
	float: left;
	margin-right: 20px;
	width: 300px;
	/*border-right: 2px solid #000;*/
}

#two {
	/*background-color: white;*/
	overflow: hidden;
	margin: 10px;
	/*border: 2px dashed #ccc;*/
	min-height: 300px;
}

@media screen and (max-width: 400px) {
	#one {
		float: none;
		margin-right: 0;
		width: auto;
		border: 0;
		border-bottom: 2px solid #000;
	}
}
</style>

<h1></h1>

<div class="wrapper">
	<div id="one">
		<ul class="nav nav-pills nav-stacked">
			<c:forEach items="${saleItems}" var="saleItem">
				<li role="presentation"><a href="JavaScript:;"
					onclick="showOptions('${saleItem.id}', '${saleItem.name}')">${saleItem.name} +</a></li>
			</c:forEach>
		</ul>

		<ul class="nav nav-pills nav-stacked" ng-controller="OrderCtrl as order">
			<li role="presentation" ng-repeat="saleitem in order.saleitemlist">
				<a href ng-click="order.showOptions(saleitem.id)">
					{{saleitem.name}} +
				</a>
		</ul>
	</div>
	<div id="two">
		<div style="text-align: center; border-bottom: 1px dashed #333333"><h3>Sepetiniz</h3></div>
		<div id="basketContent">
			
		</div>
		<button type="button" class="btn btn-primary" onclick="subbmitOrder()">Siparişi Ver</button>
	</div>
</div>


<div class="modal fade" id="optionModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">Modal title</h4>
			</div>
			<div class="modal-body">
				<div id="itemName"></div>
				<div id="childItemWrapper"></div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Vazgeç</button>
				<button type="button" class="btn btn-primary" onclick="addToBasket()">Sepete Ekle</button>
			</div>
		</div>
	</div>
</div>

<div class="modal fade" id="messageModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 id="messageTitle" class="modal-title" id="myModalLabel"></h4>
			</div>
			<div id="messageContent" class="modal-body"></div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">

	/*************************
	Refactor all javascirpt to use JS classes with JSON data.
	**************************/

	var showOptions = function(itemId, itemName) {
		$.getJSON("api/saleitem/children.json?id=" + itemId, function(data) {
			var items = [];
			$.each(data, function(key, value) {
				items.push("<li id='" + value.id + "'>"
					+ value.name
					+ " (<span id='count" + value.id +"'>0</span>) <a href='JavaScript:;' onclick='addChildItem("
					+ value.id
					+ ")'>Ekle</a></li>");
			});

			$("<ul/>", {"id" : "childItems", "class" : "", html : items.join("")}).appendTo("#childItemWrapper");
		});

		var modal = $('#optionModal');
		modal.find('#childItemWrapper').empty();
		modal.find('.modal-title').html("<span id='"+itemId+"' class='itemIdCls'>" + itemName + "</span>");
		modal.modal('show');
	};

	var addChildItem = function(childItemId) {
		var newCount = parseInt($('#childItemWrapper').find(
				'#count' + childItemId).text()) + 1;
		$('#childItemWrapper').find('#count' + childItemId).text(newCount);
	};

	var addToBasket = function() {
		var modal = $('#optionModal');
		var itemId = modal.find('.itemIdCls').attr('id');
		var itemName = modal.find('.itemIdCls').html();

		$itemWrapper = $("<div/>", {
			"id" : "itemWrapper",
			"class" : "",
			html : ""
		})
		$itemWrapper.appendTo("#basketContent");
		$itemWrapper
				.append("<div id="+itemId+"><strong>"
						+ itemName
						+ "</strong><button type='button' class='close' data-dismiss='modal' onclick='$(this).parent().parent().remove();'><span aria-hidden='true'>&times;</span><span class='sr-only'>Close</span></button></div>");
		$itemWrapper.append($("#childItems"));
		$("#childItems").attr("id", "childItemsBasket");
		$('#childItemsBasket a').remove();
		modal.modal("hide");
	};

	var subbmitOrder = function() {

		var requestBody = "[";
		
		var orderItems = [];
		$("#basketContent #itemWrapper").each(function() {
			var tmpStr = '{"itemCount":1,"saleItem":{"id":';
			
			//requestBody = requestBody + '{"itemCount":1,"saleItem":{"id":'
			var saleItemId = $(this).find("div").attr("id");
			tmpStr = tmpStr + saleItemId + "}";
			//requestBody = requestBody + saleItemId + "}";
			
			if($(this).find("li").length > 0){
				tmpStr = tmpStr + ', "children" : [';
				//requestBody = requestBody + ', "children" : [';
				
				var childItems = [];
				$(this).find("li").each(function(){
					var saleItemId = $(this).attr("id");
					var saleItemCount = $(this).find("span").text();
					if(saleItemCount != "0"){
						childItems.push('{"itemCount":'+saleItemCount+', "saleItem":{"id":'+saleItemId+'}}');
					}
				});
				
				tmpStr = tmpStr + childItems.join(', ') + ']';
				//requestBody = requestBody + childItemStr + ']';
			}
			
			tmpStr = tmpStr + "}";
			orderItems.push(tmpStr);
			//requestBody = requestBody + "},";
		});
		
		requestBody = requestBody + orderItems.join(', ') + "]";

		if (requestBody == "[]") {
			message("Bilgi", "Lütfen menüden seçim yapınız.");
		}
		else {

			$.ajax({
			    contentType: 'application/json',
			    data: requestBody,
			    dataType: 'json',
			    success: function(data){
			    	message("Bilgi", "Siparişiniz tamamlandı. <br/> Toplam ücret : "+data.totalPrice + "TL");
			    	$("#basketContent").empty();
			    },
			    error: function(){
			    	message("Hata", "Siparişiniz verilemedi.");
			    },
			    processData: false,
			    type: 'POST',
			    url: './api/order/receive.json'
			});
			console.log("Request Sent :" + requestBody);
		}
		
	};
	
	var message = function(title, message) {
		$("#messageModal").find("#messageTitle").text(title);
		$("#messageModal").find("#messageContent").empty();
		$("#messageModal").find("#messageContent").append(message);
		$("#messageModal").modal("show");
	};
</script>



<%@include file="footer.jsp"%>