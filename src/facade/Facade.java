package facade;

import users.User;
import easyaccept.EasyAccept;
import controller.Controller;

public class Facade {

	
	public static void main(String[] args) {
	    args = new String[] {"facade.Facade", "use_cases/usecase_1.txt", "use_cases/usecase_2.txt", "use_cases/usecase_3.txt", "use_cases/usecase_4.txt"};
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
	
	public void atualizaPerfil(String field, String newField) throws Exception{
		
		controller.atualizaPerfil(field, newField);
	}
	
	public void atualizaPerfil(String field, String novaSenha, String senhaAntiga) throws Exception{
		
		controller.atualizaPerfil(field, novaSenha, senhaAntiga);
	}
	
	public String adicionaAmigo(String email) throws Exception{
		
		return controller.adicionaAmigo(email);
		
	}
	
	public void aceitaAmizade(String email) throws Exception{
		
		controller.aceitaAmizade(email);
	}
	
	public String rejeitaAmizade(String email) throws Exception{
		
		return controller.rejeitaAmizade(email);
	}
	
	public boolean removeAmigo(String email) throws Exception{
		
		return controller.removeAmigo(email);
		
	}
	

	public String getInfoUsuario(String field) throws Exception{
		
		return controller.getInfoUsuario(field);
		
	}
	
	public String getInfoUsuario(String field, String id) throws Exception{
		
		return controller.getInfoUsuario(field, id);
		
	}
	
	public int getQtdAmigos() throws Exception{
		
		return controller.getQtdAmigos();
	}
	
	public int getNotificacoes() throws Exception{
		return controller.getNotificacoes();
		
	}
	
	public String getNextNotificacao() throws Exception{
		return controller.getNextNotificacao();
		
	}
	
	public String removeUsuario(String id) throws Exception{
		
		return controller.removeUsuario(id);
		
	}
	
	public void criaPost(String message, String date) throws Exception{
		
		controller.criaPost(message, date);
		
	}
	
	public String getPost(int index) {
		
		return controller.getPost(index);
		
	}
	
	public String getPost(String field, int index) {
		
		return controller.getPost(field, index);
		
	}
	
	public String getConteudoPost(int index, int postIndex) throws Exception{
		
		return controller.getConteudoPost(index, postIndex);
		
	}
	
	public String fechaSistema() throws Exception{
		
		return controller.fechaSistema();
		
	}
}
