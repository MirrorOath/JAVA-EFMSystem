package controller.jspused;

public class MonthUsed {
    private Integer userId;
    private Integer year;
    private Integer month;
    private Integer used;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public Integer getUsed() {
        return used;
    }

    public void setUsed(Integer used) {
        this.used = used;
    }

    @Override
    public String toString() {
        return "MonthUsed [userId=" + userId + ", year=" + year + ", month=" + month + ", used=" + used + "]";
    }

}
