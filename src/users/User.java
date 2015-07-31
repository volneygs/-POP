package users;

import java.util.ArrayList;

import factory.PostFactory;

public class User {
	
	private String email;
	private String name;
	private String password;
	private String birthdate;
	private String image;
	private String phone;
	private String pop;
	private ArrayList<Post> mural;
	private ArrayList<User> friends;
	private PostFactory makePost;
	
	
	public User(String email, String name, String password, String birthdate, String image, String phone) throws Exception{
		
		
		if(email.equals("")){
			throw new Exception("Invalid email.");
			
		}else if(name.equals("")){				
			throw new Exception("Invalid name.");
			
		}else if(password.equals("")){
			throw new Exception("Invalid password.");
			
		}else if(birthdate.equals("")){
			throw new Exception("Invalid birthdate.");
			
		}else if(phone.length() <= 8 || phone.length() > 9){
			throw new Exception("Invalid phone.");
		}

		
		this.email = email;
		this.name = name;
		this.password = password;
		this.birthdate = birthdate;
		this.image = image;
		this.phone = phone;
		this.mural = new ArrayList<Post>();
		this.friends = new ArrayList<User>();
		this.makePost = new PostFactory();
	}
	
	public String getName(){
		
		return this.name;
	}
	
	public boolean addFriend(User user){
		return friends.add(user);
	}
	
	public boolean removeFriend(User user){
		return friends.remove(user);
	}
	
	public boolean postInMural(User user, String message){
		
		Post post = makePost.makePost(user, message);
		
		return user.mural.add(post);
		
	}

}
