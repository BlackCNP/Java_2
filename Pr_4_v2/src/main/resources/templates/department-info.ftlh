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
    <strong>Аудиторія:</strong> ${department.classroom}
</div>
<div>
    <a href="/departments">Повернутись до списку кафедр</a>
</div>
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
</body>
</html>