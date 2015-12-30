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

import es.uvigo.esei.dgss.exercises.domain.Link;
import es.uvigo.esei.dgss.exercises.domain.Photo;
import es.uvigo.esei.dgss.exercises.domain.Post;
import es.uvigo.esei.dgss.exercises.domain.Video;
import es.uvigo.esei.dgss.exercises.service.PostEJB;
import es.uvigo.esei.dgss.exercises.service.UserEJB;

@Path("/post")
@Stateless
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PostRest {
	
	@Context
	private UriInfo uriInfo;
	
	@EJB
	private UserEJB userEJB;
	
	@EJB
	private PostEJB postEJB;
	
	
	//Post
	@GET
	public Response myPosts() {
		List <Post> posts = postEJB.getMyPosts();
		if (posts.isEmpty()) {
			return Response.noContent().build();
		}
		return Response.ok(posts).build();
	}
	
	//Post
	@GET
	@Path("/wall")
	public Response getWall() {
		List <Post> posts = postEJB.getMyWall();
		if (posts.isEmpty()) {
			return Response.noContent().build();
		}
		return Response.ok(posts).build();
	}
	
	@DELETE
	@Path("{idpost}")
	public Response deleteUser(@PathParam("idpost") Integer idpost) {
		Post post = postEJB.findPost(idpost);
		if (post.getUser().equals(userEJB.getUser()))
		{
			postEJB.removePost(post);
			return Response.noContent().build();
		}
		return Response.notModified().build();
	}
	
	@PUT
	public Response updatePost(Post p) {
		postEJB.updatePost(p);
		return Response.ok().build();
	}
	
	@POST
	@Path("{idpost}/like")
	public Response likePost(@PathParam("idpost") Integer idpost) {
		postEJB.likePost(postEJB.findPost(idpost));
		return Response.ok().build();
	}
	
	@POST
	@Path("/link")
	public Response addLink(Link l) {
		Link li=postEJB.addLink(l.getURL(),userEJB.getUser());
		URI userUri = uriInfo.getAbsolutePathBuilder().path(Integer.toString(li.getId())).build();
		return Response.created(userUri).build();
	}
	
	@POST
	@Path("/photo")
	public Response addPhoto(Photo p) {
		Photo pho=postEJB.addPhoto(p.getContent(),userEJB.getUser());
		URI userUri = uriInfo.getAbsolutePathBuilder().path(Integer.toString(pho.getId())).build();
		return Response.created(userUri).build();
	}
	
	@POST
	@Path("/video")
	public Response addVideo(Video v) {
		Video vi=postEJB.addVideo(v.getDuration(),userEJB.getUser());
		URI userUri = uriInfo.getAbsolutePathBuilder().path(Integer.toString(vi.getId())).build();
		return Response.created(userUri).build();
	}
}
