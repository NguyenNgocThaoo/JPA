<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Edit Video</title>
</head>
<body>
<h2>Edit Video</h2>
<form action="${pageContext.request.contextPath}/admin/video/update" method="post" enctype="multipart/form-data">
    <input type="hidden" name="videoId" value="${video.videoId}">

    <label for="title">Title</label>
    <input type="text" name="title" value="${video.title}" required>

    <label for="views">Views</label>
    <input type="number" name="views" value="${video.views}" required>

    <label for="description">Description</label>
    <textarea name="description" required>${video.description}</textarea>

    <label for="category">Category</label>
    <select name="categoryid" required>
        <c:forEach var="category" items="${categories}">
            <option value="${category.categoryid}" <c:if test="${category.categoryid == video.category.categoryid}">selected</c:if>>${category.categoryname}</option>
        </c:forEach>
    </select>

    <label for="active">Active</label>
    <input type="checkbox" name="active" value="1" <c:if test="${video.active == 1}">checked</c:if>> Hoạt động

    <label for="poster">Poster</label>
    <input type="file" name="poster">
    <img src="${pageContext.request.contextPath}/upload/${video.poster}" alt="Current Poster" width="100">

    <button type="submit">Update</button>
</form>
<a href="${pageContext.request.contextPath}/admin/videos">Back to Video List</a>
</body>
</html>