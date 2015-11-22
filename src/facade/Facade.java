package facade;

import controller.Controller;
import easyaccept.EasyAccept;
import inputOutput.FechaSistema;
import inputOutput.IniciaSistema;
import users.User;

public class Facade {

	
	public static void main(String[] args) {
	    args = new String[] {"facade.Facade", "testes/usecase_1.txt", "testes/usecase_2.txt", "testes/usecase_3.txt", "testes/usecase_4.txt",
	    "testes/usecase_5.txt", "testes/usecase_6.txt", "testes/usecase_7.txt", "testes/usecase_8.txt", "testes/usecase_9.txt", "testes/usecase_10.txt"};
	    EasyAccept.main(args);
	}
	
	private Controller controller;
	private IniciaSistema iniciaSistema;
	private FechaSistema fechaSistema;
	
	public Facade(){

		controller = new Controller();
		iniciaSistema = new IniciaSistema();
		fechaSistema = new FechaSistema();
	}
	
	/**
	 * Metodo inicia o sistema importando as informações necessárias dos arquivos.
	 * @throws Exception 
	 * lança exceção caso algum erro ocorro com os arquivos.
	 */
	
	public void iniciaSistema() throws Exception{
		
		controller = iniciaSistema.leArquivo();
	}
	
	/**
	 * Método que fecha sistema, salvando informações no arquivo.
	 * @throws Exception
	 * lança exceção caso ocorra algum erro com os arquivos.
	 */
	
	public void fechaSistema() throws Exception{
		
		fechaSistema.escreveArquivo(controller.getLogged(), controller);
		
	}
	
	/**
	 * Método que serve para salvar posts do usuário em arquivos.
	 * @return
	 * boolean informando se o arquivo foi salvo corretamente
	 * @throws Exception
	 * lança exceção caso ocorra algum erro com o arquivo.
	 */
	
	 public boolean baixaPosts() throws Exception{
		
		return controller.baixaPosts();
	} 
	
	public User getLogged(){
	
		return controller.getLogged();
	}
	
	/**
	 * Metodo cadastra usuario sem foto na base para que o mesmo posso logar.
	 * @param nome
	 * nome do usuario
	 * @param email
	 * email do usuario
	 * @param senha
	 * senha do usuario
	 * @param dataDeNascimento
	 * data de nascimento do usuario.
	 * @return
	 * retorna uma String com o email do usuario.
	 * @throws Exception
	 * lança exceção caso algum parametro esteja em formato invalido.
	 */

	public String cadastraUsuario (String nome, String email, String senha, String dataDeNascimento) throws Exception{
	
		return controller.cadastraUsuario(nome, email, senha, dataDeNascimento);
	}
	
	/**
	 * Metodo cadastra usuario com foto na base para que o mesmo possa logar.
	 * @param nome
	 * nome do usuario
	 * @param email
	 * email do usuario
	 * @param senha
	 * senha do usuario
	 * @param dataDeNascimento
	 * data de nascimento do usuario.
	 * @param foto
	 * foto do usuario
	 * @return
	 * retorna uma String com o email do usuario.
	 * @throws Exception
	 * lança exceção caso algum parametro esteja em formato invalido.
	 */
	
	public String cadastraUsuario (String nome, String email, String senha, String dataDeNascimento, String foto) throws Exception{
		
		return controller.cadastraUsuario(nome, email, senha, dataDeNascimento, foto);
	}
	
	/**
	 * Metodo serve para efetuar o login do usuario no sistema.
	 * @param email
	 * email do usuario
	 * @param senha
	 * senha do usuario
	 * @throws Exception
	 * lança exceção se usuario não estiver cadastrado
	 */
	
	public void login(String email, String senha) throws Exception{
		
		controller.login(email, senha);
	}
	
	/**
	 * Metodo serve para efetuar o logout do usuario no sistema 
	 * @throws Exception
	 * lança exceção se o usuario já estiver deslogado.
	 */
	
	public void logout() throws Exception{
		
		controller.logout();
	}
	
	/**
	 * Metodo utilizado para atualizar o feed de noticias do usuario pegando os posts mais recentes de seus amigos, dependendo da popularidade.
	 */
	
	public void atualizaFeed(){
		
		controller.atualizaFeed();
	}
	
	/**
	 * Metodo utilizado para pegar certo post do feed de noticias ordenado pelos mais recentes
	 * @param indice
	 * indice que será passado para a localização do post
	 * @return
	 * string contendo as informações do post
	 */
	
	public String getPostFeedNoticiasRecentes(int indice){
		
		return controller.getPostFeedNoticiasRecentes(indice);
	}
	
	/**
	 * Metodo utilizado para pegar certo post do feed de noticias ordenado pelos mais populares
	 * @param indice
	 * indice que será passado para a localização do post
	 * @return
	 * string contendo as informações do post
	 */
	
