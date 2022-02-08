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
<form:form cssClass="p-1 my-5 mx-5" enctype="multipart/form-data" action="/order/showOrderMenu" method="get"
           modelAttribute="commentDto">
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
                                    <th>subService</th>
                                    <th>explanation</th>
                                    <th>suggestedPrice</th>
                                    <th>address</th>
                                    <th>status</th>
                                </tr>
                                <tr>
                                    <td>${orderDto.subService.name}</td>
                                    <td>${orderDto.explanation}</td>
                                    <td>${orderDto.suggestedPrice}</td>
                                    <td>${orderDto.address.address}</td>
                                    <td>${orderDto.status}</td>
                                </tr>
                            </table>
                            <h2>detail of order</h2>
                            <table class="table table-striped table-info table-hover">
                                <tr>
                                    <th>expertName</th>
                                    <th>expertRate</th>
                                    <th>suggestedPrice</th>
                                    <th>startDate</th>
                                    <th>suggestedDurationOfService</th>
                                </tr>
                                <tr>
                                    <td>${acceptedOfferOfOrder.expert.firstName}+${acceptedOfferOfOrder.expert.lastName}</td>
                                    <td>${acceptedOfferOfOrder.expert.rateString}</td>
                                    <td>${acceptedOfferOfOrder.suggestedPrice}</td>
                                    <td>${acceptedOfferOfOrder.startDate}</td>
                                    <td>${acceptedOfferOfOrder.suggestedDurationOfService}</td>
                                </tr>
                            </table>


                            <c:if test="${orderDto.status=='DONE'}">

                                <c:if test="${orderDto.comment!=null}">
                                    <a href="/comment/submitComment" type="button"
                                       class=" justify-content-center col-sm-6 btn btn-secondary btn-md mb-2 shadow-lg"
                                       id="btnPay"> comment </a>
                                </c:if>
                                <a href="/order/payOrder" type="button"
                                   class=" justify-content-center col-sm-6 btn btn-secondary btn-md mb-2 shadow-lg"
                                   id="btnPay"> Pay </a>

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
