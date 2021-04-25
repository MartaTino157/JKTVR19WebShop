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
          <li class="nav-item active">
            <a class="nav-link" href="registrationForm">Регистрация
              <span class="sr-only">(current)</span>
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="profile">Личный кабинет</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="listCustomers">Покупатели</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="addProductForm">Добавить товар</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="listProducts">Товары</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="makeDealForm">Купить</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="adminPanel">Админ-панель</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="loginForm">Войти</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="logout">Выйти</a>
          </li>
      </div>
    </div>
</nav>