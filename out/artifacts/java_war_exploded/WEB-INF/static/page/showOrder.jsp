<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 彭鑫
  Date: 2020/12/18
  Time: 14:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${sessionScope.user.name}的订单</title>
</head>
<body>
<%@include file="head.jsp"%>

<div class="container-fluid">
    <div class="row">
        <h3>${sessionScope.user.name}的订单</h3>
        <%--                搜索框--%>
        <div class="col-md-offset-8">
            <form action="${APP_PATH}/ShowOrderServlet">
                    <input type="text" placeholder="Search" name="search">
                <button type="submit" class="btn btn-default">Submit</button>
            </form>
        </div>
    </div>
    <table class="table table-hover">
        <thead>
            <td>订单编号</td>
            <td>总金额</td>
            <td>收货地址</td>
            <td>收货人姓名</td>
            <td>联系电话</td>
            <td>订单状态</td>
            <td>下单时间</td>
            <td>详情</td>
            <td>购买/取消</td>
        </thead>

        <c:if test="${empty orders}">
            <h3 style="color: red">没有查询到订单</h3>
        </c:if>

        <c:forEach var="item" items="${orders}">
            <tr>
                <td>${item.id}</td>
                <td>${item.money}</td>
                <td>${item.receiverAddress}</td>
                <td>${item.receiverName}</td>
                <td>${item.receiverPhone}</td>
                <td>${item.type}</td>
                <td>${item.checked}</td>
                <td>
                    <a rel="stylesheet" href="${APP_PATH}/ShowOrderServlet?info=1&id=${item.id}">详情</a>
                </td>
                <td>
                    <a rel="stylesheet" href="${APP_PATH}/ShowOrderServlet?info=2&id=${item.id}">购买</a>
                    /
                    <a rel="stylesheet" href="${APP_PATH}/ShowOrderServlet?info=0&id=${item.id}">取消</a>
                </td>
            </tr>
        </c:forEach>
    </table>

</div>

<%@include file="gonggao.jsp"%>

</body>
</html>
