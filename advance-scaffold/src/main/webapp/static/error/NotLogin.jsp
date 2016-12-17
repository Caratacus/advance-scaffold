<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%@ include file="/WEB-INF/views/common/config.jsp" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>重新登录 </title>
    <script type="text/javascript" charset="utf-8">
        setTimeout("parent.location.href='${root}/'", 5000);
    </script>
    <link href="${root}/static/style/css/static.css" media="screen" rel="stylesheet" type="text/css"/>
</head>

<body>
<div style="padding-top:70px;">
    <h2>登录超时</h2>

    <h3>您还没有登录或登录已超时，请重新登录，然后再刷新本功能!</h3>
    <hr/>
</div>
</body>
</html>
