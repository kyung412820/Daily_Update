<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>일정 추가</title>
  <script>
    // 서버에서 전달된 에러 메시지 표시
    const errorMessage = "<%= request.getAttribute("errorMessage") != null ? request.getAttribute("errorMessage") : "" %>";
    if (errorMessage) {
      alert(errorMessage); // 메시지 박스 띄우기
    }
  </script>
  <style>
    body {
      font-family: Arial, sans-serif;
      margin: 20px;
    }
    .form-container {
      max-width: 500px;
      margin: 0 auto;
      padding: 20px;
      border: 1px solid #ccc;
      border-radius: 5px;
      background-color: #f9f9f9;
    }
    .form-container h2 {
      text-align: center;
    }
    .form-group {
      margin-bottom: 15px;
    }
    .form-group label {
      display: block;
      margin-bottom: 5px;
      font-weight: bold;
    }
    .form-group input, .form-group textarea {
      width: 100%;
      padding: 8px;
      box-sizing: border-box;
      border: 1px solid #ccc;
      border-radius: 4px;
    }
    .form-actions {
      display: flex;
      justify-content: space-between;
    }
    .form-actions button {
      padding: 10px 20px;
      border: none;
      border-radius: 4px;
      cursor: pointer;
    }
    .btn-save {
      background-color: green;
      color: white;
    }
    .btn-cancel {
      background-color: gray;
      color: white;
    }
  </style>
</head>
<body>
<div class="form-container">
  <h2>일정 추가</h2>

  <!-- 에러 메시지 출력 -->
  <c:if test="${not empty errors}">
    <ul style="color: red;">
      <c:forEach var="error" items="${errors}">
        <li>${error.defaultMessage}</li>
      </c:forEach>
    </ul>
  </c:if>

  <form action="/mvc/posts/save" method="post">
    <div class="form-group">
      <label for="id">번호</label>
      <input type="text" id="id" name="id" required>
    </div>
    <div class="form-group">
      <label for="work">할 일</label>
      <textarea id="work" name="work" required></textarea>
    </div>
    <div class="form-group">
      <label for="name">작성자명</label>
      <input type="text" id="name" name="name" required>
    </div>
    <div class="form-group">
      <label for="password">비밀번호</label>
      <input type="password" id="password" name="password" required>
    </div>
    <div class="form-group">
      <label for="origindate">작성일</label>
      <input type="date" id="origindate" name="origindate" required>
    </div>
    <div class="form-group">
      <label for="date">수정일</label>
      <input type="date" id="date" name="date" required>
    </div>
    <div class="form-group">
      <label for="email">이메일</label>
      <input type="email" id="email" name="email" required>
    </div>
    <div class="form-actions">
      <button type="submit" class="btn-save">저장</button>
      <button type="button" class="btn-cancel" onclick="window.history.back()">취소</button>
    </div>
  </form>
</div>

</body>
</html>
