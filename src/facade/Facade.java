package facade;

import java.util.ArrayList;

import users.User;
import factory.UserFactory;

public class Facade {
	
	private ArrayList<User> allUsers;
	private UserFactory userFactory;
	private User logged;
	
	public Facade(){
		
		this.allUsers = new ArrayList<User>();
		this.userFactory = new UserFactory();
		this.logged = null;
	}
	
	public User getLogged(){
		return this.logged;
		
	}
	
	public boolean cadastro (String email, String name, String password, String birthdate, String image, String phone) throws Exception{
		
		User user = userFactory.makeUser(email, name, password, birthdate, image, phone);
		
		return allUsers.add(user);
	}
	
	public ArrayList<User> getAllUsers(){
		
		return this.allUsers;
	}
	
	public boolean login(User user){
		if(this.logged == null){
			logged = user;
			
			return true;
			
		}else if(this.logged != null){
			System.out.println("you already logged");
			
			return false;
		}
		
		return false;
	}
	
	public boolean logout(){
		if(this.logged != null){
			this.logged = null;
			
			return true;
		}else if(this.logged == null){
			System.out.println("You already dislogged.");
			
			return false;
		}
		
		return false;
	}
	
	public boolean addFriend(User user){
		
		if(logged != null){
			logged.addFriend(user);
			
			return true;
		}else{
			System.out.println("You need to login.");
			
			return false;
		}
	}
	
	public boolean removeFriend(User user){
		
		if(logged != null){
			logged.removeFriend(user);
			
			return true;
		}else{
			System.out.println("You need to login.");
			
			return false;
		}
	}
	
	public void postInMural(User userReceive, String message){
		
		if(logged != null){
			if(logged.getFriends().contains(userReceive)){
				logged.postInMural(logged, userReceive, message);
				
			}else{
				System.out.println("You need be friend to use this action.");
			}
		}else{
			System.out.println("You need to login to use this action.");
			
		}
		
	}

}
