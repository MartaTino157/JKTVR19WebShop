

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<p class="text-primary">${info}</p>
<div class="card border-danger mb-3" style="max-width: 25rem;">
  <div class="card-header">Данные покупателя</div> 
    <div class="card-body">
        <h4 class="card-title">Пополнить баланс покупателя</h4>
        <form action="addMoney" method="POST">
          <input type="hidden" name="customerId" value="${customer.id}">
          <div class="form-group row">
              <label class="col-sm-6 col-form-label">Клиент</label>
              <div class="col-sm-6">
                <input type="text" readonly class="form-control-plaintext" value="${customer.firstname} ${customer.lastname}">
              </div>
          </div>
          <div class="form-group row">
              <label class="col-sm-6 col-form-label">Номер телефона</label>
              <div class="col-sm-6">
                <input type="text" readonly class="form-control-plaintext" value="${customer.phone}">
              </div>
          </div>
          <div class="form-group row">
              <label class="col-sm-6 col-form-label">Текущий баланс</label>
              <div class="col-sm-6">
                <input type="text" readonly class="form-control-plaintext" value="${customer.balance}€">
              </div>
          </div>
          <div class="form-group">
            <label>На сколько вы хотите пополнить счет?</label>
            <input type="text" class="form-control" name="money" value="${strMoney}">
          </div>
          <button type="submit" name="submit" class="btn btn-success">Пополнить счет</button>
          <a href="listCustomers" type="button" class="btn btn-secondary">Список покупателей</a>
        </form>
    </div>
</div>
