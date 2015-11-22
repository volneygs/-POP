package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.text.StyledEditorKit.ForegroundAction;

import factory.HashtagFactory;
import factory.PostFactory;
import factory.UserFactory;
import inputOutput.FechaSistema;
import inputOutput.IniciaSistema;
import post.Hashtag;
import post.Post;
import users.User;

public class Controller implements Serializable {
	
	private List<User> allUsers;
	private List<Hashtag> trendingTopics;
	private UserFactory userFactory;
	private PostFactory postFactory;
	private HashtagFactory hashtagFactory;
	private IniciaSistema iniciaSistema;
	private FechaSistema fechaSistema;
	private User logged;	
	
	public Controller(){
		this.userFactory = new UserFactory();
		this.postFactory = new PostFactory();
		this.hashtagFactory = new HashtagFactory();
		this.allUsers = new ArrayList<User>();
		this.iniciaSistema = new IniciaSistema();
		this.fechaSistema = new FechaSistema();
		this.logged = null;
	}
	
	public boolean baixaPosts() throws Exception{
		
		if(logged.getMural().size() == 0){
			throw new Exception("Erro ao baixar posts. O usuario nao possui posts.");
		}
		
		// ACHO QUE VAI SER NECESS�RIO MUDAR O NOME DO ARQUIVO
		
		String email = logged.getEmail().replace("@","[at]" ).replace(".", "");
		
		DateTimeFormatter dateValidator = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		
		String arquivoNome = "arquivos/posts_" + email + ".txt";
		
		try{
			
			File meuArquivo = new File(arquivoNome);
			
			if(meuArquivo.exists()){
				meuArquivo.delete();
			}
			
			PrintWriter meuWriter = new PrintWriter(new BufferedWriter(new FileWriter(meuArquivo)));
			
			int i = 1;
			
			for (Post post : logged.getMural()) {

				meuWriter.println("Post #" + (i) + " - " + post.getData().format(dateValidator));
				meuWriter.println("Conteudo:");
				meuWriter.println(post.getOnlyText());
				if(!(post.getFilesList().isEmpty())){
					for (String string : post.getFilesList()) {
						meuWriter.println(string);
					}
				}
				// ADICIONAR ARQUIVO DE IMAGEM E DE AUDIO
				if(!(post.getHashtagList().isEmpty())){
					meuWriter.println(post.getOnlyHashtag());
				}
				meuWriter.print("+Pop: " + post.getPop());
				
				if(!(i == logged.getMural().size())){
					
					meuWriter.println();
					meuWriter.println();
					meuWriter.println();
				}
				
				
				i++;
			}
				
			meuWriter.close();
			return true;
			
		}catch(IOException e){
			// DETERMINAR COMO TRATAR
			throw new Exception("O arquivo n�o foi encontrado!");
		}
		
	} 
	
	public User getLogged(){
	
		return this.logged;
		
	}
	
	public ArrayList<User> getUsers(){
		
		return new ArrayList<User>();
	}
	
	/**
	 * retorna a quantidade de amigos do usuario logado
	 * @return
	 * int representando a quantidade de amigos
	 * @throws Exception
	 * lan�a exce��o caso usuario n�o esteja logado
	 */
	
	public int getQtdAmigos() throws Exception{
		
		if(logged != null){
			return logged.getAmigos().size();
		}else{
			throw new Exception("Usuarix deve estar logado.");
		}
	}
	
	/**
	 * Metodo que serve para verificar quantas notifica��es ainda n�o foram lidas
	 * @return
	 * int representando a quantidade de notifica��es
	 * @throws Exception
	 * lan�a exce��o caso usuario n�o esteja logado
	 */
	
	public int getNotificacoes() throws Exception{
		
		if(logged != null){
			return logged.getQtdNotificacao();
		}else{
			throw new Exception("Usuarix deve estar logado.");
		}
	}
	
	/**
	 * Metodo que retorna a proxima notifica��o ainda n�o lida
	 * @return
	 * proxima notifica��o n�o lida
	 * @throws Exception
	 * lan�a exce��o caso usuario n�o esteja logado
	 */
	
