package facade;

import java.util.ArrayList;

import users.User;
import easyaccept.EasyAccept;
import factory.UserFactory;
import java.util.HashMap;

public class Facade {

	
	public static void main(String[] args) {
	    args = new String[] {"facade.Facade", "use_cases/usecase_1.txt"};
	    EasyAccept.main(args);
	}

	private int userNum = 1;
	private HashMap<String, User> allUsers;
	private UserFactory userFactory;
	private User logged;
	
	public Facade(){
		
		this.allUsers = new HashMap<String, User>();
		this.userFactory = new UserFactory();
		this.logged = null; // checks if there's a user logged in.
	}
	
	public User getLogged(){
		return this.logged;
		
	}
	
	public String register (String email, String name, String password, String birthdate) throws Exception{
		
		String id = "id" + userNum;
		User user = userFactory.makeUser(email, name, password, birthdate);
		allUsers.put(id, user);
		userNum = userNum + 1;

		return id;
	}
	
	public String register (String email, String name, String password, String birthdate, String image) throws Exception{
		
		String id = "id" + userNum;
		User user = userFactory.makeUser(email, name, password, birthdate, image);
		allUsers.put(id, user);
		userNum = userNum + 1;

		return id;
	}
	
	public HashMap<String, User> getAllUsers(){
		
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

	public String getUserInfo(String field, String id) throws Exception{
		
		if (id.contains("id") && id.contains("@") == false){

			if (field.equals("name")){
				return allUsers.get(id).getName();

			}else if (field.equals("password")){
				throw new Exception("The user's password is protected.");
		
			}else if (field.equals("birthdate")){
				return allUsers.get(id).getBirthdate().toString();
		
			}else if (field.equals("picture")){
				return allUsers.get(id).getImage();
			}
		
		}else if (id.contains("@")){
			if (field.equals("name") && allUsers.containsValue(id)){
				return allUsers.get(id).getName();
				
			}else{
				throw new Exception("There's no registered user with this email adress (" + id + ").");
			}
		}
		
		throw new Exception("You need to specify a valid field.");
	}
	
public String getUserInfo(String field) throws Exception{

		if (field.equals("name")){
			return this.logged.getName();

		}else if (field.equals("password")){
			throw new Exception("The user's password is protected.");
		
		}else if (field.equals("birthdate")){
			return this.logged.getBirthdate().toString();
		
		}else if (field.equals("picture")){
			return this.logged.getImage();
		}
	throw new Exception("You need to specify a valid field.");
	}

}
