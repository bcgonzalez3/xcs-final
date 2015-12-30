package es.uvigo.esei.dgss.exercises.web;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import es.uvigo.esei.dgss.exercises.domain.Comment;
import es.uvigo.esei.dgss.exercises.domain.Link;
import es.uvigo.esei.dgss.exercises.domain.Photo;
import es.uvigo.esei.dgss.exercises.domain.Post;
import es.uvigo.esei.dgss.exercises.domain.User;
import es.uvigo.esei.dgss.exercises.domain.isFriend;

@Dependent
public class Facade {

	@PersistenceContext
	private EntityManager em;

	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	public User addUser(String login, String name, String password,
			byte[] picture) {
		User user = new User(login);

		user.setName(name);
		user.setPassword(password);
		user.setPicture(picture);

		em.persist(user);

		return user;
	}

	public isFriend addFriendship(User u1, User u2) {
		isFriend friendship = new isFriend();
		friendship.setFriendTo(u1);
		friendship.setFriendFrom(u2);

		em.persist(friendship);

		return friendship;
	}

	public Link addLink(String URL, User user) {
		Link link = new Link();
		link.setURL(URL);
		link.setUser(user);

		em.persist(link);

		return link;
	}

	public Photo addPhoto(String content, User user) {
		Photo photo = new Photo();
		photo.setContent(content);
		photo.setUser(user);

		em.persist(photo);

		return photo;
	}

	public Comment addComment(String comentario, Post post, User user) {
		Comment comment = new Comment();
		comment.setPost(post);
		comment.setUser(user);
		comment.setComment(comentario);

		em.persist(comment);

		return comment;
	}

	public void likePost(User user, Post post) {
		user.likePost(post);
	}

	public List<User> getAllFriends(User u) {
		List<isFriend> friendsFrom = u.getFriendsFrom();
		List<isFriend> friendsTo = u.getFriendsTo();
		List<User> friends = new LinkedList<User>();

		for (final isFriend friendship : friendsFrom) {
			friends.add(friendship.getFriendTo());
		}
		for (final isFriend friendship : friendsTo) {
			friends.add(friendship.getFriendFrom());
		}

		return friends;
	}

	public List<Post> getAllPostsOfFriendsOf(User u) {
		List<User> friends = getAllFriends(u);
		List<Post> posts = new LinkedList<Post>();

		for (final User friend : friends) {
			posts.addAll(friend.getPosts());
		}
		return posts;
	}

	public List<Post> getPostsCommentedByFriendsOfAfter(User u, Date date) {
		List<User> friends = getAllFriends(u);
		List<Post> posts = new LinkedList<Post>();
		for (final User friend : friends) {
			List<Comment> comments = friend.getComments();
			for (final Comment comment : comments) {
				if (comment.getDate().compareTo(date) > 0) {
					posts.add(comment.getPost());
				}
			}
		}
		return posts;
	}

	public List<User> getFriendsOfWhoLikes(User user, Post post) {
		List<User> friends = getAllFriends(user);
		List<User> friendsLike = new LinkedList<User>();
		for (final User ulikes : post.getLikes()) {
			if (friends.contains(ulikes))
				friendsLike.add(ulikes);
		}
		return friendsLike;
	}

	public List<Photo> getPicturesLikedBy(User u) {
		List<Photo> photos = new LinkedList<Photo>();
		List<Post> likes = u.getPostsliked();
		for (final Post like : likes) {
			if (like.getClass().equals(Photo.class))
				photos.add((Photo) like);
		}
		return photos;
	}

	public List<User> getPotentialFriends(User u) {
		List<User> PFriends = new LinkedList<User>();
		List<User> friends = getAllFriends(u);
		for (final User friend : friends) {
			List<User> friendsOfFriends = getAllFriends(friend);
			for (final User friendof : friendsOfFriends) {
				if (!friends.contains(friendof) && !PFriends.contains(friendof)
						&& !friendof.equals(u))
					PFriends.add(friendof);
			}
		}
		return PFriends;
	}
}
