<%--
  Created by IntelliJ IDEA.
  User: 彭鑫
  Date: 2020/12/22
  Time: 21:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>详情</title>
</head>
<body>
<%@include file="head.jsp"%>
    <div class="container-fluid">
        <table class="table table-hover">
            <thead>
                <td>编号</td>
                <td>书</td>
                <td>数量</td>
                <td>价格</td>
            </thead>
            <c:forEach var="item" items="${order2s}">
                <tr>
                    <td>${item.id}</td>
                    <td><img style="width: 50px;height: 60px;" src="${APP_PATH}/img/${item.id1}.jpg"></td>
                    <td>${item.count}</td>
                    <td>${item.money}</td>
                </tr>
            </c:forEach>
        </table>
    </div>

<%@include file="gonggao.jsp"%>
</body>
</html>
