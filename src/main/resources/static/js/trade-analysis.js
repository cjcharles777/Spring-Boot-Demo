var currentLeague ="";

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
function retrieveTeamRoster(teamData)
{
    $.ajax({
        url: "../team/retrieve/roster/"+teamData.team_key+"/"+currentLeague.current_week+"/",
        type: "GET",
        contentType: "application/json",
        success: function (data, created) {

            //alert(data);
            //prepareTeamInfo(teamData, rosterData)
            var select = $("#playersOnTeam"), options = '';
            select.empty();
            var players = data.players.player;
            for(var i=0;i < players.length; i++)
            {
                options += "<option value='"+players[i].player_key +"'>"+ players[i].name.full + " - "+players[i].display_position +"</option>";
            }

            select.append(options);
        },
        error: function (textStatus, errorThrown) {
            alert("Error: " + textStatus + " " + errorThrown);
        }
    });
}