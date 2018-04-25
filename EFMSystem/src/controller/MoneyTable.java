package controller;

public class MoneyTable {
    private Double history;
    private Double thismonthcost;
    private Double cur_money;
    private boolean isSuccess;

    public Double getHistory() {
        return history;
    }

    public void setHistory(Double history) {
        this.history = history;
    }

    public Double getThismonthcost() {
        return thismonthcost;
    }

    public void setThismonthcost(Double thismonthcost) {
        this.thismonthcost = thismonthcost;
    }

    public Double getCur_money() {
        return cur_money;
    }

    public void setCur_money(Double cur_money) {
        this.cur_money = cur_money;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    @Override
    public String toString() {
        return "MoneyTable [history=" + history + ", thismonthcost=" + thismonthcost + ", cur_money=" + cur_money
                + ", isSuccess=" + isSuccess + "]";
    }

}
