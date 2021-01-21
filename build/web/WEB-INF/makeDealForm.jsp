
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<p>${info}</p>
<div class="jumbotron">
  <h1 class="display-3">Покупка товара</h1>
  <form action="makeDeal" method="POST">
      <h4>Покупатель: <span class="text-success">${customer.firstname} ${customer.lastname}</span></h4>
      <h4>Текущий баланс: <span class="text-success">${customer.balance}€</span></h4>
      <input type="hidden" name="customerId" value="${customer.id}">
      <p class="lead">Выберите товар</p>
      <hr class="my-4">
      <div class="row">
          <c:forEach var="product" items="${listProducts}" varStatus="status">
              <input type="hidden" name="productId" value="${product.id}">
              <div class="col-md-3">
                  <div class="card border-success mb-3" style="max-width: 15rem;">
                      <div class="card-header">${product.country}</div>
                      <div class="card-body">
                        <h4 class="card-title">${product.name}</h4>
                        <p class="card-text">Цена: <span class="text-light">${product.price}€</span></p>
                        <button type="submit" name="submit" class="btn btn-primary">Купить</button> 
                      </div>
                  </div>
              </div>
          </c:forEach>
      </div>
  </form>
</div>

