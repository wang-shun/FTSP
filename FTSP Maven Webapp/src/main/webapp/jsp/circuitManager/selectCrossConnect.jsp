<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head><meta http-equiv="X-UA-Compatible" content="IE=8">
<title>FTSP3.0 传输网络维护支撑平台</title>
    <link rel="stylesheet" type="text/css" href="../../resource/ext/resources/css/ext-all.css" />
    <script type="text/javascript">
		
		var	neId="<%=request.getParameter("neId")%>";
		displayName="<%=session.getAttribute("USER_NAME")%>";
		userName="<%=session.getAttribute("LOGIN_NAME")%>";
    	</script>
    <script type="text/javascript" src="../../resource/ext/adapter/ext/ext-base.js"></script>
    <script type="text/javascript" src="../../resource/ext/ext-all-debug.js"></script>
    <script type="text/javascript" src="../../resource/expandExt/js/TreeCheckNodeUI.js"></script>
    <script type="text/javascript" src="../../resource/expandExt/js/combo.js"></script>
    <script type="text/javascript" src="../../resource/expandExt/js/ext-lang-zh_CN.js"></script>
   <script type="text/javascript" src="<%=request.getContextPath()%>/resource/js/util.js"></script> 
      <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resource/expandExt/css/LockingGridView.css" />
	<script type="text/javascript" src="../../resource/expandExt/ux/LockingGridView.js"></script>
	<script type="text/javascript" src="../commonManager/commonAuthDomian.js"></script>
	<script type="text/javascript" src="selectCrossConnect.js"></script>
	
</head>
<body>

</body>
</html>