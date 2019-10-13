<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: hongyao
  Date: 2019/10/7
  Time: 20:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="<c:url value="/user/add" />" method="post" >
    <input type="hidden" name="method" value="add" />
    age : <input type="text" name="age" />
    name : <input type="text" name="name"  />
    <input type="submit" value="添加用户" />
</form>
</body>
</html>
