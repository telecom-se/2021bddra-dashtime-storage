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

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

}
