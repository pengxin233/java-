<%--
  Created by IntelliJ IDEA.
  User: 彭鑫
  Date: 2020/12/13
  Time: 12:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <%--    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">--%>
    <style type="text/css">
        form span {
            color: red;
        }
    </style>
</head>
<body>
<%--头部--%>
    <%@include file="head.jsp"%>

<br/>
<br/>
<br/>
<br/>

    <form class="form-horizontal" action="${APP_PATH}/LoginServlet">
        <div class="form-group">
            <label for="name" class="col-sm-2 control-label">姓名</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="name" name="name" placeholder="请输入姓名">
            </div>
            <div class="col-sm-6">
                <span class="has-error">${msg.get("name")}</span>
            </div>
        </div>

        <div class="form-group">
            <label for="password" class="col-sm-2 control-label">密码</label>
            <div class="col-sm-4">
                <input type="password" id="password" name="password" class="form-control" placeholder="请输入密码">
            </div>
            <div class="col-sm-6">
                <span class="has-error">${msg.get("password")}</span>
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-4 col-sm-offset-2">
                <button type="reset" class="btn btn-default">重置</button>
                <button type="submit" class="btn btn-primary">登录</button>
            </div>
        </div>
    </form>

<br/>
<br/>
<br/>
<br/>

<%--公告--%>
    <%@include file="gonggao.jsp"%>


</body>
</html>
