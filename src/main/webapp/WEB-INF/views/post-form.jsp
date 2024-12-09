<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>일정 관리</title>
  <style>
    body {
      font-family: Arial, sans-serif;
    }
    .navbar {
      display: flex;
      background-color: #f8f9fa;
      padding: 10px;
    }
    .navbar a {
      margin-right: 15px;
      text-decoration: none;
      color: #000;
      font-weight: bold;
    }
    .navbar a.active {
      color: green;
    }
    .filter, .schedule {
      margin: 20px;
    }
    .filter input[type="date"], .filter button, .filter select {
      margin-right: 10px;
      padding: 5px;
    }
    .schedule table {
      width: 100%;
      border-collapse: collapse;
    }
    .schedule th, .schedule td {
      border: 1px solid #ccc;
      padding: 10px;
      text-align: left;
    }
    .add-btn {
      display: flex;
      justify-content: flex-end;
      margin: 20px;
    }
    .add-btn button {
      background-color: green;
      color: white;
      border: none;
      padding: 10px 15px;
      cursor: pointer;
    }
  </style>
</head>
<body>
<div class="navbar">
  <a href="#" class="active">일정표</a>
  <a href="#">프로젝트 관리</a>
  <a href="#">팀 일정</a>
  <a href="#">개인 일정</a>
</div>

<div class="filter">
  <form action="/mvc/posts/search" method="get">
    <div class="form-group">
      <label for="id">id</label>
      <input type="text" id="id" name="id">

      <label for="date">수정일</label>
      <input type="date" id="date" name="date">

      <button type="submit">검색</button>
    </div>
  </form>
</div>

<div class="schedule">
  <h2>일정표</h2>
  <table>
    <thead>
    <tr>
      <th>id</th>
      <th>할일</th>
      <th>작성자명</th>
      <th>작성일</th>
      <th>수정일</th>
      <th>수정</th>
      <th>삭제</th> <!-- 수정 컬럼 추가 -->
    </tr>
    </thead>
    <tbody>
    <c:choose>
      <c:when test="${not empty posts}">
        <c:forEach var="post" items="${posts}">
          <tr>
            <td>${post.id}</td>
            <td>${post.work}</td>
            <td>${post.name}</td>
            <td>${post.origindate}</td>
            <td>${post.date}</td>
            <td>
              <a href="/mvc/posts/edit?id=${post.id}">수정</a> <!-- 수정 링크 -->
            </td>
            <td>
              <a href="/mvc/posts/delete?id=${post.id}">삭제</a> <!-- 수정 링크 -->
            </td>
          </tr>
        </c:forEach>
      </c:when>
      <c:otherwise>
        <tr>
          <td colspan="7">일정 데이터가 없습니다.</td>
        </tr>
      </c:otherwise>
    </c:choose>
    </tbody>
  </table>
</div>


<div class="add-btn">
  <button onclick="window.location.href='/mvc/posts/add-schedule'">일정 추가</button>
</div>
</body>
</html>
