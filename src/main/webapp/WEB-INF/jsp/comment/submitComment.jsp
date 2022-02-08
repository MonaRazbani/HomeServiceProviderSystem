<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 2/8/2022
  Time: 2:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>submitComment</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
          integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
    <link rel="stylesheet" href="/static/css/table.css">

</head>

<body>
<form:form cssClass="p-1 my-5 mx-5" enctype="multipart/form-data" action="/comment/submitComment" method="post"
           modelAttribute="commentDto">
<div style="position: relative;">
    <div class="bg-image">
    </div>
    <div class="box" style="position:absolute">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col col-lg-12">
                    <div class="list-group">
                        <table class="table table-bordered table-striped text-dark">
                            <tr>
                                <td>
                                    <form:label path="rate">rate:</form:label>
                                </td>
                                <td>
                                    <form:input path="rate" name="rate"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                </td>
                                <td>
                                    <form:errors path="rate" cssClass="text-danger"/>
                                </td>
                            </tr>
                            <tr>

                                <td>
                                    <form:label for="comment" path="comment">comment :</form:label>
                                </td>
                                <td>
                                    <form:textarea cssClass="form-control" rows="5" id="comment" path="comment"
                                                   name="comment"/>
                                </td>
                            </tr>
                        </table>
                        <form:button name="submit">submit</form:button>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
