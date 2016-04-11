require([ 'jquery', 'knockout', 'ujs', 'director','error' ], function($, ko) {
	//登录后，首页显示的内容
	window.addRouter = function(path, func) {
		var truePath = path.replace('/:id', '');
		var tmparray = truePath.split("/");
		func = func || function(id) {
			initPage('static/pages' + truePath, id)
		}

		// router.routes = router.routes || {};
		//定义三级路由
		if (tmparray[1] in router.routes
				&& tmparray[2] in router.routes[tmparray[1]]
				&& tmparray[3] in router.routes[tmparray[1]][tmparray[2]]) {
			return;
		} else {
			router.on(path, func)
		}
	}

	window.router = Router();

	var initMenuTree = function() {
		var initFunUrl = $ctx + '/initFunTree';
		$.ajax({
			type : "GET",
			url : initFunUrl,
			cache:false,
			dataType : 'json',
			async : false,
			success : function(data) {
				$('.sub-menu').find("a[href*='#']").each(
						function() {
							var url = $(this).attr("href");
							var li = $(this).parent();
							$.each(data, function(i, item) {
								if (url == ("#" + item.url)) {
									if (li.parent().is("dt")) {
										li.parent().css("display", "block");
									} else if (li.parent().parent().prev("dt")
											.is("dt")) {
										li.css("display", "block");
										li.parent().parent().prev("dt").css(
												"display", "block");
									}
								}
							}
							);
						 }
						);
			},
			error : function(req, textStatus, errorThrown) {
				if (req.responseJSON) {
					var validateObj = req.responseJSON;
					if (validateObj.code) {
						jAlert(validateObj.code, "错误");
					}
				}
				// jAlert("保存失败!","错误");
			}
		});
	}

	
	
	$(function() {
		/*
		 * $.ajax({ type : 'GET', url : $ctx + "/mgr/function/rootmenu", data :
		 * '', dataType : 'json', success : function(data) { initFuncTree(data); }
		 * });
		 */
		initMenuTree();
		$('.sub-menu').find("a[href*='#']").each(function() {
			var path = this.hash.replace('#', '');
			addRouter(path);
		});
		window.router.init()
	})

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