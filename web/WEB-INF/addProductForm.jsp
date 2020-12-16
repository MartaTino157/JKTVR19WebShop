<%-- 
    Document   : page
    Created on : 10.12.2020, 11:22:34
    Author     : Alice
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Добавление товара</title>
    </head>
    <body>
        <h1>Добавить товар</h1>
        <p>${info}</p>
        <form action="createProduct" method="POST">
            Наименование товара: <input type="text" name="name" value="${name}"><br>
            Страна производства: <input type="text" name="country" value="${country}"><br>
            Стоимость: <input type="text" name="price" value="${strPrice}"><br>
            <input type="submit" name="submit" value="Добавить товар"><br>
        </form>
        <p>
            <a href="index.jsp">Главная</a>
        </p>
    </body>
</html>
