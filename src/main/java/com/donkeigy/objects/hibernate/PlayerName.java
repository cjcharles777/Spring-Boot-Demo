package com.donkeigy.objects.hibernate;

import com.yahoo.objects.players.Name;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by cedric on 8/28/15.
 */
@Entity
@Table(name = "PlayersName")
public class PlayerName extends Name implements Serializable
{
    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator", strategy = "increment")
    @Column(name = "player_name_id", nullable=false)
    private Integer id;
    @Column(name = "full_name", length=1000, nullable=false)
    private String full;
    @Column(name = "first_name", length=500, nullable=true)
    private String first;
    @Column(name = "last_name", length=500, nullable=true)
    private String last;
    @Column(name = "ascii_first_name", length=500, nullable=true)
    private String ascii_first;
    @Column(name = "ascii_last_name", length=500, nullable=true)
    private String ascii_last;

    public PlayerName(Name name)
    {
        this.full = name.getFull();
        this.first = name.getFirst();
        this.last = name.getLast();
        this.ascii_first = name.getAscii_first();
        this.ascii_last = name.getAscii_last();
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getFull() {
        return full;
    }

    public void setFull(String full) {
        this.full = full;
    }


    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }


    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }


    public String getAscii_first() {
        return ascii_first;
    }

    public void setAscii_first(String ascii_first) {
        this.ascii_first = ascii_first;
    }


    public String getAscii_last() {
        return ascii_last;
    }

    public void setAscii_last(String ascii_last) {
        this.ascii_last = ascii_last;
    }



}
