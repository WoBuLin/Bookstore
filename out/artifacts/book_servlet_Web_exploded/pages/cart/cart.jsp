<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>购物车</title>
    <%-- 静态包含 base 标签、css 样式、jQuery 文件 --%>
    <%@ include file="/pages/common/head.jsp" %>

    <script type="text/javascript">

        $(function () {
            //修改商品数量的事务
            $(".count_btn").change(function () {
                var count = this.value;
                var id = $(this).attr("bookId");
                var bookName = $(this).parent().parent().find("td").first().text();
                if (confirm("是否要修改<" + bookName + ">的数量为:" + count)) {
                    //发起请求。给服务器保存修改
                    location.href = "cartServlet?action=updateItem&id=" + id + "&count=" + count;
                } else {
                    // defaultValue 属性是表单项 Dom 对象的属性。它表示默认的 value 属性值。
                    //这里的用处是，点击取消后，仍回显原来的商品数量
                    this.value = this.defaultValue
                }
            });

            //删除的提示框事务
            $(".delete_btn").click(function () {
                return confirm("是否要删除<" + $(this).parent().parent().find("td").first().text() + ">");
            });

            //清空购物车的提示框事务
            $("#clear_btn").click(function () {
                return confirm("是否要清空购物车");
            });


        });
    </script>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">购物车</span>

    <%--静态包含，登录 成功之后的菜单 --%>
    <%@ include file="/pages/common/login_success_menu.jsp" %>
</div>

<div id="main">
    <table>
        <tr>
            <td>商品名称</td>
            <td>数量</td>
            <td>单价</td>
            <td>金额</td>
            <td>操作</td>
        </tr>
        <%--如果购物车是空的--%>
        <c:if test="${ empty sessionScope.cart.items}">
            <tr>
                <td colspan="5"><h1>亲，当前购物车是空的哦 <a href="index.jsp">点这里</a>去商品页看看吧！</h1></td>
            </tr>
        </c:if>
        <%--如果购物车是非空的--%>
        <c:if test="${ not empty sessionScope.cart.items}">
            <c:forEach items="${sessionScope.cart.items}" var="item">
                <tr>
                    <td>${item.value.name}</td>
                    <td>
                        <input class="count_btn" bookId="${item.value.id}"
                               style="width: 60px" value="${item.value.count}">
                    </td>
                    <td>${item.value.price}</td>
                    <td>${item.value.totalPrice}</td>
                    <td><a class="delete_btn" href="cartServlet?action=deleteItem&id=${item.value.id}">删除</a></td>
                </tr>
            </c:forEach>
        </c:if>
    </table>
    <%--如果购物车非空才输出页面的内容--%>
    <c:if test="${ not empty sessionScope.cart.items}">
        <div class="cart_info">
            <span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
            <span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
            <span class="cart_span"><a href="cartServlet?action=clear" id="clear_btn">清空购物车</a></span>
            <span class="cart_span"><a href="client/orderServlet?action=createOrder">去结账</a></span>
        </div>
    </c:if>
</div>

<%--静态包含页脚内容--%>
<%@include file="/pages/common/footer.jsp" %>
</div>
</body>
</html>