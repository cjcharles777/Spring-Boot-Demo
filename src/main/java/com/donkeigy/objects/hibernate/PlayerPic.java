package com.donkeigy.objects.hibernate;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by cedric on 8/28/15.
 */
@Entity
@Table(name = "PlayerPic")
public class PlayerPic implements Serializable
{

    private Integer id;
    private String url;
    private String size;

    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator", strategy = "increment")
    @Column(name = "player_pic_id", nullable=false)
    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "url",length=1000, nullable=false)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column(name = "pic_size",length=1000, nullable=false)
    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }



}