<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>add serviceCategory </title>
</head>
<body>
<div style="position: relative;">
    <div class="row">
        <div class="col-6-sm">
            <form:form cssClass="p-1 my-5 mx-5" modelAttribute="serviceCategoryDto"
                       enctype="multipart/form-data" action="addServiceCategoryProcess" method="post">
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
                            <form:button name="addServiceCategoryProcess">add</form:button>
                        </td>
                    </tr>
                </table>
            </form:form>
        </div>
    </div>
</div>
</body>
</html>
</body>
</html>
