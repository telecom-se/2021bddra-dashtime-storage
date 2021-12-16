package Types;

import java.util.Date;

public class Timestamp {

	private long timestamp;

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
	public Timestamp() {
		timestamp = new Date().getTime();
	}
}
