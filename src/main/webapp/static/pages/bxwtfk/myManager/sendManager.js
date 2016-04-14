define([ 'jquery', 'knockout', 'text!static/pages/bxwtfk/myManager/sendManager.html', 'mvvc','jquery.custom'], function($, ko, template) {

	var pageUrl = '/bxwtfk/myManager/sendManager';
	var pageUrl1 = '/bxwtfk/myManager/getAllUser';
	
	var viewModel = {
			data : {
				content : ko.observableArray([]),
				allUser : ko.observableArray([])
			},
			searchText : ko.observable(""),
			setData : function(data) {
				this.data.content(data.USERINFO);
				this.data.allUser(data.ALLUSERINFO);
			},
			pageUrl : pageUrl,
			pageUrl1 : pageUrl1
	};
	
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
	
	viewModel.loadAll = function(){
		var me = this;
		
		$.ajax({
			type : 'POST',
			cache : false,
			url : $ctx + this.pageUrl1,
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

	var str = "";
	this.addUser = function(){
		var me = this;
		str += "<div id='"+me.id+"'>姓名：<span>"+me.username+"</span>" +
				"部门：<span>"+me.dept+"</span>" +
				"手机号：<span>"+me.phone+"</span>" +
				"邮箱：<span>"+me.email+"</span>" +
				"<a href='#' onclick='delUser("+me.id+"'>  -  </a></div>";
		$("#aa").html(str);
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