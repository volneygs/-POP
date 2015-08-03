package testes;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import users.User;
import facade.Facade;

public class TestFacade {
	
	private Facade facade;
	private User volney;
	private User william;
	
	@Before
	public void setUp() throws Exception{
		
		facade = new Facade();
		
		volney = new User("volneygsilva@gmail.com", "volney", "asdf123", "01/12/1993", "eu", "988102876");
		william = new User("williamberg@gmail.com", "william", "123asdf", "01/12/1993", "eu", "999999999");
		
		
	}
	
	@Test
	
	public void testLoginLogout(){
		
		Assert.assertEquals(null, facade.getLogged());
		
		facade.login(volney);
		Assert.assertEquals(volney, facade.getLogged());
		
		facade.logout();
		Assert.assertEquals(null, facade.getLogged());
		
	}
	
	@Test
	public void testAddRemoveFriendAndPost(){
		
		facade.login(volney);
		
		facade.addFriend(william);
		Assert.assertTrue(facade.getLogged().getFriends().contains(william));
		
		facade.postInMural(william, "teste");
		Assert.assertEquals("volney: teste", william.getMural().get(0).visualizePost());
		
		facade.removeFriend(william);
		Assert.assertFalse(facade.getLogged().getFriends().contains(william));
		
	}
	

}
