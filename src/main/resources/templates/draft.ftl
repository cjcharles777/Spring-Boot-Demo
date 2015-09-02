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



        $(document).ready(function(){updateDataTable();} );

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

        var dataTable,
                domTable,
                htmlTable = '<table id="example"><tbody></tbody></table>';



        function updateDataTable()
        {
            if ($.fn.DataTable.fnIsDataTable(domTable)) {
                dataTable.fnDestroy(true);
                $('#tablediv').append(htmlTable);
            }

            $('#example').DataTable(
                    {

                        "processing": true,
                        "ajax":
                        {
                            url: '/draft/retrieve/mfl/'+($("#leagueInFocus").val())+'/',
                            dataSrc: ''
                        },
                        "columns":
                                [
                                    { "data": "player.name.first", "title":"First Name" },
                                    { "data": "player.name.last", "title": "Last Name" },
                                    { "data": "player.display_position", "title": "Position" },
                                    { "data": "player.editorial_team_full_name", "title":"Team" },
                                    { "data": "adp.adp", "title":"ADP" ,  "defaultContent": 9999.99},
                                    { "data": "adp.precentageOfDrafts", "title":"Percentage of Drafts",  "defaultContent": -1 }
                                ],
                        bDestroy : true

                    } );
            domTable = document.getElementById('example');

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
<#if leagues?size != 0 >
    <select id="leagueInFocus">
        <#list leagues as league>
            <option value="${league.league_key}">${league.name}</option>
        </#list>
    </select>
    <button onclick="loadLeaguePlayerPool()">Load Player Pool </button></ br>
</#if>
</div>
<div id="tablediv">


</div>
<table id="example">
    <tbody></tbody>
</table>
</div>
</body>
</html>