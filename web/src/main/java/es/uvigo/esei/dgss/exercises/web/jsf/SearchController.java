package es.uvigo.esei.dgss.exercises.web.jsf;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import es.uvigo.esei.dgss.exercises.domain.Post;
import es.uvigo.esei.dgss.exercises.domain.User;
import es.uvigo.esei.dgss.exercises.service.PostEJB;
import es.uvigo.esei.dgss.exercises.service.UserEJB;

@ManagedBean(name="searchController")
@SessionScoped
public class SearchController implements Serializable {
	private String searchString;
	private List <User> searchResults;
	private User selectedUser;
	private List <Post> selectedUserPosts;

	@Inject
	private UserEJB userEJB;

	@Inject
	private PostEJB postEJB;
	
	
	public String getSearchString() {
		return searchString;
	}

	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}

	public List<User> getSearchResults() {
		return searchResults;
	}

	public void setSearchResults(List<User> searchResults) {
		this.searchResults = searchResults;
	}

	public User getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(User selectedUser) {
		this.selectedUser = selectedUser;
	}

	public List<Post> getSelectedUserPosts() {
		return selectedUserPosts;
	}

	public void setSelectedUserPosts(List<Post> selectedUserPosts) {
		this.selectedUserPosts = selectedUserPosts;
	}

	//devolver string?
	public void doSearch(){
		searchResults = userEJB.findUsers(searchString);
	}

	public void doSelectUser(User usuario){
		selectedUser=usuario;
		selectedUserPosts = postEJB.getWall(usuario);
	}

}
