<%-- 
    Document   : addMoneyForm
    Created on : 13.12.2020, 18:43:42
    Author     : Alice
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Пополнение счета</title>
    </head>
    <body>
        <h1>Пополнить баланс покупателя</h1>
        <p>${info}</p>
        <form action="addMoney" method="POST">
            <input type="hidden" name="customerId" value="${customer.id}">
            Клиент: ${customer.firstname} ${customer.lastname}<br>
            Номер телефона: ${customer.phone}<br>
            Текущий баланс: ${customer.balance}€<br>
            <label>На сколько вы хотите пополнить счет?</label><br>
            <input type="text" name="money" value="${strMoney}">
            <input type="submit" name="submit" value="Пополнить счет"><br>
        </form>
        <p>
            <a href="listCustomers">Список покупателей</a>
        </p>
        <p>
            <a href="index.jsp">Главная</a>
        </p>
    </body>
</html>
