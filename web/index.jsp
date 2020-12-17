<%-- 
    Document   : page2
    Created on : 10.12.2020, 11:43:21
    Author     : Alice
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JKTVR19WebShop</title>
    </head>
    <body>
        <h1>Привутствуем Вас в нашем онлайн-магазине!</h1>
        <p>${info}</p>
        <p>
            <a href="loginForm">Войти в систему</a>
        </p>
        <p>
            <a href="registrationForm">Зарегистрироваться</a>
        </p>
        <p>
            <a href="logout">Выйти из системы</a>
        </p>
        <p>
            <a href="listCustomers">Список покупателей</a>
        </p>
        <p>
            <a href="addProductForm">Добавить товар</a>
        </p>
        <p>
            <a href="listProducts">Список товаров</a>
        </p>
        <p>
            <a href="makeDealForm">Сделать покупку</a>
        </p>
    </body>
</html>
