<%-- 
    Document   : listProducts
    Created on : 11.12.2020, 9:27:16
    Author     : Alice
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Список товаров</title>
    </head>
    <body>
        <h1>Все товары</h1>
        <p>${info}</p>
            <table>
                <tr>
                    <th>Наименование</th>
                    <th>Страна</th>
                    <th>Стоимость</th>
                    <th></th>
                </tr>
                <c:forEach var="product" items="${listProducts}" varStatus="status">
                    <tr>
                        <td>${product.name}</td>
                        <td>${product.country}</td>
                        <td>${product.price}€</td>
                        <td><a href="editProductForm?productId=${product.id}">Править</a></td>
                    </tr>
                </c:forEach>
            </table>
        <br>
        <a href="index.jsp">Главная</a>
    </body>
</html>
