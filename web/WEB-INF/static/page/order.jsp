<%--
  Created by IntelliJ IDEA.
  User: 彭鑫
  Date: 2020/12/16
  Time: 13:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${sessionScope.user.name}的结算</title>
    <style type="text/css">
        form span{
            color: red;
        }
    </style>
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
        </tr>
        <% int i = 1;%>
            <c:set var="p" value="0"></c:set>
        <c:forEach var="item" items="${sessionScope.car}">
            <tr>
                <td><%out.print(i++);%></td>
                <td>${item.value.book.name}</td>
                <td>${item.value.book.price}</td>
                <td>${item.value.count}</td>
                <td>${item.value.book.price*item.value.count}</td>
                <c:set var="p" value="${p+item.value.book.price*item.value.count}"></c:set>
            </tr>
        </c:forEach>
    </table>
    <form action="${APP_PATH}/OrderServlet" class="form-horizontal" method="post" onsubmit="return checkOnSubmit();" id="orderForm">
        <div class="form-group">
            <label for="receiverAddress" class="col-sm-2 control-label">收货地址</label>
            <div class="col-sm-5">
                <input type="text" class="form-control" id="receiverAddress" name="receiverAddress" placeholder="receiverAddress" onkeyup="checkReceiverAddress();">
                <span id="receiverAddressMsg"></span>
            </div>
        </div>
        <div class="form-group">
            <label for="receiverName" class="col-sm-2 control-label">收货人</label>
            <div class="col-sm-5">
                <input type="text" class="form-control" id="receiverName" name="receiverName" placeholder="receiverName" onkeyup="checkReceiverName();">
                <span id="receiverNameMsg"></span>
            </div>
        </div>
        <div class="form-group">
            <label for="receiverPhone" class="col-sm-2 control-label">联系电话</label>
            <div class="col-sm-5">
                <input type="text" class="form-control" id="receiverPhone" name="receiverPhone" placeholder="receiverPhone" onkeyup="checkReceiverPhone();">
                <span id="receiverPhoneMsg"></span>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-5 col-sm-offset-5">
                <h3 style="color: red">总金额 : ${p}</h3>
                <button type="reset" class="btn btn-primary">重置</button>
                <button type="submit" class="btn btn-primary">结账</button>
            </div>
        </div>
    </form>

</div>

<script src="${APP_PATH}/js/order.js"></script>

<%@include file="gonggao.jsp"%>
</body>
</html>
