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
		
		volney = new User("volneygsilva@gmail.com", "volney", "vaicorinthians", "01/12/1993", "volney.png");
		william = new User("williamferreiragt@gmail.com", "william", "kamehameha123", "06/04/1995", "william.png");
		
		
	}
	
	@Test
	
	public void testLogout() throws Exception{
		
		Assert.assertEquals(null, facade.getLogged());
		
		//facade.login(volney);
		Assert.assertEquals(volney, facade.getLogged());
		
		facade.logout();
		Assert.assertEquals(null, facade.getLogged());
		
	}
	
/*	@Test
	public void testAddRemoveFriendAndPost(){
		
		//facade.login(volney);
		
		facade.adicionaAmigo(william);
		Assert.assertTrue(facade.getLogged().getFriends().contains(william));
		
		facade.postInMural(william, "teste", "11/10/15");
		Assert.assertEquals("volney: teste", william.getMural().get(0).visualizePost());
		
		facade.removeAmigo(william);
		Assert.assertFalse(facade.getLogged().getFriends().contains(william));
		
	}
	 */

}
