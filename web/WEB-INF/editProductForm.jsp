
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<p class="text-primary">${info}</p>
<div class="card border-danger mb-3" style="max-width: 25rem;">
  <div class="card-header">Данные товара</div> 
    <div class="card-body">
      <h4 class="card-title">Изменить информацию</h4>
        <form action="editProduct" method="POST">
          <input type="hidden" name="productId" value="${product.id}">
          <div class="form-group">
            <label>Наименование товара</label>
            <input type="text" class="form-control" name="name" value="${product.name}">
          </div>
          <div class="form-group">
            <label>Страна производства</label>
            <input type="text" class="form-control" name="country" value="${product.country}">
          </div>
          <div class="form-group">
            <label>Стоимость</label>
            <input type="text" class="form-control" name="price" value="${product.price}">
          </div>
          <button type="submit" name="submit" class="btn btn-success">Править</button> 
          <a href="listProducts" type="button" class="btn btn-secondary">К списоку товаров</a>
        <!--<a href="index.jsp" type="button" class="btn btn-secondary">Главная</a>-->
    </form>
</div>

