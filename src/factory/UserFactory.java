package factory;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

import users.User;

public class UserFactory {
	
	User user;
	private DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT);
	
	public UserFactory(){
		
	}

	public User createUser(String email, String name, String password, String birthdate, String image) throws Exception{
		
		checkUser(name, email, password, birthdate);
		
		user = new User(email, name, password, birthdate, image);
		
		return user;	
	}

	private void checkUser(String email, String name, String password, String birthdate) throws Exception {
		
		checkName(name);
		checkEmail(email);
		checkPassword(password);
		checkBirthdate(birthdate);
		
	}
	
	private void checkName(String name) throws Exception {
		if(name.equals("") || name.trim().equals("")){
			throw new Exception("Erro no cadastro de Usuarios. Nome dx usuarix nao pode ser vazio.");	
		}
	}
	
	private void checkEmail(String email) throws Exception {
		if(email.equals("") || email.contains("@") == false || email.contains(".com") == false){				
			throw new Exception("Erro no cadastro de Usuarios. Formato de e-mail esta invalido.");
			
		}
	}
	
	private void checkPassword(String password) throws Exception {
		if(password.equals("")){
			throw new Exception("Senha invalida.");
			
		}
	}
		
	private void checkBirthdate(String birthdate) throws Exception {
		
		if(birthdate.equals("")){
			throw new Exception("Erro no cadastro de Usuarios. Formato de data esta invalida.");
			
		}
		
		try { 
			
			birthdate = LocalDate.parse(birthdate, dateFormat).format(dateFormat);
		
		} catch (DateTimeException e) {

			if (e.toString().contains("could not be parsed at index")){

				throw new Exception("Erro no cadastro de Usuarios. Formato de data esta invalida.");
			
			}else if (e.toString().contains("Invalid value for")){
				throw new Exception("Erro no cadastro de Usuarios. Data nao existe.");
			
			}else if (e.toString().contains("Invalid date")){
				throw new Exception("Erro no cadastro de Usuarios. Data nao existe.");
			
			}
		}
	}
	
}
