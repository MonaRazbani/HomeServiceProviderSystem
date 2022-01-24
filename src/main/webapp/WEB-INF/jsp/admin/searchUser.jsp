<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>search user </title>
</head>
<body>
<form:form cssClass="m-5 p-5 text-center" cssStyle="width: 1200px" modelAttribute="userCategoryDto"
           action="/admin/searchUserProcess" method="post">
    <table class="table table-striped table-success table-hover">
        <tr>
            <td>
                <form:input path="firstName" name="fisrtName" placeHolder="fisrt name"/>
            </td>
        <tr>
            <td>
            </td>
            <td>
                <form:errors path="firstName" cssClass="text-danger"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:input path="lastName" name="lastName" placeHolder="last name"/>
            </td>
        <tr>
            <td>
            </td>
            <td>
                <form:errors path="lastName" cssClass="text-danger"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:input path="email" name="email" placeHolder="email"/>
            </td>
        <tr>
            <td>
            </td>
            <td>
                <form:errors path="email" cssClass="text-danger"/>
            </td>
        </tr>
        <tr>
            <td>
                roleType:
                BreakFast<form:checkbox path="roleType" value="customer"/>
                Lunch<form:checkbox path="roleType" value="expert"/>
            </td>
        <tr>
            subService:<form:select path="subService">

            <c:forEach items="${serviceServiceDtoAll}" var="subService">
                <form:option value="${subService.name}" label="${subService.name}"/>
            </c:forEach>

            </form:select>
    </tr>

        <tr>
            <td>
                <form:button name="search">Search</form:button>
            </td>
        </tr>
        <tr>
            <th>Code</th>
            <th>Name</th>
            <th>Price</th>
        </tr>
        <c:forEach items="${userDtoList}" var="userDto">
            <tr>
                <td>${userDto.firstName}</td>
                <td>${userDto.lastName}</td>
                <td>${userDto.email}</td>
            </tr>
        </c:forEach>
    </table>
</form:form>
</body>
</html>
