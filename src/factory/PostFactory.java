package factory;

import java.io.Serializable;

import post.Post;
import users.User;

public class PostFactory implements Serializable {
	
	public PostFactory(){
		
	}
	
	public Post createPost(String message, String date) throws Exception{
		
		Post post = new Post(message, date);

		return post;
		
	}

}
