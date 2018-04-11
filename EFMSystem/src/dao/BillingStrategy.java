package dao;

public class BillingStrategy {

	private Integer id;
	private Integer tactics;
	private Integer time_start;
	private Integer time_end;
	private Integer price;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getTactics() {
        return tactics;
    }
    public void setTactics(Integer tactics) {
        this.tactics = tactics;
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
    public Integer getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }
    @Override
    public String toString() {
        return "BillingStrategy [id=" + id + ", tactics=" + tactics + ", time_start=" + time_start + ", time_end="
                + time_end + ", price=" + price + "]";
    }

}
