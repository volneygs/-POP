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
	
	/*userSend = usuario que vai enviar a mensagem, o nome dele deve ser passado para identificacao do post
	*userReceive = usuario que vai receber a mensagem.
	*/
	public boolean postInMural(User userSend, User userReceive, String message){
		
		Post post = makePost.makePost(userSend, message);
		
		return userReceive.mural.add(post);
		
	}

}
