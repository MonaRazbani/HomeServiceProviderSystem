<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>submitOrder</title>
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
                <form:form cssClass=" p-1" modelAttribute="orderDto"
                           enctype="multipart/form-data" action="/order/submitOrder" method="post">
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
                                <form:label  for="explanation" path="explanation">explanation :</form:label>
                            </td>
                            <td>
                                <form:textarea cssClass="form-control" rows="5" id="comment" path="explanation" name="explanation"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                            </td>
                            <td>
                                <form:errors path="explanation" cssClass="text-danger"/>
                            </td>
                        </tr>
                        <tr>

                            <td>
                                <form:label  for="address" path="address.address">explanation :</form:label>
                            </td>
                            <td>
                                <form:textarea cssClass="form-control" rows="5" id="comment" path="address.address" name="address.address"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                            </td>
                            <td>
                                <form:errors path="address.address" cssClass="text-danger"/>
                            </td>
                        </tr>
                        <tr>
                    </table>
                    <form:button name="submit">next</form:button>
                </form:form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
