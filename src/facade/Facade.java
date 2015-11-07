package facade;

import users.User;
import easyaccept.EasyAccept;
import controller.Controller;

public class Facade {

	
	public static void main(String[] args) {
	    args = new String[] {"facade.Facade", "use_cases/usecase_1.txt", "use_cases/usecase_2.txt", "use_cases/usecase_3.txt", "use_cases/usecase_4.txt",
	    "use_cases/usecase_5.txt", "use_cases/usecase_6.txt", "use_cases/usecase_7.txt"};
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
	
	public void login(String email, String password) throws Exception{
		
		controller.login(email, password);
	}
	
	public String logout() throws Exception{
		
		return controller.logout();
	}
	
	public void adicionaPops(int valor){
		controller.adicionaPop(valor);
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
	
	public String atualizaRanking() {
		return controller.atualizaRanking();
	}
	
	public String atualizaTrendingTopics() {
		return controller.atualizaTrendingTopics();
	}
	
	public void rejeitaAmizade(String email) throws Exception{
		
		controller.rejeitaAmizade(email);
	}
	
	public void removeAmigo(String email) throws Exception{
		
		controller.removeAmigo(email);	
	}
	
	public String getPopularidade() {
		return controller.getPopularidade();
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
	
	public String removeUsuario(String email) throws Exception{
		
		return controller.removeUsuario(email);
	}
	
	public void criaPost(String message, String date) throws Exception{
		
		controller.criaPost(message, date);
	}
	
	public void curtirPost(String email, int index) throws Exception{
		controller.curtirPost(email, index);
	}
	
	public void rejeitarPost(String email, int index) throws Exception{
		controller.rejeitarPost(email, index);
	}
	
	public int qtdCurtidasDePost(int postIndex) throws Exception{
		return controller.getCurtidasPost(postIndex);
	}

	public int qtdRejeicoesDePost(int postIndex) throws Exception{
		return controller.getRejeicoesPost(postIndex);
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
	
	public int getPopsPost(int postIndex) throws Exception{
		return controller.getPopsPost(postIndex);
	}

	public int getPopsUsuario(String email) throws Exception{
		return controller.getPopsUsuario(email);
	}
	
	public int getPopsUsuario(){
		return controller.getPopsUsuario();
	}
	
	public String fechaSistema() throws Exception{
		
		return controller.fechaSistema();
		
	}
}
