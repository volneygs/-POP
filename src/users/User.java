package users;

import java.util.ArrayList;

import factory.PostFactory;
import java.time.*;
import java.time.format.*;

public class User {
	
	private DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private String email;
	private String name;
	private String password;
	private LocalDate birthdate;
	private String image;
	private String pop;
	private ArrayList<Post> mural;
	private ArrayList<User> friends;
	private PostFactory makePost;

	public User(String name, String email, String password, String birthdate) throws Exception{
		
		
		if(name.equals("") || name.trim().equals("")){
			throw new Exception("Erro no cadastro de Usuarios. Nome dx usuarix nao pode ser vazio.");
			
		}else if(email.equals("") || email.contains("@") == false || email.contains(".com") == false){				
			throw new Exception("Erro no cadastro de Usuarios. Formato de e-mail esta invalido.");
			
		}else if(password.equals("")){
			throw new Exception("Invalid password.");
			
		}else if(birthdate.equals("")){
			throw new Exception("Erro no cadastro de Usuarios. Formato de data esta invalida.");
			
		}

		
		try { 
			this.birthdate = LocalDate.parse(birthdate, dateFormat);
		
		} catch (DateTimeException e) {
						
			if (e.toString().contains("could not be parsed at index")){
				throw new Exception("Erro no cadastro de Usuarios. Formato de data esta invalida.");
			
			}else if (e.toString().contains("Invalid value for")){
				throw new Exception("Erro no cadastro de Usuarios. Data nao existe.");
			}
		}
		
		this.name = name;
		this.email = email;
		this.password = password;
		this.image = "resources/default.jpg";
		this.mural = new ArrayList<Post>();
		this.friends = new ArrayList<User>();
		this.makePost = new PostFactory();
	}
	
	public User(String name, String email, String password, String birthdate, String image) throws Exception{
		
		
		if(name.equals("")){
			throw new Exception("Erro no cadastro de Usuarios. Nome dx usuarix nao pode ser vazio.");
			
		}else if(email.equals("")){				
			throw new Exception("Erro no cadastro de Usuarios. Formato de e-mail esta invalido.");
			
		}else if(password.equals("")){
			throw new Exception("Invalid password.");
			
		}else if(birthdate.equals("")){
			throw new Exception("Erro no cadastro de Usuarios. Formato de data esta invalida.");
			
		}

		
		try { 
			this.birthdate = LocalDate.parse(birthdate, dateFormat);
		
		} catch (DateTimeException e) {
						
			if (e.toString().contains("could not be parsed at index")){
				throw new Exception("Error: Invalid date format.");
			
			}else if (e.toString().contains("Invalid value for")){
				throw new Exception("Erro no cadastro de Usuarios. Data nao existe.");
			}
		}
		
		this.email = email;
		this.name = name;
		this.password = password;
		this.image = image;
		this.mural = new ArrayList<Post>();
		this.friends = new ArrayList<User>();
		this.makePost = new PostFactory();
	}

	public boolean addFriend(User user){
		return this.friends.add(user);
	}
	
	public boolean removeFriend(User user){
		if(this.friends.contains(user)){
			return this.friends.remove(user);
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
		return this.friends;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getEmail() {
		return this.email;
	}

	public String getPassword() {
		return this.password;
	}

	public LocalDate getBirthdate() {
		return this.birthdate;
	}

	public String getImage() {
		return this.image;
	}

}
