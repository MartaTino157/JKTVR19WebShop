
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

    <h2 class="text-success">Все покупатели</h2>
    <p class="text-success">${info}</p>
    <table class="table table-hover">
        <thead>
            <tr>
                <th scope="col">Имя</th>
                <th scope="col">Фамилия</th>
                <th scope="col">Телефон</th>
                <th scope="col">Баланс</th>
                <th scope="col"></th>
                <th scope="col"></th>
            </tr>
        </thead>
        <c:forEach var="customer" items="${listCustomers}" varStatus="status">
            <tbody>
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
            </tbody>
        </c:forEach>
    </table>

        <!--<a href="index.jsp" type="button" class="btn btn-secondary">Главная</a>-->
