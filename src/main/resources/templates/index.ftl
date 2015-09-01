<!DOCTYPE HTML>
<html>
<head>
    <title>Spring Freemarker</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" href="/css/bootstrap.min.css" />
    <link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/css/bootstrap-combined.min.css" rel="stylesheet"/>
    <link href="https://cdn.datatables.net/1.10.9/css/jquery.dataTables.min.css" rel="stylesheet"/>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.9/js/jquery.dataTables.min.js"></script>
    <script type="application/javascript">

        function loadLeaguePlayerPool()
        {


            var dataFields = {  league_id: $("#leagueInFocus").val()  };//gets value by id

            $.ajax({
                url: "players/load",
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

        $(document).ready(function()
        {
            $('#example').DataTable(
                    {

                        "processing": true,
                        "ajax":
                        {
                                url: 'players/retrieve/league/'+($("#leagueInFocus").val())+'/',
                                dataSrc: ''
                        },
                        "columns":
                                [
                                    { "data": "name.first" },
                                    { "data": "name.last" },
                                    { "data": "display_position" }
                                    { "data": "display_position" }
                                ]
                    } );
        } );
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
    <#if leagues?size != 0 >
        <select id="leagueInFocus">
        <#list leagues as league>
            <option value="${league.league_key}">${league.name}</option>
        </#list>
        </select>
        <button onclick="loadLeaguePlayerPool()">Load Player Pool </button></ br>
    </#if>
    </div>
    <div>

        <table id="example" class="display" cellspacing="0" width="100%">
            <thead>
                <tr>
                    <th>Player Name Last</th>
                    <th>Player Name First</th>
                    <th>Player Display Position</th>
                </tr>
            </thead>

            <tfoot>
                <tr>
                    <th>Player Name Last</th>
                    <th>Player Name First</th>
                    <th>Player Display Position</th>
                </tr>
            </tfoot>
        </table>
    </div>
</div>
</body>
</html>