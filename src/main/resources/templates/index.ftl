<#import "lib/utils.ftl" as u>
<!DOCTYPE HTML>
<html>
<head>
    <title>Spring Freemarker</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" type="text/css" href="/css/popup.css" />
    <link rel="stylesheet" type="text/css" href="/css/team-detail.css" />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
    <link href="https://cdn.datatables.net/1.10.9/css/jquery.dataTables.min.css" rel="stylesheet"/>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.9/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.rawgit.com/vast-engineering/jquery-popup-overlay/1.7.10/jquery.popupoverlay.js"></script>
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script src="/js/index.js"></script>

</head>
<body>

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
<div class="panel-group" id="accordion">
    <div class="panel panel-default">
        <div class="panel-heading">
            <h4 class="panel-title">
                <a data-toggle="collapse" data-parent="#accordion" href="#matchup">
                    Next Matchup </a>
            </h4>
        </div>
        <div id="matchup" class="panel-collapse collapse in">
            <div class="panel-body">
                Test Your Might.
            </div>
        </div>
    </div>
    <div class="panel panel-default">
        <div class="panel-heading">
            <h4 class="panel-title">
                <a data-toggle="collapse" data-parent="#accordion" href="#standings">
                    Standings</a>
            </h4>
        </div>
        <div id="standings" class="panel-collapse collapse">
            <div class="panel-body">
                <table id="example">
                    <tbody></tbody>
                </table>
            </div>
        </div>
    </div>

    <div class="panel panel-default">
        <div class="panel-heading">
            <h4 class="panel-title">
                <a data-toggle="collapse" data-parent="#accordion" href="#transactions">
                    League Transactions</a>
            </h4>
        </div>
        <div id="transactions" class="panel-collapse collapse">
            <div class="panel-body">
                Test Your Might.
            </div>
        </div>
    </div>

    <div class="panel panel-default">
        <div class="panel-heading">
            <h4 class="panel-title">
                <a data-toggle="collapse" data-parent="#accordion" href="#analysis">
                    League Analysis</a>
            </h4>
        </div>
        <div id="analysis" class="panel-collapse collapse">
            <div class="panel-body">
                Test Your Might.
            </div>
        </div>
    </div>


</div>
<!-- Add content to the popup -->
<@u.teaminfopopup />
</body>
</html>