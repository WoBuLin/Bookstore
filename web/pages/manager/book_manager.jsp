<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>图书管理</title>
    <%-- 静态包含 base 标签、css 样式、jQuery 文件 --%>
    <%@ include file="/pages/common/head.jsp" %>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">图书管理系统</span>
    <%-- 静态包含后台管理按钮 --%>
    <%@ include file="/pages/common/manger_menu.jsp" %>
</div>
<script type="text/javascript">
    $(function () {
        $("a#delete").click(function () {
            return confirm("是否要删除《" + $(this).parent().parent().find("td:first").text() + "》？");
        });
    });
</script>

<div id="main">
    <table>
        <tr>
            <td>名称</td>
            <td>价格</td>
            <td>作者</td>
            <td>销量</td>
            <td>库存</td>
            <td colspan="2">操作</td>
        </tr>
        <c:forEach items="${ requestScope.page.items }" var="book">
            <tr>
                <td>${ book.name }</td>
                <td>${ book.price }</td>
                <td>${ book.author }</td>
                <td>${ book.sales }</td>
                <td>${ book.stock }</td>
                <td>
                    <a href="manager/bookServlet?action=getBook&id=${ book.id }&pageNo=${ requestScope.page.pageNo }">修改</a>
                </td>
                <td><a id="delete"
                       href="manager/bookServlet?action=delete&id=${ book.id }&pageNo=${ requestScope.page.pageNo }">删除</a>
                </td>
            </tr>
        </c:forEach>

        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td><a href="manager/bookServlet?action=getBook&pageNo=${ requestScope.page.pageNo }">添加图书</a></td>
        </tr>
    </table>
</div>


<%--静态包含分页条--%>
<%@ include file="/pages/common/page_nav.jsp"%>

<%--静态包含页脚内容--%>
<%@ include file="/pages/common/footer.jsp" %>
</body>
</html>