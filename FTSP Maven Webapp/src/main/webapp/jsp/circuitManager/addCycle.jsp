<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head><meta http-equiv="X-UA-Compatible" content="IE=8">
  	<meta http-equiv="X-UA-Compatible" content="IE=8">
    <base/>
    <title>FTSP3.0 传输网络维护支撑平台</title>  
    <link rel="stylesheet" type="text/css" href="../../resource/ext/resources/css/ext-all.css" />
    <link rel="stylesheet" type="text/css" href="../../resource/expandExt/css/expand.css" />
    <link rel="stylesheet" type="text/css" href="../../resource/expandExt/css/MultiSelect.css" />
    <script type="text/javascript">
        var	period_type="<%=request.getParameter("period_type")%>";
        var	period="<%=request.getParameter("period")%>";
        var nexttime = "<%=request.getParameter("nexttime")%>";
        var task_id = "<%=request.getParameter("task_id")%>";
	 </script>
    <script type="text/javascript" src="../../resource/ext/adapter/ext/ext-base.js"></script>
    <script type="text/javascript" src="../../resource/ext/ext-all.js"></script>
    <script type="text/javascript" src="../../resource/expandExt/js/combo.js"></script>
    <script type="text/javascript" src="../../resource/expandExt/js/sideText.js"></script>
    <script type="text/javascript" src="../../resource/expandExt/js/MultiSelect.js"></script>
    <script type="text/javascript" src="../../resource/expandExt/js/ItemSelector.js"></script>
    <script type="text/javascript" src="../../resource/expandExt/js/ext-lang-zh_CN.js"></script>
    <script type="text/javascript" src="../../resource/expandExt/js/timeFormat.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resource/js/util.js"></script> 
    <script type="text/javascript" src="../../resource/expandExt/js/timeComponent/WdatePicker.js"></script>
    <script type="text/javascript" src="addCycle.js"></script>
    
  </head>
<body>
    <div id="form"></div>
</body>
</html>