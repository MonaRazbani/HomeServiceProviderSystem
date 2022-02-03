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
                                <form:input cssClass="form-control" path="name" name="name"/>
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
                                <form:input cssClass="form-control" path="baseCost" name="baseCost"/>
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
                                <form:textarea cssClass="form-control" rows="3" id="comment" path="explanation" name="address.address"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <form:errors path="explanation" cssClass="text-danger"/>
                            </td>
                        </tr>
                        <tr>
                            <div class="form-group">
                                <label for="sel1" style="font-family:'Segoe UI' ;font-size: large">select
                                    ServiceCategory:</label>
                                <form:select path="serviceCategoryName" cssClass="form-control" id="sel1">
                                <c:forEach items="${serviceCategoryNameAll}" var="categoryService">
                                    <form:option value="${categoryService}" label="${categoryService}"/>
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
