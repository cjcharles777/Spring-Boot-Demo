var currentLeague ="";
var standingsDataTable,
    standingsDomTable,
    standingsHtmlTable = '<table id="example"><tbody></tbody></table>';
var standingsTable;


$(document).ready(function()
{

    initializePopups();
    loadLeagueData();

});

google.load("visualization", "1", {packages:["corechart"]});

function loadLeagueData()
{
    loadLeagueInfo();
    updateTransactionsDataTable();
    loadLeagueAnalysis();
}

function loadLeagueInfo()
{


   // $.ajax({
  //      url: "players/load/league/" + $("#leagueInFocus").val() +"/",
  //      type: "GET",
  //      contentType: "application/json"
 //   });


    $.ajax({
        url: "data/league/" + $("#leagueInFocus").val() +"/",
        type: "GET",
        contentType: "application/json",
        success: function (data, created) {

           // alert(data);
            currentLeague = data;
            loadLeagueScoreboard();
            updateStandingsDataTable();
        },
        error: function (textStatus, errorThrown) {
            alert("Error: " + textStatus + " " + errorThrown);
        }
    });
}
function loadLeagueAnalysis()
{




    $.ajax({
        url: "data/analysis/league/" + $("#leagueInFocus").val() +"/",
        type: "GET",
        contentType: "application/json",
        success: function (data, created) {

            // alert(data);
            createAvgPointAnalysisChart(data);
            createPlayerPerformanceTable(data);

        },
        error: function (textStatus, errorThrown) {
            alert("Error: " + textStatus + " " + errorThrown);
        }
    });
}

function loadLeagueScoreboard()
{
    $.ajax({
        url: "/data/league/scoreboard/" + $("#leagueInFocus").val() +"/"+currentLeague.current_week+"/",
        type: "GET",
        contentType: "application/json",
        success: function (data, created) {

            // alert(data);
            //currentLeague = data;

            var source = $('#template').html();
            var template = Handlebars.compile(source);
            var html = template(data);
            $('#matchupBody').html(html);
        },
        error: function (textStatus, errorThrown) {
            alert("Error: " + textStatus + " " + errorThrown);
        }
    });
}



function updateStandingsDataTable()
{

    standingsTable =  $('#example').DataTable(
        {

            "processing": true,
            "select": true,
            "ajax":
            {
                url: 'team/retrieve/league/'+($("#leagueInFocus").val())+'/',
                dataSrc: ''
            },
            "columns":
                [
                    { "data": "standings.rank", "title":"Rank" },
                    { "data": "name", "title":"Team Name" },
                    { "data": "standings.outcome_totals.wins", "title": "Wins" },
                    { "data": "standings.outcome_totals.losses", "title": "Losses" },
                    { "data": "standings.outcome_totals.ties", "title":"Ties" },
                    { "data": "standings.outcome_totals.percentage", "title":"Percentage" },
                    { "data": "standings.points_for", "title":"Points For" },
                    { "data": "standings.points_against", "title":"Points Against" }
                ],
            bDestroy : true

        });


    $('#example tbody').on('click', 'tr', function () {
        var data = standingsTable.row( this ).data();
        retrieveTeamInfo(data);
        retrieveTeamPoints(data);
        showTeamInfoPopup();

    } );
    standingsDomTable = document.getElementById('example');

}

var playerPerformanceDataTable,
    playerPerformanceDomTable,
    playerPerformanceHtmlTable = '<table id="player-performance-table" class="row-border"><tbody></tbody></table>';
var playerPerformanceTable;
function createPlayerPerformanceTable(data)
{
    playerPerformanceTable =  $('#player-performance-table').DataTable(
        {

            "processing": true,
            "select": true,
            "data": data.playerPerformanceList,
            "columns":
                [
                    { "data": "player.name.full", "title":"Player" },
                    { "data": "team.name", "title":"Team Name" },
                    { "data": "points", "title": "Points" },
                    { "data": "effectivePoints", "title": "Effective Points" },
                    { "data": "noneffectivePoints", "title": "Non-Effective Points" },
                    { "data": null, "title": "Effective Difference",  "render": function (row) {
                        return row.effectivePoints - row.noneffectivePoints;
                        } }

                ],
            bDestroy : true

        } );

}
var transactionsDataTable,
    transactionsDomTable,
    transactionsHtmlTable = '<table id="league-transactions-table" class="row-border"><tbody></tbody></table>';
var transactionsTable;

