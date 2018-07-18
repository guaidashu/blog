<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8"/>
    <title>作业前端</title>
</head>
<body>
<c:forEach var="item" items="${data}">
${item.key}
</c:forEach>
ftl
</body>
</html>
