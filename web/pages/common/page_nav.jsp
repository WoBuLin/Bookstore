<%--
  Created by IntelliJ IDEA.
  User: com.basara
  Date: 2022/11/9
  Time: 3:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%-- 分页模块 --%>
<div id="page_nav">
    <%--不是首页的时候才显示首页和上一页--%>
    <c:if test="${ requestScope.page.pageNo > 1}">
        <a href="${ requestScope.page.url }">首页</a>
        <a href="${ requestScope.page.url }&pageNo=${ requestScope.page.pageNo - 1}">上一页</a>
    </c:if>
    <c:choose>
        <%-- 当总页码在5以内时 --%>
        <c:when test="${ requestScope.page.pageTotal <= 5}">
            <c:set var="begin" value="1"/>
            <c:set var="end" value="${ requestScope.page.pageTotal }"/>
        </c:when>
        <%-- 当总页码大于5时 --%>
        <c:when test="${ requestScope.page.pageTotal > 5}">
            <%-- 分多种情况 --%>
            <c:choose>
                <%-- 情况1：当前页码在3以内 --%>
                <c:when test="${requestScope.page.pageNo <= 3}">
                    <c:set var="begin" value="1"/>
                    <c:set var="end" value="5"/>
                </c:when>
                <%-- 情况2：当前页码是最后三页其中一页 --%>
                <c:when test="${requestScope.page.pageTotal - requestScope.page.pageNo <= 2}">
                    <c:set var="begin" value="${ requestScope.page.pageTotal -4 }"/>
                    <c:set var="end" value="${ requestScope.page.pageTotal }"/>
                </c:when>
                <%-- 情况2：除上述两种外的其他情况 --%>
                <c:otherwise>
                    <c:set var="begin" value="${ requestScope.page.pageNo -2 }"/>
                    <c:set var="end" value="${ requestScope.page.pageNo +2 }"/>
                </c:otherwise>
            </c:choose>
        </c:when>
    </c:choose>
    <c:forEach begin="${begin}" end="${end}" var="i">
        <c:if test="${ requestScope.page.pageNo == i}">
            <font color="red" face="宋体" size="4">${i}</font>
        </c:if>
        <c:if test="${ requestScope.page.pageNo != i}">
            <a href="${ requestScope.page.url }&pageNo=${i}">${i}</a>
        </c:if>
    </c:forEach>
    <%--不是最后一页才显示末页和下一页--%>
    <c:if test="${ requestScope.page.pageNo < requestScope.page.pageTotal}">
        <a href="${ requestScope.page.url }&pageNo=${ requestScope.page.pageNo + 1}">下一页</a>
        <a href="${ requestScope.page.url }&pageNo=${ requestScope.page.pageTotal}">末页</a>
    </c:if>


    共${ requestScope.page.pageTotal}页，${ requestScope.page.pageTotalCount}条记录
    到第<input id="pn_input" value=${requestScope.page.pageNo}>页
    <input id="pn_button" type="button" value="确定">

        <script type="text/javascript">
            $(function () {
                $("#pn_button").click(function () {
                    var pageNo = $("#pn_input").val();
                    // javaScript 语言中提供了一个 location 地址栏对象
                    // 它有一个属性叫 href.它可以获取浏览器地址栏中的地址
                    // href 属性可读，可写
                    location.href = "${ requestScope.page.url }&pageNo=" + pageNo;
                });
            });
        </script>
</div>
<br>
</div>
<br>
</body>
</html>