function updateTransactionsDataTable()
{


    transactionsTable =  $('#league-transactions-table').DataTable(
        {

            "processing": true,
            "select": true,
            "ajax":
            {
                url: 'data/analysis/league/transactions/'+($("#leagueInFocus").val())+'/',
                dataSrc: ''
            },
            "columns":
                [
                    { "data": "timestamp", "title":"Time", "render" :  function ( data, type, full, meta ) {return timeConverter(data)}},
                    { "data": "type", "title": "Type" },
                    { "data": "status", "title":"Status" },
                    { "data": "players", "title": "Players",
                        "render": function ( data, type, full, meta ) {
                            var display = "";
                            if(data != null) {
                                for (x in data.player) {
                                    var player = data.player[x];
                                    display = display.concat(prepareTransactionPlayerOutput(player));
                                }
                                return display;
                            }
                            else
                            {
                                return "No Players";
                            }
                    } }
                ],
            bDestroy : true

        } );


    transactionsDomTable = document.getElementById('example');

}
function prepareTransactionPlayerOutput(player)
{
    var result = "";
    var transactionData = player.transaction_data;
    if(transactionData.type == "drop")
    {
        result = result.concat("Dropped : ");
    }
    if(transactionData.type == "add")
    {
        result = result.concat("Added : ");
    }
    result = result.concat(player.name.full + " " + player.editorial_team_abbr + " - " + player.display_position + " <br//>");
    if(transactionData.type == "drop")
    {
        result = result.concat( player.transaction_data.source_team_name +" "+ symbol_play +" "+player.transaction_data.destination_type + " <br//><br//>");
    }
    if(transactionData.type == "add")
    {
        result = result.concat(player.transaction_data.source_type + " "+ symbol_play +" "+ player.transaction_data.destination_team_name + " <br//><br//>");
    }
    return result;
}
function initializePopups()
{

    // Initialize the plugin
    $('#team_detail_popup').popup({
        color: 'white',
        opacity: 1,
        transition: '0.3s',
        scrolllock: true
    });

}
function showTeamInfoPopup()
{


    $('#team_detail_popup').popup('show');
}

function retrieveTeamInfo(teamData)
{
    $.ajax({
        url: "team/retrieve/roster/"+teamData.team_key+"/"+currentLeague.current_week+"/",
        type: "GET",
        contentType: "application/json",
        success: function (rosterData, created) {

            //alert(data);
            prepareTeamInfo(teamData, rosterData)
        },
        error: function (textStatus, errorThrown) {
            alert("Error: " + textStatus + " " + errorThrown);
        }
    });
}
function prepareTeamInfo(teamData, rosterData)
{
    // TODO: add JQuery Magic

    $('#team_detail_name').html(teamData.name);

    var players = rosterData.players.player;
    $( "#team_detail_roster tbody" ).remove();
    var x;
    for(x in players)
    {
        addPlayerToRoster(players[x]);
    }


}

function addPlayerToRoster(player)
{
    if ($("#team_detail_roster tbody").length == 0)
    {
        $("#team_detail_roster").append("<tbody></tbody>");
    }
    // Append row to the table
    $("#team_detail_roster tbody").append(
        "<tr>" +
        "<td><img src='" +player.headshot.url+ "'></td>" +
        "<td>" + player.name.full + "</td>" +
        "<td>" + player.display_position + "</td>" +
        "</tr>" );
}

function retrieveTeamPoints (teamData)
{
    $.ajax({
        url: "team/retrieve/stats/"+teamData.team_key+"/"+currentLeague.current_week+"/",
        type: "GET",
        contentType: "application/json",
        success: function (pointsData, created) {

            //alert(data);
            prepareTeamPointsSection(teamData, pointsData);
            prepareTeamBreakDownSection(teamData, pointsData);
        },
        error: function (textStatus, errorThrown) {
            alert("Error: " + textStatus + " " + errorThrown);
        }
    });
    $.ajax({
        url: "data/analysis/team/season/points/"+teamData.team_key+"/",
        type: "GET",
        contentType: "application/json",
        success: function (seasonPointsData, created) {

            //alert(data);
            prepareTeamSeasonPointsSection(teamData, seasonPointsData);

        },
        error: function (textStatus, errorThrown) {
            alert("Error: " + textStatus + " " + errorThrown);
        }
    });
}
function prepareTeamSeasonPointsSection(teamData, seasonPointsData)
{
    var gPointsData = new google.visualization.DataTable();
    gPointsData.addColumn('string', 'Week');
    gPointsData.addColumn('number', 'Actual Points');
    gPointsData.addColumn('number', 'Projected Points');
    for(x in seasonPointsData)
    {
        var week = ((x*1)+1);
        gPointsData.addRow([ week.toString(),seasonPointsData[x].team_points.total*1, seasonPointsData[x].team_projected_points.total*1]);
    }

    var gOptions = {
        title: 'Actual Vs Projected Points',
        curveType: 'function',
        legend: { position: 'bottom' }
    };
    var chart = new google.visualization.LineChart(document.getElementById('teamSeasonPtsChart'));
    google.setOnLoadCallback(drawChart(gPointsData, gOptions, chart));
}

