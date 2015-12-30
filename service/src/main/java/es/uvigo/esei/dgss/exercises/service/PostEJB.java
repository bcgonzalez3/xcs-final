package es.uvigo.esei.dgss.exercises.service;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import es.uvigo.esei.dgss.exercises.domain.Comment;
import es.uvigo.esei.dgss.exercises.domain.Link;
import es.uvigo.esei.dgss.exercises.domain.Photo;
import es.uvigo.esei.dgss.exercises.domain.Post;
import es.uvigo.esei.dgss.exercises.domain.User;
import es.uvigo.esei.dgss.exercises.domain.Video;

@Stateless
public class PostEJB {

	@Resource
	private SessionContext ctx;
	
	@PersistenceContext
	private EntityManager em;
	
	@EJB
	private UserEJB userEJB;

	public Post findPost(Integer id) {
		return em.find(Post.class, id);
	}
	
	public void updatePost(Post p) {
		final Post p2 = em.find(Post.class, p.getId());
		p2.setComentarios(p.getComentarios());
		p2.setDate(p.getDate());
		p2.setUser(p.getUser());
		p2.setLikes(p.getLikes());
	}
	
	public List<Post> getMyWall() {
		User user = userEJB.getUser();
		return getWall(user);

	}

	public List<Post> getMyPosts() {
		return userEJB.getUser().getPosts();
	}
	
	public List<Post> getWall(User us) {
		User user = em.find(User.class, us.getLogin());
		List<User> friends = userEJB.getAllFriends(user);
		List<Post> posts = new LinkedList<Post>();

		for (final User friend : friends) {
			posts.addAll(friend.getPosts());
		}
		posts.addAll(user.getPosts());

		return posts;
	}

	public Link addLink (String URL, User user) {
		Link link = new Link ();
		link.setURL(URL);
		link.setUser(user);	
		
		em.persist(link);
		
		return link;
	}
	
	public Photo addPhoto (String content, User user) {
		Photo photo = new Photo ();
		photo.setContent(content);
		photo.setUser(user);	
		
		em.persist(photo);
		
		return photo;
	}
	
	public Video addVideo (String duration, User user) {
		Video video = new Video ();
		video.setDuration(duration);
		video.setUser(user);	
		
		em.persist(video);
		
		return video;
	}
	
	public void removePost (Post p)
	{
		em.createQuery("DELETE FROM Post p WHERE p.id = :id")
		 	.setParameter("id", p.getId()).executeUpdate();
	}
		
	public void updateLink (Link l) {
		final Link l2 = em.find(Link.class,  l.getId());
		l2.setURL(l.getURL());
	}
	
	public void updateVideo (Video v) {
		final Video v2 = em.find(Video.class, v.getId());
		v2.setDuration(v.getDuration());
	}
	
	public void updatePhoto (Photo p) {
		final Photo p2 = em.find(Photo.class, p.getId());
		p2.setContent(p.getContent());
	}
	
	public Comment addComment (String comentario, Post p, User u) {
		Post post = em.find(Post.class,  p.getId());
		User user = em.find(User.class,  u.getLogin());
		Comment comment = new Comment ();
		comment.setPost(post);
		comment.setUser(user);	
		comment.setComment(comentario);
		
		em.persist(comment);
		
		return comment;
	}
	
	public void likePost (Post p) {
		userEJB.getUser().likePost(p);
	}

}
