package facade;

import java.util.ArrayList;

import users.User;
import factory.UserFactory;

public class Facade {
	
	private ArrayList<User> allUsers;
	private UserFactory userFactory;
	private User logged;
	
	public Facade(){
		
		this.allUsers = new ArrayList<User>();
		this.userFactory = new UserFactory();
		//serve para verificar se o usuario esta logado ou nao.
		this.logged = null;
	}
	
	public User getLogged(){
		return this.logged;
		
	}
	
	public boolean cadastro (String email, String name, String password, String birthdate, String image, String phone) throws Exception{
		
		User user = userFactory.makeUser(email, name, password, birthdate, image, phone);
		
		return allUsers.add(user);
	}
	
	public ArrayList<User> getAllUsers(){
		
		return this.allUsers;
	}
	
	public boolean login(User user){
		//verifica e o usuario esta logado.
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
			System.out.println("You already dislogged.");
			
			return false;
		}
		
		return false;
	}
	
	public boolean addFriend(User user){
		
		if(this.logged != null){
			this.logged.addFriend(user);
			
			return true;
		}else{
			System.out.println("You need to login.");
			
			return false;
		}
	}
	
	public boolean removeFriend(User user){
		
		if(this.logged != null){
			if(logged.getFriends().contains(user)){
				this.logged.removeFriend(user);
				
				return true;
			}else{
				System.out.println("You haven't a friend with this expecification.");
				
				return false;
			}
		}else{
			System.out.println("You need to login.");
			
			return false;
		}
	}
	
	public void postInMural(User userReceive, String message){
		//verifica se usuario esta logado
		if(this.logged != null){
			//verifica se o usuario que ira receber o post e amigo.
			if(this.logged.getFriends().contains(userReceive)){
				this.logged.postInMural(this.logged, userReceive, message);
				
			}else{
				System.out.println("You need be friend to use this action.");
			}
		}else{
			System.out.println("You need to login to use this action.");
			
		}
		
	}

}
