package com.donkeigy.objects.hibernate;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by cedric on 8/28/15.
 */
@Entity
@Table(name = "PlayerPic")
public class PlayerPic extends com.yahoo.objects.players.PlayerPic implements Serializable
{

    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator", strategy = "increment")
    @Column(name = "player_pic_id", nullable=false)
    private Integer id;
    @Column(name = "url",length=1000, nullable=false)
    private String url;
    @Column(name = "pic_size",length=1000, nullable=false)
    private String size;


    public PlayerPic(com.yahoo.objects.players.PlayerPic playerPic) {
        this.url = playerPic.getUrl();
        this.size = playerPic.getSize();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }



}