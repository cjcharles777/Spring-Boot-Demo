<#macro page>
    <html>
        <head>
            <title>${title}</title>
        </head>
        <body>
        <h1>${title}</h1>

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