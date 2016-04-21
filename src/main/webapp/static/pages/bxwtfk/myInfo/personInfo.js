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
				getXX();
			},
			error : function(obj){
				$("#errorts").html("信息提示");
				$("#errorinfo").html("由于网络原因，数据加载失败");
			    $('#errorinfoModel').modal(); 
			}
		});
	}
	
	this.delcfm = function(e){
		removexxid=e.id;
		$("#cfminfo").html("您确认要删除吗？");
		$("#cfmfunction").attr("onclick","removexx('"+removexxid+"');");
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
				getXX();
				$("#myRRIFStitle").html(obj.SENDCONTENT.CONTENT_THEMES);
				$("#myRRIFSinfo").html(obj.SENDCONTENT.CONTENT);
				$("#myRRIFStime").html(obj.SENDCONTENT.SEND_TIME);
				$("#myRRIFSinfofunction").attr("onclick","removexx('"+id+"');");
			    $('#myRRIFSinfoModel').modal(); 
			    viewModel.load();
			},
			error : function(obj){
				$("#errorts").html("信息提示");
				$("#errorinfo").html("由于网络原因，获取当前信息失败");
			    $('#errorinfoModel').modal(); 
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
					id = "title-" + id;
					$("#"+id).parent().parent().remove();
					getXX();
					viewModel.load();
				}else{
					$("#errorts").html("信息提示");
					$("#errorinfo").html("由于网络原因，删除信息失败");
				    $('#errorinfoModel').modal(); 
				}
			},
			error : function(obj){
				$("#errorts").html("信息提示");
				$("#errorinfo").html("由于网络原因，删除信息失败");
			    $('#errorinfoModel').modal(); 
			}
		});
	}
	
	function getXX(){
		//我的信息数
		var totalUrl = $ctx + '/bxwtfk/myInfo/getPersonInfoNum';
		$.ajax({
			type : "GET",
			contentType : 'application/json',
			url : totalUrl,
			dataType : 'json',
			cache:false,
			success : function(data) {
				var num = eval(data);
				$('#num').html(num);

			},
			error : function(obj){
				$("#errorts").html("信息提示");
				$("#errorinfo").html("由于网络原因，修改参数信息失败");
			    $('#errorinfoModel').modal(); 
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