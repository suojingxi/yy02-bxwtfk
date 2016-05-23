<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<title>报销问题反馈系统</title>
		<link href="${ctx}/static/css/style.css" rel="stylesheet" type="text/css" />
		<script src="${ctx}/static/lib/jquery/jquery-1.11.3.js"></script>
		<link rel="stylesheet" href="${ctx}/static/lib/bootstrap/css/bootstrap.min.css">
		<script src="${ctx}/static/lib/bootstrap/js/bootstrap.min.js"></script>
		<script src="${ctx}/static/lib/bootstrap/js/bootstrap-dropdown.js"></script>
		<script>
			window.$ctx = '${ctx}';
		</script>
		
	</head>
	<body>
		<div class="main">
			<div class="left">
				<ul class="sub-menu list-group" style="width:100%;" >
					<li style="display:none;" class="active list-group-item" id="wdxx" onclick="javascript:show(this)"><span class="badge" id="num"></span><a style="display: block;width: 100%;height: 20px;text-decoration: none;" href="#/bxwtfk/myInfo/personInfo">我的信息</a></li>
					<li style="display:none;" class="list-group-item"  id="gl" onclick="javascript:show(this)"><a style="display: block;width: 100%;height: 20px;text-decoration: none;" href="#/bxwtfk/myManager/sendManager">管理</a></li>
				</ul>
			</div>
			<div class="content"></div>
		</div>
		
<!-- 错误消息提示 -->  
<div class="modal fade" id="errorinfoModel">  
	<div class="modal-dialog">  
		<div class="modal-content message_align">  
			<div class="modal-header">  
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">×</span>
				</button>  
				<h4 class="modal-title" id="errorts">错误提示</h4>  
	 		</div>  
	 		<div class="modal-body">  
				<p id="errorinfo"></p>  
	 		</div>  
	 		<div class="modal-footer">  
	 			<input type="hidden" id="url"/>  
	 			<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>  
	 		</div>  
		</div><!-- /.modal-content -->  
	</div><!-- /.modal-dialog -->  
</div><!-- /.modal -->

<!-- 信息删除确认 -->  
<div class="modal fade" id="cfminfoModel">  
	<div class="modal-dialog">  
		<div class="modal-content message_align">  
	  		<div class="modal-header">  
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">×</span>
				</button>  
				<h4 class="modal-title">提示信息</h4>  
	  		</div>  
	  		<div class="modal-body">  
				<p id="cfminfo"></p>  
	  		</div>  
	  		<div class="modal-footer">  
		 		<input type="hidden" id="url"/>  
		 		<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>  
		 		<a id="cfmfunction"  onclick="removexx(this);" class="btn btn-success" data-dismiss="modal">确定</a>  
	  		</div>  
		</div><!-- /.modal-content -->  
  	</div><!-- /.modal-dialog -->  
</div><!-- /.modal -->  

	
<!-- 详细信息 -->  
<div class="modal fade" id="myRRIFSinfoModel">  
	<div class="modal-dialog">  
		<div class="modal-content message_align">  
			<div class="modal-header">  
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">×</span>
				</button>  
				<h4 id="myRRIFStitle" class="modal-title"></h4>  
			</div>  
			<div class="modal-body">  
				<p id="myRRIFSinfo"></p>  
			</div>  
			<div class="modal-footer">  
				<input type="hidden" id="url"/>  
				<p style="float:left" id="myRRIFStime" class="modal-title"></p>  
			 	<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>  
			 	<a id="myRRIFSinfofunction"  onclick="" class="btn btn-success" data-dismiss="modal">删除</a>  
		  	</div>  
		</div><!-- /.modal-content -->  
  	</div><!-- /.modal-dialog -->  
</div><!-- /.modal --> 	
		
		
		
		
		<script type="text/javascript">
			function show(e){
				var temp=e.id;
				if(temp=="wdxx")
				{
				  $("#wdxx").removeClass('active').addClass('active');
				  $("#gl").removeClass('active');
				}
				else{
				  $("#gl").removeClass('active').addClass('active');
				  $("#wdxx").removeClass('active');
				}
			}
		</script>
		<script src="${ctx}/static/lib/requirejs/require.debug.js"></script>
		<script src="${ctx}/static/js/config.js"></script>
		<script src="${ctx}/static/js/index.js"></script>
	</body>
</html>