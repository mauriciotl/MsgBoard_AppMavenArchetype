<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <title>Message Board</title>
  <meta charset="UTF-8">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
  <script src="${pageContext.request.contextPath}/js/script.js"></script>

</head>
<body>
<h1>Message Board</h1>
<a href="messages/addByForm">Add a new message</a>
<br/>
<h2>Messages</h2>
<ul>
  <c:forEach var="message" items="${messages}" varStatus="status">
    <li>
      <p><b>${message.user}</b> (${message.date}): ${message.msgContent}</p>
      <form action="${pageContext.request.contextPath}/messages/editMessageByForm/${message.id}" method="get" style="display: inline;">
        <button type="submit">Edit</button>
      </form>
      <form action="${pageContext.request.contextPath}/messages/delete/${message.id}" method="post" style="display: inline;">
        <button type="submit">Delete</button>
      </form>
    </li>
  </c:forEach>
</ul>
</body>
</html>
