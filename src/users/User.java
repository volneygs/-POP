package users;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import comparator.PostComparator;

public class User implements Comparable<User> {
	
	private DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT);
	private String email;
	private String nome;
	private String senha;
	private LocalDate dataDeNascimento;
	private PostComparator comparador = new PostComparator();
	private String foto;
	private UsuarioPadrao usuarioFama;
	private List<Post> mural;
	private List<User> amigos;
	private List<String> solicitacoesDeAmizade;
	private List<String> notificacoes;
	private List<Post> feedNoticias;
	private String popularidade;
	private int pop;
	private int indiceNot;
	private int qtdNotificacao;
	

	public User(String nome, String email, String senha, String dataDeNascimento, String foto) throws Exception{
			
		this.email = email;
		this.nome = nome;
		this.dataDeNascimento = LocalDate.parse(dataDeNascimento, dateFormat);
		this.senha = senha;
		this.foto = foto;
		this.mural = new ArrayList<Post>();
		this.amigos = new ArrayList<User>();
		this.notificacoes = new ArrayList<String>();
		this.solicitacoesDeAmizade = new ArrayList<String>();
		this.usuarioFama = new Normal();
		this.popularidade = "Normal Pop";
		this.indiceNot = 0;
		this.qtdNotificacao = 0;
		this.pop = 0;
	}
	
	public void mudaNome(String novoNome) throws Exception{
		
		if(novoNome.equals("") || novoNome.trim().equals("")){
			throw new Exception("Erro na atualizacao de perfil. Nome dx usuarix nao pode ser vazio.");
		}else{
			this.nome = novoNome;
		}
	}
	
	public void mudaEmail(String novoEmail) throws Exception{
		
		if(novoEmail.equals("") || !(novoEmail.contains("@")) || !(novoEmail.contains(".com"))){				
			throw new Exception("Erro na atualizacao de perfil. Formato de e-mail esta invalido.");
		}else{
			this.email = novoEmail;
		}
	}
	
	public void mudaSenha(String novaSenha, String senhaAntiga) throws Exception{
		if(autenticacao(senhaAntiga)){
			senha = novaSenha;
		}else{
			throw new Exception("Erro na atualizacao de perfil. A senha fornecida esta incorreta.");
		}
	}
	
	public void mudaDataNascimento(String novaDataNascimento) throws Exception{
		
		try { 
			
			this.dataDeNascimento = LocalDate.parse(novaDataNascimento, dateFormat);
		
		} catch (DateTimeException e) {
				
			if (e.toString().contains("could not be parsed at index")){
				throw new Exception("Erro na atualizacao de perfil. Formato de data esta invalida.");
			
			}else if (e.toString().contains("Invalid value for")){
				throw new Exception("Erro na atualizacao de perfil. Data nao existe.");
			
			}else if (e.toString().contains("Invalid date")){
				throw new Exception("Erro na atualizacao de perfil. Data nao existe.");
			
			}
		}
		
	}
	
	public boolean autenticacao(String tentaSenha){
		if(tentaSenha.equals(senha)){
			return true;
		}else{
			return false;
		}
	}
	
	public void adicionaPop(int valor){
		
		this.pop = this.pop + valor;
		
		if(this.pop < 500 && !(this.usuarioFama instanceof Normal)){
			
			this.usuarioFama = new Normal();
			this.popularidade = "Normal Pop";
			
		}else if(this.pop >= 500 && this.pop < 1000 && !(usuarioFama instanceof CelebridadePOP)){
			
			this.usuarioFama = new CelebridadePOP();
			this.popularidade = "Celebridade Pop";
			
		}else if(this.pop >= 1000 && !(usuarioFama instanceof IconePOP)){
			this.usuarioFama = new IconePOP();
			this.popularidade = "Icone Pop";
		}
	}
	
	public void adicionaNotificacao(String notificacao){
		
		qtdNotificacao += 1;
		notificacoes.add(notificacao);
	}
	
	public int getNotificacoes(){
		return notificacoes.size();
	}
	
	public String getNextNotificacao() throws Exception{
		
		if(this.qtdNotificacao == 0){
			throw new Exception("Nao ha mais notificacoes.");
		}else{
			qtdNotificacao -= 1;
			String notificacao = notificacoes.get(indiceNot);
			indiceNot+= 1;
			return notificacao;
		}
		
	}
	
	public String getPopularidade() {
		
		return this.popularidade;
		
	}
	
	public void adicionaSoliticacaoAmizade(String email) {
		
		solicitacoesDeAmizade.add(email);
	}
	
	public boolean removeAmigo(User user){
		if(this.amigos.contains(user)){
			return this.amigos.remove(user);
			
		}else{
			return false;
		}
	}
	
	public void curtirPost(User usuario, int index) {
		
		usuarioFama.curtirPost(usuario, nome, index);
		
		
	}
	
	public void rejeitarPost(User usuario, int index) {
		
		usuarioFama.rejeitarPost(usuario, nome, index);
		
		
	}
	
	public void atualizaPerfil(String field, String newField) throws Exception{

		if(field.equals("Nome")){
			mudaNome(newField);
			
		}else if(field.equals("Foto")){
			this.foto = newField;
			
		}else if(field.equals("E-mail")){
			mudaEmail(newField);
			
		}else if(field.equals("Data de Nascimento")){
			mudaDataNascimento(newField);
		}
		
	}
	
	public String getPostFeedNoticiasRecentes(int indice){
		
		Collections.sort(feedNoticias);
		
		Collections.reverse(feedNoticias);
		
		return feedNoticias.get(indice).getMessage();
	}
	
	public String getPostFeedNoticiasMaisPopulares(int indice){
		
		Collections.sort(feedNoticias, comparador);
		
		return feedNoticias.get(indice).getMessage();
	}
	
	public void atualizaFeed(){
		
		feedNoticias = new ArrayList<Post>();
		List<Post> muralAmigo = new ArrayList<Post>();
		
		for (User user : amigos) {
			
			muralAmigo = user.getMural();
			
			Collections.sort(muralAmigo);
			
			if(user.getUsuarioFama() instanceof Normal){
				
				for (int i = 0; i < muralAmigo.size() && i < 2; i++) {
					
					feedNoticias.add(muralAmigo.get(i));
				}
				
			}else if(user.getUsuarioFama() instanceof CelebridadePOP){
				
				for (int i = 0; i < muralAmigo.size() && i < 4; i++) {
					
					feedNoticias.add(muralAmigo.get(i));
				}
			}else if(user.getUsuarioFama() instanceof IconePOP){
				
				for (int i = 0; i < muralAmigo.size() && i < 6; i++) {
					
					feedNoticias.add(muralAmigo.get(i));
				}
			}
		}
	}

	public void adicionaAmigo(User usuario) {
		
		String notificacao = this.nome + " quer sua amizade.";
		
		usuario.adicionaNotificacao(notificacao);
		
		usuario.adicionaSoliticacaoAmizade(this.email);
		
	}

	public void rejeitaAmizade(User usuario) throws Exception{
		
		if(getSolicitacoesDeAmizades().contains(usuario.getEmail())){
			
			getSolicitacoesDeAmizades().remove(usuario.getEmail());
			
			String notificacao = this.nome + " rejeitou sua amizade.";
			
			usuario.adicionaNotificacao(notificacao);
			
		}else{
			throw new Exception(usuario.getName() + " nao lhe enviou solicitacoes de amizade.");
		}
		
	} 
	
	public List<String> getSolicitacoesDeAmizades(){
		return solicitacoesDeAmizade;
		
	}
	
	public List<String> getNotification(){
		return notificacoes;
	}
	
	public List<Post> getMural(){
		return this.mural;
	}
	
	public int getQtdNotificacao(){
		return qtdNotificacao;
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
	
	public int getPops(){
		return pop;
	}
	
	public UsuarioPadrao getUsuarioFama(){
		
		return usuarioFama;
	}
	
	public List<User> getAmigos(){
		
		return amigos;
	}

	public String getInfoUsuario(String field) throws Exception{

		if(field.equals("Nome")){
			return this.nome;

		}else if (field.equals("Senha")){
			throw new Exception("A senha dx usuarix eh protegida.");
			
		}else if (field.equals("Data de Nascimento")){
			return this.dataDeNascimento.toString();
			
		}else if (field.equals("Foto")){
			return this.foto;
			
		}else{
			throw new Exception("Vc precisa especificar um campo valido.");
		}
	}

	public String getInfoUsuario(String field, User usuario) throws Exception{
	
		if(field.equals("Nome")){
			return usuario.getName();

		}else if (field.equals("Senha")){
			throw new Exception("A senha dx usuarix eh protegida.");
					
		}else if (field.equals("Data de Nascimento")){
			return usuario.getBirthdate().toString();
					
		}else if (field.equals("Foto")){
			return usuario.getImage();
			
		}else{
			throw new Exception("Vc precisa especificar um campo valido.");
		}
		
	}

	@Override
	public int compareTo(User user) {
		if (this.pop < user.getPops()) { 
			
           	return 1;
           	
        }else if (this.pop > user.getPops()) {
        	
            return -1;
            
        }else {
        	
        	return email.compareTo(user.getEmail()) * (-1);
        }
        
	}

}
