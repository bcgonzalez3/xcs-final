package es.uvigo.esei.dgss.exercises.domain;

import java.sql.Timestamp;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@IdClass(CommentId.class)
public class Comment {

	@Id
	@ManyToOne
	  @PrimaryKeyJoinColumn(referencedColumnName="login")
	private User user;
	
	@Id
	@ManyToOne
	  @PrimaryKeyJoinColumn(referencedColumnName="id")
	private Post post;
	
	@Id
	private Timestamp date;
	
	private String comment;
	
	public Comment (){
		date = new java.sql.Timestamp(Calendar.getInstance().getTimeInMillis());
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Timestamp getDate() {
		return date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
		user.internalAddComment(this);
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
		post.internalAddComment(this);
	}
	
	
}
