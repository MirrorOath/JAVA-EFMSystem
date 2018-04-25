package dao.tables;

import java.util.Date;

public class UseResourds {
    private Integer id;
    private Integer userId;
    private Integer curUsed;
    private Date date;

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

    public Integer getCurUsed() {
        return curUsed;
    }

    public void setCurUsed(Integer curUsed) {
        this.curUsed = curUsed;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "UseResourds [id=" + id + ", userId=" + userId + ", curUsed=" + curUsed + ", date=" + date + "]";
    }

}
