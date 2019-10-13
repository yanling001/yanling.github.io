<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: hongyao
  Date: 2019/10/7
  Time: 20:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
    <tr>
        <td><a href="<c:url value="/jsp/add.jsp" />">add</a></td>
    </tr>
    <tr>
        <form action="<c:url value="/user/find" />" method="post">
            <input type="hidden" name="method" value="find" />
            <input type="text" name="id"/>
            <input type="submit" value="find"/>
       </form>
    </tr>
    <c:forEach items="${users}" var="user">
        <tr>
            <td>id : ${user.id}</td>
            <td>age : ${user.age}</td>
            <td>name : ${user.name}</td>
            <td><a href="<c:url value="/user/delete?method=delete&id=${user.id}" />">delete</a></td>
            <td><a href="<c:url value="/user/update?method=update&id=${user.id}" />">update</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
