<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Органайзер настольных игр | Все игры</title>
</head>
<body>
<table style="text-align: center; border: 1px solid;">
    <th>Название</th>
    <th>Год</th>
    <th>Язык</th>
    <th>Издатель</th>
    <th>Мин. количество игроков</th>
    <th>Макс. количество игроков</th>
    <th>Цена</th>
    <c:forEach var="game" items="${requestScope.games}">
        <tr>
            <td>${game.name}</td>
            <td>${game.year}</td>
            <td>${game.language}</td>
            <td>${game.publisher}</td>
            <td>${game.minPlayers}</td>
            <td>${game.maxPlayers}</td>
            <td>${game.price}</td>
        </tr>
    </c:forEach>
</table>
<a href="${pageContext.request.contextPath}/login">Войти в аккаунт</a>
</body>
</html>
