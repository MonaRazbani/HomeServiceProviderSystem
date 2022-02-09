<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> expert offers </title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
          integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
    <link rel="stylesheet" href="/static/css/tableExpertOffers.css">

</head>

<body>
<form:form cssClass="p-1 my-5 mx-5" enctype="multipart/form-data" action="/offer/expertOffers" method="get"
           modelAttribute="expertOffers">
<div style="position: relative;">
    <div class="bg-image">
    </div>
    <div class="box" style="position:absolute">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col col-lg-12">
                    <table class="table table-sm table-striped ">
                        <tr>
                            <th>order of offer</th>
                            <th>offer</th>
                        </tr>
                        <c:forEach items="${expertOffers}" var="offer">
                            <tr>
                                <td>
                                    <table class="table table-sm">
                                        <tr>
                                            <th>customerName</th>
                                            <th>subService</th>
                                            <th>explanation</th>
                                            <th>suggestedPrice</th>
                                            <th>address</th>
                                            <th>action</th>
                                        </tr>

                                        <tr>
                                            <td>${offer.order.customer.firstName} ${offer.order.customer.lastName}</td>
                                            <td>${offer.order.subService.name}</td>
                                            <td>${offer.order.explanation}</td>
                                            <td>${offer.order.suggestedPrice}</td>
                                            <td>${offer.order.address.address}</td>
                                        </tr>
                                    </table>
                                </td>
                                <td>
                                    <table class="table table-sm">
                                        <tr>
                                            <th>suggestedPrice</th>
                                            <th>startDate</th>
                                            <th>suggestedDurationOfService</th>
                                            <th>action</th>
                                        </tr>
                                        <tr>

                                            <td>${offer.suggestedPrice}</td>
                                            <td>${offer.startDate}</td>
                                            <td>${offer.suggestedDurationOfService}</td>
                                            <td>
                                                <a href="/offer/expertOffers/${offer.identificationCode}"
                                                   class="btn btn-outline-secondary btn-sm  shadow-lg">
                                                    select</a>
                                            </td>
                                        </tr>
                                    </table>
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
</body>
</html>
