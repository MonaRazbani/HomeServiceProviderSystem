<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>customerSignUp</title>
</head>
<body>

<form:form action="/customerSignUp" modelAttribute="customerDto" >
    firstName:
    <form:input path="firstName"/>
    lastName:
    <form:input path="lastName"/>
    email:
    <form:input path="email"/>
    gender:
    female<form:checkbox path="gender" value="FEMALE"/>
    male<form:checkbox path="gender" value="MALE"/>
</form:form>
    <form:form action="/customerSignUp" modelAtterbute="password">
        password:
        <form:input path="password"/>
    </form:form>
    <input type="submit" value="Submit"/>
    <a href="/signUpProcess">singUp</a>
</body>
</html>
