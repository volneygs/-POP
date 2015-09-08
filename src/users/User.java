package users;

import java.util.ArrayList;

import factory.PostFactory;

public class User {
	
	private String email;
	private String name;
	private String password;
	private String birthdate;
	private String image;
	private String pop;
	private ArrayList<Post> mural;
	private ArrayList<User> friends;
	private PostFactory makePost;

	public User(String name, String email, String password, String birthdate) throws Exception{
		
		
		if(name.equals("")){
			throw new Exception("Error: Username field can't be empty.");
			
		}else if(email.equals("")){				
			throw new Exception("Invalid email.");
			
		}else if(password.equals("")){
			throw new Exception("Invalid password.");
			
		}else if(birthdate.equals("")){
			throw new Exception("Invalid birthdate.");
			
		}

		
		this.name = name;
		this.email = email;
		this.password = password;
		this.birthdate = birthdate;
		this.image = "default_image.png";
		this.mural = new ArrayList<Post>();
		this.friends = new ArrayList<User>();
		this.makePost = new PostFactory();
	}
	
	public User(String name, String email, String password, String birthdate, String image) throws Exception{
		
		
		if(name.equals("")){
			throw new Exception("Error: Username field can't be empty.");
			
		}else if(email.equals("")){				
			throw new Exception("Invalid email.");
			
		}else if(password.equals("")){
			throw new Exception("Invalid password.");
			
		}else if(birthdate.equals("")){
			throw new Exception("Invalid birthdate.");
			
		}

		
		this.email = email;
		this.name = name;
		this.password = password;
		this.birthdate = birthdate;
		this.image = image;
		this.mural = new ArrayList<Post>();
		this.friends = new ArrayList<User>();
		this.makePost = new PostFactory();
	}
	
	public ArrayList<Post> getMural(){
		return this.mural;
	}
	
	public ArrayList<User> getFriends(){
		return this.friends;
	}
	
	public String getName(){
		
		return this.name;
	}
	
	public boolean addFriend(User user){
		return this.friends.add(user);
	}
	
	public boolean removeFriend(User user){
		if(this.friends.contains(user)){
			return this.friends.remove(user);
		}else{
			return false;
		}
	}
	
	public boolean postInMural(User userSend, User userReceive, String message){
		
		Post post = makePost.makePost(userSend, message);
		
		return userReceive.mural.add(post);
		
	}

}
