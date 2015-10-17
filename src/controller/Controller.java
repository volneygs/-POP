package controller;

import java.util.ArrayList;

import factory.UserFactory;
import users.User;

public class Controller {
	
	private ArrayList<User> allUsers;
	private UserFactory userFactory;
	private User logged;
	
	public Controller(){
		this.userFactory = new UserFactory();
		this.allUsers = new ArrayList<User>();
		this.logged = null;
	}
	
	public User getLogged(){
	
		return this.logged;
		
	}
	
	public ArrayList<User> getUsers(){
		
		return new ArrayList<User>();
	}

	public String registerUser (String nome, String email, String senha, String dataDeNascimento) throws Exception{
		
		User usuario = userFactory.makeUser(nome, email, senha, dataDeNascimento);
		this.allUsers.add(usuario);

		return email;
	}
	
	public String registerUser (String name, String email, String password, String birthdate, String picture) throws Exception{
		
		User usuario = userFactory.makeUser(name, email, password, birthdate, picture);
		this.allUsers.add(usuario);

		return email;
	}

	public ArrayList<User> getAllUsers(){
		
		return this.allUsers;
	}

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
			
		}else if (field.equals("Data de Nascimento")){
			return logged.getBirthdate().toString();
			
		}else if (field.equals("Foto")){
			return logged.getImage();
			
		}else{
			throw new Exception("Vc precisa especificar um campo valido.");
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
		
		throw new Exception("Vc precisa especificar um campo valido.");
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

	public void atualizaPerfil(String field, String newField) {
		
	/*	if(nome.equals("") || nome.trim().equals("")){
			throw new Exception("Erro no cadastro de Usuarios. Nome dx usuarix nao pode ser vazio.");
			
		}else if(email.equals("") || email.contains("@") == false || email.contains(".com") == false){				
			throw new Exception("Erro no cadastro de Usuarios. Formato de e-mail esta invalido.");
			
		}else if(senha.equals("")){
			throw new Exception("Invalid password.");
			
		}else if(dataDeNascimento.equals("")){
			throw new Exception("Erro no cadastro de Usuarios. Formato de data esta invalida.");
			
		} */
		
		if(this.logged != null){
			if(field.equals("Nome")){
				logged.setNome(newField);
				
			}else if(field.equals("Foto")){
				logged.setFoto(newField);
				
			}else if(field.equals("E-mail")){
				logged.setEmail(newField);
				
			}else if(field.equals("Data de Nascimento")){
				//set dataDeNascimento
			}
		}
	}
	
	public void atualizaPerfil(String field, String novaSenha, String senhaAntiga) throws Exception{
		
		if(this.logged != null){
			
			logged.mudaSenha(novaSenha, senhaAntiga);
				
		}
	}
	
}

