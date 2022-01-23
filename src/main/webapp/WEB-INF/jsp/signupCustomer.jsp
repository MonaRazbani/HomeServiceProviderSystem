<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register Page</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
<body>
<div class="btn-group mt-5 mx-5">
    <a href="<c:url value="/"/>" class="btn btn-outline-primary active">Register</a>
    <a href="<c:url value="/login"/>" class="btn btn-outline-primary">Login</a>
</div>
<form:form cssClass="p-1 my-5 mx-5" cssStyle="" modelAttribute="customer"
           enctype="multipart/form-data" action="register" method="post">
    <table class="table table-bordered table-striped text-dark">
        <tr>
            <td>
                <form:label path="fullName">Full Name :</form:label>
            </td>
            <td>
                <form:input path="fullName" name="fullName"/>
            </td>
        </tr>
        <tr>
            <td>
            </td>
            <td>
                <form:errors path="fullName" cssClass="text-danger"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="userName">UserName :</form:label>
            </td>
            <td>
                <form:input path="userName" name="userName"/>
            </td>
        </tr>
        <tr>
            <td>
            </td>
            <td>
                <form:errors path="userName" cssClass="text-danger"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="password">Password :</form:label>
            </td>
            <td>
                <form:input path="password" name="password"/>
            </td>
        </tr>
        <tr>
            <td>
            </td>
            <td>
                <form:errors path="password" cssClass="text-danger"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="emailAddress">Email :</form:label>
            </td>
            <td>
                <form:input path="emailAddress" name="emailAddress"/>
            </td>
        </tr>
        <tr>
            <td>
            </td>
            <td>
                <form:errors path="emailAddress" cssClass="text-danger"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="dateOfBirth">BirthDate :</form:label>
            </td>
            <td>
                <form:input type="date" path="dateOfBirth" name="dateOfBirth"/>
            </td>
        </tr>
        <tr>
            <td>
            </td>
            <td>
                <form:errors path="dateOfBirth" cssClass="text-danger"/>
            </td>
        </tr>
        <tr>
            <td>
                <label>Upload Profile Image :</label>
            </td>
            <td>
                <input type="file" id="image" name="image">
            </td>
        </tr>
        <tr>
            <td>
            </td>
            <td>
                <form:button name="register">Register</form:button>
            </td>
        </tr>
    </table>
</form:form>

</body>
</html>

