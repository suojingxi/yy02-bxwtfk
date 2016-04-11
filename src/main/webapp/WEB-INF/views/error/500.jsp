<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="org.slf4j.Logger,org.slf4j.LoggerFactory" %>
<%	
	//设置返回码200，避免浏览器自带的错误页面
	if(response!=null){
	response.setStatus(200);
	}
    String error="";
    if(exception!=null)
       error=exception.getMessage();
	//记录日志
	Logger logger = LoggerFactory.getLogger("500.jsp");
	if(logger!=null&&exception!=null)
	   logger.error(error, exception);
	String contextUrl="";
	if(request!=null)
	    contextUrl=request.getContextPath();
%>

<!DOCTYPE html>
<html>
<head>
	<title>500 - 系统内部错误</title>
</head>
<script type="text/javascript">
function clock(i){
	var $ctx="<%=contextUrl%>";
	  document.title="本窗口将在"+i+"秒后自动登出!";
	  i=i-1;
	  if(i>0)
	    setTimeout("clock("+i+")",1000);
	  else
	  window.location.href=$ctx+"/exit";
	 }
</script>
<body onload="clock(15)">
	<h2><%=error %></h2>
</body>
</html>
