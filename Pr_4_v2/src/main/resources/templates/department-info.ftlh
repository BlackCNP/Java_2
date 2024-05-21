<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<h1>Деталі кафедри</h1>
<div>
    <strong>Назва:</strong> ${department.name}<br>
    <strong>Title:</strong> ${department.title}<br>
    <strong>Телефон:</strong> ${department.phone}<br>
    <strong>Аудиторія:</strong> ${department.classroom}<br>

</div>
<div>
    <a href="/departments">Повернутись до списку кафедр</a>
</div>
<!-- Відображення доданих предметів -->
 <h2>Предмети кафедри</h2>
<#if department.subjects??>
    <ul>
        <#list department.subjects as subject>
            <li>${subject.name} - ${subject.description}</li>
        </#list>
    </ul>
<#else>
    <p>Наразі до кафедри не додано жодних предметів.</p>
</#if>


<!-- <h2>Новини кафедри</h2>
<#if department.news??>
    <ul>
        <#list department.news as newsItem>
            <li>
                <strong>${newsItem.title}</strong> - ${newsItem.text} (Опубліковано: ${newsItem.publicationDate})
                <form action="/departments/${department.id}/news/delete/${newsItem.id}" method="post">
                    <button type="submit">Видалити</button>
                </form>
            </li>
        </#list>
    </ul>
<#else>
    <p>Наразі до кафедри не додано жодних новин.</p>
</#if>
-->




<!-- Форма для додавання предметів до кафедри -->
<h2>Додати предмети до кафедри</h2>
<form action="/departments/${department.id}" method="post">
    <select name="subjectIds" multiple>
        <#list allSubjects as subject>
            <option value="${subject.id}">${subject.name}</option>
        </#list>
    </select>
    <button type="submit">Додати вибрані предмети</button>
</form>

<!-- Форма для створення новини -->
<!--<<h2>Створити новину</h2>
<form action="/departments/${department.id}/news" method="post">
    <div>
        <label>Заголовок:</label>
        <input type="text" name="title" required>
    </div>
    <div>
        <label>Текст:</label>
        <textarea name="text" required></textarea>
    </div>
    <div>
        <label>Дата публікації:</label>
        <input type="date" name="publicationDate" required>
    </div>
    <button type="submit">Створити</button>
</form>
-->
<div>
    <h1>Редагування кафедри</h1>
    <form action="/departments/update" method="post">
        <input type="hidden" name="id" value="${department.id}">
        <div>
            <label>Назва кафедри:</label>
            <input type="text" name="name" value="${department.name}" required>
        </div>
        <div>
            <label>Заголовок:</label>
            <input type="text" name="title" value="${department.title}" required>
        </div>
        <div>
            <label>Телефон:</label>
            <input type="text" name="phone" value="${department.phone}" required>
        </div>
        <div>
            <label>Аудиторія:</label>
            <input type="text" name="classroom" value="${department.classroom}" required>
        </div>
        <button type="submit">Зберегти</button>



    </form>
    <a href="/departments">Повернутись до списку кафедр</a>
</div>
</body>
</html>