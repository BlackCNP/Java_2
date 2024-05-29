<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <title>departments</title>
    <style>

        /* dark-theme.css */
        body {
            background-color: #121212;
            color: #e0e0e0;
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 20px;
        }

        h1, h2 {
            color: #bb86fc;
        }

        form {
            background-color: #1e1e1e;
            border: 1px solid #333;
            border-radius: 5px;
            padding: 20px;
            margin-bottom: 20px;
        }

        form div {
            margin-bottom: 15px;
        }

        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }

        input[type="text"] {
            width: 100%;
            padding: 10px;
            border: 1px solid #333;
            border-radius: 5px;
            background-color: #2c2c2c;
            color: #e0e0e0;
        }

        button,
        input[type="submit"] {
            background-color: #bb86fc;
            color: #121212;
            padding: 10px 15px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            text-align: center;
        }

        button:hover,
        input[type="submit"]:hover {
            background-color: #9a67ea;
        }

        button a {
            color: #121212;
            text-decoration: none;
        }

        button a:hover {
            text-decoration: underline;
        }

        ul {
            list-style-type: none;
            padding: 0;
        }

        li {
            background-color: #1e1e1e;
            border: 1px solid #333;
            border-radius: 5px;
            margin-bottom: 10px;
            padding: 10px;
        }

        a {
            color: #bb86fc;
            text-decoration: none;
        }

        a:hover {
            text-decoration: underline;
        }








    </style>
























</head>
<body>
<h1>Список Кафедр</h1>
<!-- Відображення списку кафедр -->
<ul>
    <#list departments as department>
        <li>
            Кафедра: <a href="/departments/${department.id}">${department.name}</a> (Заголовок: ${department.title}, Телефон: ${department.phone}, Аудиторія: ${department.classroom})
            <form action="/departments/delete/${department.id}" method="post" style="display:inline;">
                <input type="submit" value="Видалити">

            </form>
        </li>
    </#list>
</ul>
<!-- Форма для додавання нової кафедри -->
<h2>Додати нову кафедру</h2>
<form action="/departments/create" method="post" accept-charset="UTF-8">
    <div>
        <label>Назва кафедри:</label>
        <input type="text" name="name" required>
    </div>
    <div>
        <label>Заголовок:</label>
        <input type="text" name="title" required>
    </div>
    <div>
        <label>Телефон:</label>
        <input type="text" name="phone" required>
    </div>
    <div>
        <label>Аудиторія:</label>
        <input type="text" name="classroom" required>
    </div>
    <button type="submit">Додати</button>
    <br>


</form>
<button><a href="/subjects">Додати Предмет</a> </button>
</body>
</html>