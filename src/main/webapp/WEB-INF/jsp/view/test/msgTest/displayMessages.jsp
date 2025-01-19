<!DOCTYPE html>
<html>
<head>
  <title>Messages</title>
</head>
<body>
<h1>All Messages</h1>

<c:choose>
  <c:when test="${not empty messages}">
    <ul>
      <c:forEach items="${messages}" var="message">
        <li>${message.msgContent}</li>
      </c:forEach>
    </ul>
  </c:when>
  <c:otherwise>
    <p>No messages available.</p>
  </c:otherwise>
</c:choose>


</body>
</html>
