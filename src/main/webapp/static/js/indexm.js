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

		// router.routes = router.routes || {};
		//定义三级路由
//		if (tmparray[1] in router.routes
//				&& tmparray[2] in router.routes[tmparray[1]]
//				&& tmparray[3] in router.routes[tmparray[1]][tmparray[2]]) {
//			return;
//		} else {
		window.router.on(path, func);
//		}
	}
	window.addRouter(infoUrl);
	
	$(function() {
		getXX();
		
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
				alert("obj err");
			}
		});
		
		$('.content').find("a[href*='#']").each(function() {
			var path = this.hash.replace('#', '');
			addRouter(path);
		});
		window.router.init()
	})
	
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
				alert("obj error");
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
	
	/***************************************************************************
	 * delete*************************** window.app = {}; app.baseModel={};
	 * app.baseModel = { data : { content : ko.observableArray([]), firstPage :
	 * ko.observable(true), lastPage : ko.observable(false), totalPages :
	 * ko.observable(0), totalElements : ko.observable(0), last :
	 * ko.observable(false), size : ko.observable(10), number :
	 * ko.observable(0), numberOfElements : ko.observable(10), first :
	 * ko.observable(true) }, searchText : ko.observable(""), setData :
	 * function(data) { this.data.content(data.content);
	 * this.data.firstPage(data.firstPage); this.data.lastPage(data.lastPage);
	 * this.data.totalPages(data.totalPages);
	 * this.data.totalElements(data.totalElements); this.data.last(data.last);
	 * this.data.size(data.size); this.data.number(data.number + 1);
	 * this.data.numberOfElements(data.numberOfElements);
	 * this.data.first(data.first); }, infoUrl : "", addUrl : "", deleteUrl :
	 * "", pageUrl : "" };
	 * 
	 * app.baseModel.add = function(){ window.router.setRoute(this.addUrl); }
	 * 
	 * app.baseModel.update = function(){ }
	 * 
	 * app.baseModel.del = function() { var me = this; $.ajax({ type : 'DELETE',
	 * dataType : 'json', async : false, url : $ctx + this.deleteUrl + this.id,
	 * success : function(data) { if (data){ jAlert('删除成功!') var pageNum =
	 * me.data.number(); me.data.content.remove(me); me.load(pageNum); } },
	 * error : function(XMLHttpRequest, textStatus, errorThrown) {
	 * jAlert("删除失败，请稍后重试"); } });
	 *  }
	 * 
	 * app.baseModel.load = function(pageIndex){ var me = this; $.ajax({ type :
	 * 'GET', url : $ctx + this.pageUrl + pageIndex + "&searchText=" +
	 * this.searchText(), data : '', dataType : 'json', success : function(data) {
	 * me.setData(data); //console.log(me.data.totalPages())
	 * $("#pagination").pagination({ totalPages : me.data.totalPages(),
	 * currentPage : me.data.number(), page : function(page) { me.load(page); } }) }
	 * });
	 *  }
	 * 
	 * app.baseModel.searchPage = function() { this.load(1); }
	 * 
	 * baseViewModel = function(controller) { var base = this; this.data = {};
	 * this.data.content = ko.observableArray([]);
	 * 
	 * this.data.firstPage = ko.observable(true); this.data.lastPage =
	 * ko.observable(false); this.data.totalPages = ko.observable(0);
	 * this.data.totalElements = ko.observable(0); this.data.last =
	 * ko.observable(false); this.data.size = ko.observable(10);
	 * this.data.number = ko.observable(0); this.data.numberOfElements =
	 * ko.observable(10); this.data.first = ko.observable(true);
	 * 
	 * this.searchText = ko.observable("");
	 * 
	 * this.setData = function(data) { base.data.content(data.content);
	 * base.data.firstPage(data.firstPage); base.data.lastPage(data.lastPage);
	 * base.data.totalPages(data.totalPages);
	 * base.data.totalElements(data.totalElements); base.data.last(data.last);
	 * base.data.size(data.size); base.data.number(data.number + 1);
	 * base.data.numberOfElements(data.numberOfElements);
	 * base.data.first(data.first); };
	 * 
	 * this.infoUrl = controller.infoUrl; this.addUrl = controller.addUrl;
	 * this.deleteUrl = controller.deleteUrl; this.pageUrl = controller.pageUrl;
	 * 
	 * this.add = function(){ //alert(base.addUrl);
	 * window.router.setRoute(base.addUrl); }
	 * 
	 * this.update = function(){ }
	 * 
	 * this.del = function() { var me = this; $.ajax({ type : 'DELETE', dataType :
	 * 'json', async : false, url : $ctx + base.deleteUrl + this.id, success :
	 * function(data) { if (data){ jAlert('删除成功!') var pageNum =
	 * base.data.number(); base.data.content.remove(me); base.load(pageNum); } },
	 * error : function(req, textStatus, errorThrown) { jAlert("删除失败，请稍后重试!!"); }
	 * });
	 *  }
	 * 
	 * this.load = function(pageIndex){ $.ajax({ type : 'GET', url : $ctx +
	 * this.pageUrl + pageIndex + "&searchText=" + this.searchText(), data : '',
	 * dataType : 'json', success : function(data) { base.setData(data);
	 * //console.log(data) //console.log(base.data.totalPages(),2)
	 * $("#pagination").pagination({ totalPages : base.data.totalPages(),
	 * currentPage : base.data.number(), page : function(page) {
	 * base.load(page); } }) } });
	 *  }
	 * 
	 * this.searchPage = function() { base.load(1); } };
	 */
});