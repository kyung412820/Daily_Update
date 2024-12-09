<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>글 목록 조회</title>
</head>
<body>
<table>
    <thead>
    <th>id</th>
    <th>title</th>
    <th>content</th>
    </thead>
    <tbody>
    <c:forEach var="post" items="${posts}">
        <tr>
            <td>${post.id}</td>
            <td>${post.title}</td>
            <td>${post.content}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>