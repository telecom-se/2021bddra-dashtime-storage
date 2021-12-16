package Types;

// Une série contient une date, une valeur et un nom
// La db stocke des collections de séries
public class Series {
	
	private Timestamp ts;
	
	private PointValues pv;
	
	private String name;

	//Constructeur avec choix du timestamp 
	public Series(String name_, Timestamp ts_, PointValues pv_) {
		name = name_;
		ts = ts_;
		pv = pv_;
	}
	
	//Constructeur sans timestamp (valeur auto)
	public Series(String name_, PointValues pv_) {
		name = name_;
		pv = pv_;
		ts = new Timestamp();
	}
	
	public Series() {
		ts = new Timestamp();
		pv = new PointValues(0);
		name = "Default";
	}
	
	public Timestamp getTs() {
		return ts;
	}

	public void setTs(Timestamp ts) {
		this.ts = ts;
	}

	public PointValues getPv() {
		return pv;
	}

	public void setPv(PointValues pv) {
		this.pv = pv;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
