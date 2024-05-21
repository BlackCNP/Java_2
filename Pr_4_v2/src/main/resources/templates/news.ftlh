<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>News</title>
</head>
<body>
<h1>News</h1>

<!-- List of news -->
<#if news??>
    <ul>
        <#list news as newsItem>
            <li>
                <strong>${newsItem.title}</strong> - ${newsItem.text} (Published on: ${newsItem.publicationDate})
                <form action="/news/delete/${newsItem.id}" method="post">
                    <button type="submit">Delete</button>
                </form>
            </li>
        </#list>
    </ul>
<#else>
    <p>No news available.</p>
</#if>

<!-- Form to create a new news item -->
<h2>Create a new news item</h2>
<form action="/news" method="post">
    <div>
        <label>Title:</label>
        <input type="text" name="title" required>
    </div>
    <div>
        <label>Text:</label>
        <textarea name="text" required></textarea>
    </div>
    <div>
        <label>Publication Date:</label>
        <input type="date" name="publicationDate" required>
    </div>
    <button type="submit">Create</button>
</form>

</body>
</html>
