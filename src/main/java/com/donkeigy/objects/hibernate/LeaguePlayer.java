package com.donkeigy.objects.hibernate;



import com.yahoo.objects.players.Player;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by cedric on 8/28/15.
 */
@Entity
@Table(name = "Players")
public class LeaguePlayer  extends Player implements Serializable
{
    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator", strategy = "increment")
    @Column(name = "playerid", nullable=false)
    private Integer id;

    private String leauge_id;

    @Column(name = "player_key", length=20, nullable=false)
    private String player_key;

    @Column(name = "player_id_yahoo", length=7, nullable=false)
    private String player_id;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private PlayerName name;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private ByeWeek byeWeeks;

    @Column(name = "display_position", length=10, nullable=false)
    private String display_position;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private PlayerPic headshot;

    @Column(name = "image_url", length=1000, nullable=false)
    private String image_url;

    //private transient  List<Position> eligible_positions;

    public LeaguePlayer()
    {
    }

    public LeaguePlayer(Player player, String leauge_id) {
        this.leauge_id = leauge_id;
        this.player_key = player.getPlayer_key();
        this.player_id = player.getPlayer_id();
        this.name = new PlayerName(player.getName());
        this.byeWeeks = new ByeWeek(player.getBye_weeks());
        this.display_position = player.getDisplay_position();
        this.headshot = new PlayerPic(player.getHeadshot());
        this.image_url = player.getImage_url();
    }

    public LeaguePlayer(String leauge_id) {
        this.leauge_id = leauge_id;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public ByeWeek getByeWeeks() {
        return byeWeeks;
    }

    public void setByeWeeks(ByeWeek byeWeeks) {
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
