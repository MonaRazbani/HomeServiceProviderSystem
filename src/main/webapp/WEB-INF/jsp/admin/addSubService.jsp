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
<div style="position: relative;">
        <div class="row">
            <div class="col-6-sm">
                <form:form cssClass="p-1 my-5 mx-5" modelAttribute="subServiceDto"
                           enctype="multipart/form-data" action="addSubServiceProcess" method="post">
                    <p class="text-danger">${error}</p>
                    <table class="table table-bordered table-striped text-dark">
                        <tr>
                            <td>
                                <form:label path="name">name :</form:label>
                            </td>
                            <td>
                                <form:input path="name" name="name"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <form:errors path="name" cssClass="text-danger"/>
                            </td>
                        </tr>

                        <tr>
                            <td>
                                <form:label path="baseCost">baseCost :</form:label>
                            </td>
                            <td>
                                <form:input path="baseCost" name="baseCost"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <form:errors path="baseCost" cssClass="text-danger"/>
                            </td>
                        </tr>

                        <tr>
                            <td>
                                <form:label path="explanation">explanation :</form:label>
                            </td>
                            <td>
                                <form:input path="explanation" name="explanation"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <form:errors path="explanation" cssClass="text-danger"/>
                            </td>
                        </tr>
                        <tr>
                            serviceCategory:
                            <form:select path="serviceCategoryName">

                            <c:forEach items="${serviceCategoryNameAll}" var="serviceCategory">
                                <form:option value="${serviceCategory}" label="${serviceCategory}"/>
                            </c:forEach>

                        </form:select>
                        </tr>

                        <tr>
                            <td>
                            <form:button name="addSubServiceProcess">add</form:button>
                            </td>
                        </tr>
                    </table>
                </form:form>
            </div>
        </div>
    </div>
</body>
</html>
