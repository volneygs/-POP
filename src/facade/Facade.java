package facade;

import java.util.ArrayList;

import users.User;
import factory.UserFactory;

public class Facade {
	
	private ArrayList<User> allUsers;
	private UserFactory userFactory;
	
	public Facade(){
		
		this.allUsers = new ArrayList<User>();
		this.userFactory = new UserFactory();
	}
	
	public boolean cadastro (String email, String name, String password, String birthdate, String image, int phone) throws Exception{
		
		User user = userFactory.makeUser(email, name, password, birthdate, image, phone);
		
		return allUsers.add(user);
	}
	
	public ArrayList<User> getAllUsers(){
		
		return allUsers;
	}

}
