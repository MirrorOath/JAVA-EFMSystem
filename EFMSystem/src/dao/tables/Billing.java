package dao.tables;

import java.util.Date;

public class Billing {

    private Integer id;
    private Integer userId;
    private String userName;
    private Integer tactics;
    private Integer curUsed;
    private Double cost;
    private Double exCost;
    private Date date;
    private Integer isPaid;
    private Integer year;
    private Integer month;

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getTactics() {
        return tactics;
    }

    public void setTactics(Integer tactics) {
        this.tactics = tactics;
    }

    public Integer getCurUsed() {
        return curUsed;
    }

    public void setCurUsed(Integer curUsed) {
        this.curUsed = curUsed;
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

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    @Override
    public String toString() {
        return "Billing [id=" + id + ", userId=" + userId + ", userName=" + userName + ", tactics=" + tactics
                + ", curUsed=" + curUsed + ", cost=" + cost + ", exCost=" + exCost + ", date=" + date + ", isPaid="
                + isPaid + ", year=" + year + ", month=" + month + "]";
    }

}
