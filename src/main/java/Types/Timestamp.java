package Types;

import java.io.Serializable;
import java.util.Date;

public class Timestamp implements Serializable{

	@Override
	public String toString() {
		return "Timestamp [timestamp=" + timestamp + "]";
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -592635719730128730L;
	private Long timestamp;

	public Timestamp() {
		this.timestamp = new Date().getTime();
	}

	public Timestamp(Long time) {
		this.timestamp = time;
	}

	public long getValue() {
		return this.timestamp;
  }
	
	public Timestamp(String time) {
		this.timestamp = Long.parseLong(time);
	}
	
	public void setValue(Long timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "Timestamp [timestamp=" + timestamp + "]";
	}

}
