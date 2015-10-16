package facade;

import java.util.ArrayList;

import users.User;
import easyaccept.EasyAccept;
import factory.UserFactory;
import java.util.HashMap;

public class Facade {

	
	public static void main(String[] args) {
	    args = new String[] {"facade.Facade", "use_cases/usecase_1.txt", "use_cases/usecase_2.txt"};
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
	
	public String iniciaSistema(){
		return "Sistema iniciado";
	}
	
	public User getLogged(){
		return this.logged;
		
	}
	
	public String cadastraUsuario (String nome, String email, String senha, String dataDeNascimento) throws Exception{
		
		User usuario = userFactory.makeUser(nome, email, senha, dataDeNascimento);
		this.allUsers.add(usuario);

		return email;
	}
	
	public String cadastraUsuario (String nome, String email, String senha, String dataDeNascimento, String foto) throws Exception{
		
		User usuario = userFactory.makeUser(nome, email, senha, dataDeNascimento, foto);
		this.allUsers.add(usuario);

		return email;
	}
	
	public ArrayList<User> getAllUsers(){
		
		return this.allUsers;
	}
	//ainda eh um esboço do que irá ser o método de login. soh estou testando os casos de uso. ainda analisando a melhor forma de fazer.
	public String login(String email, String password) throws Exception{
		
		int sentry = 0;
		
		if(this.logged == null){
			
			for (User user : allUsers) {
				
				sentry = sentry + 1;
				
				if(user.getEmail().equals(email)){
					
					if(user.getPassword().equals(password)){
						
						this.logged = user;
						
						return "Login successeful";
					}else{
						throw new Exception("Nao foi possivel realizar login. Senha invalida.");
					}
				}else if(sentry == allUsers.size()){
					throw new Exception("Nao foi possivel realizar login. Um usuarix com email "+email+" nao esta cadastradx.");
				}
			}
		}else{
			throw new Exception("Nao foi possivel realizar login. Um usuarix ja esta logadx: "+logged.getName()+".");
		}
		return "login not made";
	}
	
	public String logout() throws Exception{
		
		if(this.logged != null){
			this.logged = null;
			
			return "logout successefull";
			
		}else{
			
			throw new Exception("Nao eh possivel realizar logout. Nenhum usuarix esta logadx no +pop.");
		}
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

	public String getInfoUsuario(String field) throws Exception{
		
		if(field.equals("Nome")){
			return logged.getName();

		}else if (field.equals("Senha")){
			throw new Exception("A senha dx usuarix eh protegida.");
			
		}else if (field.equals("Data de nascimento")){
			return logged.getBirthdate().toString();
			
		}else if (field.equals("Foto")){
			return logged.getImage();
			
		}else{
			throw new Exception("You need to specify a valid field.");
		}
	}
	public String getInfoUsuario(String field, String id) throws Exception{
		
		int sentry = 0;
		
		for(User user : allUsers) {
			
			sentry = sentry + 1;
			
			if(user.getEmail().equals(id)){
				
				if(field.equals("Nome")){
					return user.getName();

				}else if (field.equals("Senha")){
					throw new Exception("A senha dx usuarix eh protegida.");
					
				}else if (field.equals("Data de Nascimento")){
					return user.getBirthdate().toString();
					
				}else if (field.equals("Foto")){
					return user.getImage();
				}
			}else if(sentry == allUsers.size()){
				throw new Exception("Um usuarix com email "+ id +" nao esta cadastradx.");
			}
		}
		
		throw new Exception("You need to specify a valid field.");
	}
	
	public String removeUsuario(String id) throws Exception{
		
		int sentry = 0;
		
		for(User user : allUsers){
			
			sentry = sentry + 1;
			
			if(user.getEmail().equals(id)){
				allUsers.remove(user);
				
				return "user successfully removed.";
			}else if(sentry == allUsers.size()){
				
				throw new Exception("Invalid field.");
			}
		}
		
		throw new Exception("You need to specify a valid field.");
	}
	
	public String fechaSistema() throws Exception{
		
		if(logged == null){
			return "system closed successeful.";
		}else{
			throw new Exception("Nao foi possivel fechar o sistema. Um usuarix ainda esta logadx.");
		}
	}
}
