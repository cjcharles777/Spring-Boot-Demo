package com.donkeigy.objects.hibernate;

import com.yahoo.objects.players.Position;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by cedric on 9/1/15.
 */
@Entity
@Table(name = "PlayerPositions")
public class PlayerPosition extends Position {


    @Column(name = "position", length=20, nullable=false)
    private String position;

    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator", strategy = "increment")
    @Column(name = "id", nullable=false)
    private Integer id;



    public PlayerPosition() {
    }
    public PlayerPosition(Position p)
    {
        this.position = p.getPosition();
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPosition() {
        return this.position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}

