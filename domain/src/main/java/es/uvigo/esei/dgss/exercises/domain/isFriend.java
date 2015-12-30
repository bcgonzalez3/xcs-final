package es.uvigo.esei.dgss.exercises.domain;

import java.sql.Date;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@IdClass(isFriendId.class)
public class isFriend {
	
	private Date date;

	@Id
	@ManyToOne
	  @PrimaryKeyJoinColumn(referencedColumnName="login")
	private User friendTo;
	
	@Id
	@ManyToOne
	  @PrimaryKeyJoinColumn(referencedColumnName="login")
	private User friendFrom;

	public isFriend() {
		date=new Date(Calendar.getInstance().getTimeInMillis());
	}

	public User getFriendTo() {
		return friendTo;
	}

	public void setFriendTo(User friendTo) {
		this.friendTo = friendTo;
		friendTo.internalAddFriendTo(this);
	}

	public User getFriendFrom() {
		return friendFrom;
	}

	public void setFriendFrom(User friendFrom) {
		this.friendFrom = friendFrom;
		friendFrom.internalAddAddFriendFrom(this);
	}

	@Override
	public String toString() {
		return "isFriend [friendTo=" + friendTo + ", friendFrom=" + friendFrom + "]";
	}

}
