define([ 'jquery', 'knockout', 'text!static/pages/bxwtfk/myInfo/personInfoM.html', 'mvvc','jquery.custom'], function($, ko, template) {
	var infoUrl = '/bxwtfk/myInfo/personInfoM/:id';
	var pageUrl = '/bxwtfk/myInfo/personInfoM/0';

	addRouter(infoUrl);
	
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
	
	viewModel.load = function(id){
		var me = this;
		
		$.ajax({
			type : 'GET',
			cache : false,
			url : $ctx + "/bxwtfk/myInfo/personInfoById/",
			data : {
				id : id
			},
			dataType : 'json',
			success : function(obj) {
				me.setData(obj);
			},
			error : function(obj){
				alert("由于网络原因，数据加载失败");
			}
		});
	}
	viewModel.add = function() {
		window.router.setRoute(pageUrl);
	}
	// 获取路径中参数的方法
	function GetArgs(params, paramName) {
		var argsIndex = params.indexOf("?");
		var arg = params.substring(argsIndex + 1);
		args = arg.split("&");
		var valArg = "";
		for (var i = 0; i < args.length; i++) {
			str = args[i];
			var arg = str.split("=");
			if (arg.length <= 1)
				continue;
			if (arg[0] == paramName) {
				valArg = arg[1];
			}
		}
		return valArg;
	}
	
	this.delcfm = function(){
		var id = $("#xxnr").val();
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
					var href = getHref();
					window.location.href = href;
				}else{
					alert("由于网络原因，删除信息失败");
				}
			},
			error : function(obj){
				alert("由于网络原因，删除信息失败");
			}
		});
	}
	
	this.returnInd = function(){
		var href = getHref();
		window.location.href = href;
	}
	
	this.bjwd = function(){
		var id = $("#xxnr").val();
		$.ajax({
			type : 'GET',
			cache : false,
			url : $ctx + "/bxwtfk/myInfo/bjPersonById",
			data : {
				id : id
			},
			dataType : 'json',
			success : function(obj) {
				if(obj > 0){
					alert("标记成功");
				}else{
					alert("由于网络原因，标记失败");
				}
			},
			error : function(obj){
				alert("由于网络原因，标记失败");
			}
		});
	}
	
	function getHref(){
		//获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
	    var curWwwPath=window.document.location.href;
	    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
	    var pathName=window.document.location.pathname;
	    var pos=curWwwPath.indexOf(pathName);
	    //获取主机地址，如： http://localhost:8083
	    var localhostPaht=curWwwPath.substring(0,pos);
	    //获取带"/"的项目名，如：/uimcardprj
	    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
	    return localhostPaht+projectName;
	}
	
	var init = function() {
		var id = GetArgs(window.location.href, "id");
		viewModel.load(id);
	}
	
	return {
		'model' : viewModel,
		'template' : template,
		'init' : init
	};
});