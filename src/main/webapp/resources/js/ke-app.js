(function(){
	var app = angular.module('keorder', []);
	
	app.controller('OrderCtrl', ['$log', '$http', function($log, $http){
		var order = this;
		order.saleitemlist = [];
		order.$log = $log;
		
		$http({method : 'GET', url : 'api/saleitem/level.json?level=1'}).success(function(data){
			order.saleitemlist = data;
		});
		
		this.showOptions = function(saleitemid) {
			
		};
	}]);
})();