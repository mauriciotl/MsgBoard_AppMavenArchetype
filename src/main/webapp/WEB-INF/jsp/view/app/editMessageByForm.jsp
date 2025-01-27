<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
<script src="${pageContext.request.contextPath}/js/script.js"></script>

<c:set var="title" value="Edit Message" />
<c:set var="loggedInUser" value="${loggedInUser}" />
<%@ include file="messageForm.jspf" %>
