package factory;

import users.User;

public class UserFactory {
	
	public UserFactory(){
		
	}
	
	public User makeUser(String email, String name, String password, String birthdate, String image, String phone) throws Exception{
		
		User user;
		
		user = new User(email, name, password, birthdate, image, phone);
		
		return user;	
	}

}
