package Types;

import java.util.Date;

public class Timestamp {

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

	public void setValue(Long timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "Timestamp [timestamp=" + timestamp + "]";
	}

}
