<!-- request 객체 안에 setAttribute로 post를 임시 저장했음 -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
</head>
<body>
<h1>글작성 성공!!!</h1>
<ul>
    <li>id=${post.getId()}</li>
    <li>title=${post.getTitle()}</li>
    <li>content=${post.getContent()}</li>
</ul>
</body>
</html>