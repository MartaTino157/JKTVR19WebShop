<%-- 
    Document   : editProductForm
    Created on : 13.12.2020, 16:46:42
    Author     : Alice
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Изменение товара</title>
    </head>
    <body>
        <h1>Изменить данные товара</h1>
        <p>${info}</p>
        <form action="editProduct" method="POST">
            <input type="hidden" name="productId" value="${product.id}">
            Наименование товара: <input type="text" name="name" value="${product.name}"><br>
            Страна производства: <input type="text" name="country" value="${product.country}"><br>
            Стоимость: <input type="text" name="price" value="${product.price}"><br>
            <input type="submit" name="submit" value="Править"><br>
        </form>
        <p>
            <a href="index.jsp">Главная</a>
        </p>
    </body>
</html>
