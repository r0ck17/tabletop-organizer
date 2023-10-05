<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Органайзер настольных игр | Регистрация</title>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/registration">
    <label for="email">Email</label>
    <input type="text" name="email" id="email" required><br>
    <label for="name">Имя</label>
    <input type="text" name="name" id="name" required><br>
    <label for="password">Пароль</label>
    <input type="password" name="password" id="password" required><br>
    <button type="submit">Зарегистрироваться</button>
</form>
<c:if test="${not empty requestScope.errors}">
    <div style="color: red">
        <c:forEach var="error" items="${requestScope.errors}">
            <span>${error.message}</span>
            <br>
        </c:forEach>
    </div>
</c:if>
</body>
</html>
