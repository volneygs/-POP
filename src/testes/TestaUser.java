package testes;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import users.User;

public class TestaUser {
	
	@Before
	public void setUp() throws Exception{
		
		User volney = new User("volneygsilva@gmail.com", "volney", "asdf123", "01/12/1993", "eu", "988102876");
		
	}
	
	@Test
	public void testaCriacao() throws Exception{
		
		try{
			User userInvalidEmail = new User("", "volney", "asdf123", "01/12/1993", "eu", "988102876");
			
		}catch(Exception e){
			Assert.assertEquals("Invalid email.", e.getMessage());
		}
		
		try{
			User userInvalidName = new User("volneygsilva@gmail.com", "", "asdf123", "01/12/1993", "eu", "988102876");
			
		}catch(Exception e){
			Assert.assertEquals("Invalid name.", e.getMessage());
		}
		
		try{
			User userInvalidPassword = new User("volneygsilva@gmail.com", "volney", "", "01/12/1993", "eu", "988102876");
			
		}catch(Exception e){
			Assert.assertEquals("Invalid password.", e.getMessage());
		}
		
		try{
			User userInvalidBirthDate = new User("volneygsilva@gmail.com", "volney", "asdf123", "", "eu", "988102876");
			
		}catch(Exception e){
			Assert.assertEquals("Invalid birthdate.", e.getMessage());
		}
		
		try{
			User userInvalidPhoneSmall = new User("volneygsilva@gmail.com", "volney", "asdf123", "01/12/1993", "eu", "150");
			
		}catch(Exception e){
			Assert.assertEquals("Invalid phone.", e.getMessage());
		}
		
		try{
			User userInvalidPhoneLarge = new User("volneygsilva@gmail.com", "volney", "asdf123", "01/12/1993", "eu", "111111111");
			
		}catch(Exception e){
			Assert.assertEquals("Invalid phone.", e.getMessage());
		}
		
		
	}

}
