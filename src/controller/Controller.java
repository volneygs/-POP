package controller;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;

import factory.UserFactory;
import factory.PostFactory;
import users.User;
import users.Post;

public class Controller {
	
	private ArrayList<User> allUsers;
	private UserFactory userFactory;
	private PostFactory postFactory;
	private User logged;
	
	public Controller(){
		this.userFactory = new UserFactory();
		this.postFactory = new PostFactory();
		this.allUsers = new ArrayList<User>();
		this.logged = null;
	}
	
	public User getLogged(){
	
		return this.logged;
		
	}
	
	public ArrayList<User> getUsers(){
		
		return new ArrayList<User>();
	}
	
	public int getQtdAmigos() throws Exception{
		
		if(logged != null){
			return logged.getAmigos().size();
		}else{
			throw new Exception("Usuarix deve estar logado.");
		}
	}
	
	public int getNotificacoes() throws Exception{
		
		if(logged != null){
			return logged.getNotification().size();
		}else{
			throw new Exception("Usuarix deve estar logado.");
		}
	}
	
	public String getNextNotificacao() throws Exception{
		
		if(logged != null){
			return logged.getNextNotificacao();
		}else{
			throw new Exception ("Usuarix deve estar logado.");
		}
		
	}

	public String registerUser (String nome, String email, String senha, String dataDeNascimento) throws Exception{
		
		User usuario = userFactory.createUser(nome, email, senha, dataDeNascimento);
		this.allUsers.add(usuario);

		return email;
	}
	
	public String registerUser (String name, String email, String password, String birthdate, String picture) throws Exception{
		
		User usuario = userFactory.createUser(name, email, password, birthdate, picture);
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
	
	public String adicionaAmigo(String email) throws Exception{
		
		if(this.logged != null){
			
			User usuario = buscaUsuario(email);
			
			String notificacao = logged.getName() + " quer sua amizade.";
			
			usuario.adicionaSoliticacaoAmizade(notificacao);
			
			usuario.getSolicitacoesDeAmizades().add(logged.getEmail());
		}
		
		return "você precisa estar logado.";
	}
	
	public void aceitaAmizade(String email) throws Exception{
		
		if(logged != null){
			
			if(logged.getSolicitacoesDeAmizades().contains(email)){
				
				User usuario = buscaUsuario(email);
				
				logged.adicionaAmigo(usuario);
				
				
			}
		}
		
	}
	
	public String rejeitaAmizade(String email) throws Exception{
		
		if(logged != null){
			
			if(logged.getSolicitacoesDeAmizades().contains(email)){
				
				logged.getSolicitacoesDeAmizades().remove(email);
				
				return "solicitacao rejeitada.";
				
			}else{
				User usuario = buscaUsuario(email);
				throw new Exception(usuario.getName() + " nao lhe enviou solicitacoes de amizade.");
			}
			
		}
		
		return "voce deve logar antes";

	}

	public boolean removeAmigo(String email) throws Exception{
		
		if(this.logged != null){
			
			User usuario = buscaUsuario(email);
			
			if(logged.getFriends().contains(usuario)){
				this.logged.removeAmigo(usuario);
				
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
	
	public void post(String message, String date){
		
		Post post = postFactory.createPost(message, date);
		
	}
	
	public void postInMural(User userReceive, String message, String date){
		
		if(this.logged != null){

			if(this.logged.getFriends().contains(userReceive)){
				this.logged.postInMural(this.logged, userReceive, message, date);
				
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

	public void atualizaPerfil(String field, String newField) throws Exception{
		//teste
		/*
			}else if(field.equals("Data de Nascimento") && field.equals("")){
				throw new Exception("Erro na atualizacao de perfil. Formato de data esta invalida.");
				
			}

			
			try { 
				this.dataDeNascimento = LocalDate.parse(dataDeNascimento, dateFormat);
			
			} catch (DateTimeException e) {
							
				if (e.toString().contains("could not be parsed at index")){
					throw new Exception("Error: Invalid date format.");
				
				}else if (e.toString().contains("Invalid value for")){
					throw new Exception("Erro no cadastro de Usuarios. Data nao existe.");
				}
			}*/ // TODO ESSSE BLOCO COMENTADO EH DE RESPONSABILIDADE DO USUARIO VERIFICAR
			
			if(this.logged != null){
				if(field.equals("Nome")){
					logged.mudaNome(newField);
					
				}else if(field.equals("Foto")){
					logged.mudaFoto(newField);
					
				}else if(field.equals("E-mail")){
					logged.mudaEmail(newField);
					
				}else if(field.equals("Data de Nascimento")){
					logged.mudaDataNascimento(newField);
				}
			}else{
				throw new Exception("Nao eh possivel atualizar um perfil. Nenhum usuarix esta logadx no +pop.");
			}
		}
		
	public void atualizaPerfil(String field, String novaSenha, String senhaAntiga) throws Exception{
			
		if(this.logged != null){
				
			logged.mudaSenha(novaSenha, senhaAntiga);
					
		}
	}
	
	private User buscaUsuario(String email) throws Exception{
		
		for(User user : allUsers){
			if(user.getEmail().equals(email)){
				return user;
			}
		}
		
		throw new Exception("Um usuarix com email " + email + " nao esta cadastradx.");
	}


}