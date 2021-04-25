
<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <p class="text-primary">${info}</p>
    <div class="card border-primary mb-3" style="max-width: 25rem;">
        <div class="card-header">Заполните форму</div>
        <div class="card-body">
          <h4 class="card-title">Введите логин и пароль</h4>
          <form action="login" method="POST">
            <div class="form-group">
                <label for="exampleInputText">Логин</label>
                <input type="text" class="form-control" id="exampleInputText" name="login" value="" aria-describedby="emailHelp" placeholder="Enter login">
            </div>
            <div class="form-group">
                <label for="exampleInputPassword1">Пароль</label>
                <input type="password" class="form-control" id="exampleInputPassword1" name="password" value="" placeholder="Password">
            </div>
            <button type="submit" class="btn btn-primary">Войти</button> 
            <a href="registrationForm" class="btn btn-success">Зарегистрироваться</a>
        </form>
        </div>
    </div>


