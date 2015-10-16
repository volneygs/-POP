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
	
	private ArrayList<User> allUsers;
	private UserFactory userFactory;
	private User logged;
	
	public Facade(){
		
		this.allUsers = new ArrayList< User>();
		this.userFactory = new UserFactory();
		this.logged = null; // checks if there's a user logged in.
	}
	
	public User getLogged(){
		return this.logged;
		
	}
	
	public String register (String name, String email, String password, String birthdate) throws Exception{
		
		User user = userFactory.makeUser(name, email, password, birthdate);
		this.allUsers.add(user);

		return email;
	}
	
	public String register (String name, String email, String password, String birthdate, String image) throws Exception{
		
		User user = userFactory.makeUser(name, email, password, birthdate, image);
		this.allUsers.add(user);

		return email;
	}
	
	public ArrayList<User> getAllUsers(){
		
		return this.allUsers;
	}
	//ainda eh um esboço do que irá ser o método de login. soh estou testando os casos de uso. ainda analisando a melhor forma de fazer.
	public String login(String email, String password) throws Exception{
		
		int sentry = 0;
		
		if(this.logged == null){
			
			for (User user : allUsers) {
				
				sentry = sentry + 1;
				
				if(user.getEmail().equals(email)){
					
					if(user.getPassword().equals(password)){
						
						this.logged = user;
						
						return "Login successeful";
					}else{
						throw new Exception("Failed to login. Invalid password.");
					}
				}else if(sentry == allUsers.size()){
					throw new Exception("Failed to login. There's no registered user with this email adress ("+ email + ").");
				}
			}
		}else{
			throw new Exception("Failed to login. Another user is already logged: " + logged.getName() + ".");
		}
		return "login not made";
	}
	
	public String logout() throws Exception{
		
		if(this.logged != null){
			this.logged = null;
			
			return "logout successefull";
			
		}else{
			
			throw new Exception("Failed to logout. There's no user logged on +Pop at this moment.");
		}
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

	public String getUserInfo(String field) throws Exception{
		
		if(field.equals("name")){
			return logged.getName();

		}else if (field.equals("password")){
			throw new Exception("The user's password is protected.");
			
		}else if (field.equals("birthdate")){
			return logged.getBirthdate().toString();
			
		}else if (field.equals("picture")){
			return logged.getImage();
			
		}else{
			throw new Exception("You need to specify a valid field.");
		}
	}
	public String getUserInfo(String field, String id) throws Exception{
		
		int sentry = 0;
		
		for(User user : allUsers) {
			
			sentry = sentry + 1;
			
			if(user.getEmail().equals(id)){
				
				if(field.equals("name")){
					return user.getName();

				}else if (field.equals("password")){
					throw new Exception("The user's password is protected.");
					
				}else if (field.equals("birthdate")){
					return user.getBirthdate().toString();
					
				}else if (field.equals("picture")){
					return user.getImage();
				}
			}else if(sentry == allUsers.size()){
				throw new Exception("There's no registered user with this email adress ("+ id + ").");
			}
		}
		
		throw new Exception("You need to specify a valid field.");
	}
	
	public String removeuser(String id) throws Exception{
		
		int sentry = 0;
		
		for(User user : allUsers){
			
			sentry = sentry + 1;
			
			if(user.getEmail().equals(id)){
				allUsers.remove(user);
				
				return "user successfully removed.";
			}else if(sentry == allUsers.size()){
				
				throw new Exception("Invalid field.");
			}
		}
		
		throw new Exception("You need to specify a valid field.");
	}
	
	public String closeSystem() throws Exception{
		
		if(logged == null){
			return "system closed successeful.";
		}else{
			throw new Exception("Failed to closeSystem. A user is still logged in.");
		}
	}
}
