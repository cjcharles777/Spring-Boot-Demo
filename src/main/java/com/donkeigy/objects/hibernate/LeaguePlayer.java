package com.donkeigy.objects.hibernate;



import com.yahoo.objects.players.Player;
import com.yahoo.objects.players.Position;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

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

    @Column(name = "editorial_player_key", length=100, nullable=false)
    private String  editorial_player_key;
    @Column(name = "editorial_team_key", length=100, nullable=false)
    private String  editorial_team_key;
    @Column(name = "editorial_team_full_name", length=100, nullable=false)
    private String  editorial_team_full_name;
    @Column(name = "editorial_team_abbr", length=100, nullable=false)
    private String  editorial_team_abbr;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private PlayerPic headshot;

    @Column(name = "image_url", length=1000, nullable=false)
    private String image_url;

    @Column(name = "editorial_team_abbr", length=100, nullable=false)
    private String position_type;

    @ManyToMany( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
    @JoinTable(
            name="PlayerToPosition",
            joinColumns = @JoinColumn( name="playerid"),
            inverseJoinColumns = @JoinColumn( name="position")
    )
    @LazyCollection(LazyCollectionOption.FALSE)
    private transient List<PlayerPosition> eligible_player_positions;

    @Column(name = "has_player_notes", length=100, nullable=false)
    private String has_player_notes;
    @Column(name = "has_recent_player_notes", length=100, nullable=false)
    private String has_recent_player_notes;
    @Column(name = "status", length=100, nullable=false)
    private String status;
    @Column(name = "on_disabled_list", length=100, nullable=false)
    private String on_disabled_list;
    @Column(name = "injury_note", length=100, nullable=false)
    private String injury_note;

    //private transient  List<Position> eligible_player_positions;

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
        this.editorial_player_key = player.getEditorial_player_key();
        this.editorial_team_key = player.getEditorial_team_key();
        this.editorial_team_full_name = player.getEditorial_team_full_name();
        this.editorial_team_abbr = player.getEditorial_team_abbr();
        this.position_type = player.getPosition_type();
        this.eligible_player_positions = new LinkedList<PlayerPosition>();
        for (Position position : player.getEligible_positions())
        {
            eligible_player_positions.add(new PlayerPosition(position));
        }
        this.has_player_notes = player.getHas_player_notes();
        this.has_recent_player_notes = player.getHas_recent_player_notes();
        this.status = player.getStatus();
        this. on_disabled_list = player.getOn_disabled_list();
        this.injury_note =  player.getInjury_note();

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

    @Override
    public String getEditorial_player_key() {
        return editorial_player_key;
    }

    @Override
    public void setEditorial_player_key(String editorial_player_key) {
        this.editorial_player_key = editorial_player_key;
    }

    @Override
    public String getEditorial_team_key() {
        return editorial_team_key;
    }

    @Override
    public void setEditorial_team_key(String editorial_team_key) {
        this.editorial_team_key = editorial_team_key;
    }

    @Override
    public String getEditorial_team_full_name() {
        return editorial_team_full_name;
    }

    @Override
    public void setEditorial_team_full_name(String editorial_team_full_name) {
        this.editorial_team_full_name = editorial_team_full_name;
    }

    @Override
    public String getEditorial_team_abbr() {
        return editorial_team_abbr;
    }

    @Override
    public void setEditorial_team_abbr(String editorial_team_abbr) {
        this.editorial_team_abbr = editorial_team_abbr;
    }

    @Override
    public String getPosition_type() {
        return position_type;
    }

    @Override
    public void setPosition_type(String position_type) {
        this.position_type = position_type;
    }


    public List<PlayerPosition> getEligible_player_positions() {
        return eligible_player_positions;
    }

    public void setEligible_player_positions(List<PlayerPosition> eligible_player_positions) {
        this.eligible_player_positions = eligible_player_positions;
    }

    @Override
    public String getHas_player_notes() {
        return has_player_notes;
    }

    @Override
    public void setHas_player_notes(String has_player_notes) {
        this.has_player_notes = has_player_notes;
    }

    @Override
    public String getHas_recent_player_notes() {
        return has_recent_player_notes;
    }

    @Override
    public void setHas_recent_player_notes(String has_recent_player_notes) {
        this.has_recent_player_notes = has_recent_player_notes;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String getOn_disabled_list() {
        return on_disabled_list;
    }

    @Override
    public void setOn_disabled_list(String on_disabled_list) {
        this.on_disabled_list = on_disabled_list;
    }

    @Override
    public String getInjury_note() {
        return injury_note;
    }

    @Override
    public void setInjury_note(String injury_note) {
        this.injury_note = injury_note;
    }
}
