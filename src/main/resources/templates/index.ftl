<#import "lib/utils.ftl" as u>
<!DOCTYPE HTML>
<html>
<head>
    <title>Spring Freemarker</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" type="text/css" href="/css/popup.css" />
    <link rel="stylesheet" type="text/css" href="/css/team-detail.css" />
    <link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/css/bootstrap-combined.min.css" rel="stylesheet"/>
    <link href="https://cdn.datatables.net/1.10.9/css/jquery.dataTables.min.css" rel="stylesheet"/>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.9/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.rawgit.com/vast-engineering/jquery-popup-overlay/1.7.10/jquery.popupoverlay.js"></script>
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script src="/js/index.js"></script>

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
        <button onclick="updateDataTable()">Select Team </button></ br>
    </#if>
    </div>
    <div id="tablediv">


    </div>
    <table id="example">
    <tbody></tbody>
    </table>
</div>
<!-- Add content to the popup -->
<@u.teaminfopopup />
</body>
</html>