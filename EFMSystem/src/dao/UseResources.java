package dao;

import java.util.Date;

public class UseResources {
    private Integer id;
    private Date rcd_time;
    private Integer cur_used;
    private Integer user_id;
    private Double cost;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getRcd_time() {
        return rcd_time;
    }

    public void setRcd_time(Date rcd_time) {
        this.rcd_time = rcd_time;
    }

    public Integer getCur_used() {
        return cur_used;
    }

    public void setCur_used(Integer cur_used) {
        this.cur_used = cur_used;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "UseResources [id=" + id + ", rcd_time=" + rcd_time + ", cur_used=" + cur_used + ", user_id=" + user_id
                + "]";
    }
}
