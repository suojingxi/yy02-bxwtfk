require([ 'jquery', 'knockout','mvvc','repair'],function($, ko, template) {
	
	var viewModel = {
				
	};
	
	
	viewModel.data = {
			data : ko.observable({}),
			fixed : fixedM(ko)
	};
	
	initViewModel(viewModel);
	
	var loadData=function(){
		var jsonUrl=$ctx +'/bxwtfk/myInfo/personInfo';
		
		  $.ajax({
				type:"GET",
				contentType: 'application/json', 
				url: jsonUrl,
				async:false,
				cache:false,
				data:{},
				dataType:'json',
				success: function(data) {
					viewModel.setData(data);
				},
				error: function(req, textStatus, errorThrown){
					if(req.responseJSON){
						var validateObj = req.responseJSON;
						if(validateObj.code){
							jAlert(validateObj.code,"错误1");
						}
					} 
					jAlert("保存失败!","错误");
				}
			});
	}
	
	//将后台数据存储到viewModel中
	viewModel.setData = function(data){
		var me = this;
		me.setFixedData(
				me.data.fixed,
				data
		);
	}
	
	//添加数据
	loadData();
	ko.applyBindings(viewModel)		
		
});