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
				$("#errorts").html("信息提示");
				$("#errorinfo").html("由于网络原因，加载用户信息失败");
			    $('#errorinfoModel').modal(); 
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
				$("#errorts").html("信息提示");
				$("#errorinfo").html("由于网络原因，加载所有用户信息失败");
			    $('#errorinfoModel').modal(); 
			}
		});
	}
	 //回车提交事件
	this.getKey=function()  
	{  
	    if(event.keyCode==13){  
	    	serchUser();
	    }     
	} 
	function HashMap(){  
		//定义长度  
		var length = 0;  
		//创建一个对象  
		var obj = new Object();  
	  
		/** 
		* 判断Map是否为空 
		*/  
		this.isEmpty = function(){  
			return length == 0;  
		};  
	  
		/** 
		* 判断对象中是否包含给定Key 
		*/  
		this.containsKey=function(key){  
			return (key in obj);  
		};  
	  
		/** 
		* 判断对象中是否包含给定的Value 
		*/  
		this.containsValue=function(value){  
			for(var key in obj){  
				if(obj[key] == value){  
					return true;  
				}  
			}  
			return false;  
		};  
	  
		/** 
		*向map中添加数据 
		*/  
		this.put=function(key,value){  
			if(!this.containsKey(key)){  
				length++;  
			}  
			obj[key] = value;  
		};  
	  
		/** 
		* 根据给定的Key获得Value 
		*/  
		this.get=function(key){  
			return this.containsKey(key)?obj[key]:null;  
		};  
	  
		/** 
		* 根据给定的Key删除一个值 
		*/  
		this.remove=function(key){  
			if(this.containsKey(key)&&(delete obj[key])){  
				length--;  
			}  
		};  
	  
		/** 
		* 获得Map中的所有Value 
		*/  
		this.values=function(){  
			var _values= new Array();  
			for(var key in obj){  
				_values.push(obj[key]);  
			}  
			return _values;  
		};  
	  
		/** 
		* 获得Map中的所有Key 
		*/  
		this.keySet=function(){  
			var _keys = new Array();  
			for(var key in obj){  
				_keys.push(key);  
			}  
			return _keys;  
		};  
	  
		/** 
		* 获得Map的长度 
		*/  
		this.size = function(){  
			return length;  
		};  
	  
		/** 
		* 清空Map 
		*/  
		this.clear = function(){  
			length = 0;  
			obj = new Object();  
		};  
	}  
	
	//操作添加的用户信息
	window.map = new HashMap();
	
	this.addUser = function(){
		var me = this;
		if($("#add"+me.userId)[0]){
			$("#errorinfo").html("该用户已存在！");
			$('#errorinfoModel').modal(); 
		} else {
			window.map.put(me.userId,1);
			var userId = me.userId;
			var name=me.name;
			var email=me.email;
			var mobile=me.mobile;
			var deptName=me.deptName.length>10?me.deptName.substr(0,9)+"...":me.deptName;
			var position=me.position.length>10?me.position.substr(0,9)+"...":me.position;
			$("#table_1").append("<div class='table'><div class='one'>" + name + "</div>" +
					"<div class='two'>"+deptName+"</div><div class='three'>"+position+"</div><div class='four'>"+mobile+"</div>" +
					"<div class='five'>"+email+"</div><div class='six'>" +
					"<a onclick='javascript:removetr(this)' id='add"+userId+"' ><img src='./static/images/pllus.gif' /></a></div></div>");
		}
	}
	
	this.removetr = function(e){
	    var temp=e.id;
		var id=temp.substring(3,temp.length);
		window.map.remove(id);
	    $("#"+temp).parent().parent().remove();
	}
	
	this.removetr1 = function(e){
		var id=e;
		window.map.remove(id);
	    $("#add"+id).parent().parent().remove();
	}
	
	//操作问题类型选择信息
	window.problem = new HashMap();
	this.showmod = function(e){
	    var temp=e.id;
		$("#problem").empty();
		$("#problem").append("<div style='width:100px;float:left;margin-top:5px;'>已选择的问题：</div>");
		window.problem.clear();
		window.problem.put(temp, 1);
	    if(temp=="mod_2_1")
	    {
	      $("#mod_2_1").removeClass("current_1").addClass("current_1");
	      $("#mod_2_2").removeClass("current_1");
	    }
	    else{
	      $("#mod_2_2").removeClass("current_1").addClass("current_1");
	      $("#mod_2_1").removeClass("current_1");
	    }
	}
	
	//添加问题类型
	window.addProblemMap = new HashMap(); 
	this.addproblem = function(e){
		var temp=e.id;
	  	var problemid=temp;
		var problemtext=$("#"+temp).text();
//	  	addProblemMap.put(e,1); 
		if($(".problem_div")[0]&&$("#mod_2_1").attr('class')=="current_1"){
			$("#errorinfo").html("只能选择一个问题！");
			$('#errorinfoModel').modal(); 
			return;
		}
		if($("#remove"+problemid)[0]){
			$("#errorinfo").html("该问题已选择！");
			$('#errorinfoModel').modal(); 
			return;
		} else {
			window.addProblemMap.put(temp,1); 
			$("#problem").append("<div class='problem_div'><div class='problem_div_select'>"+problemtext+"</div><div class='problem_div_delete'><img id='remove"+problemid+"' onclick='removeaddProblem(this)' src='./static/images/delete.jpg' /></div></div>");
		}
	  }
	  this.removeaddProblem = function(e){
	     var temp=e.id;
		 var id=temp.substring(6,temp.length);
		 addProblemMap.remove(id);
	     $("#"+temp).parent().parent().remove();
	  }
	
	this.cfmremoveall = function(){
		$("#cfminfo").html("您确认要清空所选内容吗？");
		$("#cfmfunction").attr("onclick","removeall();");
        $('#cfminfoModel').modal();
	}
	
	this.removeall = function (){	
		window.map.clear();	
		window.addProblemMap.clear();
		$("#problem").empty();
		$("#table_1").empty();
		$('#fbcontent').val('');
		$("#problem").append("<div style='width:100px;float:left;margin-top:5px;'>已选择的问题：</div>");
		return;
	}
	
	this.submitRIFS = function(){
		//获取添加人员信息，问题类型信息，具体描述信息
		//判断：如果获取添加人员信息，问题类型信息，为空则提示
		var arri = new Array();
		arri = window.map.keySet();
		if(arri.length==0){
			$("#errorts").html("信息提示");
			$("#errorinfo").html("请添加人员信息");
		    $('#errorinfoModel').modal(); 
			return;
		}
		var arrp = new Array();
		arrp = window.addProblemMap.keySet();
		if(arrp.length==0){
			$("#errorts").html("信息提示");
			$("#errorinfo").html("请选择问题类型");
		    $('#errorinfoModel').modal(); 
			return;
		}
		var content = $("#fbcontent").val();
		if(content.length > 300){
			$("#errorts").html("信息提示");
			$("#errorinfo").html("您输入的信息太多，请精简内容");
		    $('#errorinfoModel').modal(); 
			return;
		}
		var ids = "";
		for(var i=0; i < arri.length; i++){
			ids += arri[i] + ",";
		}
		ids = ids.substring(0,ids.length-1);
		var problems = "";
		for(var i=0; i < arrp.length; i++){
			problems += arrp[i] + ",";
		}
		problems = problems.substring(0,problems.length-1);
		$.ajax({
			type : 'POST',
			cache : false,
			url : $ctx + "/bxwtfk/myManager/sendManager",
			data : {
				ids : ids,
				problems : problems,
				content : content
			},
			dataType : 'text',
			success : function(obj) {
				var strb = obj;
				if(strb==""){
					$("#errorts").html("信息提示");
					$("#errorinfo").html("由于网络原因，发布信息失败");
				    $('#errorinfoModel').modal(); 
				}else{
					var str = strb.split(",");
					if(str.length==arri.length){
						$("#errorts").html("信息提示");
						$("#errorinfo").html("发布信息成功");
					    $('#errorinfoModel').modal(); 
					    removeall();
					}else{
						$("#errorts").html("信息提示");
						$("#errorinfo").html("由于网络原因，部分信息发布失败，请继续重发");
					    $('#errorinfoModel').modal(); 
					    for(var i=0; i<str.length; i++){
					    	removetr1(str[i]);
					    }
					}
				}
			},
			error : function(obj){
				$("#errorts").html("信息提示");
				$("#errorinfo").html("由于网络原因，发布信息失败");
			    $('#errorinfoModel').modal(); 
			}
		});
    }
	
	this.serchUser = function(){
		viewModel.serchU();
	}
	
	viewModel.serchU = function(){
		var me = this;
		var serchContent = $("#serchContent").val();
		$.ajax({
			type : 'GET',
			cache : false,
			url : $ctx + "/bxwtfk/myManager/getSerchUser",
			data : {
				serchContent : serchContent
			},
			dataType : 'json',
			success : function(obj) {
//				$("#showuser").empty();
//				$("#showuser").append("<li><div class='userinfolileft'><input type='hidden' data-bind='value: userId' /><input type='hidden' data-bind='value: position' /><input type='hidden' data-bind='value: email' /><p><span class='user_name' data-bind='text: name'></span>&nbsp;&nbsp;<span class='user_department' data-bind='text: deptName'></span></p><p class='user_mobile'><span data-bind='text: mobile'></span></p></div><div class='add'><input type='image' src='./static/images/add.gif' data-bind='click:addUser' alt='添加'/></div></li>");
				me.setData(obj);
			},
			error : function(obj){
				$("#errorts").html("信息提示");
				$("#errorinfo").html("由于网络原因，搜索用户信息失败");
			    $('#errorinfoModel').modal(); 
			}
		});
	}
	
	var init = function() {
		viewModel.loadAll();
	}
	
	return {
		'model' : viewModel,
		'template' : template,
		'init' : init
	};
});