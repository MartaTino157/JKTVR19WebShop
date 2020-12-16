<%-- 
    Document   : listCustomers
    Created on : 11.12.2020, 9:38:54
    Author     : Alice
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Список покупателей</title>
    </head>
    <body>
        <h1>Все покупатели</h1>
        <p>${info}</p>
        <table>
            <tr>
                <th>Имя</th>
                <th>Фамилия</th>
                <th>Телефон</th>
                <th>Баланс</th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach var="customer" items="${listCustomers}" varStatus="status">
                <tr>
                    <td>${customer.firstname}</td>
                    <td>${customer.lastname}</td>
                    <td>${customer.phone}</td>
                    <td>${customer.balance}€</td>
                    <td>
                        <a href="editCustomerForm?customerId=${customer.id}">Изменить</a>
                    </td>
                    <td>
                        <a href="addMoneyForm?customerId=${customer.id}">Пополнить счет</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <a href="index.jsp">Главная</a>
    </body>
</html>
