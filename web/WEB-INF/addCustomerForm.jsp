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
        <h1>Новый покупатель</h1>
        <p>${info}</p>
        <form action="createCustomer" method="POST">
            Имя: <input type="text" name="firstname" value="${firstname}"><br>
            Фамилия: <input type="text" name="lastname" value="${lastname}"><br>
            Номер телефона: <input type="text" name="phone" value="${phone}"><br>
            Баланс: <input type="text" name="balance" value="${strBalance}"><br>
            <input type="submit" name="submit" value="Добавить покупателя"><br>
        </form>
        <p>
            <a href="index.jsp">Главная</a>
        </p>
    </body>
</html>
