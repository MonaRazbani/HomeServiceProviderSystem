
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<!doctype html>
<html lang="en">
<head>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
    <link rel="stylesheet" href="/static/css/homePage.css">
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light"class="sticky-top" class="shadow-lg">
    <a class="navbar-brand" href="#">Home Service Provider</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Link</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Dropdown
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="#">Action</a>
                    <a class="dropdown-item" href="#">Another action</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="#">Something else here</a>
                </div>
            </li>
            <li class="nav-item">
                <a class="nav-link disabled" href="#">Disabled</a>
            </li>
        </ul>
        <form class="form-inline my-2 my-lg-0">
            <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
        </form>
    </div>
</nav>


<div style="position: relative;">
    <img src="/static/image/home.jpg" class="img-fluid" alt="Responsive image">
    <div class="centered">
        <div class="row">
            <div class="col-sm-6">
                <div class="col-sm-12"style="font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;" >
                    <h1>Home Service Provider</h1>
                    <a href="/customer/signup" type="button" class="col-sm-12 btn btn-secondary btn-md mb-2 shadow-lg ">log in as customer</a>
                    <a href="/expert/signup" type="button" class=" col-sm-12 btn btn-secondary btn-md mb-2 shadow-lg"> log in as expert </a>
                    <button type="button" class="col-sm-12 btn btn-secondary btn-md mb-2 shadow-lg"> log in as admin </button>
                </div>
            </div>
        </div>
    </div>
</div>


</div>
</body>
</html>
