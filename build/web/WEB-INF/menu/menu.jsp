<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">   
      <a class="navbar-brand" href="index.jsp">Магазин одежды</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor02" aria-controls="navbarColor02" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse" id="navbarColor02">
        <ul class="navbar-nav mr-auto">
          <li class="nav-item">
            <a class="nav-link <c:if test="${active=='registration'}">active</c:if>" href="registrationForm">Регистрация
              <span class="sr-only">(current)</span>
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link <c:if test="${active=='profile'}">active</c:if>" href="profile">Личный кабинет</a>
          </li>
          <li class="nav-item">
            <a class="nav-link <c:if test="${active=='listCustomers'}">active</c:if>" href="listCustomers">Покупатели</a>
          </li>
          <li class="nav-item">
            <a class="nav-link <c:if test="${active=='addProductForm'}">active</c:if>" href="addProductForm">Добавить товар</a>
          </li>
          <li class="nav-item">
            <a class="nav-link <c:if test="${active=='listProducts'}">active</c:if>" href="listProducts">Товары</a>
          </li>
          <li class="nav-item">
            <a class="nav-link <c:if test="${active=='makeDealForm'}">active</c:if>" href="makeDealForm">Купить</a>
          </li>
          <li class="nav-item">
            <a class="nav-link <c:if test="${active=='adminPanel'}">active</c:if>" href="adminPanel">Админ-панель</a>
          </li>
          <li class="nav-item">
            <a class="nav-link <c:if test="${active=='loginForm'}">active</c:if>" href="loginForm">Войти</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="logout">Выйти</a>
          </li>
        </ul>
      </div>
    </div>
</nav>