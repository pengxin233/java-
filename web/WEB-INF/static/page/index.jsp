
<%--
  Created by IntelliJ IDEA.
  User: 彭鑫
  Date: 2020/12/12
  Time: 20:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<%--    <% pageContext.setAttribute("APP_PATH",request.getContextPath());%>--%>
    <title>首页</title>

    <style type="text/css">
        /*.container-fluid{*/
        /*    padding-left: 100px; */
        /*    padding-top: 50px;*/
        /*}*/
        .container-fluid .well{
            /* 让书籍在同一行显示*/
            display: inline-block;
            /*设置外边距*/
            margin-left: 40px;
            margin-top: 20px;;
        }
    </style>

    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
<%--    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">--%>
</head>
<body>


<%--    头部--%>
<%@include file="head.jsp"%>

<%--分类--%>
<div class="panel panel-default">
    <div class="panel-heading">
        <nav class="navbar">
            <div class="container-fluid">


                <div class="navbar-header">
                    <p class="navbar-brand">分类</p>
                </div>

                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <c:forEach var="item" items="${classes}">
                            <li class="dropdown">
                                <a href="${APP_PATH}/IndexServlet?fenlei2=${item.id}" class="dropdown-toggle" data-toggle="dropdown" role="button"
                                   aria-haspopup="true" aria-expanded="true">${item.name} <span class="caret"></span></a>
                                <ul class="dropdown-menu">

                                    <c:forEach var="name" items="${bClasses}">
                                        <c:if test="${name.name.equals(item.name)}">
                                            <li><a rel="stylesheet" href="${APP_PATH}/IndexServlet?fenlei=${name.id}">${name.name2}</a></li>
                                        </c:if>
                                    </c:forEach>
                                </ul>
                            </li>
                        </c:forEach>
                    </ul>

                    <%--                搜索框--%>
                    <form action="${APP_PATH}/IndexServlet" class="navbar-form navbar-right">
                        <div class="form-group">
                            <input type="text" class="form-control" placeholder="Search" name="search">
                        </div>
                        <%--                    价格查询--%>
                        <div class="form-group">
                            <input type="text" class="form-control" placeholder="低价格" name="low">
                            ---
                            <input type="text" class="form-control" placeholder="高价格" name="height">
                        </div>
                        <button type="submit" class="btn btn-default">Submit</button>
                    </form>

                </div>


            </div>
        </nav>
    </div>

    <div class="container-fluid" style="padding-left: 100px; padding-top: 50px;">

        <c:if test="${empty books}">
            <h3 style="color: red">没有搜索到相关内容</h3>
        </c:if>


        <c:forEach var="item" items="${books}">
            <div class="well">
                    <%--    显示图片--%>
                <img style="width: 200px;height: 250px" src="img/${item.id}.jpg" alt="图片加载失败" class="img-rounded img-responsive">

                <div style="padding-left: 50px">
                    <div>
                        <a rel="stylesheet" href="https://www.xsbiquge.com/15_15338/"><h4>${item.name}</h4></a>
                    </div>
                    <div>
                        <small style="color: darkgray">${item.auther}</small>
                    </div>
                    <div><span style="color: red" class="glyphicon glyphicon-jpy">${item.price}</span></div>
                    <div style="margin-top: 10px"><a class="btn btn-primary" rel="stylesheet" href="${APP_PATH}/${sessionScope.user!=null?"CarServlet?info=1&bid=".concat(item.id) :"LoginServlet?info=0"}">加入购物车</a></div>
                </div>
            </div>
        </c:forEach>

</div>


<%--公告--%>
<%@include file="gonggao.jsp"%>


<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<%--<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>--%>
<%--<script src="https://cdn.jsdelivr.net/npm/jquery@3.2/dist/jquery.min.js"></script>--%>
</body>
</html>
