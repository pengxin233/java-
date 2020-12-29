<%--
  Created by IntelliJ IDEA.
  User: 彭鑫
  Date: 2020/12/12
  Time: 22:30
  To change this template use File | Settings | File Templates.
--%>

<%--将所有页面公用的资源放在此处--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
    <% pageContext.setAttribute("APP_PATH",request.getContextPath());%>

    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="${APP_PATH}/css/my.css">
</head>

<%--    头部--%>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <%--            图标--%>
        <div class="col-md-5">
            <div class="navbar-header">
                <h3 class="navbar-brand" style="color: slateblue">PX</h3>
            </div>
<%--            用户名或者未登录--%>
            <div>
                <a rel="stylesheet" class="navbar-link" href="${APP_PATH}/UserServlet?info=${sessionScope.user!=null ?0:1}">${sessionScope.user!=null ? sessionScope.user.name:"未登录"}</a>
            </div>
        </div>
        <div class="col-md-3 col-md-offset-4">
            <ul class="nav navbar-nav">
                <li>
                    <a class="navbar-link" rel="stylesheet" href="${APP_PATH}/IndexServlet"><span class="glyphicon glyphicon-home"/>首页</a>
                </li>

                <li>
                    <a class="navbar-link" rel="stylesheet" href="${APP_PATH}/${sessionScope.user==null ? "LoginServlet?info=0":"CarServlet?info=0"}"><span class="glyphicon glyphicon-shopping-cart"/>购物车</a>
                </li>

                <li>
                    <a class="navbar-link" rel="stylesheet" href="${APP_PATH}/${sessionScope.user==null ? "LoginServlet?info=0":"ShowOrderServlet"}"><span class="glyphicon glyphicon-book"/>我的订单</a>
                </li>
                <li>
                    <a class="navbar-link" rel="stylesheet" href="${APP_PATH}/RegisterServlet?info=0 "><span class="glyphicon glyphicon-wrench"/>注册</a>
                </li>
            </ul>
        </div>

    </div>
</nav>


<%--公告版和本周热卖--%>
<div class="container-fluid panel panel-default">
    <div class="panel-body">
        <%--        公告--%>
        <div class="col-md-6">
            <div class="row">
                <h3><span class="glyphicon glyphicon-pushpin"/>公告板</h3>
            </div>
            <div class="row">
                <c:forEach var="item" items="${sessionScope.info}">
                    <p>${item.head}：</p>
                    <p>&nbsp;&nbsp;${item.p1}</p>
                    <p>${item.p2}</p>
                </c:forEach>
                <strong style="display: inline-block">PX书城</strong>&nbsp;感谢大家的支持。
            </div>
        </div>
        <%--        本周热卖--%>
        <div class="col-md-6">
            <div class="row">
                <h3><span class="glyphicon glyphicon-fire"/>本周热卖</h3>
            </div>
            <div class="row">

                <c:forEach var="item" items="${sessionScope.hot}">
                    <div class="col-sm-3">
                            <%--    显示图片--%>
                        <img style="width: 100px;height: 120px" src="img/${item.id}.jpg" alt="图片加载失败" class="img-rounded img-responsive">

                        <div>
                            <div>
                                <a rel="stylesheet" href="https://www.xsbiquge.com/15_15338/"><h4>${item.name}</h4></a>
                            </div>
                            <div>
                                <small style="color: darkgray">${item.auther}</small>
                            </div>
                            <div><span style="color: red" class="glyphicon glyphicon-jpy">${item.price}</span></div>
                            <div>销量:  ${item.c}</div>
                            <div style="margin-top: 10px"><a class="btn btn-primary" rel="stylesheet" href="${APP_PATH}/${sessionScope.user!=null?"CarServlet?info=1&bid=".concat(item.id):"LoginServlet?info=0"}">加入购物车</a></div>
                        </div>
                    </div>

                </c:forEach>
            </div>
        </div>
    </div>
</div>
