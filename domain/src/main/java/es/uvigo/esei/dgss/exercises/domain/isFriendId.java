package es.uvigo.esei.dgss.exercises.domain;

import java.io.Serializable;

public class isFriendId implements Serializable {
	
	private String friendTo;

	private String friendFrom;

	public isFriendId() {
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((friendFrom == null) ? 0 : friendFrom.hashCode());
		result = prime * result + ((friendTo == null) ? 0 : friendTo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		isFriendId other = (isFriendId) obj;
		if (friendFrom == null) {
			if (other.friendFrom != null)
				return false;
		} else if (!friendFrom.equals(other.friendFrom))
			return false;
		if (friendTo == null) {
			if (other.friendTo != null)
				return false;
		} else if (!friendTo.equals(other.friendTo))
			return false;
		return true;
	}

	

}
