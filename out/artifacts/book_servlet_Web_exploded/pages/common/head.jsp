<%--
  Created by IntelliJ IDEA.
  User: com.basara
  Date: 2022/11/5
  Time: 1:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!--动态获取base 标签，永远固定相对路径跳转的结果-->
    <base href="<%=request.getScheme()%>://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/"/>
    <link type="text/css" rel="stylesheet" href="static/css/style.css">
    <script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>
    <title>Title</title>
</head>
<body>
</body>
</html>
