<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.Date" %>
<html>
<head>
    <title>File Explorer</title>
</head>
<body>
    <h1>File Explorer</h1>
    <p>Generated at: ${Time}</p>
    <p>Current Directory: ${currentPath}</p>

    <c:if test="${not empty parentPath}">
        <a href="explorer?path=${parentPath.replace('\\', '/')}">🗂 Вверх</a><br>
    </c:if>

    <table>
        <thead>
            <tr>
                <th>Файл</th>
                <th>Размер</th>
                <th>Дата</th>
            </tr>
        </thead>
        <tbody>
           <c:forEach var="file" items="${files}">
               <tr>
                   <td>
                       <c:choose>
                           <c:when test="${file.isDirectory()}">
                               <a href="explorer?path=${file.getAbsolutePath().replace('\\', '/')}">📁 ${file.getName()}</a>
                           </c:when>
                           <c:otherwise>
                              <a href="download?path=${file.getAbsolutePath().replace('\\', '/')}">📄 ${file.getName()}</a>
                           </c:otherwise>
                       </c:choose>
                   </td>
                   <td>${file.length()} B</td>
                   <td>${file.lastModified()}</td>
               </tr>
           </c:forEach>
        </tbody>
    </table>
</body>
</html>
