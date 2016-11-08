<#import "lib/utils.ftl" as u>

<@u.page>
    <script src="/js/comparison.js"></script>
    <#if leagues?size != 0 >
    <select id="leagueInFocus">
        <#list leagues as league>
            <option value="${league.league_key}">${league.name}</option>
        </#list>
    </select>
    <button onclick="loadLeagueData()">Select League </button></br>
    </#if>
    <select id="team_one_select"></select> ~vs~ <select id="team_two_select"></select></br>
    <select id="pos_one_select"></select> ~vs~ <select id="pos_two_select"></select>



    <table id="position-comparison-table" class="row-border">
        <tbody></tbody>
    </table>
</@u.page>

