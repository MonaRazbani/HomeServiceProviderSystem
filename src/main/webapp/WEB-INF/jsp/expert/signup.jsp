<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register Page</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" href="/static/css/customerSignup.css">
</head>
<body>

<div style="position: relative;">
    <div class="bg-image">
    </div>
    <div class="box" style="position:absolute">
        <div class="container">
            <div class="row justify-content-md-center">
                <div class="btn-group mt-5 mx-5">
                    <a href="<c:url value="/expert/signup"/>" class="btn btn-outline-primary active">signup</a>
                    <a href="<c:url value="/expert/login"/>" class="btn btn-outline-primary">Login</a>
                </div>
                <form:form cssClass=" p-1" modelAttribute="expertDto"
                           enctype="multipart/form-data" action="submitSignup" method="post">
                    <p class="text-danger">${error}</p>
                    <table class="table table-bordered table-striped text-dark">
                        <tr>
                            <td>
                                <form:label path="firstName">firstName :</form:label>
                            </td>
                            <td>
                                <form:input path="firstName" name="firstName"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                            </td>
                            <td>
                                <form:errors path="firstName" cssClass="text-danger"/>
                            </td>
                        </tr>
                        <tr>

                            <td>
                                <form:label path="lastName">last name :</form:label>
                            </td>
                            <td>
                                <form:input path="lastName" name="lastName"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                            </td>
                            <td>
                                <form:errors path="lastName" cssClass="text-danger"/>
                            </td>
                        </tr>
                        <tr>

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

                        <tr>
                            <td>
                                gender:
                            </td>
                            <td>
                                female <form:radiobutton path="gender" value="FEMALE"/>
                                male <form:radiobutton path="gender" value="MALE"/>
                            </td>
                        </tr>

                        <tr>

                        <tr>
                            <td>
                                <label>Upload Profile Image :</label>
                            </td>
                            <td>
                                <input type="file" id="image" name="image">
                            </td>
                        </tr>
                    </table>
                </form:form>
                <a href="/expert/dashboard" type="button"
                   class=" col-sm-6 justify-center btn btn-secondary btn-md mt-3 shadow-lg">
                    select</a>
            </div>
        </div>
    </div>
</div>


<script>
    const imageFile = document.getElementById("image");

    imageFile.onchange = function () {
        const maxAllowedSize = 300 * 1024;
        if (this.files[0].size > maxAllowedSize) {
            alert("Image File is too big!");
            this.value = "";
        }
    }
</script>
</body>
</html>
