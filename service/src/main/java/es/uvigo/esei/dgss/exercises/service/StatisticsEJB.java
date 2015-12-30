package es.uvigo.esei.dgss.exercises.service;

import java.util.concurrent.TimeUnit;

import javax.ejb.AccessTimeout;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Singleton
@Lock(LockType.WRITE)
@AccessTimeout(value = 20, unit = TimeUnit.SECONDS)
public class StatisticsEJB {
	
	@PersistenceContext
	private EntityManager em;
	
	private long nPosts;
	private long nUsers;
	

	public void updateStatisticsEJB() {
		nPosts = em.createQuery("SELECT count(u) FROM User u", Long.class).getSingleResult();
		nUsers = em.createQuery("SELECT count(p) FROM Post p", Long.class).getSingleResult();
	}

	public void addUser(){
		nUsers+=1;
	}
	
	public void removeUser(){
		nUsers-=1;
	}
	
	public void addPost(){
		nPosts+=1;
	}
	
	public void removePost(){
		nPosts-=1;
	}
	
	@Lock(LockType.READ)
	public long getnPosts() {
		return nPosts;
	}
	
	@Lock(LockType.READ)
	public long getnUsers() {
		return nUsers;
	}
	
		

	
	

}
