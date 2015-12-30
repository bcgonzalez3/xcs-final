package es.uvigo.esei.dgss.exercises.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.uvigo.esei.dgss.exercises.domain.Link;
import es.uvigo.esei.dgss.exercises.domain.Post;
import es.uvigo.esei.dgss.exercises.domain.User;
import es.uvigo.esei.dgss.exercises.domain.isFriend;
import es.uvigo.esei.dgss.exercises.service.PostEJB;
import es.uvigo.esei.dgss.exercises.service.StatisticsEJB;
import es.uvigo.esei.dgss.exercises.service.UserEJB;

@WebServlet("/SimpleServlet")
public class SimpleServlet extends HttpServlet{
	
//	@Inject
//	private Facade facade;
//	
//	@Resource
//	private UserTransaction transaction;
	
	@EJB
	private UserEJB userEJB;
	
	@EJB
	private PostEJB postEJB;
	
	@EJB
	private StatisticsEJB statisticsEJB;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter writer =resp.getWriter();
		
		writer.println("<html>");
		writer.println("<body>");
		writer.println("<h1>EJB tests</h1>");
		

		
		//work with Facade
		
//		try {
//			transaction.begin();
//			
//			writer.println("<b>1. Create a new user given its login, name, password and picture</b></br>");
		statisticsEJB.updateStatisticsEJB();
		writer.println("Number of user: " + statisticsEJB.getnUsers() + "</br>");
		
		User u = userEJB.addUser("usuario1", "Juan", "pass", new byte[]{});
		User u2 = userEJB.addUser("usuario2", "Pepe", "pass2", new byte[] {});
		User u3 = userEJB.addUser("usuario3", "Maria", "pass3", new byte[] {});
		User u4 = userEJB.addUser("usuario4", "Pepe Jose", "pass4", new byte[] {});
		User u5 = userEJB.addUser("usuario5", "Laura", "pass5", new byte[] {});
		writer.println("User: " + u.getLogin() + "</br>");

		writer.println("Number of user: " + statisticsEJB.getnUsers() + "</br>");
		
//		userEJB.removeUser(u2);
			
//			
//			writer.println("</br><b>2. Create a friendship between two given users</b></br>");
			isFriend f  = userEJB.addFriendship(u, u2);
			isFriend f2 = userEJB.addFriendship(u2, u);
			isFriend f3 = userEJB.addFriendship(u2, u3);
			isFriend f4 = userEJB.addFriendship(u3, u2);
			isFriend f6 = userEJB.addFriendship(u2, u4);
			isFriend f5 = userEJB.addFriendship(u5, u2);
			writer.println("Friendship: " + f.getFriendTo().getLogin() + " between " + f.getFriendFrom().getLogin() + "</br>");
			

//			
//			writer.println("</br><b>3. Get all friends of a given user</b></br>");
//			List <User> friends = facade.getAllFriends(u);
//			writer.println("User: " + u.getLogin() + "</br>");
//			for (User friend : friends){
//				writer.println("Friend: " + friend.getLogin() + "</br>");
//			}
//		
//			writer.println("</br><b>4. Get all posts of the friends of a given user</b></br>");
			Link l1 = postEJB.addLink("http://probando.com", u4);
			Link l2 = postEJB.addLink("http://probando2.com", u2);	
			Link l3 = postEJB.addLink("http://probando3.com", u3);
			Link l4 = postEJB.addLink("http://probando4.com", u2);
//			List <Post> posts = facade.getAllPostsOfFriendsOf(u);
//			writer.println("User: " + u.getLogin() + "</br>");
//			for (Post post : posts){
//				writer.println("Friend: " + post.getUser().getLogin() + " (" + post.getDate() +") " + "</br>");
//			}
//			
//			writer.println("</br><b>5. Get the posts that have been commented by the friends of a given user after a given date</b></br>");
//			Comment c1 = facade.addComment("Comentario1",l1, u2);		
//			Comment c2 = facade.addComment("Comentario2",l2, u3);
//			Comment c3 = facade.addComment("Comentario3",l2, u2);	
//			Date date=new Date(Calendar.getInstance().getTimeInMillis());
//			date.setMonth(date.getMonth()-1);
//			writer.println("User: " + u.getLogin() + " Date: " + date + "</br>");
//			List <Post> postsCBFOA = facade.getPostsCommentedByFriendsOfAfter(u,date);
//			for (Post post : postsCBFOA){
//				writer.println("Friend: " + post.getUser().getLogin() + " (" + post.getDate() +") " + "</br>");
//			}
//			Date date2=new Date(Calendar.getInstance().getTimeInMillis());
//			date2.setMonth(date2.getMonth()+1);
//			writer.println("User: " + u.getLogin() + " Date: " + date2 + "</br>");
//			List <Post> postsCBFOA2 = facade.getPostsCommentedByFriendsOfAfter(u,date2);
//			for (Post post : postsCBFOA2){
//				writer.println("Friend: " + post.getUser().getLogin() + " (" + post.getDate() +") " + "</br>");
//			}
//
//			writer.println("</br><b>6. Get the users which are friends of a given user who like a given post</b></br>");
//			facade.likePost(u3,l1);
//			facade.likePost(u4,l1); //
//			facade.likePost(u3,l2); //
//			facade.likePost(u2,l1);
//			List <User> usersLikePost = facade.getFriendsOfWhoLikes(u,l1);
//			writer.println("Post: (" + l1.getDate() +") " + "</br>");
//			for (User userlike : usersLikePost){
//				writer.println("User: " + userlike.getLogin() + "</br>");
//			}
//			
//			writer.println("</br><b>7. Give me all the pictures a given user likes</b></br>");
//			Photo p1 = facade.addPhoto("img1.jpeg", u2);
//			Photo p2 = facade.addPhoto("img2.jpeg", u2);
//			Photo p3 = facade.addPhoto("img3.jpeg", u3);
//			Photo p4 = facade.addPhoto("img4.jpeg", u5);
//
//			facade.likePost(u,p1);
//			facade.likePost(u,p2);
//			facade.likePost(u2,p3); //
//			facade.likePost(u,p4);
//			List <Photo> pictures = facade.getPicturesLikedBy(u);
//			writer.println("User: " + u.getLogin() + "</br>");
//			for (Photo pic : pictures){
//				writer.println("Picture: " + pic.getContent() + "</br>");
//			}
//			
//			writer.println("</br><b>8. Create a list of potential friends for a given user</b></br>");
//			List<User> Pfriends = facade.getPotentialFriends(u);
//			writer.println("User: " + u.getLogin() + "</br>");
//			for (User friend : Pfriends){
//				writer.println("Potential friend: " + friend.getLogin() + "</br>");
//			}
//			
//			transaction.commit();
//		} catch (NotSupportedException 
//				| SystemException 
//				| SecurityException 
//				| IllegalStateException 
//				| RollbackException 
//				| HeuristicMixedException 
//				| HeuristicRollbackException e) {
//			
//			try {
//				transaction.rollback();
//			} catch (IllegalStateException 
//					| SecurityException 
//					| SystemException e1) {
//				e1.printStackTrace();
//			}
//			e.printStackTrace();
//			
//		}

		
		writer.println("</body>");
		writer.println("</html>");

	}

}
