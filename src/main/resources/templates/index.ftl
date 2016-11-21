<#import "lib/utils.ftl" as u>
<@u.page>
<script src="/js/index.js"></script>
<script id="template" type="x-tmpl-mustache">
      {{#matchups.matchup}}
          <div class="matchup-wrapper">
               {{#teams.team}}
                    <div class="matchup-team inline">
                        <div class="avatar inline-block">
                            <img class="avatar-img" src ="{{team_logos.team_logo.url}}"/>
                        </div>
                        <div class="inline-block">
                            <div>{{name}}</div>
                            <div>{{team_points.total}}</div>
                            <div>{{team_projected_points.total}}</div>
                        </div>
                    </div>
               {{/teams.team}}
          </div>
          <br style="clear:both" />
      {{/matchups.matchup}}
    </script>
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
               <div id = "matchupBody"></div>
               </div>
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
            <div class="panel-body" id="example-table-div">
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
                <table id="league-transactions-table" class="row-border">
                    <tbody></tbody>
                </table>
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
                <div id="avg_points_chart_div" style="width: 900px; height: 500px;"></div>
                <div id="total_points_chart_div" style="width: 900px; height: 500px;"></div>
                    <div>
                        <table id="player-performance-table" class="row-border">
                            <tbody></tbody>
                        </table>
                    </div>
            </div>
        </div>
    </div>
    <div class="panel panel-default">
        <div class="panel-heading">
            <h4 class="panel-title">
                <a data-toggle="collapse" data-parent="#accordion" href="#comparison">
                    Team Comparison</a>
            </h4>
        </div>
        <div id="comparison" class="panel-collapse collapse">
            <div class="panel-body">
                What do i want here?
            </div>
        </div>
    </div>

</div>
<!-- Add content to the popup -->
<@u.teaminfopopup />
</@u.page>