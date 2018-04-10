package dao;

public class UserInfo {
	private Integer id;
	private String user_name;
	private Integer user_id;
	private String password;
	private Integer age;
	private String address;
	private Integer role;
	private Integer tactics;

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

	@Override
	public String toString() {
		return "UserInfo [id=" + id + ", user_name=" + user_name + ", user_id=" + user_id + ", password=" + password
				+ ", age=" + age + ", address=" + address + ", role=" + role + ", tactics=" + tactics + "]";
	}
}
