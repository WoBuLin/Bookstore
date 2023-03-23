<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <!--动态获取base 标签，永远固定相对路径跳转的结果-->
    <base href="<%=request.getScheme()%>://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/"/>
</head>
<body>
您访问的页面不存在或已删除<br>
<a href="index.jsp">点击返回主页</a>
</body>
</html>
