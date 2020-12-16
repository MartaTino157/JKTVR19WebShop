<%-- 
    Document   : editCustomerForm
    Created on : 15.12.2020, 9:14:25
    Author     : Alice
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Изменить</title>
    </head>
    <body>
        <h1>Изменить информацию</h1>
        <p>${info}</p>
        <form action="editCustomer" method="POST">
            <input type="hidden" name="customerId" value="${customer.id}">
            Имя: <input type="text" name="firstname" value="${customer.firstname}"><br>
            Фамилия: <input type="text" name="lastname" value="${customer.lastname}"><br>
            Номер телефона: <input type="text" name="phone" value="${customer.phone}"><br>
            Текущий баланс: ${customer.balance}€<br>
            <input type="submit" name="submit" value="Править"><br>
        </form>
        <p>
            <a href="index.jsp">Главная</a>
        </p>
    </body>
</html>
