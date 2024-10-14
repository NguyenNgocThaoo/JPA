<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Add Video</title>
</head>
<body>
<h2>Add Video</h2>
<form action="${pageContext.request.contextPath}/admin/video/insert" method="post" enctype="multipart/form-data">
    
    <label for="videoId">Video ID</label>
    <input type="text" name="videoId" required>

    <label for="title">Title</label>
    <input type="text" name="title" required>

    <label for="views">Views</label>
    <input type="number" name="views" required>

    <label for="description">Description</label>
    <textarea name="description" required></textarea>

    <label for="category">Category</label>
    <select name="categoryid" required>
        <c:forEach var="category" items="${categories}">
            <option value="${category.categoryid}">${category.categoryname}</option>
        </c:forEach>
    </select>

    <label for="active">Active</label>
    <input type="checkbox" name="active" value="1"> Hoạt động

    <label for="poster">Poster</label>
    <input type="file" name="poster">

    <button type="submit">Save</button>
</form>
<a href="${pageContext.request.contextPath}/admin/videos">Back to Video List</a>
</body>
</html>