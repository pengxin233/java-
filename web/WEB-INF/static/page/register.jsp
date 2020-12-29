<%--<jsp:useBean id="rfb" scope="request" type="com.px.utils.RegisterFormBean"/>--%>
<%@ page language="java" pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 
Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
<%--		<% pageContext.setAttribute("APP_PATH",request.getContextPath());%>--%>
    <title>用户注册</title>
    <style type="text/css">
        form span {
	        color: #ff0000
        }
    </style>
</head>
<body>
		<%@include file="head.jsp"%>

		<br/>
		<br/>
		<br/>
		<br/>

	    <form action="${APP_PATH}/RegisterServlet" method="post">
		    <div class="form-horizontal">
				<div class="form-group">
					<h2 class="header col-sm-offset-2">用户注册</h2>
				</div>

			    <div class="form-group">
				    <label for="name" class="col-sm-2 control-label">姓名：</label>
				    <div class="col-sm-10">
						<input type="text" id="name" name="name" value="${rfb.name }" />
					    <span>${rfb.errors.name}${rfb.errors.DBMes}</span>
				    </div>
			    </div>

			    <div class="form-group">
					<label for="password" class="col-sm-2 control-label">密码：</label>
					<div class="col-sm-10">
					    <input type="password" id="password" name="password" />
					    <span>${rfb.errors.password}</span>
				    </div>
			    </div>
				<div class="form-group">
					<label for="password2" class="col-sm-2 control-label">确认密码：</label>
					<div class="col-sm-10">
					    <input type="password" id="password2" name="password2" />
					    <span>${rfb.errors.password2}</span>
				    </div>
			    </div>
				<div class="form-group">
					<label for="email" class="col-sm-2 control-label">邮箱：</label>
					<div class="col-sm-10">
						<input type="text" id="email" name="email" value="${rfb.email }" />
					    <span>${rfb.errors.email}</span>
				    </div>
			    </div>
				<div class="form-group">
				    <div class="col-sm-offset-2 col-sm-10">
						<input class="btn btn-default" type="reset" value="重置 " />
						<input class="btn btn-default" type="submit" value="注册" />
					</div>
			    </div>
		    </div>
	    </form>

		<br/>
		<br/>
		<br/>
		<br/>

	<%@include file="gonggao.jsp"%>
		<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<%--		<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>--%>
<%--		<script src="https://cdn.jsdelivr.net/npm/jquery@3.2/dist/jquery.min.js"></script>--%>
</body>
</html>
