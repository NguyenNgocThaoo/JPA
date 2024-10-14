<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Video List</title>
</head>
<body>
<h2>Video List</h2>
<a href="${pageContext.request.contextPath}/admin/video/add">Add New Video</a>
<table border="1">
    <tr>
        <th>Video ID</th>
        <th>Title</th>
        <th>Description</th>
        <th>Category</th>
        <th>Views</th>
        <th>Poster</th> <!-- Thêm cột Poster -->
        <th>Active</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="video" items="${listVideos}">
        <tr>
            <td>${video.videoId}</td>
            <td>${video.title}</td>
            <td>${video.description}</td>
            <td>${video.category.categoryname}</td>
            <td>${video.views}</td>
            <td>
                <!-- Kiểm tra và hiển thị poster -->
                <c:if test="${video.poster.substring(0, 5) != 'https'}">
                    <c:url value="/image?fname=${video.poster}" var="imgUrl"></c:url>
                </c:if>
                <c:if test="${video.poster.substring(0, 5) == 'https'}">
                   <c:url value="${video.poster}" var="imgUrl"></c:url>
                </c:if>
                <img src="${imgUrl}" alt="Video Poster" height="100" width="150"/> <!-- Hiển thị hình ảnh poster -->
            </td>
            <td>
                <c:choose>
                    <c:when test="${video.active == 1}">Hoạt động</c:when>
                    <c:otherwise>Khóa</c:otherwise>
                </c:choose>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/admin/video/edit?id=${video.videoId}">Edit</a>
                <a href="${pageContext.request.contextPath}/admin/video/delete?id=${video.videoId}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>