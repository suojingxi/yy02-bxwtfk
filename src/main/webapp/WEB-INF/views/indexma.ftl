<!DOCTYPE html>
<html>
	<head>
	    <meta charset="utf-8">
	    <title>我的报销信息反馈</title>
	
	    <!-- Sets initial viewport load and disables zooming  -->
	    <meta name="viewport" content="initial-scale=1, maximum-scale=1">
	
	    <!-- Makes your prototype chrome-less once bookmarked to your phone's home screen -->
	    <meta name="apple-mobile-web-app-capable" content="yes">
	    <meta name="apple-mobile-web-app-status-bar-style" content="black">
	
	    <!-- Include the compiled Ratchet CSS -->
	    <link href="${ctx}/static/lib/ratchet/css/ratchet.css" rel="stylesheet">
	    
		<script src="${ctx}/static/lib/jquery/jquery-1.11.3.js"></script>
	
	    <!-- Include the compiled Ratchet JS -->
	    <script src="${ctx}/static/lib/ratchet/js/ratchet.js"></script>
	    <script>
			window.$ctx = '${ctx}';
		</script>
  	</head>
	<body>
	    <!-- Make sure all your bars are the first things in your <body> -->
	    <header class="bar bar-nav" style="background-color:#00bbd2;">
	  		<a class="icon icon-left-nav pull-left" style="color:white;"></a>
	  		<h1 class="title pull-center" style="color:white;">我的报销信息反馈（<span id="num"></span>）</h1>
	    </header>
    	<!-- Wrap all non-bar HTML in the .content div (this is actually what scrolls) -->
		<div class="content">
        	<ul class="table-view" id="tableView">
				<div class="card">
          			<li class="table-view-cell">
            			<a href="#/bxwtfk/myInfo/getPersonInfoNum" data-transition="slide-in" style="padding-right:15px;">
              				<div class="media-body">
                				<span>报销信息主题1</span>
				 				<div class="pull-right" style="border:1px solid #777;font-size:12px;margin:0;padding:0;line-height:15px;height:15px;">已阅</div>
				 				<div>
                					<span style="line-height:20px;font-size:12px;">2016年4月22日 16:36:15</span>
                					<p>现金支出单据可以作为员工、业务员向单位借支现金时使用。</p>
								</div>
			  				</div>
            			</a>
          			</li>
		  		</div>
        	</ul>
        </div>
        <script src="${ctx}/static/lib/requirejs/require.debug.js"></script>
		<script src="${ctx}/static/js/config.js"></script>
		<script src="${ctx}/static/js/indexma.js"></script>
		<script src="${ctx}/static/lib/gzq-cordova/android/cordova.js"></script>
	</body>
</html>