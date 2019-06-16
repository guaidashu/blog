<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8"/>
    <title>作业前端</title>
</head>
<body>
<c:forEach var="item" items="${requestScope.data}">
    ${item.key}：${item.value} <br/>
</c:forEach>
${article.content}
</body>
</html>