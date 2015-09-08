package facade;

import java.util.ArrayList;

import users.User;
import easyaccept.EasyAccept;
import factory.UserFactory;

public class Facade {
	
	public static void main(String[] args) {
	    args = new String[] {"facade.Facade", "use_cases/usecase_1.txt"};
	    EasyAccept.main(args);
	}

	
	private ArrayList<User> allUsers;
	private UserFactory userFactory;
	private User logged;
	
	public Facade(){
		
		this.allUsers = new ArrayList<User>();
		this.userFactory = new UserFactory();
		this.logged = null; // checks if there's a user logged in.
	}
	
	public User getLogged(){
		return this.logged;
		
	}
	
	public boolean register (String email, String name, String password, String birthdate) throws Exception{
		
		User user = userFactory.makeUser(email, name, password, birthdate);
		
		return allUsers.add(user);
	}
	
	public boolean register (String email, String name, String password, String birthdate, String image) throws Exception{
		
		User user = userFactory.makeUser(email, name, password, birthdate, image);
		
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
			System.out.println("You're already logged off.");
			
			return false;
		}
		
		return false;
	}
	
	public boolean addFriend(User user){
		
		if(this.logged != null){
			this.logged.addFriend(user);
			
			return true;
		}else{
			System.out.println("You need to login before use this feature.");
			
			return false;
		}
	}
	
	public boolean removeFriend(User user){
		
		if(this.logged != null){
			if(logged.getFriends().contains(user)){
				this.logged.removeFriend(user);
				
				return true;
			}else{
				System.out.println("You and this person aren't friends.");
				
				return false;
			}
		}else{
			System.out.println("You need to login before use this feature.");
			
			return false;
		}
	}
	
	public void postInMural(User userReceive, String message){
		
		if(this.logged != null){

			if(this.logged.getFriends().contains(userReceive)){
				this.logged.postInMural(this.logged, userReceive, message);
				
			}else{
				System.out.println("You need to be friend of this person before use this feature.");
			}
		}else{
			System.out.println("You need to login before use this feature.");
			
		}
		
	}

}
