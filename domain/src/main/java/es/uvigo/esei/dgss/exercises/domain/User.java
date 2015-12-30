package es.uvigo.esei.dgss.exercises.domain;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@XmlAccessorType(XmlAccessType.FIELD)
public class User {
	@Id
	private String login;

	private String name;

	@XmlTransient
	private String password;
	
	@XmlTransient
	private String role;

	private byte[] picture;
	
	@XmlTransient
	@OneToMany(mappedBy = "user")
	private List<Post> posts = new LinkedList<Post>();
	
	@XmlTransient
	@ManyToMany
	@JoinTable(name = "likes", joinColumns = {
			@JoinColumn(name = "login", referencedColumnName = "login") }, inverseJoinColumns = {
					@JoinColumn(name = "post", referencedColumnName = "id") })
	private List<Post> postsliked = new LinkedList<Post>();
	
	@XmlTransient
	@OneToMany(mappedBy = "user")
	private List<Comment> comments = new LinkedList<Comment>();

	@XmlTransient
	@OneToMany(mappedBy = "friendTo")
	private List<isFriend> friendsTo = new LinkedList<isFriend>();

	@XmlTransient
	@OneToMany(mappedBy = "friendFrom")
	private List<isFriend> friendsFrom = new LinkedList<isFriend>();

	User() {

	}

	public User(String login) {
		this.login = login;
		this.role = "user";
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Post> getPostsliked() {
		return postsliked;
	}

	public List<Post> getPosts() {
		return posts;
	}

	void internalAddPost(Post post) {
		posts.add(post);
	}

	void internalRemovePost(Post post) {
		posts.remove(post);
	}

	public List<Comment> getComments() {
		return comments;
	}

	void internalAddComment(Comment comment) {
		comments.add(comment);
	}

	void internalRemoveComments(Comment comment) {
		comments.remove(comment);
	}

	public List<isFriend> getFriendsTo() {
		return friendsTo;
	}

	void internalAddFriendTo(isFriend isfriend) {
		friendsTo.add(isfriend);
	}

	void internalRemoveAddFriendTo(isFriend isfriend) {
		friendsTo.remove(isfriend);
	}

	public List<isFriend> getFriendsFrom() {
		return friendsFrom;
	}

	void internalAddAddFriendFrom(isFriend isfriend) {
		friendsFrom.add(isfriend);
	}

	void internalRemoveFriendFrom(isFriend isfriend) {
		friendsFrom.remove(isfriend);
	}

	public void likePost(Post post) {
		postsliked.add(post);
		post.internalAddLike(this);
	}

	@Override
	public String toString() {
		return "User [login=" + login + ", name=" + name + "]";
	}

}
