<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register Page</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" href="/static/css/signup.css">
</head>
<body>
<div class="btn-group mt-5 mx-5">
    <a href="<c:url value="/expert/signup"/>" class="btn btn-outline-primary active">signup</a>
    <a href="<c:url value="/expert/login"/>" class="btn btn-outline-primary">Login</a>
</div>
<div style="position: relative;">
    <div class="bg-image">
    </div>
    <div class="box" style="position:absolute">
        <div class="row">
            <div class="col-6-sm">
                <form:form cssClass="p-1 my-5 mx-5" cssStyle="position: absolute" modelAttribute="expertDto"
                           enctype="multipart/form-data" action="signup" method="post">
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
                                <form:label path="lastName">firstName :</form:label>
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
                                <label>Upload Profile Image :</label>
                            </td>
                            <td>
                                <input type="file" id="image" name="image">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <form:button name="signup">signup</form:button>
                            </td>
                        </tr>
                    </table>
                </form:form>

            </div>
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
