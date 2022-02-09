<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> edit suggested price </title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
          integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
    <link rel="stylesheet" href="/static/css/table.css">

</head>

<body>
<form:form cssClass="p-1 my-5 mx-5" enctype="multipart/form-data" action="/offer/editOfferSuggestedDurationOfService" method="post">
<div class="box" style="position: relative;">
    <div class="bg-image">
    </div>
    <div class="box" style="position:absolute">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col col-lg-12">
                    <div class="list-group">
                        <h4> order of offer </h4>
                        <table class="table table-striped table-light table-hover">
                            <tr>
                                <th>old start date </th>
                                <th>new start date </th>
                            </tr>
                            <tr>
                                <td>${sessionScope.offerDto.suggestedPrice}</td>
                                <td>

                                    <input name="suggestedPrice" placeHolder=" new suggested price"/>
                                </td>
                        </table>
                        <input type="submit" value="submit"/>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
