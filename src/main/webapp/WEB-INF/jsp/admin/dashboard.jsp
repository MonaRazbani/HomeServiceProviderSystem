
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>admin dashboard </title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
          integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
    <link rel="stylesheet" href="/static/css/customerSignup.css">
</head>
<body>
<div style="position: relative;">
    <div class="bg-image">
    </div>
    <div class="box" style="position:absolute">
        <div class="container">
            <div class="row justify-content-md-center">
                <div class="col col-lg-12">
                    <a href="/admin/searchUser" type="button"
                       class="col-sm-12 btn btn-secondary btn-md mb-2 shadow-lg ">search user</a>
                    <a href="/admin/addSubService" type="button"
                       class=" col-sm-12 btn btn-secondary btn-md mb-2 shadow-lg"> add subService </a>
                    <a href="/admin/addServiceCategory" type="button"
                       class=" col-sm-12 btn btn-secondary btn-md mb-2 shadow-lg"> add ServiceCategory </a>
                    <button type="button" class="col-sm-12 btn btn-secondary btn-md mb-2 shadow-lg"> log in as admin
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
