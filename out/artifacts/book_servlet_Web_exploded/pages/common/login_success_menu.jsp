<%--
  Created by IntelliJ IDEA.
  User: com.basara
  Date: 2022/11/5
  Time: 1:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
    <span>欢迎<span class="um_span">${ sessionScope.user.username }</span>光临尚硅谷书城</span>
    <a href="client/orderServlet?action=showMyOrders">我的订单</a>
    <a href="userServlet?action=logout">注销</a>&nbsp;&nbsp;
    <a href="index.jsp">返回</a>
</div>
</body>
</html>
