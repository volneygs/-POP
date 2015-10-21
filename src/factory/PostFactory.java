package factory;

import users.Post;
import users.User;

public class PostFactory {
	
	public PostFactory(){
		
	}
	
	public Post createPost(String message, String date) throws Exception{
		
		Post post = new Post(message, date);
		
		return post;
		
	}

}
