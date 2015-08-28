package com.donkeigy.objects.hibernate;



import java.io.Serializable;

/**
 * Created by cedric on 8/28/15.
 */
public class LeaguePlayer  implements Serializable
{
    private int id;
    private String leauge_id;
    private String player_key;
    private String player_id;
    private PlayerName name;
    private String byeWeeks;
    private String display_position;
    private PlayerPic headshot;
    private String image_url;
    //private transient  List<Position> eligible_positions;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getPlayer_key() {
        return player_key;
    }

    public void setPlayer_key(String player_key) {
        this.player_key = player_key;
    }

    public String getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(String player_id) {
        this.player_id = player_id;
    }

    public PlayerName getName() {
        return name;
    }

    public void setName(PlayerName name) {
        this.name = name;
    }

    public String getByeWeeks() {
        return byeWeeks;
    }

    public void setByeWeeks(String byeWeeks) {
        this.byeWeeks = byeWeeks;
    }

    public String getDisplay_position() {
        return display_position;
    }

    public void setDisplay_position(String display_position) {
        this.display_position = display_position;
    }

    public PlayerPic getHeadshot() {
        return headshot;
    }

    public void setHeadshot(PlayerPic headshot) {
        this.headshot = headshot;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getLeauge_id() {
        return leauge_id;
    }

    public void setLeauge_id(String leauge_id) {
        this.leauge_id = leauge_id;
    }
}
