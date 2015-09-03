package testes;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import users.User;

public class TestaUser {
	
	@Before
	public void setUp() throws Exception{
		
		User volney = new User("volneygsilva@gmail.com", "volney", "vaicorinthians", "01/12/1993", "volney.png");
		
	}
	
	@Test
	public void signinTest() throws Exception{
		
		try{
			User userInvalidEmail = new User("", "volney", "vaicorinthians", "01/12/1993", "volney.png");
			
		}catch(Exception e){
			Assert.assertEquals("Invalid email.", e.getMessage());
		}
		
		try{
			User userInvalidName = new User("volneygsilva@gmail.com", "", "vaicorinthians", "01/12/1993", "volney.png");
			
		}catch(Exception e){
			Assert.assertEquals("Invalid name.", e.getMessage());
		}
		
		try{
			User userInvalidPassword = new User("volneygsilva@gmail.com", "volney", "", "01/12/1993", "volney.png");
			
		}catch(Exception e){
			Assert.assertEquals("Invalid password.", e.getMessage());
		}
		
		try{
			User userInvalidBirthDate = new User("volneygsilva@gmail.com", "volney", "vaicorinthians", "", "volney.png");
			
		}catch(Exception e){
			Assert.assertEquals("Invalid birthdate.", e.getMessage());
		}
				
	}

}
