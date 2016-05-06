require([ 'jquery', 'knockout', 'ujs', 'director','error' ], function($, ko) {
	var infoUrl = '/bxwtfk/myInfo/personInfoM/:id';
	var pageUrl = '/bxwtfk/myInfo/personInfoM/0';
	
	window.router = Router();
	//登录后，首页显示的内容
	window.addRouter = function(path, func) {
		var truePath = path.replace('/:id', '');
		var tmparray = truePath.split("/");
		func = func || function(id) {
			initPage('static/pages' + truePath, id)
		}

		window.router.on(path, func);
//		}
	}
	window.addRouter(infoUrl);
	var authCode = "";
	var authCodeRelRandomKey = "";
	$(function() {
		document.addEventListener('deviceready', function() {
			cordova.exec(
				 function(res) {
					 result = eval('(' + res + ')'); 
					 authCode=result.data.auth_code;
					 authCodeRelRandomKey = result.data.authCodeRelRandomKey;
					 if(authCode!=null&&authCode!=""&&authCodeRelRandomKey!=null&&authCodeRelRandomKey!=""){
						 	dddl(authCode, authCodeRelRandomKey);
					 }
				 },
				 function(res) {
					 alert("您不是该企业用户,不能进入");
					 return;
				 },
				 'CspAuthCode',    //类名
				 'getCspAuthCode',		//方法名
				 [{
				 	"isSecure": 1		//1，安全；0，非安全；默认为0。int型。
				 }]
			);
        }, false)
		
	})
	
	
	
	this.dddl = function(authCode, authCodeRelRandomKey){
		var authCode = authCode;
		var authCodeRelRandomKey = authCodeRelRandomKey;
		$.ajax({
			type : 'POST',
			cache : false,
			url : $ctx + "/dddl",
			data : {
				authCode : authCode,
				authCodeRelRandomKey : authCodeRelRandomKey
			},
			dataType : 'json',
			success : function(obj) {
				if(obj=="1"){
					getXX();
					getPersonInfo();
					$('.content').find("a[href*='#']").each(function() {
						var path = this.hash.replace('#', '');
						addRouter(path);
					});
					window.router.init();
				}
			},
			error : function(obj){
				alert("由于网络异常，单点登录失败");
			}
		});
	}
	
	this.getPersonInfo = function(){
		$.ajax({
			type : 'POST',
			cache : false,
			url : $ctx + "/bxwtfk/myInfo/personInfo",
			data : {},
			dataType : 'json',
			success : function(obj) {
				$("#tableView").empty();
				for(var i = 0; i < obj.SENDCONTENT.length; i++){
					var id = obj.SENDCONTENT[i].ID;
					var contentThemes = obj.SENDCONTENT[i].CONTENT_THEMES.length>7?obj.SENDCONTENT[i].CONTENT_THEMES.substr(0,7)+"...":obj.SENDCONTENT[i].CONTENT_THEMES;
					var sendTime = obj.SENDCONTENT[i].SEND_TIME;
					var duStatu = "未读";
					var colors = "color:black;";
					if(obj.SENDCONTENT[i].DU_STATU!="0"){
						duStatu = "已读";
						colors = "color:gray";
					}
					var content = obj.SENDCONTENT[i].CONTENT.length>19?obj.SENDCONTENT[i].CONTENT.substr(0,16)+"...":obj.SENDCONTENT[i].CONTENT;
					$("#tableView").append("<div class='card'><li class='table-view-cell'>" +
							"<a href='javascript:;' onclick='getContent(this);' id='"+id+"' data-transition='slide-in' style='padding-right:15px;'>" +
							"<div class='media-body'><span style='"+colors+"'>" + contentThemes + "</span>" +
							"<div class='pull-right' style='font-size:12px;margin:0;padding:0;line-height:15px;height:15px;"+colors+"'>"+duStatu+"</div>" +
							"<div><span style='line-height:20px;font-size:12px;"+colors+"'>"+sendTime+"</span>" +
							"<p style='"+colors+"'>"+content+"</p></div></div></a></li></div>");
				}
			},
			error : function(obj){
				alert("由于网络异常，加载用户消息失败");
			}
		});
	}
	
	this.getContent = function(e){
		var id = e.id;
		var url = "/bxwtfk/myInfo/personInfoM/0?&id="+id;
		addRouter(url);
		window.router.setRoute(url);
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
				alert("由于网络异常，获取未读信息数量失败");
			}
		});
	}

	function initPage(p, id) {
		var module = p;
		requirejs.undef(module);
		require([ module ], function(module) {
			if(module===undefined||module==null||module.template=='status:405'){
			  window.location.href=$ctx+"/";
			  return;
			}
			
			ko.cleanNode($('.content')[0]);
			$('.content').html('');
			$('.content').html(module.template);
			ko.applyBindings(module.model, $('.content')[0]);

			module.init(id);
		})
		$.ajaxSetup({
			contentType : "application/x-www-form-urlencoded;charset=utf-8",
			complete : function(XMLHttpRequest, textStatus) {
				var sessionstatus = XMLHttpRequest
						.getResponseHeader("sessionstatus"); // 通过XMLHttpRequest取得响应头，sessionstatus，
				if (sessionstatus == "timeout") {
					// alert("登录超时,请重新登录！","提示");
					// 如果超时就处理 ，指定要跳转的页面
					window.location.replace($ctx + "/");
				}
			}
		});
	}
});