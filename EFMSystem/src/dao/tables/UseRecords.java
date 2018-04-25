package dao.tables;

import java.util.Date;

public class UseRecords {
    private Integer id;
    private Integer userId;
    private String userName;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
        return "UseResourds [id=" + id + ", userId=" + userId + ", userName=" + userName + ", curUsed=" + curUsed
                + ", date=" + date + "]";
    }

}
