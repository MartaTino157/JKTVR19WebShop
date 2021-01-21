
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
          <div class="form-group row">
              <label class="col-sm-6 col-form-label">Текущий баланс</label>
              <div class="col-sm-6">
                <input type="text" readonly class="form-control-plaintext" value="${customer.balance}€">
              </div>
          </div> 
          <button type="submit" name="submit" class="btn btn-success">Править</button> 
        <!--<a href="index.jsp" type="button" class="btn btn-secondary">Главная</a>-->
    </form>
</div>



