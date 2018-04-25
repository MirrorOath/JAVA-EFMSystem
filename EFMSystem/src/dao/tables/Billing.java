package dao.tables;

import java.util.Date;

public class Billing {

    private Integer id;
    private Integer userId;
    private Integer tactics;
    private Double cost;
    private Double exCost;
    private Date date;
    private Integer isPaid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTactics() {
        return tactics;
    }

    public void setTactics(Integer tactics) {
        this.tactics = tactics;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Double getExCost() {
        return exCost;
    }

    public void setExCost(Double exCost) {
        this.exCost = exCost;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(Integer isPaid) {
        this.isPaid = isPaid;
    }

    @Override
    public String toString() {
        return "Billing [id=" + id + ", userId=" + userId + ", tactics=" + tactics + ", cost=" + cost + ", exCost="
                + exCost + ", date=" + date + ", isPaid=" + isPaid + "]";
    }

}
