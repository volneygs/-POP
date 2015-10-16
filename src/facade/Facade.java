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
	public void login(String email, String password) throws Exception{
				
		if(this.logged == null){
			
			for (int i = 0; i < allUsers.size(); i++) {
				
				if(allUsers.get(i).getEmail().equals(email)){
					
					if(allUsers.get(i).getPassword().equals(password)){
						
						this.logged = allUsers.get(i);
					}
				}
			}

		/*	if(allUsers.get(id).getEmail().equals(email)){
				//System.out.println("pinga ni mim");
				
				if(password.equals(allUsers.get(id).getPassword())){
					
					this.logged = allUsers.get(email);
					
				} else { throw new Exception("Failed to login. Invalid password."); }
			
			} else { throw new Exception("Failed to login. There's no registered user with this email adress (" + email + ")."); }
		*/	
		}
	
	}
	
	/*public boolean login(User user){
		
		if(this.logged == null){
			logged = user;
			
			return true;
			
		}else if(this.logged != null){
			System.out.println("you already logged");
			
			return false;
		}
		
		return false;
	}*/
	
	public boolean logout(){
		if(this.logged != null){
			this.logged = null;
			
			return true;
		}else if(this.logged == null){
			System.out.println("You're already logged off.");
			
			return false;
		}
		
		return false;
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
		/*if (id.contains("id") && id.contains("@") == false){

			if (field.equals("name")){
				return allUsers.get(id).getName();

			}else if (field.equals("password")){
				throw new Exception("The user's password is protected.");
		
			}else if (field.equals("birthdate")){
				return allUsers.get(id).getBirthdate().toString();
		
			}else if (field.equals("picture")){
				return allUsers.get(id).getImage();
			}
		
		}else if (id.contains("@")){
			if (field.equals("name") && allUsers.containsValue(id)){
				return allUsers.get(id).getName();
				
			}else{
				throw new Exception("There's no registered user with this email adress (" + id + ").");
			}
		}
		
		throw new Exception("You need to specify a valid field."); */
	}
//metodo errado. vou corrigir isso na segunda-feira!
	
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
//teste commit aqui!

//teste commit aqui também!
}
