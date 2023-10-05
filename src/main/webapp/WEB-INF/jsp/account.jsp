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
    <c:forEach var="game" items="${requestScope.games}">
        <tr>
            <td>${game.name}</td>
            <td>${game.year}</td>
            <td>${game.publisher}</td>
            <td>${game.minPlayers}</td>
            <td>${game.maxPlayers}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
