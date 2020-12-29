<%@ page import="java.util.Map" %>
<%@ page import="com.px.bean.Car" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 彭鑫
  Date: 2020/12/16
  Time: 13:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${sessionScope.user.name}的购物车</title>
</head>
<body>
<%@include file="head.jsp"%>

<div class="container-fluid">
    <table class="table table-hover">
<%--        头部--%>
        <tr>
            <td>#</td>
            <td>商品名称</td>
            <td>价格</td>
            <td>数量</td>
            <td>小计</td>
            <td>取消</td>
        </tr>
        <% int i = 1;%>
    <c:set var="p" value="0"></c:set>
        <c:forEach var="item" items="${sessionScope.car}">
            <tr>
                <td><%out.print(i++);%></td>
                <td>
                    <img style="width: 50px;height: 60px" src="${APP_PATH}/img/${item.value.book.id}.jpg">
                </td>
                <td>${item.value.book.price}</td>
                <td>
                        <%--                减少--%>
                    <a class="btn btn-default" rel="stylesheet" href="${APP_PATH}/CarServlet?info=2&bid=${item.key}&value=${item.value.count-1}">-</a>
                    <input style="width: 50px" type="text" id="text<%out.print(i);%>}" value="${item.value.count}" disabled>
                    <a class="btn btn-default" rel="stylesheet" href="${APP_PATH}/CarServlet?info=2&bid=${item.key}&value=${item.value.count+1}">+</a>
                </td>
                <td>${item.value.book.price*item.value.count}</td>
                <td>
                    <a class="btn btn-danger" rel="stylesheet" href="${APP_PATH}/CarServlet?info=4&bid=${item.key}">删除</a>
                </td>
                <c:set var="p" value="${p+item.value.book.price*item.value.count}"></c:set>
            </tr>
        </c:forEach>

    </table>
    <div class="col-sm-offset-10">
        <h3 style="color: red"> 总金额 : ${p}</h3>
        <a class="btn btn-danger" rel="stylesheet" href="${APP_PATH}/CarServlet?info=3">清空</a>
        <a class="btn btn-primary" rel="stylesheet" href="${APP_PATH}/CarServlet?info=5">结算</a>
        &nbsp;&nbsp;&nbsp;<span style="color: red">${msg}</span>
    </div>
</div>

<%@include file="gonggao.jsp"%>
</body>
</html>
