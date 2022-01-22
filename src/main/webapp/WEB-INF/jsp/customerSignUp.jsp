<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>customerSignUp</title>
</head>
<body>

<form:form action="signUpProcess" method="post" modelAttribute="customerDto">
    firstName:
    <form:input path="firstName"/>
    lastName:
    <form:input path="lastName"/>
    email:
    <form:input path="email"/>
    gender:
    female<form:checkbox path="gender" value="FEMALE"/>
    male<form:checkbox path="gender" value="MALE"/>
    password:
    <form:input path="password"/>
    <input type="submit" value="Submit" />

</form:form>
</body>
</html>
