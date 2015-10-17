package users;

import java.util.ArrayList;

import factory.PostFactory;
import java.time.*;
import java.time.format.*;

public class User {
	
	private DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private String email;
	private String nome;
	private String senha;
	private LocalDate dataDeNascimento;
	private String foto;
	private String pop;
	private ArrayList<Post> mural;
	private ArrayList<User> amigos;
	private PostFactory makePost;

	public User(String nome, String email, String senha, String dataDeNascimento) throws Exception{
		
		
		if(nome.equals("") || nome.trim().equals("")){
			throw new Exception("Erro no cadastro de Usuarios. Nome dx usuarix nao pode ser vazio.");
			
		}else if(email.equals("") || email.contains("@") == false || email.contains(".com") == false){				
			throw new Exception("Erro no cadastro de Usuarios. Formato de e-mail esta invalido.");
			
		}else if(senha.equals("")){
			throw new Exception("Invalid password.");
			
		}else if(dataDeNascimento.equals("")){
			throw new Exception("Erro no cadastro de Usuarios. Formato de data esta invalida.");
			
		}

		
		try { 
			this.dataDeNascimento = LocalDate.parse(dataDeNascimento, dateFormat);
		
		} catch (DateTimeException e) {
						
			if (e.toString().contains("could not be parsed at index")){
				throw new Exception("Erro no cadastro de Usuarios. Formato de data esta invalida.");
			
			}else if (e.toString().contains("Invalid value for")){
				throw new Exception("Erro no cadastro de Usuarios. Data nao existe.");
			}
		}
		
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.foto = "resources/default.jpg";
		this.mural = new ArrayList<Post>();
		this.amigos = new ArrayList<User>();
		this.makePost = new PostFactory();
	}
	
	public User(String nome, String email, String senha, String dataDeNascimento, String foto) throws Exception{
		
		
		if(nome.equals("")){
			throw new Exception("Erro no cadastro de Usuarios. Nome dx usuarix nao pode ser vazio.");
			
		}else if(email.equals("")){				
			throw new Exception("Erro no cadastro de Usuarios. Formato de e-mail esta invalido.");
			
		}else if(senha.equals("")){
			throw new Exception("Invalid password.");
			
		}else if(dataDeNascimento.equals("")){
			throw new Exception("Erro no cadastro de Usuarios. Formato de data esta invalida.");
			
		}

		
		try { 
			this.dataDeNascimento = LocalDate.parse(dataDeNascimento, dateFormat);
		
		} catch (DateTimeException e) {
						
			if (e.toString().contains("could not be parsed at index")){
				throw new Exception("Error: Invalid date format.");
			
			}else if (e.toString().contains("Invalid value for")){
				throw new Exception("Erro no cadastro de Usuarios. Data nao existe.");
			}
		}
		
		this.email = email;
		this.nome = nome;
		this.senha = senha;
		this.foto = foto;
		this.mural = new ArrayList<Post>();
		this.amigos = new ArrayList<User>();
		this.makePost = new PostFactory();
	}

	public boolean addFriend(User user){
		return this.amigos.add(user);
	}
	
	public boolean removeFriend(User user){
		if(this.amigos.contains(user)){
			return this.amigos.remove(user);
		}else{
			return false;
		}
	}
	
	public boolean postInMural(User userSend, User userReceive, String message){
		
		Post post = makePost.makePost(userSend, message);
		
		return userReceive.mural.add(post);
		
	}
	
	public ArrayList<Post> getMural(){
		return this.mural;
	}
	
	public ArrayList<User> getFriends(){
		return this.amigos;
	}
	
	public String getName() {
		return this.nome;
	}
	
	public String getEmail() {
		return this.email;
	}

	public String getPassword() {
		return this.senha;
	}

	public LocalDate getBirthdate() {
		return this.dataDeNascimento;
	}

	public String getImage() {
		return this.foto;
	}

}
