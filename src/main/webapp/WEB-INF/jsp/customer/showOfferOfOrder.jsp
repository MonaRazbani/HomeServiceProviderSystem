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
<form:form cssClass="p-1 my-5 mx-5" enctype="multipart/form-data" action="/customer/showOfferOfOrder" method="get" modelAttribute="offerDtosOfOrder">
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
                                <th>subService</th>
                                <th>explanation</th>
                                <th>suggestedPrice</th>
                                <th>address</th>
                            </tr>
                                <tr>
                                    <td>${orderDto.subService.name}</td>
                                    <td>${orderDto.explanation}</td>
                                    <td>${orderDto.suggestedPrice}</td>
                                    <td>${orderDto.address.address}</td>
                                </tr>
                        </table>

                        <table class="table table-striped table-light table-hover">
                            <tr>
                                <th>expertName </th>
                                <th>expertRate </th>
                                <th>suggestedPrice</th>
                                <th>startDate</th>
                                <th>suggestedDurationOfService</th>
                            </tr>
                            <c:forEach items="${offerDtosOfOrder}" var="offer">
                                <tr>
                                    <td>${offer.expert.firstName}+${offer.expert.lastName}</td>
                                    <td>${offer.expert.rateString}</td>
                                    <td>${offer.suggestedPrice}</td>
                                    <td>${offer.startDate}</td>
                                    <td>${offer.suggestedDurationOfService}</td>
                                    <td>
                                        <a href="/customer/showOfferOfOrder/${offer.identificationCode}"
                                           class="btn btn-outline-secondary btn-sm  shadow-lg">
                                            accept</a>
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
