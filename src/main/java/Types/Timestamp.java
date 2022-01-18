package Types;

import java.io.Serializable;
import java.util.Date;

/**
 * Timestamp represente la date d'une mesure
 * 
 * Elle correspond au nombre de milisecondes ecoulees depuis le 1er janvier 1970
 * (cela correspond au Date.getTime() de Java)
 */
public class Timestamp implements Serializable, Comparable<Timestamp> {

	private static final long serialVersionUID = -592635719730128730L;
	private Long timestamp; // La date

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

	@Override
	public int compareTo(Timestamp o) {
		return this.timestamp.compareTo(o.timestamp);
	}

}