	public String getNextNotificacao() throws Exception{
		
		if(logged != null){
			return logged.getNextNotificacao();
		}else{
			throw new Exception ("Usuarix deve estar logado.");
		}
		
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
	 * lan�a exce��o caso algum parametro esteja em formato invalido.
	 */

	public String registerUser (String nome, String email, String senha, String dataDeNascimento) throws Exception{
		
		String foto = "resources/default.jpg";
		
		User usuario = userFactory.createUser(nome, email, senha, dataDeNascimento, foto);
		if(!(allUsers.contains(usuario))){
			this.allUsers.add(usuario);
		}else{
			throw new Exception("email j� utilizado.");
		}

		return email;
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
	 * lan�a exce��o caso algum parametro esteja em formato invalido.
	 */
	
	public String registerUser (String nome, String email, String senha, String dataDeNascimento, String foto) throws Exception{
		
		User usuario = userFactory.createUser(nome, email, senha, dataDeNascimento, foto);
		
		if(!(allUsers.contains(usuario))){
			this.allUsers.add(usuario);
		}else{
			throw new Exception("email j� utilizado.");
		}

		return email;
	}

	public List<User> getAllUsers(){
		
		return this.allUsers;
	}
	
	/**
	 * Metodo serve para efetuar o login do usuario no sistema.
	 * @param email
	 * email do usuario
	 * @param senha
	 * senha do usuario
	 * @throws Exception
	 * lan�a exce��o se usuario n�o estiver cadastrado
	 */

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
	
	/**
	 * Metodo serve para efetuar o logout do usuario no sistema
	 * @return
	 * string informando se logout foi efeutado com exito 
	 * @throws Exception
	 * lan�a exce��o se o usuario j� estiver deslogado.
	 */

	public String logout() throws Exception{
		
		if(this.logged != null){
			this.logged = null;
			
			return "logout successefull";
			
		}else{
			
			throw new Exception("Nao eh possivel realizar logout. Nenhum usuarix esta logadx no +pop.");
		}
	}
	
	/**
	 * Metodo que serve para adicionar um amigo
	 * @param email
	 * email do usuario que receber� o convite
	 * @return
	 * string informando se a solicita��o foi enviada com �xito
	 * @throws Exception
	 * lan�a exce��o caso usuario n�o esteja cadastrado na base
	 */
	
	public String adicionaAmigo(String email) throws Exception{
		
		User usuario = buscaUsuario(email);
		
		if(this.logged != null){
			
			logged.adicionaAmigo(usuario);
		}
		
		return "voce precisa estar logado.";
	}
	
	/**
	 * metodo que serve para aceitar uma solicita��o de amizade que foi recebida
	 * @param email
	 * email do usuario que enviou a solicita��o
	 * @throws Exception
	 * lan�a exce��o caso o usuario n�o tenha enviado tal solicita��o.
	 */
	
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
	
	
	/**
	 * Metodo que serve para rejeitar uma solicita��o de amizade recebida.
	 * @param email
	 * email da pessoa que enviou a solicita��o
	 * @throws Exception
	 * lan�a exce��o caso a pessoa n�o tenha enviado tal solicita��o.
	 */
	
	public void rejeitaAmizade(String email) throws Exception{
		
		User usuario = buscaUsuario(email);
		
		if(logged != null){
			
			logged.rejeitaAmizade(usuario);	
		}
	}
	
	/**
	 * Metodo serve para exclui amigo da lista de amigos do usuario
	 * @param email
	 * email do usuario que ser� excluido
	 * @throws Exception
	 * lan�a exce��o caso o usuario n�o seja amigo ou n�o seja encontrado na base.
	 */

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
	
	/**
	 * Metodo serve para criar e coloca-lo no mural do usuario
	 * @param message
	 * mensagem que ser� passada, podendo conter imagem, audio e hashtags
	 * @param date
	 * data do post
	 * @throws Exception
	 * lan�a exce��o caso algum campo seja invalido ou usuario n�o esteja logado
	 */
	
	public void criaPost(String message, String date) throws Exception{
		
		if(logged != null){
			
			Post post = postFactory.createPost(message, date);
			
			logged.getMural().add(post);
			
		}
		
	}
	
	
	/**
	 * Metodo serve para adicionar pops a certo usuario.
	 * @param valor
	 * valor de pops que ser� adicionado
	 */

	public void adicionaPop(int valor){
		logged.adicionaPop(valor);
	}
	
	/**
	 * Metodo que atualiza o rank de usuarios mais populares e menos populares.
	 * @return
	 * string com a informa��o dos 3 mais populares e dos 3 menos populares
	 */
	
	public String atualizaRanking(){

		Collections.sort(allUsers);
		
		String maisPopulares = "Mais Populares: (1) "+ allUsers.get(0).getName() + " " + allUsers.get(0).getPops()+ "; (2) "+ allUsers.get(1).getName() + " " + allUsers.get(1).getPops()+ "; (3) "+ allUsers.get(2).getName() + " " + allUsers.get(2).getPops()+ ";";
		
		Collections.reverse(allUsers);
		
		String menosPopulares = "Menos Populares: (1) "+ allUsers.get(0).getName() + " " + allUsers.get(0).getPops()+ "; (2) "+ allUsers.get(1).getName() + " " + allUsers.get(1).getPops()+ "; (3) "+ allUsers.get(2).getName() + " " + allUsers.get(2).getPops()+ ";";
		
		return maisPopulares + " | " + menosPopulares;
	}
	
	/**
	 * Metodo que atualiza o trendingTopics pelos mais utilizados
	 * @return
	 * string com a informa��o das hashtags mais utilizadas entre todos os usuarios.
	 */	
	
	public String atualizaTrendingTopics()throws Exception{
		
		trendingTopics = new ArrayList<Hashtag>();
		
		for (User user : allUsers) {
			for (Post post : user.getMural()){
				for (String string : post.getHashtagList()) {
					
					Hashtag hashtag = hashtagFactory.criaHashtag(string);
					
					if(trendingTopics.contains(hashtag)){
						
						Hashtag hash = buscaHashtag(hashtag.getHashtag());
						
						hash.adicionaOcorrencia();
						
					}else{
						trendingTopics.add(hashtag);
					}
				}	
			}
		}
		
		Collections.sort(trendingTopics);
		
		return "Trending Topics:  (1) " + trendingTopics.get(0).getHashtag() + ": " + trendingTopics.get(0).getOcorrencias() + "; " + "(2) " + trendingTopics.get(1).getHashtag() + ": " + trendingTopics.get(1).getOcorrencias() + "; " + "(3) " + trendingTopics.get(2).getHashtag() + ": " + trendingTopics.get(2).getOcorrencias() + ";";
	}
	
	/**
	 * Metodo que serve para curtir post de algum amigo
	 * @param email
	 * email do usuario para localiza��o
	 * @param index
	 * indice do post que ser� adicionado a curtida
	 * @throws Exception
	 * lan�a exce��o caso usuario n�o exista, n�o seja amigo ou indice esteja incorreto
	 */
	
	public void curtirPost(String email, int index) throws Exception {
		
		User usuario = buscaUsuario(email);
		
		logged.curtirPost(usuario, index);
		
	}
	
	/**
	 * Metodo que serve para rejeitar post de algum amigo
	 * @param email
	 * email do usuario para localiza��o
	 * @param index
	 * indice do post que ser� adicionado a rejei��o
	 * @throws Exception
	 * lan�a exce��o caso usuario n�o exista, n�o seja amigo ou indice esteja incorreto
	 */
	
	public void rejeitarPost(String email, int index) throws Exception {
		
		User usuario = buscaUsuario(email);
		
		logged.rejeitarPost(usuario, index);
		
	}
		
	/**
	 * Metodo que retorna pops de um usuario
	 * @param email
	 * email do usuario
	 * @return
	 * int com a quantidade de pops do usuario
	 * @throws Exception
	 * lan�a exce��o caso email seja invalido
	 */
	
	public int getPopsUsuario(String email) throws Exception {
		
		User usuario = buscaUsuario(email);
		
		if(this.logged == null){
		
			return usuario.getPops();
			
		} else {
			
			throw new Exception("Erro na consulta de Pops. Um usuarix ainda esta logadx.");
		
		}
		
	}
	
	/**
	 * Metodo retorna pops do usuario logado
	 * @return
	 * int com a quantidade de pops do usuario
	 */
	
	public int getPopsUsuario(){
		return logged.getPops();		
	}
	
	/**
	 * Metodo que retorna post
	 * @param index
	 * indice do post que ser� buscado
	 * @return
	 * string contendo informa��es do post
	 */
	
	public String getPost(int index) {
		
			return logged.getMural().get(index).toString();
	
	}
	
	/**
	 * Metodo que retorna a data ou as hashtags de dado post
	 * @param field
	 * campo solicitado
	 * @param index
	 * indice do post que ser� buscado
	 * @return
	 * string contendo as informa��es do campo solicitado
	 */
	
	public String getPost(String field, int index) {
		
		if (field.equals("Hashtags")) {
		
			return logged.getMural().get(index).getOnlyHashtag().replace(" ", ",");
			
		} else if (field.equals("Data")) {
			
			return logged.getMural().get(index).getDateTime();
			
		} else {
			return logged.getMural().get(index).getMensagem();
			
		}
		
	}
	
	/**
	 * Metodo que serve para saber a quantide de curtidas de certo post
	 * @param postIndex
	 * indice do post que ser� buscado
	 * @return
	 * quantidade de curtidas do post
	 * @throws Exception
	 * lan�a exce��o caso indice esteja incorreto
	 */
	
	public int getCurtidasPost(int postIndex) throws Exception{
		
		if (postIndex < 0){
			
			throw new Exception("Requisicao invalida. O indice deve ser maior ou igual a zero.");
		
		} else if (postIndex > logged.getMural().size()){

			throw new Exception("Post #" + postIndex + " nao existe. Usuarix possui apenas " + (logged.getMural().size()) + " post(s).");
		
		} else { return logged.getMural().get(postIndex).getQtdCurtidas(); } 
	
	}
	
	/**
	 * Metodo que serve para saber a quantide de rejei��es de certo post
	 * @param postIndex
	 * indice do post que ser� buscado
	 * @return
	 * quantidade de rejei��es do post
	 * @throws Exception
	 * lan�a exce��o caso indice esteja incorreto
	 */
	
	public int getRejeicoesPost(int postIndex) throws Exception{
		
		if (postIndex < 0){
			
			throw new Exception("Requisicao invalida. O indice deve ser maior ou igual a zero.");
		
		} else if (postIndex > logged.getMural().size()){

			throw new Exception("Post #" + postIndex + " nao existe. Usuarix possui apenas " + (logged.getMural().size()) + " post(s).");
		
		} else { return logged.getMural().get(postIndex).getQtdRejeicoes(); } 

		
	}
	
	/**
	 * Metodo retorna pops de dado post
	 * @param postIndex
	 * indice do post
	 * @return
	 * int com a quantidade de pops do post
	 * @throws Exception
	 * lan�a exce��o caso indice seja invalido
	 */
	
	public int getPopsPost(int postIndex) {
		return logged.getMural().get(postIndex).getPop();
	}
	
	/**
	 * Metodo que retorna Post com as informa��es solicitadas
	 * @param index
	 * indice do post
	 * @param postIndex
	 * informa��es que deve conter no post
	 * @return
	 * string com as informa��es solicitadas
	 * @throws Exception
	 * lan�a exce��o caso os indices sejam invalidos
	 */
	
	public String getConteudoPost(int index, int postIndex) throws Exception{

		if (index < 0){
			
			throw new Exception("Requisicao invalida. O indice deve ser maior ou igual a zero.");
		
		} else if (index > logged.getMural().size()){

			throw new Exception("Item #" + index + " nao existe nesse post, ele possui apenas " + (logged.getMural().size() + 1) + " itens distintos.");
			
		} else {
			
			return logged.getMural().get(postIndex).getConteudoPost(index);
		}
		
	}
	
	/**
	 * Metodo que retorna informa��es completas do usuario logadocom exce��o da senha
	 * @param field
	 * campo que est� sendo solicitado
	 * @return
	 * campo que foi solicitado
	 * @throws Exception
	 * lan�a exce��o caso campo seja invalido
	 */

	public String getInfoUsuario(String field) throws Exception{
		
		return logged.getInfoUsuario(field);
	}
	
	public int getTotalPosts(){
		return logged.getTotalPosts();
	}
	
	/**
	 * Metodo que retorna informa��o de algum usuario da base exceto a senha
	 * @param field
	 * campo que est� sendo solicitado
	 * @param email
	 * email do usuario dono da informa��o
	 * @return
	 * campo solicitado
	 * @throws Exception
	 * lan�a exce��o caso campo seja invalido
	 */
	
	public String getInfoUsuario(String field, String email) throws Exception{
		
		User usuario = buscaUsuario(email);
		
		return usuario.getInfoUsuario(field);
	}
	
	public String getPopularidade() {
		return logged.getPopularidade();
	}
	
	/**
	 * metodo que remove usuario da base do sistema
	 * @param email
	 * eamil do usuario que ser� removido
	 * @return
	 * string informando se foi removido com sucesso
	 * @throws Exception
	 * lan�a exce��o caso usuario n�o esteja cadastrado
	 */
	
	public String removeUsuario(String email) throws Exception{
		
		User usuario = buscaUsuario(email);	
			
		if(usuario.getEmail().equals(email)){
			allUsers.remove(usuario);
				
			return "user successfully removed.";
		}else{
			throw new Exception("You need to specify a valid field.");
			
		}
	}
	
	/**
	 * Metodo que serve para atualizar informa��es do usuario
	 * @param field
	 * informa��o do que ser� atualizado
	 * @param newField
	 * informa��o de como ser� atualizado
	 * @throws Exception
	 * lan�a exce��o caso haja algum campo inv�lido
	 */

	public void atualizaPerfil(String field, String newField) throws Exception{
			
		if(this.logged != null){
			
			logged.atualizaPerfil(field, newField);
				
		}else{
			throw new Exception("Nao eh possivel atualizar um perfil. Nenhum usuarix esta logadx no +pop.");
		}
	}
	
	/**
	 * Metodo que serve para atualizar senha do usuario
	 * @param field
	 * campo que ser� atualizado(senha)
	 * @param novaSenha
	 * nova senha que ser� salva
	 * @param senhaAntiga
	 * senha antiga para autentica��o
	 * @throws Exception
	 * lan�a exce��o caso a senha antiga n�o seja correta
	 */
		
	public void atualizaPerfil(String field, String novaSenha, String senhaAntiga) throws Exception{
			
		if(this.logged != null){
				
			logged.mudaSenha(novaSenha, senhaAntiga);			
		}
	}
	
	/**
	 * Metodo que serve para buscar usuario na hora do login na lista de usuarios cadastrados
	 * @param email
	 * email do usuario
	 * @return
	 * usuario que foi encontrado
	 * @throws Exception
	 * lan�a exe��o caso usuario n�o esteja cadastrado
	 */
	
	private User buscaUsuarioLogin(String email) throws Exception{
		
		for(User user : allUsers){
			if(user.getEmail().equals(email)){
				return user;
			}
		}
		
		throw new Exception("Nao foi possivel realizar login. Um usuarix com email " + email + " nao esta cadastradx.");
	}
	
	/**
	 * Metodo que serve para buscar usuario na lista de usuarios cadastrados
	 * @param email
	 * email do usuario
	 * @return
	 * usuario que foi encontrado
	 * @throws Exception
	 * lan�a exe��o caso usuario n�o esteja cadastrado
	 */
	
	private User buscaUsuario(String email) throws Exception{

		for(User user : allUsers){
			if(user.getEmail().equals(email)){
				return user;
			}
		}
		
		throw new Exception("Um usuarix com email " + email + " nao esta cadastradx.");
	}
	
	private Hashtag buscaHashtag(String hash)throws Exception{
		
		for (Hashtag hashtag : trendingTopics) {
			if(hash.equals(hashtag.getHashtag())){
				return hashtag;
			}
		}
		
		throw new Exception("hashtag nao encontrada");
	}
	
	/**
	 * Metodo que serve para autentica��o de senha para login
	 * @param usuario
	 * usuario que esta tentando logar
	 * @param email
	 * email do usuario
	 * @param senha
	 * senha para verifica��o
	 * @return
	 * boolean informando se a senha estava correta ou n�o
	 * @throws Exception
	 * lan�a exce��o caso a senha esteja errada ou usuario j� esteja logado
	 */
	
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
	
	/**
	 * Metodo utilizado para pegar certo post do feed de noticias ordenado pelos mais recentes
	 * @param indice
	 * indice que ser� passado para a localiza��o do post
	 * @return
	 * string contendo as informa��es do post
	 */

	public String getPostFeedNoticiasRecentes(int indice) {
		
		return logged.getPostFeedNoticiasRecentes(indice);
		
	}
	
	/**
	 * Metodo utilizado para atualizar o feed de noticias do usuario pegando os posts mais recentes de seus amigos, dependendo da popularidade.
	 */

	public void atualizaFeed() {
		
		logged.atualizaFeed();
		
	}
	
	/**
	 * Metodo utilizado para pegar certo post do feed de noticias ordenado pelos mais populares
	 * @param indice
	 * indice que ser� passado para a localiza��o do post
	 * @return
	 * string contendo as informa��es do post
	 */

	public String getPostFeedNoticiasMaisPopulares(int indice) {
		
		return logged.getPostFeedNoticiasMaisPopulares(indice);
	}

}