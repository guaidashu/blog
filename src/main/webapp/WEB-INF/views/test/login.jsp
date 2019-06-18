<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta content="initial-scale=1.0, minimum-scale=1.0, maximum-scale=2.0, user-scalable=no, width=device-width"
          name="viewport">
    <link rel="shortcut icon" type="image/x-icon" href="${path}/images/ooopic_1460463927.ico" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${path}/asset/css/amazeui.css"/>
    <link rel="stylesheet" type="text/css" href="${path}/css/body.css"/>
    <link rel="stylesheet" type="text/css" href="${path}/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="${path}/css/yy.css"/>
</head>
<body>
<center>
    <h1>请登录</h1>
    <form action="${path}/test/testDoLogin" method="post">
        <span>用户名：</span><input type="text" name="username"/>
        <span>密码：</span><input type="password" name="password"/>
        <input type="submit"/>
    </form>
</center>
</body>
</html>
