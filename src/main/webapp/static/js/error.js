;(function($,window,document,undefined){
 var _ajax=$.ajax;
 //重写jquery的ajax方法
 $.ajax=function(opt){
	 var fn={
     error:function(jqXHR,textStatus,errorThrown){},
	 success:function(data,textStatus){}
     }
	 if(opt.error){
		 fn.error=opt.error;
	  }
	if(opt.success){
		fn.success=opt.success;
	 }
 
  //扩展增强处理
  var _opt=$.extend(opt,{
	 error:function(jqXHR,textStatus,errorThrown){
   	  var msg=jqXHR.getAllResponseHeaders();
      if (msg=='undefined'||msg==null||msg=="") {
          window.location.href = $ctx+"/";
        }
		 //错误方法增强处理
		 fn.error(jqXHR,textStatus,errorThrown);
	 }
  });
 _ajax(_opt);
};
})(jQuery,window,document);