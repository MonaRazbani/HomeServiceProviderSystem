<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register Page</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" href="/static/css/customerSignup.css">


<script>
    var clock = request.getParameter( "clock" );
    if( clock == null ) clock = "180";
    var timeout = <%="10:00"%>;
    function timer() {
        if (--timeout > 0) {
            document.forma.clock.value = timeout;
            window.setTimeout("timer()", 1000);
        } else {
            document.forma.clock.value = "Time over";
        }
    }

</script>
</head>
<body>

<div style="position: relative;">
    <div class="bg-image">
    </div>
    <div class="box" style="position:absolute">
        <div class="container">
            <div class="row justify-content-md-center">
                <form action="<%=request.getRequestURL()%>" name="forma">
                    Seconds remaining: <input type="text" name="clock" value="<%="10:00"%>" style="border:0px solid white">
                <form:form cssClass=" p-1" modelAttribute="bankCartDto"
                           enctype="multipart/form-data" action="/order/paymentWithBankCard" method="post">
                    <p class="text-danger">${error}</p>
                    <table class="table table-bordered table-striped text-dark">
                        <tr>
                            <td>
                                <form:label path="cardNumber">cardNumber :</form:label>
                            </td>
                            <td>
                                <form:input path="cardNumber" name="cardNumber"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                            </td>
                            <td>
                                <form:errors path="cardExpiration" cssClass="text-danger"/>
                            </td>
                        </tr>
                        <tr>

                            <td>
                                <form:label path="cardExpiration">cardExpiration :</form:label>
                            </td>
                            <td>
                                <form:input path="ccv2" name="ccv2"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                            </td>
                            <td>
                                <form:errors path="ccv2" cssClass="text-danger"/>
                            </td>
                        </tr>
                        <tr>

                    </table>
                    <form:button name="submitSignup">submitSignup</form:button>
                </form:form>
                </form>
            </div>
        </div>
    </div>
</div>
<script>
   timer();
</script>

</body>
</html>
