package facade;

import users.User;
import easyaccept.EasyAccept;

import controller.Controller;

public class Facade {

	
	public static void main(String[] args) {
	    args = new String[] {"facade.Facade", "use_cases/usecase_1.txt", "use_cases/usecase_2.txt", "use_cases/usecase_3.txt"};
	    EasyAccept.main(args);
	}
	
	private Controller controller = new Controller();
	
	public Facade(){

	}
	
	public String iniciaSistema(){
		
		return "Sistema iniciado";
	}
	
	public User getLogged(){
	
		return controller.getLogged();
		
	}

	public String cadastraUsuario (String nome, String email, String senha, String dataDeNascimento) throws Exception{
	
		return controller.registerUser(nome, email, senha, dataDeNascimento);
	
	}
	
	public String cadastraUsuario (String nome, String email, String senha, String dataDeNascimento, String foto) throws Exception{
		
		return controller.registerUser(nome, email, senha, dataDeNascimento, foto);
		
	}
	
	public String login(String email, String password) throws Exception{
		
		return controller.login(email, password);
		
	}
	
	public String logout() throws Exception{
		
		return controller.logout();
		
	}
	
	public void atualizaPerfil(String field, String newField){
		
		controller.atualizaPerfil(field, newField);
	}
	
	public void atualizaPerfil(String field, String novaSenha, String senhaAntiga) throws Exception{
		
		controller.atualizaPerfil(field, novaSenha, senhaAntiga);
	}
	
	public boolean addFriend(User user){
		
		return controller.addFriend(user);
		
	}
	
	public boolean removeFriend(User user){
		
		return controller.removeFriend(user);
		
	}
	
	public void postInMural(User userReceive, String message, String date){
		
		controller.postInMural(userReceive, message, date);
		
	}

	public String getInfoUsuario(String field) throws Exception{
		
		return controller.getInfoUsuario(field);
		
	}
	
	public String getInfoUsuario(String field, String id) throws Exception{
		
		return controller.getInfoUsuario(field, id);
		
	}
	
	public String removeUsuario(String id) throws Exception{
		
		return controller.removeUsuario(id);
		
	}
	
	public void criaPost(String message, String date){
		
		controller.post(message, date);
		
	}
	
	public String fechaSistema() throws Exception{
		
		return controller.fechaSistema();
		
	}
}
