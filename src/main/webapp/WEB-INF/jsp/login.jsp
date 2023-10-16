<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Органайзер настольных игр | Вход в систему</title>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/login">
    <label for="login">Email</label>
    <input type="text" name="email" id="login" value="${param.email}" required><br>
    <label for="password">Пароль</label>
    <input type="password" name="password" id="password" required><br>
    <c:if test="${param.error != null}">
        <div style="color: red">
            <span>Пользователь с таким email и паролем не найден</span>
        </div>
    </c:if>
    <button type="submit">Войти</button>
</form>
<a href="${pageContext.request.contextPath}/registration">
    <button type="button">Регистрация</button>
</a>
<br/>
<a href="${pageContext.request.contextPath}/games">Список всех игр</a>
</body>
</html>
