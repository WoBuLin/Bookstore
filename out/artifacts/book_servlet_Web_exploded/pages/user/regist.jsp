<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>尚硅谷会员注册页面</title>
    <%-- 静态包含 base 标签、css 样式、jQuery 文件 --%>
    <%@ include file="/pages/common/head.jsp" %>
    <script type="text/javascript">
        $(function () {

            //验证用户名是否存在
            $("#username").blur(function () {
                var username = this.value;
                $.getJSON("http://localhost:8080/book/userServlet","action=ajaxExistsUsername&username=" + username,function (data) {
                    $("span.errorMsg").text(data.errorMsg);
                });
            });

            //点击验证码更换图片
            $("#kaptcha_img").click(function () {
                this.src = "http://localhost:8080/book/kaptcha.jpg?d=" + new Date();
            });

            //给注册按钮绑定单击事件
            $("#sub_btn").click(function () {
                // 验证用户名：必须由字母，数字下划线组成，并且长度为 5 到 12 位
                //>1.获取用户名输入框中的内容
                var usernameText = $("#username").val();
                //2.创建正则表达式对象
                var usernamePatt = /^\w{5,12}$/;
                //3.使用test方法验证，如果不匹配就提示用户
                if (!usernamePatt.test(usernameText)) {
                    //4.提示用户结果
                    $("span.errorMsg").text("用户不合法！");
                    return false;
                }

                // 验证密码：必须由字母，数字下划线组成，并且长度为 5 到 12 位
                //>1.获取密码输入框中的内容
                var passwordText = $("#password").val();
                //2.创建正则表达式对象
                var passwordPatt = /^\w{5,12}$/;
                //3.使用test方法验证，如果不匹配就提示用户
                if (!passwordPatt.test(passwordText)) {
                    //4.提示用户结果
                    $("span.errorMsg").text("密码不合法！");
                    return false;
                }

                // 验证确认密码：和密码相同
                //1.获取确认密码内容
                var repwdText = $("#repwd").val();
                //2.跟密码相比较
                if (repwdText != passwordText) {
                    //3.获取提示
                    $("span.errorMsg").text("确认密码与用户密码不一致！");
                    return false;
                }

                // 邮箱验证：xxxxx@xxx.com
                //1.获取邮箱里的内容
                var emailText = $("#email").val();
                //2.创建正则表达式对象
                var emailPatt = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
                //3.使用test方法验证是否合法
                if (!emailPatt.test(emailText)) {
                    //4.提示用户
                    $("span.errorMsg").text("邮箱格式不合法！");
                    return false;
                }
                // 验证码：
                var codeText = $("#code").val();

                //去掉验证码前后空格，防止输入空格通过验证
                codeText = $.trim(codeText);
                if (codeText == null || codeText == "") {
                    $("span.errorMsg").text("验证码为空！");
                    return false;
                }

                //第一次不合法，第二次又合法了，就删除掉提示。
                $("span.errorMsg").text("");

            });
        });
    </script>
    <style type="text/css">
        .login_form {
            height: 420px;
            margin-top: 25px;
        }

    </style>
</head>
<body>
<div id="login_header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
</div>

<div class="login_banner">

    <div id="l_content">
        <span class="login_word">欢迎注册</span>
    </div>

    <div id="content">
        <div class="login_form">
            <div class="login_box">
                <div class="tit">
                    <h1>注册尚硅谷会员</h1>
<%--                    如果js中的错误都没有，但还是有问题，那只剩下验证码错误这个个错误--%>
                    <span class="errorMsg">${ empty requestScope.error?"":"验证码错误"}</span>
                </div>
                <div class="form">
                    <form action="/book/userServlet" method="post">
                        <input type="hidden" name="action" value="regist"/>
                        <label>用户名称：</label>
                        <input class="itxt" type="text" placeholder="请输入用户名"
                               autocomplete="off" tabindex="1" name="username" id="username"
                               value="${ requestScope.username }"/>
                        <br/>
                        <br/>
                        <label>用户密码：</label>
                        <input class="itxt" type="password" placeholder="请输入密码"
                               autocomplete="off" tabindex="1" name="password" id="password"/>
                        <br/>
                        <br/>
                        <label>确认密码：</label>
                        <input class="itxt" type="password" placeholder="确认密码"
                               autocomplete="off" tabindex="1" name="repwd" id="repwd"/>
                        <br/>
                        <br/>
                        <label>电子邮件：</label>
                        <input class="itxt" type="text" placeholder="请输入邮箱地址"
                               autocomplete="off" tabindex="1" name="email" id="email"
                               value="${ requestScope.email }"/>
                        <br/>
                        <br/>
                        <label>验证码：</label>
                        <input class="itxt" type="text" style="width: 60px; margin-left: 20px;" id="code" name="code"/>
                        <img id="kaptcha_img" src="http://localhost:8080/book/kaptcha.jpg" style="float: right; margin-right: 50px; width: 150px;">
                        <br/>
                        <br/>
                        <input type="submit" value="注册" id="sub_btn"/>

                    </form>
                </div>

            </div>
        </div>
    </div>
</div>
<%--静态包含页脚内容--%>
<%@include file="/pages/common/footer.jsp" %>
</body>
</html>