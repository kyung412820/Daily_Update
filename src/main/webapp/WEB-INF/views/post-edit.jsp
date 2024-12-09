<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 24. 12. 9.
  Time: 오후 3:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>수정</title>
</head>
<body>
<form action="/mvc/posts/update" method="post">
  <input type="hidden" name="id" value="${post.id}">

  <label for="name">작성자 이름:</label>
  <input type="text" id="name" name="name" value="${post.name}" required>

  <label for="email">이메일:</label>
  <input type="email" id="email" name="email" value="${post.email}" required>

  <label for="work">할 일:</label>
  <input type="text" id="work" name="work" value="${post.work}" required>

  <label for="password">작업 비밀번호:</label>
  <input type="password" id="password" name="password" value="${post.password}" required>

  <label for="date">수정일:</label>
  <input type="date" id="date" name="date" value="${post.date}" required>

  <label for="origindate">작성일:</label>
  <input type="date" id="origindate" name="origindate" value="${post.origindate}" required>


  <input type="hidden" name="userId" value="${post.userId}">

  <button type="submit">수정</button>
</form>
</body>
</html>

