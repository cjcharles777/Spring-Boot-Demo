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
    <script src="/js/trade-analysis.js"></script>

</head>
<body>
<div>

        <div>
        <#if leagues?size != 0 >
            <select id="leagueInFocus">
                <#list leagues as league>
                    <option value="${league.league_key}">${league.name}</option>
                </#list>
            </select>
            <button onclick="loadLeagueData()">Select League </button></ br>
        </#if>
        </div>

</div>
<div>

    <select id="playersOnTeam">

    </select>
    <button onclick="loadLeagueData()">Select League </button></ br>

</div>
</body>
</html>