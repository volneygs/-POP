package factory;

import users.User;

public class UserFactory {
	
	public UserFactory(){
		
	}

	public User createUser(String email, String name, String password, String birthdate) throws Exception{
		
		User user;
		
		user = new User(email, name, password, birthdate);
		
		return user;	
	}
	
	public User createUser(String email, String name, String password, String birthdate, String image) throws Exception{
		
		User user;
		
		user = new User(email, name, password, birthdate, image);
		
		return user;	
	}

}
