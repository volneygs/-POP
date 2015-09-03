package factory;

import users.User;

public class UserFactory {
	
	public UserFactory(){
		
	}
	
	public User makeUser(String email, String name, String password, String birthdate, String image) throws Exception{
		
		User user;
		
		user = new User(email, name, password, birthdate, image);
		
		return user;	
	}

}
