var currentLeague;
var currentUserTeam;
$(document).ready(function()
{

    loadLeagueData();

});
function loadLeagueData()
{
    loadLeagueInfo();

}
function loadLeagueInfo()
{


    // $.ajax({
    //      url: "players/load/league/" + $("#leagueInFocus").val() +"/",
    //      type: "GET",
    //      contentType: "application/json"
    //   });


    $.ajax({
        url: "../data/league/" + $("#leagueInFocus").val() +"/",
        type: "GET",
        contentType: "application/json",
        success: function (data, created) {

            // alert(data);
            currentLeague = data;
            retrieveTeam();
            retrieveOpponentsTeams();
        },
        error: function (textStatus, errorThrown) {
            alert("Error: " + textStatus + " " + errorThrown);
        }
    });
}
function retrieveTeam()
{
    $.ajax({
        url: "../team/retrieve/league/"+$("#leagueInFocus").val()+"/self/",
        type: "GET",
        contentType: "application/json",
        success: function (data, created) {

            // alert(data);

            retrieveTeamRoster(data);

        },
        error: function (textStatus, errorThrown) {
            alert("Error: " + textStatus + " " + errorThrown);
        }
    });
}
function retrieveOpponentsTeams()
{
    $.ajax({
        url: "../team/retrieve/league/"+$("#leagueInFocus").val()+"/opp/",
        type: "GET",
        contentType: "application/json",
        success: function (data, created) {



            populateSelectWithTeamData("#comparison_team",data);
            retrieveTeamRoster(data[0]);

        },
        error: function (textStatus, errorThrown) {
            alert("Error: " + textStatus + " " + errorThrown);
        }
    });
}
function retrieveTeamRoster(teamData)
{
    $.ajax({
        url: "../team/retrieve/roster/"+teamData.team_key+"/"+currentLeague.current_week+"/",
        type: "GET",
        contentType: "application/json",
        success: function (data, created) {
            prepareTeamInfo(teamData, data)

        },
        error: function (textStatus, errorThrown) {
            alert("Error: " + textStatus + " " + errorThrown);
        }
    });


}

function prepareTeamInfo(teamData, rosterData)
{
    // TODO: add JQuery Magic
    var divId;
    var players = rosterData.players.player;

    if(teamData.is_owned_by_current_login == "1")
    {
        divId = "#users_team_roster";
    }
    else
    {
        divId = "#comparison_team_roster";
    }

    $(divId+'.team_detail_name').html(teamData.name);


    $(divId+" .roster tbody").remove();
    var x;
    for(x in players)
    {
        addPlayerToRoster(divId,players[x]);
    }


}