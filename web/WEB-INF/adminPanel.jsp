<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h1>Панель администратора</h1>
<p class="text-danger">${info}</p>
<h2 class="text-success">Список пользователей</h2>
<form action="addNewRole" method="POST">
    <table class="table table-hover">
      <thead>
        <tr>
            <th scope="col">Выбрать</th>
            <th scope="col">Имя</th>
            <th scope="col">Фамилия</th>
            <th scope="col">Логин</th>
            <th scope="col">Текущая роль</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="entry" items="${usersMap}" varStatus="status">
        <tr>
            <td>
            <div class="form-group">
                <div class="custom-control custom-radio">
                  <input type="radio" id="${entry.key.id}" name="userId" class="custom-control-input" checked="" value="${entry.key.id}">
                  <label class="custom-control-label" for="${entry.key.id}">&#10004;</label>
                </div>
            </div>
            </td>
            <td>${entry.key.customer.firstname}</td>
            <td>${entry.key.customer.lastname}</td>
            <td>${entry.key.login}</td>
            <td>${entry.value}</td>
        </tr>
        </c:forEach>
      </tbody>
    </table>
    <div class="card border-primary mb-3" style="max-width: 20rem;">
  <div class="card-header">Назначение</div>
  <div class="card-body">
    <h4 class="card-title">Список ролей</h4>
    <p class="card-text">
        <div class="form-group">
          <label for="exampleSelect2">Выберите роль</label>
          <select multiple="" class="form-control" id="exampleSelect2" name="roleId">
            <c:forEach var="role" items="${listRoles}" varStatus="status">
                <option value="${role.id}">${role.roleName}</option>
            </c:forEach>
          </select>
        </div>
    </p>
    <button class="btn btn-success">Назначить</button>
  </div>
</div>
</form>

