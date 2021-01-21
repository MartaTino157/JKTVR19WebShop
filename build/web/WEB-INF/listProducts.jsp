
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <h2 class="text-success">Все товары</h2>
        <p class="text-success">${info}</p>
        <table class="table table-hover">
            <thead>
                <tr>
                    <th scope="col">Наименование</th>
                    <th scope="col">Страна</th>
                    <th scope="col">Стоимость</th>
                    <th scope="col"></th>
                </tr>
            </thead>
            <c:forEach var="product" items="${listProducts}" varStatus="status">
                <tbody>
                    <tr>
                        <td>${product.name}</td>
                        <td>${product.country}</td>
                        <td>${product.price}€</td>
                        <td><a href="editProductForm?productId=${product.id}">Править</a></td>
                    </tr>
                </tbody>
            </c:forEach>
        </table>
