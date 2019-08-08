<%@page contentType="text/html;charset=UTF-8"
language="java" %>
<html>
    <head>
        <meta contentType="text/html; charset=utf-8" />
        <script src="http://code.jquery.com/jquery-3.4.1.js"></script>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"/>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
        <title>Servlet</title>

        <script>
           $(document).ready(function() {
               $.get("/servlet-1.0-SNAPSHOT/get_users", function(data) {
                   console.log(data);
                   var i, str = "";
                   for (i = 0; i < data.length; i++) {
                       str += data[i].id + ". " + data[i].fio + "<br>";
                   }
                   console.log(str);
                   $(".users").html(str);
               });
           });
        </script>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-md-6">
                    <div class="users">
                </div>
            </div>
        </div>
    </body>
</html>
