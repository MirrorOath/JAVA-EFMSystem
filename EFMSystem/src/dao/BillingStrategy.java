package dao;

public class BillingStrategy {

	private Integer id;
	private Integer time_start;
	private Integer time_end;
	private Integer prive;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTime_start() {
		return time_start;
	}

	public void setTime_start(Integer time_start) {
		this.time_start = time_start;
	}

	public Integer getTime_end() {
		return time_end;
	}

	public void setTime_end(Integer time_end) {
		this.time_end = time_end;
	}

	public Integer getPrive() {
		return prive;
	}

	public void setPrive(Integer prive) {
		this.prive = prive;
	}

	@Override
	public String toString() {
		return "BillingStrategy [id=" + id + ", time_start=" + time_start + ", time_end=" + time_end + ", prive="
				+ prive + "]";
	}
}
