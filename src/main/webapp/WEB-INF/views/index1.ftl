<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<title>报销问题反馈系统</title>
		<link href="${ctx}/static/css/style.css" rel="stylesheet" type="text/css" />
		<script src="${ctx}/static/lib/jQueryAlert/jquery.js" type="text/javascript"></script>
		<script src="${ctx}/static/lib/jQueryAlert/jquery.ui.draggable.js" type="text/javascript"></script>
		<script src="${ctx}/static/lib/jQueryAlert/jquery.alerts.js" type="text/javascript"></script>
		
		<link href="${ctx}/static/lib/jQueryAlert/jquery.alerts.css" rel="stylesheet" type="text/css" media="screen" />
		
		<link href="${ctx}/static/assets/css/jquery.ui.all.css" rel="stylesheet" type="text/css"/>
		<link href="${ctx}/static/assets/css/demos.css" rel="stylesheet" type="text/css"/>
		<link href="${ctx}/static/css/style.css" rel="stylesheet" type="text/css" />
		
		<script>
			window.$ctx = '${ctx}';
		</script>
	</head>
	<body>
		<div class="main">
			<!--左侧菜单-->
			<div class="left">
		        <div class="menu">
		        	<dl id="tab">
		            	<dt style="display:block">
			            	<ul class="sub-menu">
			                	<span style="display:inline-block;float:left;position:relative;top:12px;">
			                		<a href="#/bxwtfk/myInfo/personInfo" style="display:block;height:39px;width:163px;">我的消息</a>
			                		<i></i>
			                	</span>
			              	</ul>
		                </dt>
		                <dd></dd>
		                <dt style="display:block">
			            	<ul class="sub-menu">
			                	<span style="display:inline-block;float:left;position:relative;top:12px;">
			                		<a href="#/bxwtfk/myManager/sendManager" style="display:block;height:39px;width:163px;">报销问题反馈</a>
			                		<i></i>
			                	</span>
			              	</ul>
		                </dt>
		                <dd></dd>
		            </dl>
		        </div>
		    </div><!--左侧菜单 结束-->
		    
		    <div class="content">
		
		</div>
		
		<script src="${ctx}/static/lib/requirejs/require.debug.js"></script>
		<script src="${ctx}/static/js/config.js"></script>
		<script src="${ctx}/static/js/index.js"></script>
	</body>
</html>