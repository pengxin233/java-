<%--
  Created by IntelliJ IDEA.
  User: 彭鑫
  Date: 2020/12/15
  Time: 9:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${sessionScope.user}的信息</title>
    <style type="text/css">
        form span{
            color: red;
        }

    </style>
</head>
<body>

<%@include file="head.jsp"%>

<form class="form-horizontal" action="${APP_PATH}/UserServlet">
    <div class="form-group">
        <label for="id" class="col-sm-2 control-label">Id</label>
        <div class="col-sm-4">
            <input type="text" class="form-control" id="id" name="id" value="${sessionScope.user.id}" disabled>
            <span>${msg.erros}</span>
        </div>
    </div>
    <div class="form-group">
        <label for="name" class="col-sm-2 control-label">Name</label>
        <div class="col-sm-4">
            <input type="text" class="form-control" id="name" name="name" value="${sessionScope.user.name}">
            <span>${msg.name}</span>
        </div>
    </div>
    <div class="form-group">
        <label for="password" class="col-sm-2 control-label">Password</label>
        <div class="col-sm-4">
            <input type="text" class="form-control" id="password" name="password" value="${sessionScope.user.password}">
            <span>${msg.password}</span>
        </div>
    </div>
    <div class="form-group">
        <label for="email" class="col-sm-2 control-label">Email</label>
        <div class="col-sm-4">
            <input type="email" class="form-control" id="email" name="email" value="${sessionScope.user.email}">
            <span>${msg.email}</span>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-2">
            <button type="submit" class="btn btn-danger">修改</button>
        </div>

        <div class="col-sm-2">
            <a class="btn btn-danger" rel="stylesheet" href="${APP_PATH}/UserServlet?info=2">退出</a>
        </div>
<%--        修改成功--%>
        <div class="col-sm-offset-3">
            <h4 style="display: inline-block;">${success}</h4>
        </div>
    </div>
</form>

<%@include file="gonggao.jsp"%>
</body>
</html>
