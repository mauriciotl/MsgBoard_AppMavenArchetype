<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>${title}</title>
</head>
<body>
<h1>${title}</h1>
<div class="actions">

  <c:if test="${error}">
    <b>The username and password you entered are not correct. Please try again.</b><br /><br />
  </c:if>

  <form:form method="post" modelAttribute="authLoginForm">
    <form:label path="username">User name:</form:label><br/>
    <form:input path="username"/><br/> <!-- This will display the preserved username -->
    <br/>
    <form:label path="password">Password:</form:label><br/>
    <form:password path="password" showPassword="true"/><br/> <!-- Ensure password is preserved -->
    <br/>
    <input type="submit" value="Submit"/>
  </form:form>
</div>
</body>
</html>