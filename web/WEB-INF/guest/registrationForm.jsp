
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<p class="text-primary">${info}</p>
<div class="card border-success mb-3" style="max-width: 45rem;">
  <div class="card-header">Новый покупатель</div> 
    <form action="registration" method="POST">
        <div class="row">
            <div class="col-md-6">
                <div class="card-body">
                  <h4 class="card-title">Ввод личных данных</h4>
                  <div class="form-group">
                    <label for="firstname">Имя</label>
                    <input type="text" class="form-control" id="firstname" name="firstname" value="${firstname}" placeholder="Your firstname">
                  </div>
                  <div class="form-group">
                    <label for="lastname">Фамилия</label>
                    <input type="text" class="form-control" id="lastname" name="lastname" value="${lastname}" placeholder="Your lastname">
                  </div>
                  <div class="form-group">
                    <label for="phone">Номер телефона</label>
                    <input type="text" class="form-control" id="phone" name="phone" value="${phone}" placeholder="Your phone">
                  </div>
                  <div class="form-group">
                    <label for="balance">Баланс</label>
                    <input type="text" class="form-control" id="balance" name="balance" value="${strBalance}" placeholder="Your balance">
                  </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="card-body">
                  <h4 class="card-title">Регистрация пользователя</h4>
                    <div class="form-group">
                        <label for="login">Логин</label>
                        <input type="text" class="form-control" id="login" name="login" value="${login}"  placeholder="Your login">
                    </div>
                    <div class="form-group">
                        <label for="password">Пароль</label>
                        <input type="password" class="form-control" id="password" name="password" value="${password}" placeholder="Password">
                    </div>
                    <button type="submit" name="submit" class="btn btn-primary">Добавить покупателя</button> 
                    <a href="index.jsp" type="button" class="btn btn-secondary">Главная</a>
                </div>
            </div>
        </div>
    </form>
</div>


