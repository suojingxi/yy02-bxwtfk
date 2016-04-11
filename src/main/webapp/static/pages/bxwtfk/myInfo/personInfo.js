define([ 'jquery', 'knockout', 'text!static/pages/bxwtfk/myInfo/personInfo.html', 'mvvc','jquery.custom'], function($, ko, template) {

	var pageUrl = '/bxwtfk/myInfo/personInfo';
	
	var viewModel = {
			data : {
				content : ko.observableArray([])
			},
			searchText : ko.observable(""),
			setData : function(data) {
				this.data.content(data.SENDCONTENT); 
			},
			pageUrl : pageUrl
	};
	/*
	viewModel.search = function() {
		viewModel.load();                                                                                              
	}
	
	viewModel.load = function(){
		var me = this;
		var test1=$.trim($('#test1').val());
		
		$.ajax({
			type : 'GET',
			cache : false,
			url : $ctx + this.pageUrl,
			data : {
				test1:test1
			},
			//dataType : 'json',
			success : function(obj) {
				me.setData(obj);
			},
			error : function(obj){
				alert("obj error");
			}
		});
	}
	*/
	
	viewModel.load = function(){
		var me = this;
		
		$.ajax({
			type : 'GET',
			cache : false,
			url : $ctx + this.pageUrl,
			data : {},
			dataType : 'json',
			success : function(obj) {
				me.setData(obj);
			},
			error : function(obj){
				alert("obj error");
			}
		});
	}
	
	var init = function() {
		viewModel.load();
	}
	
	return {
		'model' : viewModel,
		'template' : template,
		'init' : init
	};
});