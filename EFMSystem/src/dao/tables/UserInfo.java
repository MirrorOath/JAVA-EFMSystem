package dao.tables;

public class UserInfo {
    private Integer id;
    private String userName;
    private String password;
    private String address;
    private String tell;
    private Double money;
    private Integer role;
    private String roleStr;
    private Integer tactics;
    private Integer isUnusually;
    private String OldPassword;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTell() {
        return tell;
    }

    public void setTell(String tell) {
        this.tell = tell;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getRoleStr() {
        return roleStr;
    }

    public void setRoleStr(String roleStr) {
        this.roleStr = roleStr;
    }

    public Integer getTactics() {
        return tactics;
    }

    public void setTactics(Integer tactics) {
        this.tactics = tactics;
    }

    public Integer getIsUnusually() {
        return isUnusually;
    }

    public void setIsUnusually(Integer isUnusually) {
        this.isUnusually = isUnusually;
    }

    public String getOldPassword() {
        return OldPassword;
    }

    public void setOldPassword(String oldPassword) {
        OldPassword = oldPassword;
    }

    @Override
    public String toString() {
        return "UserInfo [id=" + id + ", userName=" + userName + ", password=" + password + ", address=" + address
                + ", tell=" + tell + ", money=" + money + ", role=" + role + ", roleStr=" + roleStr + ", tactics="
                + tactics + ", isUnusually=" + isUnusually + ", OldPassword=" + OldPassword + "]";
    }

}
