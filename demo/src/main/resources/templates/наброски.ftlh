<table th:each="${searchItem}" border="1px">
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