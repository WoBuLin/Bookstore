<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>书城首页</title>
    <%-- 静态包含 base 标签、css 样式、jQuery 文件 --%>
    <%@ include file="/pages/common/head.jsp" %>
    <script type="text/javascript">
        $(function () {
            //添加购物车操作
            $(".add_btn").click(function () {
                var id = $(this).attr("bookId");
                $.getJSON("http://localhost:8080/book/cartServlet","action=AjaxAddItem&id=" + id,function (data) {
                    $("#cartTotalCount").text("您的购物车中有"+ data.totalCount +"件商品");
                    $("#cartLastName").text(data.lastName);
                    //判断如果购物车不是空了就刷新
                    if (${empty sessionScope.cart.items}){
                        location.replace(data.referer);
                    }
                });
            });
        });
    </script>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">网上书城</span>
    <div>

        <c:if test="${ empty sessionScope.user.username }">
            <a href="pages/user/login.jsp">登录</a> |
            <a href="pages/user/regist.jsp">注册</a>
        </c:if>
        <c:if test="${ not empty sessionScope.user.username }">
            <span>欢迎<span class="um_span">${ sessionScope.user.username }</span>光临尚硅谷书城</span>
            <a href="client/orderServlet?action=showMyOrders">我的订单</a>
            <a href="client/userServlet?action=logout">注销</a>&nbsp;&nbsp;
        </c:if>

        <a href="pages/cart/cart.jsp">购物车</a>
        <a href="pages/manager/manager.jsp">后台管理</a>
    </div>
</div>
<div id="main">
    <div id="book">
        <div class="book_cond">
            <form action="client/bookServlet" method="get">
                <input type="hidden" name="action" value="pageByPrice">
                价格：<input id="min" type="text" name="min" value="${ param.min }"> 元 -
                <input id="max" type="text" name="max" value="${ param.max }"> 元
                <input type="submit" value="查询"/>
            </form>
        </div>
        <div style="text-align: center">
            <%--购物车不是空的时--%>
            <c:if test="${ not empty sessionScope.cart.items}">
                <span id="cartTotalCount">您的购物车中有 ${sessionScope.cart.totalCount} 件商品</span>
                <div>
                    您刚刚将<span style="color: red" id="cartLastName">${sessionScope.lastName}</span>加入到了购物车中
                </div>
            </c:if>
            <%--购物车为空时--%>
            <c:if test="${ empty sessionScope.cart.items}">
                <span style="color: red">当前购物车没有商品</span>
            </c:if>
        </div>
        <c:forEach var="book" items="${ requestScope.page.items }">
            <div class="b_list">
                <div class="img_div">
                    <img class="book_img" alt="" src="${ book.img_path}"/>
                </div>
                <div class="book_info">
                    <div class="book_name">
                        <span class="sp1">书名:</span>
                        <span class="sp2">${ book.name}</span>
                    </div>
                    <div class="book_author">
                        <span class="sp1">作者:</span>
                        <span class="sp2">${ book.author}</span>
                    </div>
                    <div class="book_price">
                        <span class="sp1">价格:</span>
                        <span class="sp2">${ book.price}</span>
                    </div>
                    <div class="book_sales">
                        <span class="sp1">销量:</span>
                        <span class="sp2">${ book.sales}</span>
                    </div>
                    <div class="book_amount">
                        <span class="sp1">库存:</span>
                        <span class="sp2">${ book.stock}</span>
                    </div>
                    <div class="book_add">
                        <button class="add_btn" bookId="${book.id}">加入购物车</button>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
    <%--静态包含分页条--%>
    <%@ include file="/pages/common/page_nav.jsp" %>

    <%--静态包含页脚内容--%>
    <%@ include file="/pages/common/footer.jsp" %>
</body>
</html>