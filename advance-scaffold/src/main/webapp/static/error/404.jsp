<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/WEB-INF/views/common/config.jsp" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <title>你所访问的页面不存在 (404)</title>
  <link href="${root}/static/style/css/static.css" media="screen" rel="stylesheet" type="text/css" />
</head>

<body>
  <h1>404</h1>
  <h3>你所访问的页面不存在.</h3>
  <hr/>
  <p>资源不存在或者没有访问权限 <a href="${root}/index.jsp">返回首页</a> 或 <a href="${root}/welcome/index">登录</a></p>
</body>
</html>
