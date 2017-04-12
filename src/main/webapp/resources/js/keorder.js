(function ($) {
    $.SaleItem = function () {

    	this.id;
    	this.name;
    	this.level;
    	this.price;

        this.parents = [];
        this.children = [];
    };

    $.SaleItem.prototype = {
        initSaleItem : function (jsonData) {
        	
        	var data = (jsonData instanceof $) ? jsonData : $(jsonData);
        	
        	this.id = data.id;
        	this.name = data.name;
        	this.level = data.level;
        	this.price = data.price;
        },
        
        loadLevel : function (level) {
        	
        	$.ajax({
			    contentType: 'application/json',
			    data: null,
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
			    url: $.Constants.PATH_API + '/order/receive.json?level=' + level
			});
        },
        //renamed your method to start with lowercase, convention is to use
        //Capitalized names for instanceables only
        moveRight: function () {
            this.element.css("left", '+=' + 10);
        },
        moveLeft: function () {
            this.element.css("left", '-=' + 10);
        }
    };


    $.Player.defaultOptions = {
        playerX: 0,
        playerY: 0
    };

}(jQuery));
