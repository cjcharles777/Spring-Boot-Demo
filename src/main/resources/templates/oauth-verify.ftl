<!DOCTYPE HTML>
<html>
<head>
    <title>Spring Freemarker</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" href="/css/bootstrap.min.css" />
    <link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/css/bootstrap-combined.min.css" rel="stylesheet"/>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script type="application/javascript">
        function login()
        {
            var win = window.open("${oAuthUrl}", "windowname1", 'width=800, height=600'); // Remove this please

        }
        function sendVerifyCode()
        {


            var dataFields = {  token: $("#approvalCode").val()  };//gets value by id

            $.ajax({
                url: "oauth/verify",
                type: "POST",
                data: JSON.stringify(dataFields),
                contentType: "application/json",
                success: function (data, created) {

                    alert(data);
                    //TODO: Add refresh Page
                },
                error: function (textStatus, errorThrown) {
                    alert("Error: " + textStatus + " " + errorThrown);
                }
            });
        }
    </script>
</head>
<body>
<div class="container">
 <!--<div class="control-group">
        <h2 class="muted">Employee</h2>
        <form name="employee" action="addEmployee" method="post">
            <div class="control-group">
                <label class="control-label" for="employeeId">Employee Id</label>
            </div>
            <div class="controls">
                <input type="text" name="employeeId">
            </div>
            <div class="control-group">
                <label class="control-label" for="employeeName">Employee Name</label>
            </div>
            <div class="controls">
                <input type="text" name="employeeName">
            </div>
            <div class="control-group">
                <label class="control-label" for="employeePhone">Employee Phone </label>
            </div>

            <div class="controls">
                <input type="text" name="employeePhone">
            </div>
            <div class="controls">
                <input type="submit" class="btn btn-primary">
            </div>
        </form>
    </div>
    -->
    <div>
        <button onclick="login()">GET CODE</button></ br>
        Approval code: <input type="text" name="approvalCode" id="approvalCode"><br>
        <button onclick="sendVerifyCode()">SUBMIT</button></ br>
    </div>
</div>
</body>
</html>