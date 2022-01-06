package Types;

import java.io.Serializable;

public class Data implements Serializable {

	@Override
	public String toString() {
		return "Data [timeStamp=" + timeStamp + ", value=" + value + "]";
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -8309456898207869492L;
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

}
