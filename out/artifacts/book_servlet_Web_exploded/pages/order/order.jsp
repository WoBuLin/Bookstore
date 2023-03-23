<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>我的订单</title>
    <%-- 静态包含 base 标签、css 样式、jQuery 文件 --%>
    <%@ include file="/pages/common/head.jsp" %>
    <style type="text/css">
        h1 {
            text-align: center;
            margin-top: 200px;
        }
    </style>

    <script type="text/javascript">
        $(function () {
            //给确认收货添加提示框
            $(".receiver_btn").click(function () {
                return confirm("是否确认收货");
            });
        });
    </script>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">我的订单</span>
    <%--静态包含，登录 成功之后的菜单 --%>
    <%@ include file="/pages/common/login_success_menu.jsp" %>
</div>

<div id="main">

    <table>
        <tr>
            <td>订单号</td>
            <td>日期</td>
            <td>金额</td>
            <td>状态</td>
            <td>详情</td>
        </tr>
        <c:forEach items="${requestScope.order}" var="order">
            <tr>
                <td>${ order.orderId }</td>
                <td>${ order.createTime }</td>
                <td>${ order.price }</td>
                <c:choose>
                    <c:when test="${order.status == 0}">
                        <td>未发出</td>
                    </c:when>
                    <c:when test="${order.status == 1}">
                        <td><a class="receiver_btn" href="client/orderServlet?action=receiverOrder&orderId=${order.orderId}">确认收货</a></td>
                    </c:when>
                    <c:when test="${order.status == 2}">
                        <td>已签收</td>
                    </c:when>
                </c:choose>

                <td><a href="client/orderServlet?action=showOrderDetail&orderId=${order.orderId}">查看详情</a></td>
            </tr>
        </c:forEach>

    </table>


</div>
<%--静态包含页脚内容--%>
<%@ include file="/pages/common/footer.jsp" %>
</body>
</html>