<#macro page>
<!DOCTYPE HTML>
<html>
<head>
    <title>Fantasy Football Assistant Coach</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" type="text/css" href="/css/popup.css" />
    <link rel="stylesheet" type="text/css" href="/css/team-detail.css" />
    <link rel="stylesheet" type="text/css" href="/css/index.css" />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
    <link href="https://cdn.datatables.net/1.10.9/css/jquery.dataTables.min.css" rel="stylesheet"/>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.9/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.rawgit.com/vast-engineering/jquery-popup-overlay/1.7.10/jquery.popupoverlay.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.0.3/handlebars.min.js"></script>
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script src="/js/const.js"></script>
    <script src="/js/common-core.js"></script>

</head>
<body>


<#-- This processes the enclosed content:  -->
    <#nested>
</body>
</html>
</#macro>

<#macro teaminfopopup>
    <div id="team_detail_popup">

       <div id="team_detail_name"></div>
        <section id="team_detail_roster_section">
            <div id="team_detail_roster">
            </div>
            <div id="team_detail_roster_stats">
                <div id="pointDonutchart" style="width: 900px; height: 500px;"></div>
                <div id="playerDonutchart" style="width: 900px; height: 500px;"></div>
            </div>

        </section>
        <div style="clear: both"/>

        <section id="team_detail_points_section">
            <div id="team_points_details">
                <div id="teamSeasonPtsChart" style="width: 900px; height: 500px;"></div>
            </div>
        </section>
        <!-- Add an optional button to close the popup -->
        <button class="team_detail_popup">Close</button>

    </div>
</#macro>