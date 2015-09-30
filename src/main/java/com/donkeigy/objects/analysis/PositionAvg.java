package com.donkeigy.objects.analysis;

import java.math.BigDecimal;

/**
 * Created by cedric on 9/29/15.
 */
public class PositionAvg
{
    private String position;
    private BigDecimal avg;
    private BigDecimal total;
    private Integer count;

    public PositionAvg() {
    }

    public PositionAvg(String position, BigDecimal avg, BigDecimal total, Integer count) {
        this.position = position;
        this.avg = avg;
        this.total = total;
        this.count = count;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public BigDecimal getAvg() {
        return avg;
    }

    public void setAvg(BigDecimal avg) {
        this.avg = avg;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
