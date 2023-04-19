<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Organizer</title>
    <link rel="stylesheet" type="text/css" href="/css/style.css"/>
</head>
<body>
<h1>Organizer</h1>
<h3>Добавить товар</h3>
<form action="/add" method="post">
    <table border="1px">
        <tr>
            <td>
                <label>Название</label>
                <input type="text" name="name">
            </td>
            <td>
                <label>Сток годности</label>
                <input type="date" name="date">
            </td>
            <td>
                <label>Вес</label>
                <input type="float" name="weight">
            </td>
            <td>
                <label>Комментарий</label>
                <input type="text" name="comment">
            </td>

            <td>
                <input type="submit" value="Добавить">
            </td>

        </tr>

    </table>
</form>
<form action="/search" method="post">
    <label>ID</label>
    <input type="number" name="id">
    <input type="submit" value="Найти">
</td>

</form>
<br>
<h2>Task list(item count : ${itemSize})</h2>
<table border="1px">
    <tr>
        <td>Id</td>
        <td>Название</td>
        <td>Срок годности</td>
        <td>Вес</td>
        <td>Комментарий</td>
        <td>Удалить</td>
    </tr>
    <#list itemList as item>
        <tr>
            <td>${item.id}</td>
            <td>${item.name}</td>
            <td>${item.date}</td>
            <td>${item.weight}</td>
            <td>${item.comment}</td>
            <td><a href="${'/delete/'+item.id}"">Удалить</a></td>
        </tr>
    </#list>
</table>
</body>
</html>