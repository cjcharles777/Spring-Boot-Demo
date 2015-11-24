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