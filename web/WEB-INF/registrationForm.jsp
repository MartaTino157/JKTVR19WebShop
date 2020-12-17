<%-- 
    Document   : addCustomerForm
    Created on : 11.12.2020, 9:38:42
    Author     : Alice
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Добавление покупателя</title>
    </head>
    <body>
        <h2>Новый покупатель</h2>
        <p>${info}</p>
        <h3>Шаг 1: Ввод личных данных</h3>
        <form action="registration" method="POST">
            Имя: <input type="text" name="firstname" value="${firstname}"><br>
            Фамилия: <input type="text" name="lastname" value="${lastname}"><br>
            Номер телефона: <input type="text" name="phone" value="${phone}"><br>
            Баланс: <input type="text" name="balance" value="${strBalance}"><br>
        <h3>Шаг 2: Регистрация пользователя</h3>
            Логин: <input type="text" name="login" value="${login}"><br>
            Пароль: <input type="text" name="password" value="${password}"><br><br>
            <input type="submit" name="submit" value="Добавить покупателя"><br>
        </form>
        <p>
            <a href="index.jsp">Главная</a>
        </p>
    </body>
</html>
