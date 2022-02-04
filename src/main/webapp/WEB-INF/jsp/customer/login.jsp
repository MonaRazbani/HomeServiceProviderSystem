<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login Page</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" href="/static/css/customerSignup.css">
</head>
<body>

<div style="position: relative;">
    <div class="bg-image">
    </div>
    <div class="box" style=" position:absolute ; ">
        <div class="container">
            <div class="row justify-content-md-center">
                <div class="btn-group mt-5 mx-5">
                    <a href="<c:url value="/customer/signup"/>" class="btn btn-outline-primary active">signup</a>
                    <a href="<c:url value="/customer/login"/>" class="btn btn-outline-primary">Login</a>
                </div>
                <form:form cssClass="p-1 my-1 mx-1" modelAttribute="customerDto"
                           enctype="multipart/form-data" action="submitLogin" method="post">
                    <p class="text-danger">${error}</p>
                    <table class="table table-bordered table-striped text-dark">

                        <tr>
                            <td>
                                <form:label path="email">email :</form:label>
                            </td>
                            <td>
                                <form:input path="email" name="email"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                            </td>
                            <td>
                                <form:errors path="email" cssClass="text-danger"/>
                            </td>
                        </tr>
                        <tr>

                        <tr>
                            <td>
                                <form:label path="password">password :</form:label>
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
                    </table>
                    <form:button name="login">login</form:button>
                </form:form>
            </div>
        </div>
    </div>
</div>
</body>
</html>