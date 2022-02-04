<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form:form cssClass="p-1 my-5 mx-5" enctype="multipart/form-data" action="/offer/submitOffer" method="post" modelAttribute="offerDto">
<div style="position: relative;">
    <div class="bg-image">
    </div>
    <div class="box" style="position:absolute">
        <div class="container">
            <div class="row justify-content-md-center">
                <div class="col col-lg-12">
                    <div class="list-group">
                        <p class="text-danger">${error}</p>
                        <table class="table table-bordered table-striped text-dark">
                            <tr>
                                <td>
                                    <form:label path="suggestedPrice">suggestedPrice:</form:label>
                                </td>
                                <td>
                                    <form:input path="suggestedPrice" name="suggestedPrice"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                </td>
                                <td>
                                    <form:errors path="suggestedPrice" cssClass="text-danger"/>
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <form:label path="startDate">startDate: HH:MM </form:label>
                                </td>
                                <td>
                                   <fmt:formatDate value="${offerDto.startDate}" var="dateString" pattern="HH:mm" />
                                    <form:input path="startDate" value="${dateString}"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                </td>
                                <td>
                                    <form:errors path="startDate" cssClass="text-danger"/>
                                </td>
                            </tr>

                            <tr>
                                <td>
                                    <form:label path="suggestedDurationOfService">suggestedDurationOfService</form:label>
                                </td>
                                <td>
                                    <form:input path="suggestedDurationOfService" name="suggestedDurationOfService"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                </td>
                                <td>
                                    <form:errors path="suggestedDurationOfService" cssClass="text-danger"/>
                                </td>
                            </tr>
                        </table>
                        <form:button name="submit">next</form:button>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
\