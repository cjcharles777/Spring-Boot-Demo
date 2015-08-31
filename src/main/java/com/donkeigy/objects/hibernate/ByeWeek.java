package com.donkeigy.objects.hibernate;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by cedric on 8/30/15.
 */
@Entity
@Table(name = "ByeWeek")
public class ByeWeek extends com.yahoo.objects.stats.ByeWeek implements Serializable
{
    private Integer id;
    private String week;

    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator", strategy = "increment")
    @Column(name = "bye_week_id", nullable=false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "week", length=2, nullable=false)
    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

}
