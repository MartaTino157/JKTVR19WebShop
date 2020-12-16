<%-- 
    Document   : makeDealForm
    Created on : 11.12.2020, 10:53:58
    Author     : Alice
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Покупка</title>
    </head>
    <body>
        <h1>Купить товар</h1>
        <p>${info}</p>
        <form action="makeDeal" method="POST">
            <h3>Шаг 1: Выберите товар</h3>
            <select name="productId">
                <option value="">Выберите товар</option>
                <c:forEach var="product" items="${listProducts}" varStatus="status">
                    <option value="${product.id}">${product.name} ${product.price}</option>
                </c:forEach>
            </select>
            <h3>Шаг 2: Выберите покупателя</h3>
            <select name="customerId">
                <option value="">Выберите покупателя</option>
                <c:forEach var="customer" items="${listCustomers}" varStatus="status">
                    <option value="${customer.id}">${customer.firstname} ${customer.lastname}</option>
                </c:forEach>
            </select>
            <p>
                <input type="submit" name="submit" value="Купить">
            </p>
            <p>
                <a href="index.jsp">Главная</a>                
            </p>
        </form>
    </body>
</html>