	public String getPostFeedNoticiasMaisPopulares(int indice){
		
		return controller.getPostFeedNoticiasMaisPopulares(indice);
	}
	
	/**
	 * Metodo serve para adicionar pops a certo usuario.
	 * @param valor
	 * valor de pops que será adicionado
	 */
	
	public void adicionaPops(int valor){
		controller.adicionaPop(valor);
	}
	
	/**
	 * Metodo que serve para atualizar informações do usuario
	 * @param campo
	 * informação do que será atualizado
	 * @param novoValor
	 * informação de como será atualizado
	 * @throws Exception
	 * lança exceção caso haja algum campo inválido
	 */
	
	public void atualizaPerfil(String campo, String novoValor) throws Exception{
		
		controller.atualizaPerfil(campo, novoValor);
	}
	
	/**
	 * Metodo que serve para atualizar senha do usuario
	 * @param campo
	 * campo que será atualizado(senha)
	 * @param novaSenha
	 * nova senha que será salva
	 * @param senhaAntiga
	 * senha antiga para autenticação
	 * @throws Exception
	 * lança exceção caso a senha antiga não seja correta
	 */
	
	public void atualizaPerfil(String campo, String novaSenha, String senhaAntiga) throws Exception{
		
		controller.atualizaPerfil(campo, novaSenha, senhaAntiga);
	}
	
	/**
	 * Metodo que serve para adicionar um amigo
	 * @param email
	 * email do usuario que receberá o convite
	 * @return
	 * string informando se a solicitação foi enviada com êxito
	 * @throws Exception
	 * lança exceção caso usuario não esteja cadastrado na base
	 */
	
	public String adicionaAmigo(String email) throws Exception{
		
		return controller.adicionaAmigo(email);
	}
	
	/**
	 * metodo que serve para aceitar uma solicitação de amizade que foi recebida
	 * @param email
	 * email do usuario que enviou a solicitação
	 * @throws Exception
	 * lança exceção caso o usuario não tenha enviado tal solicitação.
	 */
	
	public void aceitaAmizade(String email) throws Exception{
		
		controller.aceitaAmizade(email);
	}
	
	/**
	 * Metodo que atualiza o rank de usuarios mais populares e menos populares.
	 * @return
	 * string com a informação dos 3 mais populares e dos 3 menos populares
	 */
	
	public String atualizaRanking() {
		return controller.atualizaRanking();
	}
	
	/**
	 * Metodo que atualiza o trendingTopics pelos mais utilizados
	 * @return
	 * string com a informação das hashtags mais utilizadas entre todos os usuarios.
	 */
	
	public String atualizaTrendingTopics() throws Exception{
		return controller.atualizaTrendingTopics();
	}
	
	/**
	 * Metodo que serve para rejeitar uma solicitação de amizade recebida.
	 * @param email
	 * email da pessoa que enviou a solicitação
	 * @throws Exception
	 * lança exceção caso a pessoa não tenha enviado tal solicitação.
	 */
	
	public void rejeitaAmizade(String email) throws Exception{
		
		controller.rejeitaAmizade(email);
	}
	
	/**
	 * Metodo serve para exclui amigo da lista de amigos do usuario
	 * @param email
	 * email do usuario que será excluido
	 * @throws Exception
	 * lança exceção caso o usuario não seja amigo ou não seja encontrado na base.
	 */
	
	public void removeAmigo(String email) throws Exception{
		
		controller.removeAmigo(email);	
	}
	
	public String getPopularidade() {
		return controller.getPopularidade();
	}
	
	/**
	 * Metodo que retorna informações completas do usuario logado com exceção da senha
	 * @param campo
	 * campo que está sendo solicitado
	 * @return
	 * campo que foi solicitado
	 * @throws Exception
	 * lança exceção caso campo seja invalido
	 */
	
	public String getInfoUsuario(String campo) throws Exception{
		
		return controller.getInfoUsuario(campo);
	}
	
	public int getTotalPosts(){
	
		return controller.getTotalPosts();
	}
	
	/**
	 * Metodo que retorna informação de algum usuario da base exceto a senha
	 * @param campo
	 * campo que está sendo solicitado
	 * @param email
	 * email do usuario dono da informação
	 * @return
	 * campo solicitado
	 * @throws Exception
	 * lança exceção caso campo seja invalido
	 */
	
	public String getInfoUsuario(String campo, String email) throws Exception{
		
		return controller.getInfoUsuario(campo, email);
	}
	
	/**
	 * Metodo que retorna a quantidade de amigos do usuario.
	 * @return
	 * int com a quantidade de amigos
	 * @throws Exception
	 * lança exceção caso o usuario não esteja logado.
	 */
	
	public int getQtdAmigos() throws Exception{
		
		return controller.getQtdAmigos();
	}
	
	/**
	 * Metodo que serve para verificar quantas notificações ainda não foram lidas
	 * @return
	 * int representando a quantidade de notificações
	 * @throws Exception
	 * lança exceção caso usuario não esteja logado
	 */
	
