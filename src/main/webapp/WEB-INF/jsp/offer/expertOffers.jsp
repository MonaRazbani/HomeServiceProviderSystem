<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
          integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
    <link rel="stylesheet" href="/static/css/selection.css">

</head>

<body>
<form:form cssClass="p-1 my-5 mx-5" enctype="multipart/form-data" action="/offer/expertOffer" method="get" modelAttribute="expertOffers">
<div style="position: relative;">
    <div class="bg-image">
    </div>
    <div class="box" style="position:absolute">
        <div class="container">
            <div class="row justify-content-md-center">
                <div class="col col-lg-12">
                    <div class="list-group">
                        <table class="table table-striped table-success table-hover">
                            <tr>
                                <th>order </th>
                                <th>suggestedPrice</th>
                                <th>subService</th>
                                <th>suggestedDurationOfService</th>
                                <th>startDate</th>
                            </tr>
                            <c:forEach items="${expertOffers}" var="offer">
                                <tr>
                                    <td>${offerDto.order.explanation}</td>
                                    <td>${offer.suggestedPrice}</td>
                                    <td>${offer.suggestedDurationOfService}</td>
                                    <td>${offer.startDate}</td>
                                    <td>
                                        <a href="/expert/selectOffer/${offer.identificationCode}"
                                           class="list-group-item list-group-item-action">
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
