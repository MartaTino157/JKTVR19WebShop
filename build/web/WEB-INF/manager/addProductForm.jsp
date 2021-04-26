
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<p class="text-primary">${info}</p>
    <div class="card border-secondary mb-3" style="max-width: 30rem;">
      <div class="card-body">
        <h4 class="card-title">Добавить товар</h4>
        <form action="createProduct" method="POST">
            <div class="form-group">
                <label for="productName">Наименование товара</label>
                <input type="text" class="form-control" id="productName" name="name" value="${name}" placeholder="Enter name">
            </div>
            <div class="form-group">
                <label for="productCountry">Страна производства</label>
                <input type="text" class="form-control" id="productCountry" name="country" value="${country}" placeholder="Enter country">
            </div>
            <div class="form-group">
                <label for="productPrice">Стоимость</label>
                <input type="text" class="form-control" id="productPrice" name="price" value="${strPrice}" placeholder="Enter price">
            </div>
<!--        <div class="form-group">
                <label for="productImage">Картинка</label><br>
                <input type="file" id="productImage" name="image" value="">
            </div>-->
            <button type="submit" name="submit" class="btn btn-primary">Добавить товар</button> 
            <a href="index.jsp" type="button" class="btn btn-secondary">Главная</a>
        </form>
      </div>
    </div>
    
