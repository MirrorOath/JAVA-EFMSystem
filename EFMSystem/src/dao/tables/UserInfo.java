package dao.tables;

import java.util.Date;

public class UserInfo {
    private Integer id;
    private String user_name;
    private Integer user_id;
    private String OldPassword;
    private String password;
    private Integer age;
    private Double money;
    private String address;
    private Integer role;
    private Integer tactics;
    private Date date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getOldPassword() {
        return OldPassword;
    }

    public void setOldPassword(String oldPassword) {
        OldPassword = oldPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Integer getTactics() {
        return tactics;
    }

    public void setTactics(Integer tactics) {
        this.tactics = tactics;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "UserInfo [id=" + id + ", user_name=" + user_name + ", user_id=" + user_id + ", OldPassword="
                + OldPassword + ", password=" + password + ", age=" + age + ", money=" + money + ", address=" + address
                + ", role=" + role + ", tactics=" + tactics + ", date=" + date + "]";
    }

}
