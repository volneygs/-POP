package factory;

import users.Post;
import users.User;

public class PostFactory {
	
	public PostFactory(){
		
	}
	
	public Post makePost(User user, String message){
		
		Post post = new Post(user, message);
		
		return post;
		
	}

}
