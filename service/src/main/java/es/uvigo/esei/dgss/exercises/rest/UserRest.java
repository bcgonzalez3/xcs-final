package es.uvigo.esei.dgss.exercises.rest;

import java.net.URI;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import es.uvigo.esei.dgss.exercises.domain.User;
import es.uvigo.esei.dgss.exercises.service.PostEJB;
import es.uvigo.esei.dgss.exercises.service.UserEJB;

@Path("/user")
@Stateless
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserRest {
	
	@Context
	private UriInfo uriInfo;
	
	@EJB
	private UserEJB userEJB;
	
	@EJB
	private PostEJB postEJB;
	
	@GET
	@Path("{login}")
	public Response getUser(@PathParam("login") String login) {
		User user = userEJB.findUser(login);
		if (user == null) {
			return Response.noContent().build();
		}
		return Response.ok(user).build();
	}
	
	@POST
	public Response createUser(User u) {
		User user = userEJB.addUser(u);
		URI userUri = uriInfo.getAbsolutePathBuilder().path(user.getLogin()).build();
		return Response.created(userUri).build();
	}
	
	@PUT
	public Response updateUser(User u) {
		userEJB.updateUser(u);
		return Response.ok().build();
	}
	
	@DELETE
	@Path("{login}")
	public Response deleteUser(@PathParam("login") String login) {
		userEJB.removeUser(userEJB.findUser(login));
		return Response.noContent().build();
	}
	
	//usuario
	@GET
	@Path("/login")
	public Response getLogin() {
		User u = userEJB.getUser();
		if (u == null) {
			return Response.noContent().build();
		}
		return Response.ok(u).build();
	}
	
	
	//Friendship
	@GET
	@Path("/friend")
	public Response getFriends() {
		List<User> u = userEJB.getAllFriends(userEJB.getUser());
		if(u.isEmpty()){
			return Response.noContent().build();
		}
		return Response.ok(u).build();
	}
	
	@POST
	@Path("/requestFriend")
	public Response requestFriendship(User u) {
		userEJB.addFriendship(userEJB.getUser(),u);
		URI userUri = uriInfo.getAbsolutePathBuilder().path(u.getLogin()).build();
		return Response.created(userUri).build();
	}
	
	@POST
	@Path("/responseFriend")
	public Response responseFriendship(User u) {
		userEJB.addFriendship(u,userEJB.getUser());
		URI userUri = uriInfo.getAbsolutePathBuilder().path(u.getLogin()).build();
		return Response.created(userUri).build();
	}

}
