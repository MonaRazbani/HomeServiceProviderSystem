<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>change offer status</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
          integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
    <link rel="stylesheet" href="/static/css/table.css">

</head>

<body>
<form:form cssClass="p-1 my-5 mx-5" enctype="multipart/form-data" action="/offer/changeOrderStatus" method="post">
    <div style="position: relative;">
        <div class="bg-image">
        </div>
        <div class="box" style="position:absolute">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col col-lg-12">
                        <div class="list-group">
                            <table class="table table-striped table-light table-hover ">
                                <tr>
                                    <th>customer name</th>
                                    <th>subService</th>
                                    <th>explanation</th>
                                    <th>suggestedPrice</th>
                                    <th>address</th>
                                    <th>status</th>
                                </tr>
                                <tr>
                                    <td>
                                            ${sessionScope.offerDto.order.customer.firstName }
                                            ${sessionScope.offerDto.order.customer.lastName} </td>
                                    <td>${sessionScope.offerDto.order.subService.name}</td>
                                    <td>${sessionScope.offerDto.order.explanation}</td>
                                    <td>${sessionScope.offerDto.order.suggestedPrice}</td>
                                    <td>${sessionScope.offerDto.order.address.address}</td>
                                    <td>${sessionScope.offerDto.order.status}</td>
                                </tr>
                            </table>
                            <h4>offer info</h4>
                            <table class="table table-striped table-info table-hover">
                                <tr>
                                    <th>suggestedPrice</th>
                                    <th>startDate</th>
                                    <th>suggestedDurationOfService</th>
                                </tr>
                                <tr>
                                    <td>${sessionScope.offerDto.suggestedPrice}</td>
                                    <td>${sessionScope.offerDto.startDate}</td>
                                    <td>${sessionScope.offerDto.suggestedDurationOfService}</td>
                                    <td>${sessionScope.offerDto.status}</td>
                                </tr>
                            </table>
                            WAITING_FOR_CHOOSING_EXPERT,
                            WAITING_FOR_COMING_EXPERT_TO_YOUR_PLACE,
                            STARTED,
                            DONE,
                            PAID

                            <c:if test="${sessionScope.offerDto.order.status=='WAITING_FOR_COMING_EXPERT_TO_YOUR_PLACE' }">
                                <a href="/offer/changeOrderStatus/${"STARTED"}" type="button"
                                   class=" justify-content-center col-sm-12 btn btn-secondary btn-md mb-2 shadow-lg"
                                   id="btnPay"> STARTED </a>
                            </c:if>


                            <c:if test="${sessionScope.offerDto.order.status=='STARTED' }">
                                <a href="/offer/changeOrderStatus/${"DONE"}" type="button"
                                   class=" justify-content-center col-sm-12 btn btn-secondary btn-md mb-2 shadow-lg"
                                   id="btnPay"> DONE </a>
                            </c:if>


                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</form:form>
</body>
</html>
