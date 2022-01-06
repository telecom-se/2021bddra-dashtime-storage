package Types;

public class Data {

	private Timestamp timeStamp;
	private Double value;

	public Data() {

	}

	public Data(Timestamp timeStamp, Double value) {
		this.timeStamp = timeStamp;
		this.value = value;
	}

	public Timestamp getTimeStamp() {
		return timeStamp;
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

}