	public int getNotificacoes() throws Exception{
		
		return controller.getNotificacoes();
	}
	
	/**
	 * Metodo que retorna a proxima notificação ainda não lida
	 * @return
	 * proxima notificação não lida
	 * @throws Exception
	 * lança exceção caso usuario não esteja logado
	 */
	
	public String getNextNotificacao() throws Exception{
		
		return controller.getNextNotificacao();
	}
	
	/**
	 * metodo que remove usuario da base do sistema
	 * @param email
	 * eamil do usuario que será removido
	 * @return
	 * string informando se foi removido com sucesso
	 * @throws Exception
	 * lança exceção caso usuario não esteja cadastrado
	 */
	
	public String removeUsuario(String email) throws Exception{
		
		return controller.removeUsuario(email);
	}
	
	/**
	 * Metodo serve para criar e coloca-lo no mural do usuario
	 * @param mensagem
	 * mensagem que será passada, podendo conter imagem, audio e hashtags
	 * @param data
	 * data do post
	 * @throws Exception
	 * lança exceção caso algum campo seja invalido ou usuario não esteja logado
	 */
	
	public void criaPost(String mensagem, String data) throws Exception{
		
		controller.criaPost(mensagem, data);
	}
	
	/**
	 * Metodo que serve para curtir post de algum amigo
	 * @param email
	 * email do usuario para localização
	 * @param indice
	 * indice do post que será adicionado a curtida
	 * @throws Exceptionl
	 * lança exceção caso usuario não exista, não seja amigo ou indice esteja incorreto
	 */
	
	public void curtirPost(String email, int indice) throws Exception{
		controller.curtirPost(email, indice);
	}
	
	/**
	 * Metodo que serve para rejeitar post de algum amigo
	 * @param email
	 * email do usuario para localização
	 * @param indice
	 * indice do post que será adicionado a rejeição
	 * @throws Exception
	 * lança exceção caso usuario não exista, não seja amigo ou indice esteja incorreto
	 */
	
	public void rejeitarPost(String email, int indice) throws Exception{
		controller.rejeitarPost(email, indice);
	}
	
	/**
	 * Metodo que serve para saber a quantidade de curtidas de certo post
	 * @param indicePost
	 * indice do post que será buscado
	 * @return
	 * quantidade de curtidas do post
	 * @throws Exception
	 * lança exceção caso indice esteja incorreto
	 */
	
	public int qtdCurtidasDePost(int indicePost) throws Exception{
		return controller.getCurtidasPost(indicePost);
	}
	
	/**
	 * Metodo que serve para saber a quantide de rejeições de certo post
	 * @param postIndex
	 * indice do post que será buscado
	 * @return
	 * quantidade de rejeições do post
	 * @throws Exception
	 * lança exceção caso indice esteja incorreto
	 */

	public int qtdRejeicoesDePost(int indicePost) throws Exception{
		return controller.getRejeicoesPost(indicePost);
	}
	
	/**
	 * Metodo que retorna post
	 * @param indice
	 * indice do post que será buscado
	 * @return
	 * string contendo informações do post
	 */
	
	public String getPost(int indice) {
		
		return controller.getPost(indice);
		
	}
	
	/**
	 * Metodo que retorna a data, arquivos ou as hashtags de dado post
	 * @param campo
	 * campo solicitado
	 * @param indice
	 * indice do post que será buscado
	 * @return
	 * string contendo as informações do campo solicitado
	 */
	
	public String getPost(String campo, int indice) {
		
		return controller.getPost(campo, indice);
		
	}
	
	/**
	 * Metodo que retorna Post com as informações solicitadas
	 * @param indice
	 * indice do post
	 * @param indicePost
	 * informações que deve conter no post
	 * @return
	 * string com as informações solicitadas
	 * @throws Exception
	 * lança exceção caso os indices sejam invalidos
	 */
	
	public String getConteudoPost(int indice, int indicePost) throws Exception{
		
		return controller.getConteudoPost(indice, indicePost);
	}
	
	/**
	 * Metodo retorna pops de dado post
	 * @param indicePost
	 * indice do post
	 * @return
	 * int com a quantidade de pops do post
	 * @throws Exception
	 * lança exceção caso indice seja invalido
	 */
	
	public int getPopsPost(int indicePost) throws Exception{
		return controller.getPopsPost(indicePost);
	}
	
	/**
	 * Metodo que retorna pops de um usuario
	 * @param email
	 * email do usuario
	 * @return
	 * int com a quantidade de pops do usuario
	 * @throws Exception
	 * lança exceção caso email seja invalido
	 */

	public int getPopsUsuario(String email) throws Exception{
		return controller.getPopsUsuario(email);
	}
	
	/**
	 * Metodo retorna pops do usuario logado
	 * @return
	 * int com a quantidade de pops do usuario
	 */
	
	public int getPopsUsuario(){
		return controller.getPopsUsuario();
	}	
}
