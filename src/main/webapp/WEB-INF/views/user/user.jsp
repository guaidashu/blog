<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>信息填写</title>
</head>
<body>
<form action="${path}/user/userHandle" method="POST">
    输入姓名：<input type="text" name="username" value="测试"/><br/>
    输入密码：<input type="password" name="password" value="wyysdsa!"/><br/>
    输入年龄：<input type="text" name="age" value="21"/><br/>
    <input type="submit" value="提交"/><br/>
</form>
</body>
</html>