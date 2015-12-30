package es.uvigo.esei.dgss.exercises.domain;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Inheritance
@XmlAccessorType(XmlAccessType.FIELD)
@DiscriminatorColumn(name = "POST_TYPE")
public class Post {

	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private Timestamp date;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private User user;

	@ManyToMany(mappedBy = "postsliked", fetch = FetchType.EAGER)
	private List<User> likes = new LinkedList<User>();

	@XmlTransient
	@OneToMany(mappedBy = "post")
	private List<Comment> comentarios = new LinkedList<Comment>();

	public Post() {
		date = new java.sql.Timestamp(Calendar.getInstance().getTimeInMillis());
	}

	public int getId() {
		return id;
	}

	public Timestamp getDate() {
		return date;
	}

	void internalAddUser(User user) {
		likes.add(user);
	}

	void internalRemoveUser(User user) {
		likes.remove(user);
	}

	void internalAddComment(Comment comment) {
		comentarios.add(comment);
	}

	void internalRemoveComment(Comment comment) {
		comentarios.remove(comment);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
		user.internalAddPost(this);
	}

	void internalAddLike(User u) {
		likes.add(u);
	}

	public List<User> getLikes() {
		return likes;
	}

	@Override
	public String toString() {
		return "Post [idPost=" + id + ", user=" + user + "]";
	}


	public void setLikes(List<User> usuarios) {
		this.likes = usuarios;
	}

	public List<Comment> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comment> comentarios) {
		this.comentarios = comentarios;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}
	
}
