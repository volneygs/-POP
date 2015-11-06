package controller;

import java.util.ArrayList;
import java.util.Collections;

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
			return logged.getQtdNotificacao();
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
		
		String foto = "resources/default.jpg";
		
		return registerUser(nome, email, senha, dataDeNascimento, foto);
	}
	
	public String registerUser (String name, String email, String password, String birthdate, String picture) throws Exception{
		
		User usuario = userFactory.createUser(name, email, password, birthdate, picture);
		this.allUsers.add(usuario);

		return email;
	}

	public ArrayList<User> getAllUsers(){
		
		return this.allUsers;
	}

	public void login(String email, String senha) throws Exception{
		
		User usuario = buscaUsuarioLogin(email);
		
		if(this.logged == null){
	
			if(autenticaLogin(usuario, email, senha)){
				this.logged = usuario;
			}
		}else{
			throw new Exception("Nao foi possivel realizar login. Um usuarix ja esta logadx: "+ logged.getName() + ".");
		}
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
		
		User usuario = buscaUsuario(email);
		
		if(this.logged != null){
			
			logged.adicionaAmigo(usuario);
		}
		
		return "voce precisa estar logado.";
	}
	
	public void aceitaAmizade(String email) throws Exception{
		
		User usuario = buscaUsuario(email);
		
		if(logged != null){
			
			if(logged.getSolicitacoesDeAmizades().contains(email)){
				
				logged.getAmigos().add((usuario));
				usuario.getAmigos().add((logged));
				
				String notificacao = logged.getName() + " aceitou sua amizade.";
				
				usuario.adicionaNotificacao(notificacao);
				
			}else{
				throw new Exception(usuario.getName() + " nao lhe enviou solicitacoes de amizade.");
			}
		}
		
	}
	
	public void rejeitaAmizade(String email) throws Exception{
		
		User usuario = buscaUsuario(email);
		
		if(logged != null){
			
			logged.rejeitaAmizade(usuario);	
		}
	}

	public void removeAmigo(String email) throws Exception{
		
		User usuario = buscaUsuario(email);
		
		if(this.logged != null){
			
			if(logged.getAmigos().contains(usuario)){
				
				this.logged.removeAmigo(usuario);
				usuario.removeAmigo(logged);
				
				String notificacao = logged.getName()+ " removeu a sua amizade.";
				
				usuario.adicionaNotificacao(notificacao);
			}
		}
	}
	
	public void criaPost(String message, String date) throws Exception{
		
		if(logged != null){
			
			Post post = postFactory.createPost(message, date);
			
			logged.getMural().add(post);
		}
	}
	
	public void adicionaPop(int valor){
		logged.adicionaPop(valor);
	}
	
	public String atualizaRanking(){

		Collections.sort(allUsers);
		
		return "Mais populares: () ; (); (); | Menos Populares: () ; (); ();";
		
	}
	
	
	public void curtirPost(String email, int index) throws Exception {
		
		User usuario = buscaUsuario(email);
		
		logged.curtirPost(usuario, index);
		
	}
	
	public void rejeitarPost(String email, int index) throws Exception {
		
		User usuario = buscaUsuario(email);
		
		logged.rejeitarPost(usuario, index);
		
	}
	
	public int getPopsUsuario(String email) throws Exception {
		
		User usuario = buscaUsuario(email);
		
		if(this.logged == null){
		
			return usuario.getPops();
			
		} else {
			
			throw new Exception("Erro na consulta de Pops. Um usuarix ainda esta logadx.");
		
		}
		
	}
	
	public int getPopsUsuario(){
		return logged.getPops();		
	}
	
	public String getPost(int index) {
		
			return logged.getMural().get(index).getMessage();
	
	}	
	
	public String getPost(String field, int index) {
		
		if (field.equals("Hashtags")) {
		
			return logged.getMural().get(index).getHashtags();
			
		} else if (field.equals("Data")) {
			
			return logged.getMural().get(index).getDateTime();
			
		} else { return logged.getMural().get(index).getText(); }
		
	}
	
	public int getCurtidasPost(int postIndex) throws Exception{
		
		if (postIndex < 0){
			
			throw new Exception("Requisicao invalida. O indice deve ser maior ou igual a zero.");
		
		} else if (postIndex > logged.getMural().size()){

			throw new Exception("Post #" + postIndex + " nao existe. Usuarix possui apenas " + (logged.getMural().size()) + " post(s).");
		
		} else { return logged.getMural().get(postIndex).getQtdCurtidas(); } 
	
	}
	
	public int getRejeicoesPost(int postIndex) throws Exception{
		
		if (postIndex < 0){
			
			throw new Exception("Requisicao invalida. O indice deve ser maior ou igual a zero.");
		
		} else if (postIndex > logged.getMural().size()){

			throw new Exception("Post #" + postIndex + " nao existe. Usuarix possui apenas " + (logged.getMural().size()) + " post(s).");
		
		} else { return logged.getMural().get(postIndex).getQtdRejeicoes(); } 

		
	}
	
	public int getPopsPost(int postIndex) {
		return logged.getMural().get(postIndex).getPop();
	}
	
	public String getConteudoPost(int index, int postIndex) throws Exception{

		if (index < 0){
			
			throw new Exception("Requisicao invalida. O indice deve ser maior ou igual a zero.");
		
		} else if (index > logged.getMural().size()){

			throw new Exception("Item #" + index + " nao existe nesse post, ele possui apenas " + (logged.getMural().size() + 1) + " itens distintos.");
			
		} else {	return logged.getMural().get(postIndex).getChest(index);	}
		
	}

	public String getInfoUsuario(String field) throws Exception{
		
		return logged.getInfoUsuario(field);
	}
	
	public String getInfoUsuario(String field, String email) throws Exception{
		
		User usuario = buscaUsuario(email);
		
		return usuario.getInfoUsuario(field);
	}
	
	public String getPopularidade() {
		return logged.getPopularidade();
	}
	
	public String removeUsuario(String email) throws Exception{
		
		User usuario = buscaUsuario(email);	
			
		if(usuario.getEmail().equals(email)){
			allUsers.remove(usuario);
				
			return "user successfully removed.";
		}else{
			throw new Exception("You need to specify a valid field.");
			
		}
	}
	
	public String fechaSistema() throws Exception{
		
		if(logged == null){
			return "system closed successeful.";
		}else{
			throw new Exception("Nao foi possivel fechar o sistema. Um usuarix ainda esta logadx.");
		}
	}

	public void atualizaPerfil(String field, String newField) throws Exception{
			
			if(this.logged != null){
				
				logged.atualizaPerfil(field, newField);
				
			}else{
				throw new Exception("Nao eh possivel atualizar um perfil. Nenhum usuarix esta logadx no +pop.");
			}
		}
		
	public void atualizaPerfil(String field, String novaSenha, String senhaAntiga) throws Exception{
			
		if(this.logged != null){
				
			logged.mudaSenha(novaSenha, senhaAntiga);			
		}
	}
	
	private User buscaUsuarioLogin(String email) throws Exception{
		
		for(User user : allUsers){
			if(user.getEmail().equals(email)){
				return user;
			}
		}
		
		throw new Exception("Nao foi possivel realizar login. Um usuarix com email " + email + " nao esta cadastradx.");
	}
	
	private User buscaUsuario(String email) throws Exception{

		for(User user : allUsers){
			if(user.getEmail().equals(email)){
				return user;
			}
		}
		
		throw new Exception("Um usuarix com email " + email + " nao esta cadastradx.");
	}
	
	private boolean autenticaLogin(User usuario, String email, String senha) throws Exception{
			
			if(usuario.getEmail().equals(email)){
					
				if(usuario.getPassword().equals(senha)){
						
					return true;
					
				}else{
					throw new Exception("Nao foi possivel realizar login. Senha invalida.");
				}
			}else{
					throw new Exception("Nao foi possivel realizar login. Um usuarix ja esta logadx: "+logged.getName()+".");
		}
	}

}