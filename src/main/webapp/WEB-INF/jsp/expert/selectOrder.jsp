<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
          integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
    <link rel="stylesheet" href="/static/css/table.css">

</head>

<body>
<form:form cssClass="p-1 my-5 mx-5" enctype="multipart/form-data" action="/expert/listOfOrders" method="post" modelAttribute="orderDtoList">
<div style="position: relative;">
    <div class="bg-image">
    </div>
    <div class="box" style="position:absolute">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col col-lg-12">
                    <div class="list-group">
                        <table class="table table-striped table-light table-hover">
                            <tr>
                                <th>customer firstName </th>
                                <th>customer lastName</th>
                                <th>subService</th>
                                <th>explanation</th>
                                <th>suggestedPrice</th>
                                <th>address</th>
                                <th>action</th>
                            </tr>
                            <c:forEach items="${orderDtoList}" var="order">
                                <tr>
                                    <td>${order.customer.firstName}</td>
                                    <td>${order.customer.lastName}</td>
                                    <td>${order.subService.name}</td>
                                    <td>${order.explanation}</td>
                                    <td>${order.suggestedPrice}</td>
                                    <td>${order.address.address}</td>
                                    <td>
                                        <a href="/expert/selectOrder/${order.identificationCode}"
                                           class="btn btn-outline-secondary btn-sm  shadow-lg">
                                                select</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
