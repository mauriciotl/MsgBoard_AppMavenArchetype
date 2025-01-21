<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <title>Message Board</title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
  <script src="${pageContext.request.contextPath}/js/script.js"></script>

</head>
<body>
<h1>Message Board</h1>
<form action="${pageContext.request.contextPath}/messages/add" method="post">
  <label for="user">User:</label>
  <input type="text" id="user" name="user" required>
  <label for="msgContent">Message:</label>
  <textarea id="msgContent" name="msgContent" required></textarea>
  <button type="submit">Add Message</button>
</form>
<h2>Messages</h2>
<ul>
  <c:forEach var="message" items="${messages}" varStatus="status">
    <li>
      <p><b>${message.user}</b> (${message.date}): ${message.msgContent}</p>
      <form action="${pageContext.request.contextPath}/messages/edit/${message.id}" method="get" style="display: inline;">
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
