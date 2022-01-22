<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 1/15/2022
  Time: 3:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
          integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
    <title>home</title>
</head>
<body>
<nav class="navbar navbar-light bg-light">
    <a class="navbar-brand">Home service provider</a>
    <form class="form-inline">
        <li class="nav-item">
            <a class="nav-link" href="#">Features</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="#">Features</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="#">Features</a>
        </li>
        <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
    </form>
</nav>
<div class="container">
    <div class="row">
        <div class="col text-center">
            <div>
                <%--<form:form action="customerSignUp">
                    <a class="btn btn-outline-primary" href="/customerSignUp" role="button">signup customer</a>
                </form:form>--%>
                    <a class="btn btn-outline-primary" href="/customerSignUp" role="button">signup customer</a>

                <%-- <form action="/customer/customerSignUp">
                        <button class="btn btn-success" type=submit><p>signUp customer</p></button>
                    </form>--%>
            </div>
            <div>
                <a class="btn btn-outline-primary" href="/expertSignIn" role="button">Link</a>
            </div>
            <div>
                <a class="btn btn-outline-primary" href="/managerLogin" role="button">Link</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>
