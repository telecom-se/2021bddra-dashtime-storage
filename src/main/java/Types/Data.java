package Types;

import java.io.Serializable;

public class Data implements Serializable, Comparable<Data> {

	private static final long serialVersionUID = -8309456898207869492L;
	private Timestamp timeStamp;
	private Double value;

	public Data() {

	}

	public Data(Timestamp timeStamp, Double value) {
		this.timeStamp = timeStamp;
		this.value = value;
	}

	public Data(String timeStamp, String value) {
		this.timeStamp = new Timestamp(timeStamp);
		this.value = Double.parseDouble(value);
	}

	public Timestamp getTimeStamp() {
		return this.timeStamp;
	}

	public void setTimeStamp(Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Data [timeStamp=" + timeStamp + ", value=" + value + "]";
	}

	@Override
	public int compareTo(Data o) {
		return this.timeStamp.compareTo(o.timeStamp);
	}

}
