package com.donkeigy.objects.analysis;

/**
 * Created by cedric on 9/29/15.
 */
public class PositionMovements
{
    private String position;
    private Integer add;
    private Integer drop;
    private Integer trades;

    public PositionMovements(String position, Integer add, Integer drop, Integer trades) {
        this.position = position;
        this.add = add;
        this.drop = drop;
        this.trades = trades;
    }

    public PositionMovements() {
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getAdd() {
        return add;
    }

    public void setAdd(Integer add) {
        this.add = add;
    }

    public Integer getDrop() {
        return drop;
    }

    public void setDrop(Integer drop) {
        this.drop = drop;
    }

    public Integer getTrades() {
        return trades;
    }

    public void setTrades(Integer trades) {
        this.trades = trades;
    }
}
