package controller.jspused;

public class BillingTable {
    private Integer month;
    private Integer used;
    private Integer tactics;
    private Double cost;

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getUsed() {
        return used;
    }

    public void setUsed(Integer used) {
        this.used = used;
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

    @Override
    public String toString() {
        return "BillingTable [month=" + month + ", used=" + used + ", tactics=" + tactics + ", cost=" + cost + "]";
    }

}
