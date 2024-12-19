<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>사용자 목록</title>
</head>
<body>
<h2>사용자 목록</h2>
<table border="1">
  <thead>
  <tr>
    <th>ID</th>
    <th>이름</th>
    <th>이메일</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach var="user" items="${users}">
    <tr>
      <td>${user.id}</td>
      <td>${user.username}</td>
      <td>${user.email}</td>
    </tr>
  </c:forEach>
  </tbody>
</table>
</body>
</html>
