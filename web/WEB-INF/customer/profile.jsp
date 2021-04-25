
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h2>Личный кабинет</h2>
<div class="card border-primary mb-3" style="max-width: 25rem;">
  <div class="card-header">Мои данные</div> 
    <div class="card-body">
        <h4 class="card-title">${info}</h4>
        <form action="" method="POST">
          <div class="form-group row">
              <label class="col-sm-6 col-form-label">Клиент</label>
              <div class="col-sm-6">
                <input type="text" readonly class="form-control-plaintext text-primary" value="${user.customer.firstname} ${user.customer.lastname}">
              </div>
          </div>
          <div class="form-group row">
              <label class="col-sm-6 col-form-label">Номер телефона</label>
              <div class="col-sm-6">
                <input type="text" readonly class="form-control-plaintext text-primary" value="${user.customer.phone}">
              </div>
          </div>
          <div class="form-group row">
              <label class="col-sm-6 col-form-label">Текущий баланс</label>
              <div class="col-sm-6">
                <input type="text" readonly class="form-control-plaintext text-primary" value="${user.customer.balance}€">
              </div>
          </div>
          <a href="editCustomerForm?customerId=${user.customer.id}" type="button" class="btn btn-primary">Изменить данные</a>
          <a href="addMoneyForm?customerId=${user.customer.id}" type="button" class="btn btn-info">Пополнить счет</a>
        </form>
    </div>
</div>