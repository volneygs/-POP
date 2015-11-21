package controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.text.StyledEditorKit.ForegroundAction;

import factory.PostFactory;
import factory.UserFactory;
import users.Post;
import users.User;

public class Controller {
	
	private ArrayList<User> allUsers;
	private ArrayList<String> trendingTopics;
	private UserFactory userFactory;
	private PostFactory postFactory;
	private User logged;
	
	
	public Controller(){
		this.userFactory = new UserFactory();
		this.postFactory = new PostFactory();
		this.allUsers = new ArrayList<User>();
		this.trendingTopics = new ArrayList<String>();
		this.logged = null;
	}
	
	public void iniciaSistema() {
		
		/* allUsers = new ArrayList<User>();
		
		try{
			
			ObjectInputStream read = new ObjectInputStream(new BufferedInputStream(new FileInputStream("todos_os_usuarios.dat")));
			
			
			while(true){
				
				allUsers.add((User)read.readObject());
			}
			
		}catch(Exception e){
			

		} */
		
	}
	
	/**
	 * Metodo que serve para encerrar o sistema e salvar informações nos arquivos
	 * @return
	 * string informado que sistema foi fechado
	 * @throws Exception
	 * lança exceção caso haja algum usuario logado
	 */
	
	public String fechaSistema() throws Exception{
			
		if(logged == null){
			
			File arquivoUsuarios = new File("todos_os_usuarios.dat");
			
			arquivoUsuarios.createNewFile();
			
			try{
				
				ObjectOutputStream write = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("todos_os_usuarios.dat")));
				
				for (User user : allUsers) {
					
					write.writeObject(user);
				}
				
				write.close();
				
				return "sistema fechado";
				
			}catch(Exception e){
				
				throw new Exception("aconteceu algum erro.");
			}
			
		}else{
			throw new Exception("Nao foi possivel fechar o sistema. Um usuarix ainda esta logadx.");
		}
	}
	
	
	public boolean baixaPosts() throws Exception{
		
		if(logged.getMural().size() == 0){
			throw new Exception("Erro ao baixar posts. O usuario nao possui posts.");
		}
		
		// ACHO QUE VAI SER NECESSÁRIO MUDAR O NOME DO ARQUIVO
		
		String email = logged.getEmail().replace("@","[at]" ).replace(".", "");
		String hashtags;
		
		DateTimeFormatter dateValidator = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		
		String arquivoNome = "arquivos/posts_" + email + ".txt";
		
		try{
			
			File meuArquivo = new File(arquivoNome);
			
			PrintWriter meuWriter = new PrintWriter(new BufferedWriter(new FileWriter(meuArquivo, true)));
			
			int i = 1;

			for (Post post : logged.getMural()) {
				
				hashtags = post.getHashtags().replace(",", " ");

				meuWriter.println("Post #" + (i) + " - " + post.getData().format(dateValidator));
				meuWriter.println("Conteudo:");
				meuWriter.println(post.getOnlyText());
				if(!(post.getOnlyFiles().equals(""))){
					meuWriter.println(post.getOnlyFiles());
				}
				// ADICIONAR ARQUIVO DE IMAGEM E DE AUDIO
				
				if(!(hashtags.equals(""))){
					meuWriter.println(hashtags);
				}
				meuWriter.println("+Pop: " + post.getPop());
				meuWriter.println();
				meuWriter.println();
				
				i++;
			}
				
			meuWriter.close();
			return true;
			
		}catch(IOException e){
			// DETERMINAR COMO TRATAR
			throw new Exception("O arquivo não foi encontrado!");
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
	 * lança exceção caso usuario não esteja logado
	 */
	
	public int getQtdAmigos() throws Exception{
		
		if(logged != null){
			return logged.getAmigos().size();
		}else{
			throw new Exception("Usuarix deve estar logado.");
		}
	}
	
	/**
	 * Metodo que serve para verificar quantas notificações ainda não foram lidas
	 * @return
	 * int representando a quantidade de notificações
	 * @throws Exception
	 * lança exceção caso usuario não esteja logado
	 */
	
	public int getNotificacoes() throws Exception{
		
		if(logged != null){
			return logged.getQtdNotificacao();
		}else{
			throw new Exception("Usuarix deve estar logado.");
		}
	}
	
	/**
	 * Metodo que retorna a proxima notificação ainda não lida
	 * @return
	 * proxima notificação não lida
	 * @throws Exception
	 * lança exceção caso usuario não esteja logado
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
	 * lança exceção caso algum parametro esteja em formato invalido.
	 */

	public String registerUser (String nome, String email, String senha, String dataDeNascimento) throws Exception{
		
		String foto = "resources/default.jpg";
		
		return registerUser(nome, email, senha, dataDeNascimento, foto);
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
	
	public String registerUser (String nome, String email, String senha, String dataDeNascimento, String foto) throws Exception{
		
		User usuario = userFactory.createUser(nome, email, senha, dataDeNascimento, foto);
		this.allUsers.add(usuario);

		return email;
	}

	public ArrayList<User> getAllUsers(){
		
		return this.allUsers;
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
	 * lança exceção se o usuario já estiver deslogado.
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
	 * email do usuario que receberá o convite
	 * @return
	 * string informando se a solicitação foi enviada com êxito
	 * @throws Exception
	 * lança exceção caso usuario não esteja cadastrado na base
	 */
	
	public String adicionaAmigo(String email) throws Exception{
		
		User usuario = buscaUsuario(email);
		
		if(this.logged != null){
			
			logged.adicionaAmigo(usuario);
		}
		
		return "voce precisa estar logado.";
	}
	
	/**
	 * metodo que serve para aceitar uma solicitação de amizade que foi recebida
	 * @param email
	 * email do usuario que enviou a solicitação
	 * @throws Exception
	 * lança exceção caso o usuario não tenha enviado tal solicitação.
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
	
	public static String atualizador(String currentValue){
		 
		return Integer.toString(Integer.parseInt(currentValue) + 1);
	 
	}
	
	/**
	 * Metodo que serve para rejeitar uma solicitação de amizade recebida.
	 * @param email
	 * email da pessoa que enviou a solicitação
	 * @throws Exception
	 * lança exceção caso a pessoa não tenha enviado tal solicitação.
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
	 * email do usuario que será excluido
	 * @throws Exception
	 * lança exceção caso o usuario não seja amigo ou não seja encontrado na base.
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
	 * mensagem que será passada, podendo conter imagem, audio e hashtags
	 * @param date
	 * data do post
	 * @throws Exception
	 * lança exceção caso algum campo seja invalido ou usuario não esteja logado
	 */
	
	public void criaPost(String message, String date) throws Exception{
		
		if(logged != null){
			
			Post post = postFactory.createPost(message, date);
			
			logged.getMural().add(post);
			
		}
		
	}
	
	public void todasHashtags() {
		
		for (User user : allUsers) {
			
			for (int i = 0; i < user.getMural().size(); i++) {
			
				for (String hashtag : user.getMural().get(i).getHashtags().split(",")) {
					int indice = trendingTopics.indexOf(hashtag); //indice da hashtag
					
					if(trendingTopics.contains(hashtag)==false){  //lista trending topics NAO CONTEM hashtag
						trendingTopics.add(hashtag);				//adiciona na lista
						trendingTopics.add("1");					//adiciona n1 apos hashtag adicionada
					} else {    							// caso ja exista elemento igual na lista de trending topics
						String atualizaValor = atualizador(trendingTopics.get(indice +1)); //incremento o valor do numero em +1
						trendingTopics.set(indice +1, atualizaValor);					 //atualizo esse valor na lista de trending topics
					}
				}
				
			}
			
		}
		
	}
	
	/**
	 * Metodo serve para adicionar pops a certo usuario.
	 * @param valor
	 * valor de pops que será adicionado
	 */

	public void adicionaPop(int valor){
		logged.adicionaPop(valor);
	}
	
	/**
	 * Metodo que atualiza o rank de usuarios mais populares e menos populares.
	 * @return
	 * string com a informação dos 3 mais populares e dos 3 menos populares
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
	 * string com a informação das hashtags mais utilizadas entre todos os usuarios.
	 */	
	
	public String atualizaTrendingTopics(){
		
		if (this.trendingTopics.isEmpty()){
			todasHashtags();
			hashtagsSort(trendingTopics);
		} else {
			this.trendingTopics.clear();
			todasHashtags();
			hashtagsSort(trendingTopics);
		}
		
		hashtagsSort(trendingTopics);
		
		Collections.reverse(trendingTopics);
		
		return "Trending Topics:  (1) " + trendingTopics.get(1) + ": " + trendingTopics.get(0) + "; " + "(2) " + trendingTopics.get(3) + ": " + trendingTopics.get(2) + "; " + "(3) " + trendingTopics.get(5) + ": " + trendingTopics.get(4) + ";";
	}
	
	/**
	 * Metodo que serve para curtir post de algum amigo
	 * @param email
	 * email do usuario para localização
	 * @param index
	 * indice do post que será adicionado a curtida
	 * @throws Exception
	 * lança exceção caso usuario não exista, não seja amigo ou indice esteja incorreto
	 */
	
	public void curtirPost(String email, int index) throws Exception {
		
		User usuario = buscaUsuario(email);
		
		logged.curtirPost(usuario, index);
		
	}
	
	/**
	 * Metodo que serve para rejeitar post de algum amigo
	 * @param email
	 * email do usuario para localização
	 * @param index
	 * indice do post que será adicionado a rejeição
	 * @throws Exception
	 * lança exceção caso usuario não exista, não seja amigo ou indice esteja incorreto
	 */
	
	public void rejeitarPost(String email, int index) throws Exception {
		
		User usuario = buscaUsuario(email);
		
		logged.rejeitarPost(usuario, index);
		
	}
	
	private static int compare(String str1, String str2){
		 
		if (Integer.parseInt(str1) > Integer.parseInt(str2)){
			return 1;
		}
		else if(Integer.parseInt(str1) < Integer.parseInt(str2)){
			return -1;
		}else{
			return 0;
		}
	}
	
	private static void hashtagsSort(ArrayList<String> list){
		 
		if(list.size() > 4){
 
			for(int i = 3; i < list.size(); i += 2){
 
				String keyValue = list.get(i);
				String keyKey = list.get(i - 1);
 
				int j = i - 2;
 
				while(j >= 1 && (compare(list.get(j), keyValue) == 1 ||
						(compare(list.get(j), keyValue) == 0 && list.get(j-1).compareToIgnoreCase(list.get(i-1)) > 0) )){
					list.set(j+2, list.get(j));
					list.set(j+1, list.get(j-1));
					j -= 2;
				}
 
				list.set(j+1, keyKey);
				list.set(j+2, keyValue);
			}
 
		}
 
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
	 * indice do post que será buscado
	 * @return
	 * string contendo informações do post
	 */
	
	public String getPost(int index) {
		
			return logged.getMural().get(index).getMessage();
	
	}
	
	/**
	 * Metodo que retorna a data ou as hashtags de dado post
	 * @param field
	 * campo solicitado
	 * @param index
	 * indice do post que será buscado
	 * @return
	 * string contendo as informações do campo solicitado
	 */
	
	public String getPost(String field, int index) {
		
		if (field.equals("Hashtags")) {
		
			return logged.getMural().get(index).getHashtags();
			
		} else if (field.equals("Data")) {
			
			return logged.getMural().get(index).getDateTime();
			
		} else { return logged.getMural().get(index).getText(); }
		
	}
	
	/**
	 * Metodo que serve para saber a quantide de curtidas de certo post
	 * @param postIndex
	 * indice do post que será buscado
	 * @return
	 * quantidade de curtidas do post
	 * @throws Exception
	 * lança exceção caso indice esteja incorreto
	 */
	
	public int getCurtidasPost(int postIndex) throws Exception{
		
		if (postIndex < 0){
			
			throw new Exception("Requisicao invalida. O indice deve ser maior ou igual a zero.");
		
		} else if (postIndex > logged.getMural().size()){

			throw new Exception("Post #" + postIndex + " nao existe. Usuarix possui apenas " + (logged.getMural().size()) + " post(s).");
		
		} else { return logged.getMural().get(postIndex).getQtdCurtidas(); } 
	
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
	 * lança exceção caso indice seja invalido
	 */
	
	public int getPopsPost(int postIndex) {
		return logged.getMural().get(postIndex).getPop();
	}
	
	/**
	 * Metodo que retorna Post com as informações solicitadas
	 * @param index
	 * indice do post
	 * @param postIndex
	 * informações que deve conter no post
	 * @return
	 * string com as informações solicitadas
	 * @throws Exception
	 * lança exceção caso os indices sejam invalidos
	 */
	
	public String getConteudoPost(int index, int postIndex) throws Exception{

		if (index < 0){
			
			throw new Exception("Requisicao invalida. O indice deve ser maior ou igual a zero.");
		
		} else if (index > logged.getMural().size()){

			throw new Exception("Item #" + index + " nao existe nesse post, ele possui apenas " + (logged.getMural().size() + 1) + " itens distintos.");
			
		} else {	return logged.getMural().get(postIndex).getChest(index);	}
		
	}
	
	/**
	 * Metodo que retorna informações completas do usuario logadocom exceção da senha
	 * @param field
	 * campo que está sendo solicitado
	 * @return
	 * campo que foi solicitado
	 * @throws Exception
	 * lança exceção caso campo seja invalido
	 */

	public String getInfoUsuario(String field) throws Exception{
		
		return logged.getInfoUsuario(field);
	}
	
	/**
	 * Metodo que retorna informação de algum usuario da base exceto a senha
	 * @param field
	 * campo que está sendo solicitado
	 * @param email
	 * email do usuario dono da informação
	 * @return
	 * campo solicitado
	 * @throws Exception
	 * lança exceção caso campo seja invalido
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
	 * eamil do usuario que será removido
	 * @return
	 * string informando se foi removido com sucesso
	 * @throws Exception
	 * lança exceção caso usuario não esteja cadastrado
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
	 * Metodo que serve para atualizar informações do usuario
	 * @param field
	 * informação do que será atualizado
	 * @param newField
	 * informação de como será atualizado
	 * @throws Exception
	 * lança exceção caso haja algum campo inválido
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
	 * campo que será atualizado(senha)
	 * @param novaSenha
	 * nova senha que será salva
	 * @param senhaAntiga
	 * senha antiga para autenticação
	 * @throws Exception
	 * lança exceção caso a senha antiga não seja correta
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
	 * lança exeção caso usuario não esteja cadastrado
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
	 * lança exeção caso usuario não esteja cadastrado
	 */
	
	private User buscaUsuario(String email) throws Exception{

		for(User user : allUsers){
			if(user.getEmail().equals(email)){
				return user;
			}
		}
		
		throw new Exception("Um usuarix com email " + email + " nao esta cadastradx.");
	}
	
	/**
	 * Metodo que serve para autenticação de senha para login
	 * @param usuario
	 * usuario que esta tentando logar
	 * @param email
	 * email do usuario
	 * @param senha
	 * senha para verificação
	 * @return
	 * boolean informando se a senha estava correta ou não
	 * @throws Exception
	 * lança exceção caso a senha esteja errada ou usuario já esteja logado
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
	 * indice que será passado para a localização do post
	 * @return
	 * string contendo as informações do post
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
	 * indice que será passado para a localização do post
	 * @return
	 * string contendo as informações do post
	 */

	public String getPostFeedNoticiasMaisPopulares(int indice) {
		
		return logged.getPostFeedNoticiasMaisPopulares(indice);
	}

}