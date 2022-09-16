<!DOCTYPE html>
<html>
<body>
<%--Создадим кнопку и на клик поставим ссылку на метод, который мы пока не создали,
напишем, что эта кнопка только для HR персонала, в контроллере создадим метод
getInfoOnlyForHR(), и укажем его адрес как /hr_info, этот метод будет возвращать пока
несуществующий view "for_hr"--%>
<h3>Information for all employees</h3>
<br><br>
<input type="button" value="Salary" onclick="window.location.href = 'hr_info'">
Only for HR staff
<br><br>
<%--Теперь по аналогии создадим кнопку только для менеджеров, точно так же определим в контроллере
новый метод для ссылки которую мы здесь передаем. Этот метод будет возвращать view "for_managers"
 Теперь создадим оба view для возврата, логики пока в них никакой не будет, они просто имитируют
 какой то возврат, в них будет просто информационное сообщение./--%>
<input type="button" value="Performance" onclick="window.location.href = 'manager_info'">
Only for managers
</body>
</html>
