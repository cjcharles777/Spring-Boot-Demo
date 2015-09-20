$(document).ready(function()
{
    updateStandingsDataTable();
    updateTransactionsDataTable();
    initializePopups();

});
google.load("visualization", "1", {packages:["corechart"]});

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

var standingsDataTable,
    standingsDomTable,
    standingsHtmlTable = '<table id="example"><tbody></tbody></table>';
var standingsTable;



function updateStandingsDataTable()
{
    if ($.fn.DataTable.fnIsDataTable(standingsDomTable)) {
        standingsDataTable.fnDestroy(true);
        $('#tablediv').append(standingsHtmlTable);
    }

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

        } );

    $('#example tbody').on('click', 'tr', function () {
        var data = table.row( this ).data();
        retrieveTeamInfo(data);
        retrieveTeamPoints(data);
        showTeamInfoPopup();

    } );
    standingsDomTable = document.getElementById('example');

}
var transactionsDataTable,
    transactionsDomTable,
    transactionsHtmlTable = '<table id="league-transactions-table"><tbody></tbody></table>';
var transactionsTable;



function updateTransactionsDataTable()
{
    if ($.fn.DataTable.fnIsDataTable(transactionsDomTable)) {
        transactionsDataTable.fnDestroy(true);
        $('#tablediv').append(transactionsHtmlTable);
    }

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
                    { "data": "players", "title": "players",
                        "render": function ( data, type, full, meta ) {
                            var display = "";
                            if(data != null) {
                                for (x in data.player) {
                                    var player = data.player[x];
                                    display = display.concat(player.name.full + " " + player.editorial_team_abbr + " - " + player.display_position + " <br//>"
                                        + " source : " + player.transaction_data.source_type + " destination : " + player.transaction_data.destination_team_name)+ " <br//><br//>";
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

 //  <!-- $('#example tbody').on('click', 'tr', function () {
 //       var data = table.row( this ).data();
 //       retrieveTeamInfo(data);
//        retrieveTeamPoints(data);
//        showTeamInfoPopup();

//    } );-->
    transactionsDomTable = document.getElementById('example');

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
        url: "team/retrieve/roster/"+teamData.team_key+"/1",
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
        url: "team/retrieve/stats/"+teamData.team_key+"/1",
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