package es.uvigo.esei.dgss.exercises.service;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import es.uvigo.esei.dgss.exercises.domain.Post;
import es.uvigo.esei.dgss.exercises.domain.User;
import es.uvigo.esei.dgss.exercises.domain.isFriend;

@Stateless
public class UserEJB {

	@Resource
	private SessionContext ctx;

	@EJB
	private StatisticsEJB statisticsEJB;

	@PersistenceContext
	private EntityManager em;

	public User findUser(String login) {
		return em.find(User.class, login);
	}

	public List<User> findUsers(String nombre) {
		final String query = "SELECT u FROM User u WHERE name LIKE '%" + nombre
				+ "%'";
		return em.createQuery(query, User.class).getResultList();
	}

	public User addUser(String login, String name, String password,	byte[] picture) {
		User user = new User(login);

		user.setName(name);
		user.setPassword(password);
		user.setPicture(picture);

		return addUser(user);
	}

	public User addUser(User u) {
		User user = new User(u.getLogin());

		user.setName(u.getName());
		user.setPassword(u.getPassword());
		user.setPicture(u.getPicture());

		em.persist(user);

		statisticsEJB.addUser();

		return user;
	}

	public void removeUser(User u) {
		em.createQuery("DELETE FROM User u WHERE u.login = :login")
				.setParameter("login", u.getLogin()).executeUpdate();
	}

	public void updateUser(User u) {
		final User u2 = em.find(User.class, u.getLogin());
		u2.setName(u.getName());
		u2.setPassword(u.getPassword());
		u2.setPicture(u.getPicture());
	}

	public isFriend addFriendship(User to, User from) {
		isFriend friendship = new isFriend();
		User userTo = em.find(User.class, to.getLogin());
		User userFrom = em.find(User.class, from.getLogin());
		friendship.setFriendTo(userTo);
		friendship.setFriendFrom(userFrom);

		em.persist(friendship);

		return friendship;
	}

	public User getUser() {
		return em.find(User.class, ctx.getCallerPrincipal().getName());
	}

	public List<User> getRequestFriendship(User u) {
		List<isFriend> friendsFrom = u.getFriendsFrom();
		List<isFriend> friendsTo = u.getFriendsTo();
		List<User> userFriendsTo = new LinkedList<User>();
		List<User> userRequest = new LinkedList<User>();

		for (final isFriend friendship : friendsTo) {
			userFriendsTo.add(friendship.getFriendTo());
		}
		for (final isFriend friendship : friendsFrom) {
			User friendFrom = friendship.getFriendFrom();
			if (!userFriendsTo.contains(friendFrom))
				userRequest.add(friendFrom);
		}

		return userRequest;
	}

	public List<User> getAllFriends(User us) {
		User u = em.find(User.class, us.getLogin());
		List<isFriend> friendsFrom = u.getFriendsFrom();
		List<isFriend> friendsTo = u.getFriendsTo();
		List<User> userFriendsFrom = new LinkedList<User>();
		List<User> friends = new LinkedList<User>();

		for (final isFriend friendship : friendsFrom) {
			userFriendsFrom.add(friendship.getFriendTo());
		}
		for (final isFriend friendship : friendsTo) {
			User friendTo = friendship.getFriendFrom();
			if (userFriendsFrom.contains(friendTo))
				friends.add(friendTo);
		}

		return friends;
	}

	public void likePost(User user, Post post) {
		user.likePost(post);
	}

}
