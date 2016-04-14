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
			<div class="left">
			    <ul class="sub-menu">
			      <li style="display:none;" class="current" id="wdxx" onclick="javascript:show(this)"><a href="#/bxwtfk/myInfo/personInfo">我的信息（13）<i>11</i></a></li>
			      <li style="display:none;" id="gl" onClick="javascript:show(this)"><a href="#/bxwtfk/myManager/sendManager">管理</a></li>
			    </ul>
			</div>
			<div class="content"></div>
		</div>
		<script type="text/javascript">
			function show(e){
				var temp=e.id;
				if(temp=="wdxx"){
				    $("#wdxx").removeClass().addClass("current");
				    $("#gl").removeClass();
			    }else{
				    $("#gl").removeClass().addClass("current");
				    $("#wdxx").removeClass();
		    	}
			}
		</script>
		
		<script src="${ctx}/static/lib/requirejs/require.debug.js"></script>
		<script src="${ctx}/static/js/config.js"></script>
		<script src="${ctx}/static/js/index.js"></script>
	</body>
</html>