<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<body>
<%--Создадим кнопку и на клик поставим ссылку на метод, который мы пока не создали,
напишем, что эта кнопка только для HR персонала, в контроллере создадим метод
getInfoOnlyForHR(), и укажем его адрес как /hr_info, этот метод будет возвращать пока
несуществующий view "for_hr"--%>
<h3>Information for all employees</h3>
<br><br>
<%--Этот комментарий относится к 68 лекции. Чтобы настроить доступ к кнопке по ролям
нам необходимо добавить зависимость Spring security taglibs, а ниже мы прячем нашу кнопку
поместив ее в контейнер security:authorize, где в параметре accsess указываем какая именно
роль имеет доступ, роль указываем в одинарных кавычках./--%>
<security:authorize access="hasRole('HR')" >
<input type="button" value="Salary" onclick="window.location.href = 'hr_info'">
Only for HR staff
</security:authorize>
<br><br>
<%--Теперь по аналогии создадим кнопку только для менеджеров, точно так же определим в контроллере
новый метод для ссылки которую мы здесь передаем. Этот метод будет возвращать view "for_managers"
 Теперь создадим оба view для возврата, логики пока в них никакой не будет, они просто имитируют
 какой то возврат, в них будет просто информационное сообщение./--%>
<security:authorize access="hasRole('MANAGER')">
<input type="button" value="Performance" onclick="window.location.href = 'manager_info'">
Only for managers
</security:authorize>
</body>
</html>
