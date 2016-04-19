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
	
	viewModel.load = function(){
		var me = this;
		
		$.ajax({
			type : 'POST',
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
	
	this.delcfm = function(e){
		removexxid=e.id;
		$("#cfminfo").html("您确认要删除吗？");
		$("#cfmfunction").attr("onclick","removexx("+removexxid+");");
	    $('#cfminfoModel').modal();  
	}
	
	this.showmyInfo = function(e){
		var id = e.id.substr(6, e.id.length);
		$.ajax({
			type : 'GET',
			cache : false,
			url : $ctx + "/bxwtfk/myInfo/personInfoById",
			data : {
				id : id
			},
			dataType : 'json',
			success : function(obj) {
				$("#myRRIFStitle").html(obj.SENDCONTENT.contentThemes);
				$("#myRRIFSinfo").html(obj.SENDCONTENT.content);
				$("#myRRIFStime").html(obj.SENDCONTENT.sendTime);
				$("#myRRIFSinfofunction").attr("onclick","removexx("+id+");");
			    $('#myRRIFSinfoModel').modal(); 
			},
			error : function(obj){
				alert("obj error");
			}
		});
		
	}
	
	//去后台交互删除
	this.removexx = function(e){
		var id = e;
		$.ajax({
			type : 'GET',
			cache : false,
			url : $ctx + "/bxwtfk/myInfo/delPersonById",
			data : {
				id : id
			},
			dataType : 'json',
			success : function(obj) {
				if(obj > 0){
					//移除页面中元素
				}else{
					//删除失败
					
				}
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