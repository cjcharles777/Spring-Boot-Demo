/**
 * Created by cedric on 11/8/16.
 */

function loadLeagueData()
{
    loadLeagueInfo(function(data)
    {
        currentLeague = data;

    });
    loadLeagueTeamsInfo();
    loadLeagueRosterPositonsInfo();
}

function loadLeagueTeamsInfo()
{
    // $.ajax({
    //      url: "players/load/league/" + $("#leagueInFocus").val() +"/",
    //      type: "GET",
    //      contentType: "application/json"
    //   });


    $.ajax({
        url: "/team/retrieve/league/" + $("#leagueInFocus").val() + "/",
        type: "GET",
        contentType: "application/json",
        success: function (data, created) {

            // alert(data);
            populateSelectWithTeamData("#team_one_select",data);
            populateSelectWithTeamData("#team_two_select",data);
        },
        error: function (textStatus, errorThrown) {
            alert("Error: " + textStatus + " " + errorThrown);
        }
    });
}
function loadLeagueRosterPositonsInfo()
{
    // $.ajax({
    //      url: "players/load/league/" + $("#leagueInFocus").val() +"/",
    //      type: "GET",
    //      contentType: "application/json"
    //   });


    $.ajax({
        url: "/data/league/" + $("#leagueInFocus").val() + "/roster-positions/",
        type: "GET",
        contentType: "application/json",
        success: function (data, created) {

            populateSelectWithPositionData("#pos_one_select", data);
            populateSelectWithPositionData("#pos_two_select", data);
        },
        error: function (textStatus, errorThrown) {
            alert("Error: " + textStatus + " " + errorThrown);
        }
    });
}

function updateStandingsDataTable()
{

    standingsTable =  $('#position-comparison-table').DataTable(
        {

            "processing": true,
            "select": true,
            "ajax":
            {
                url: '/data/compare/positions/'+($("#team_one_select").val())+'/'+($("#pos_one_select").val())+'/'
                +($("#team_two_select").val())+'/'+($("#pos_two_select").val())+'/',
                dataSrc: ''
            },
            "columns":
                [
                    { "data": "standings.rank", "title":"Rank" },
                    { "data": "name", "title":"Team Name" },
                    { "data": "standings.outcome_totals.wins", "title": "Wins" },
                    { "data": "standings.outcome_totals.losses", "title": "Losses" },
                    { "data": "standings.outcome_totals.ties" , "title":"Ties" },
                    { "data": "standings.outcome_totals.percentage", "title":"Percentage" },
                    { "data": "standings.points_for", "title":"Points For" },
                    { "data": "standings.points_against", "title":"Points Against"}
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
