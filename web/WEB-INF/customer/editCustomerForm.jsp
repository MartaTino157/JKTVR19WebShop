
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<p class="text-primary">${info}</p>
<div class="card border-danger mb-3" style="max-width: 25rem;">
  <div class="card-header">Данные покупателя</div> 
    <div class="card-body">
      <h4 class="card-title">Изменить информацию</h4>
        <form action="editCustomer" method="POST">
          <input type="hidden" name="customerId" value="${customer.id}">
          <div class="form-group">
            <label for="firstname">Имя</label>
            <input type="text" class="form-control" id="firstname" name="firstname" value="${customer.firstname}">
          </div>
          <div class="form-group">
            <label for="lastname">Фамилия</label>
            <input type="text" class="form-control" id="lastname" name="lastname" value="${customer.lastname}">
          </div>
          <div class="form-group">
            <label for="phone">Номер телефона</label>
            <input type="text" class="form-control" id="phone" name="phone" value="${customer.phone}">
          </div>
          <button type="submit" name="submit" class="btn btn-success">Сохранить</button> 
            <a href="profile" type="button" class="btn btn-secondary">Назад</a>
    </form>
</div>