function prepareTeamPointsSection(teamData, pointsData)
{
    //$('#team_detail_roster_stats').html(teamData.name)
    var positionArr = new Array();
    var pointsArr = new Array();
    var positionCountArr = new Array();
    var x;

    for(x in pointsData)
    {
        if(!(positionArr.indexOf(pointsData[x].selectedPosition) > -1))
        {
            positionArr.push(pointsData[x].selectedPosition);
            pointsArr.push(0);

        }
        var loc = positionArr.indexOf(pointsData[x].selectedPosition);
        pointsArr[loc] = pointsArr[loc] + pointsData[x].playerPoints;
        positionCountArr[loc]++;

    }
    var gPointsData = new google.visualization.DataTable();
    gPointsData.addColumn('string', 'Position');
    gPointsData.addColumn('number', 'Points');

    for (var i=0,  tot=positionArr.length; i < tot; i++)
    {
        if(!(positionArr[i] == 'BN'))
        {
            gPointsData.addRow([positionArr[i], pointsArr[i]]);
        }

    }

    var gOptions = {
        title: 'Weekly Points Breakdowns',
        pieHole: 0.4,
    };
    var chart = new google.visualization.PieChart(document.getElementById('pointDonutchart'));
    google.setOnLoadCallback(drawChart(gPointsData, gOptions, chart));

}
function prepareTeamBreakDownSection(teamData, pointsData)
{
    //$('#team_detail_roster_stats').html(teamData.name)
    var positionArr = new Array();
    var positionCountArr = new Array();
    var x;

    for(x in pointsData)
    {
        var y;
        for(y in pointsData[x].eligiblePositions)
        if(!(positionArr.indexOf(pointsData[x].eligiblePositions[y]) > -1))
        {
            positionArr.push(pointsData[x].eligiblePositions[y]);
            positionCountArr.push(0);

        }
        var loc = positionArr.indexOf(pointsData[x].eligiblePositions[y]);
        positionCountArr[loc]++;

    }
    var gPlayersData = new google.visualization.DataTable();
    gPlayersData.addColumn('string', 'Position');
    gPlayersData.addColumn('number', '# of Players');

    for (var i=0,  tot=positionArr.length; i < tot; i++)
    {
        if(!(positionArr[i] == 'BN'))
        {
            gPlayersData.addRow([positionArr[i], positionCountArr[i]]);
        }

    }

    var gOptions = {
        title: 'Weekly Team Breakdowns',
        pieHole: 0.4,
    };
    var chart = new google.visualization.PieChart(document.getElementById('playerDonutchart'));
    google.setOnLoadCallback(drawChart(gPlayersData, gOptions, chart));
}

function drawChart(data, options, chart) {

    chart.draw(data, options);
}

function timeConverter(UNIX_timestamp){
    var a = new Date(UNIX_timestamp * 1000);
    var months = ['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec'];
    var year = a.getFullYear();
    var month = months[a.getMonth()];
    var date = a.getDate();
    var hour = a.getHours()< 10 ? '0' + a.getHours() : a.getHours();
    var min = a.getMinutes()< 10 ? '0' + a.getMinutes() : a.getMinutes();
    var sec = a.getSeconds()< 10 ? '0' + a.getSeconds() : a.getSeconds();
    var time = date + ' ' + month + ' ' + year + ' ' + hour + ':' + min + ':' + sec ;
    return time;
}
function createAvgPointAnalysisChart(data)
{
    var gAvgData = google.visualization.arrayToDataTable(prepareAvgPointAnalysisTable(data));


    var numTeams = data.teamPositionAvgList.length ; // must be using Location
    var seriesArray = new Array (numTeams);
    seriesArray[numTeams] =  {type: 'line'};
    var gOptions = {
        title : 'Average Point Production by Position',
        height: 500,
        width: 900,
        vAxis: {title: 'Average Points'},
        hAxis: {title: 'Positions'},
        seriesType: 'bars',
        series: seriesArray
    };
    var chart = new google.visualization.ComboChart(document.getElementById('avg_points_chart_div'));
    google.setOnLoadCallback(drawChart(gAvgData, gOptions, chart));
}
function prepareAvgPointAnalysisTable(data)
{
    var positions = data.availablePositonsArray;
    var teamData = data.teamPositionAvgList;
    var leagueData = data.positionAvgList;
    removeA(positions, 'IR');
    removeA(positions, 'BN');

    var finalArr = [];
    var headerArr = [];
    headerArr.push("Position");
    for (x in teamData)
    {
        headerArr.push(teamData[x].team.name);
    }
    headerArr.push("League");
    finalArr.push(headerArr);
    for (y in positions)
    {

        var position = positions[y];
        var tempPosArr = [position];
        for(z in teamData)
        {
            var matches = teamData[z].positionAvgList.filter(function(val, index, array) {
                return val.position == position;
            });
            tempPosArr.push(matches[0].avg);
        }
        var leaugeMatches = leagueData.filter(function(val, index, array) {
            return val.position == position;
        });
        tempPosArr.push(leaugeMatches[0].avg);
        finalArr.push(tempPosArr);
    }
    return finalArr;
}

function removeA(arr)
{
    var what, a = arguments, L = a.length, ax;
    while (L > 1 && arr.length) {
        what = a[--L];
        while ((ax= arr.indexOf(what)) !== -1) {
            arr.splice(ax, 1);
        }
    }
    return arr;
}

