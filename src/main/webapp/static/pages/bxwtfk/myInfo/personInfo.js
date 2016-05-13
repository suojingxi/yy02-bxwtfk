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
		var href = window.location.href;
		var hrefs = href.split('?');
		if(hrefs.length>1){
			if(hrefs[1].indexOf('id=')==0){
				var id = hrefs[1].substr(3, 35);
				showmyInfoById(id);
				return;
			}
		}
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
		var id = e.id.substr(6,e.id.length);
		$.ajax({
			type : 'GET',
			cache : false,
			url : $ctx + "/bxwtfk/myInfo/personInfoById?id="+id,
			data : {
			},
			dataType : 'json',
			success : function(obj) {
				getXX();
				var id="personInfoym-"+obj.SENDCONTENT.ID;
				var contentThemes = obj.SENDCONTENT.CONTENT_THEMES;
				var sendTime = obj.SENDCONTENT.SEND_TIME;
				var content = obj.SENDCONTENT.CONTENT;
				$("#divwdxx").hide();
//				$("#personInfoym").append("<div id='xxxxhead-"+id+"'><div><a href='javascript:;' onclick='returnInit(this);' id='xxlb-"+id+"'>信息列表</a>>信息详情</div><div>"+contentThemes+"</div><div>"+sendTime+"</div><div>"+content+"</div></div>");
				$("#personInfoym").append("<div id='xxxxhead-"+id+"' class='xxxx'><div class='header'>" +
						"<a href='javascript:;' onclick='returnInit(this);' id='xxlb-"+id+"'>所有信息</a> > 信息详情</div>" +
						"<div class='middle'><div style='padding: 5px 0 10px 20px;'>报销问题反馈信息详情</div><div class='hr'></div>" +
						"<div style=' line-height: 30px; padding: 5px 25px;'><span style='float: right;'>"+sendTime+"</span>"+contentThemes+"</div>" +
						"<div style='padding: 0 25px; line-height: 24px;'>"+content+"</div></div></div>");
			},
			error : function(obj){
				$("#errorts").html("信息提示");
				$("#errorinfo").html("由于网络原因，获取当前信息失败");
			    $('#errorinfoModel').modal(); 
			}
		});
		
	}
	
	this.showmyInfoById = function(e){
		var id = e;
		var me = this;
//		var href = window.location.href;
//		var hrefs = href.split('?');
//		window.location.href = hrefs[0]+"#/bxwtfk/myInfo/personInfo?"+hrefs[1].split('&')[0];
		$.ajax({
			type : 'GET',
			cache : false,
			url : $ctx + "/bxwtfk/myInfo/personInfoById?id="+id,
			data : {
			},
			dataType : 'json',
			success : function(obj) {
				getXX();
				var id="personInfoym-"+obj.SENDCONTENT.ID;
				var contentThemes = obj.SENDCONTENT.CONTENT_THEMES;
				var sendTime = obj.SENDCONTENT.SEND_TIME;
				var content = obj.SENDCONTENT.CONTENT;
				$("#divwdxx").hide();
//				$("#personInfoym").append("<div id='xxxxhead-"+id+"'><div><a href='javascript:;' onclick='returnInit(this);' id='xxlb-"+id+"'>信息列表</a>>信息详情</div><div>"+contentThemes+"</div><div>"+sendTime+"</div><div>"+content+"</div></div>");
				$("#personInfoym").append("<div id='xxxxhead-"+id+"' class='xxxx'><div class='header'>" +
						"<a href='javascript:;' onclick='returnInit(this);' id='xxlb-"+id+"'>所有信息</a> > 信息详情</div>" +
						"<div class='middle'><div style='padding: 5px 0 10px 20px;'>报销问题反馈信息详情</div><div class='hr'></div>" +
						"<div style=' line-height: 30px; padding: 5px 25px;'><span style='float: right;'>"+sendTime+"</span>"+contentThemes+"</div>" +
						"<div style='padding: 0 25px; line-height: 24px;'>"+content+"</div></div></div>");
			},
			error : function(obj){
				$("#errorts").html("信息提示");
				$("#errorinfo").html("由于网络原因，获取当前信息 失 败");
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
	
	this.returnInit = function(e){
		var id = e.id.substr(5,e.id.length);
		id = "xxxxhead-"+id;
		$("#"+id).remove();
		$("#divwdxx").show();
		if(window.location.href.indexOf("?")>-1){
			window.location.href = window.location.href.split('?')[0];
		}
		init();
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