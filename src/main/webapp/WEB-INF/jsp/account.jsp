<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Органайзер настольных игр | Личный кабинет</title>
</head>
<body>
<%@ include file="header.jsp" %>

<table style="text-align: center; border: 1px solid;">
    <th>Название</th>
    <th>Год</th>
    <th>Издатель</th>
    <th>Мин. количество игроков</th>
    <th>Макс. количество игроков</th>
    <th>Цена</th>
    <th>Дата покупки</th>
    <c:forEach var="game" items="${requestScope.games}">
        <tr>
            <td>${game.boardGame.name}</td>
            <td>${game.boardGame.year}</td>
            <td>${game.boardGame.publisher}</td>
            <td>${game.boardGame.minPlayers}</td>
            <td>${game.boardGame.maxPlayers}</td>
            <td>${game.price}</td>
            <td>${game.purchaseDate}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
