function loadLeagueInfo(callbackMeth)
{


    // $.ajax({
    //      url: "players/load/league/" + $("#leagueInFocus").val() +"/",
    //      type: "GET",
    //      contentType: "application/json"
    //   });


    $.ajax({
        url: "/data/league/" + $("#leagueInFocus").val() +"/",
        type: "GET",
        contentType: "application/json",
        success: function (data, created) {

            // alert(data);
            callbackMeth(data);

        },
        error: function (textStatus, errorThrown) {
            alert("Error: " + textStatus + " " + errorThrown);
        }
    });
}
function addPlayerToRoster(id, player)
{
    if ($(id +" .roster tbody").length == 0)
    {
        $(id + " .roster").append("<tbody></tbody>");
    }
    // Append row to the table
    $(id +" .roster tbody").append(
        "<tr>" +
        "<td><img src='" +player.headshot.url+ "'></td>" +
        "<td>" + player.name.full + "</td>" +
        "<td>" + player.display_position + "</td>" +
        "</tr>"
    );
}
function addPlayerToRosterWithCurrPos(id, player, currentPosition, team)
{
    if ($(id +" .roster tbody").length == 0)
    {
        $(id + " .roster").append("<tbody></tbody>");
    }
    // Append row to the table
    $(id +" .roster tbody").append(
        "<tr>" +
        "<td><img src='" +player.headshot.url+ "'></td>" +
        "<td>" + player.name.full + "</td>" +
        "<td>" + currentPosition + "</td>" +
        "<td>" + team.name + "</td>" +
        "</tr>"
    );
}
function populateSelectWithTeamData(id, data)
{
    var select = $(id), options = '';
    select.empty();
    var teams = data;
    for(var i=0;i < teams.length; i++)
    {
        options += "<option value='"+teams[i].team_key +"'>"+ teams[i].name + "</option>";
    }

    select.append(options);
}

function populateSelectWithPositionData(id, data)
{
    var select = $(id), options = '';
    select.empty();
    var positons = data;
    for(var i=0;i < positons.length; i++)
    {
        if(positons[i].position_type != null)
        {
            options += "<option value='"+positons[i].position +"'>"+ positons[i].position + "</option>";
        }

    }

    select.append(options);
}