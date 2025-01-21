<!DOCTYPE html>
<html>
<head>
  <title>Edit Message</title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
  <script src="${pageContext.request.contextPath}/js/script.js"></script>

</head>
<body>
<h1>Edit Message</h1>
<form action="${pageContext.request.contextPath}/messages/update" method="post">
  <input type="hidden" name="id" value="${message.id}">
  <label for="user">User:</label>
  <input type="text" id="user" name="user" value="${message.user}" required>
  <label for="msgContent">Message:</label>
  <textarea id="msgContent" name="msgContent" required>${message.msgContent}</textarea>
  <button type="submit">Apply Update</button>
  <a href="${pageContext.request.contextPath}/messages"><button type="button">Cancel</button></a>
</form>
</body>
</html>
