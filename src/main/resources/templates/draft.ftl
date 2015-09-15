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
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script type="application/javascript">



        $(document).ready(function(){
            $('#myTabs').tab();
            updateDataTable();
        } );

        function loadLeaguePlayerPool()
        {


            var dataFields = {  league_id: $("#leagueInFocus").val()  };//gets value by id

            $.ajax({
                url: "data/load",
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
                htmlTable = '<table id="adp-table"><tbody></tbody></table>';



        function updateDataTable()
        {
            if ($.fn.DataTable.fnIsDataTable(domTable)) {
                dataTable.fnDestroy(true);
                $('#tablediv').append(htmlTable);
            }

            $('#adp-table').DataTable(
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
                                    { "data": "player.status", "title":"Status" },
                                    { "data": "adp.adp", "title":"ADP" ,  "defaultContent": 9999.99},
                                    { "data": "adp.minPick", "title":"Min Pick" ,  "defaultContent": -1},
                                    { "data": "adp.maxPick", "title":"Max Pick" ,  "defaultContent": 9999.99},
                                    { "data": "adp.precentageOfDrafts", "title":"Percentage of Drafts",  "defaultContent": -1 }
                                ],
                        bDestroy : true

                    } );
            domTable = document.getElementById('adp-table');

            var dataFields = {  league_id: $("#leagueInFocus").val()  };//gets value by id

            $.ajax({
                url: '/draft/retrieve/results/past/'+($("#leagueInFocus").val())+'/',
                type: "GET",
                data: JSON.stringify(dataFields),
                contentType: "application/json",
                success: function (data, created) {

                    printDraftList(data);
                    //TODO: Add refresh Page
                },
                error: function (textStatus, errorThrown) {
                    alert("Error: " + textStatus + " " + errorThrown);
                }
            });

        }
       function printDraftList(json_example)
       {
           var oldTable = document.getElementById('last-years-draft'),
                   newTable = oldTable.cloneNode(true);
           var tr =document.createElement('tr');
           var round = 1;
           for(var i = 0; i < json_example.length; i++)
           {
               if (json_example[i].round * 1 != round)
               {
                   newTable.appendChild(tr);
                   round++;
                   tr = document.createElement('tr');
               }

                    var td = document.createElement('td');
                    var img = document.createElement('img');
                    img.setAttribute('src', json_example[i].team.team_logos.team_logo.url)
                    td.appendChild(img);

                   td.appendChild(document.createTextNode(json_example[i].player.name.full));your
                   tr.appendChild(td);





           }

           oldTable.parentNode.replaceChild(newTable, oldTable);
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
    <ul id="myTabs" class="nav nav-tabs" data-tabs="tabs">
        <li class="active"><a href="#adp-draft-panel" data-toggle="tab">ADP</a></li>
        <li><a href="#last-years-draft-panel" data-toggle="tab">Last Year</a></li>
        <li><a href="#" data-toggle="tab">Best Draft</a></li>
        <li><a href="#" data-toggle="tab">Current Draft</a></li>
    </ul>
    <div class="tab-content">
        <div role="tabpanel" class="tab-pane active" id="adp-draft-panel">
            <div id="tablediv">
                <table id="adp-table"><tbody></tbody></table>
            </div>
        </div>
        <div role="tabpanel" class="tab-pane" id="last-years-draft-panel">
            <table id="last-years-draft"></table>
        </div>
        <div role="tabpanel" class="tab-pane" id="best-draft-potential-panel">...</div>
        <div role="tabpanel" class="tab-pane" id="current-draft-panel">...</div>
    </div>


</div>
</body>
</html>