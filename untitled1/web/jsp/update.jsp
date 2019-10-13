<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: hongyao
  Date: 2019/10/7
  Time: 20:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
 <form action="<c:url value="/user/sumbit" />" method="post">
     <input type="hidden" name="method" value="sumbit" />
     <input type="hidden" name="id" value="${user.id}" />
     <input type="text" name="age" value="${user.age}"/>
     <input type="text" name="name" value="${user.name}" />
     <input type="submit" value="提交修改" />
 </form>
</body>
</html>
