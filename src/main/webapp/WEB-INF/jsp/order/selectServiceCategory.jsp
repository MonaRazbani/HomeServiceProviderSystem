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
<form:form cssClass="p-1 my-5 mx-5" enctype="multipart/form-data" action="/order/listOfServiceCategory" method="post">
<p class="text-danger">${error}</p>
<div style="position: relative;">
    <div class="bg-image">
    </div>
    <div class="box" style="position:absolute">
        <div class="container">
            <div class="row justify-content-md-center">
                <div class="col col-lg-12">
                    <div class="list-group">
                        <c:forEach items="${categoryServiceAll}" var="categoryService">
                            <a href="/order/selectServiceCategory/${categoryService}"
                               class="list-group-item list-group-item-action">
                                    ${categoryService}</a>
                        </c:forEach>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
