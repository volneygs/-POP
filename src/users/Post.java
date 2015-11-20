package users;
import java.time.*;
import java.time.format.*;
import java.util.*;

public class Post implements Comparable<Post>{
	
	private ArrayList<String> stringChest;
	
	private ArrayList<String> hashtagList;
	private int pop;
	private LocalDateTime date;
	private DateTimeFormatter dateValidator = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss");
	private StringBuilder text;
	private String files;
	private String hashtags;
	private String message;
	private int qtdCurtidas;
	private int qtdRejeicoes;
	
	public Post(String message, String date) throws Exception{
		
		this.pop = 0;
		this.qtdCurtidas = 0;
		this.qtdRejeicoes = 0;
		this.date = LocalDateTime.parse(date, dateValidator);
		this.hashtagList = new ArrayList<String>();
		this.stringChest = new ArrayList<String>();
		this.message = message;
		this.files = new String();
		this.hashtags = new String();

		stringsManipulator();

	}
	
	public void stringsManipulator() throws Exception{
		
		this.text = new StringBuilder();
		String[] manipulator = message.split(" ");

		for (int i = 0; i < manipulator.length; i++) {
			if (manipulator[i].contains("<imagem>")) {
				this.files += manipulator[i] + " ";
			} else if (manipulator[i].contains("<audio>")) {
				this.files += manipulator[i] + " ";
			} else if (manipulator[i].contains("#")) {
				//this.hashtagList.add(manipulator[i]);
				this.hashtags += manipulator[i] + " ";
			} else if (this.hashtags.isEmpty() == false) {
				//this.hashtagList.add(manipulator[i]);
				this.hashtags += manipulator[i] + " ";
			} else { this.text.append(manipulator[i] + " "); }
		}
	
		setText(this.text);
		
		if (this.files.isEmpty() == false) {
			setFiles(this.files);
		}
		
		if (this.hashtags.isEmpty() == false) {
			setHashtags(this.hashtags);
		}
	}
	
	/**
	 * adiciona a hashtag "#epicwin caso não haja nenhuma ao post
	 */
	
	public void addEpicWin(){
		
		if (this.hashtagList.contains("#epicwin") == false){
			this.hashtagList.add("#epicwin");
		}
	}
	
	/**
	 * adiciona a hashtag "#epicfail caso não haja nenhuma ao post
	 */
	
	public void addEpicFail(){
		if (this.hashtagList.contains("#epicfail") == false){
			this.hashtagList.add("#epicfail");
		}
	}
	
	/**
	 * cria a mensagem do post
	 * @param text
	 * string que irá formar o post
	 * @throws Exception
	 * lança exceção caso o post tenha muitos caracteres
	 */
	
	public void setText(StringBuilder text) throws Exception {
		
		if (text.toString().trim().length() < 199) {
			
			stringChest.add(text.toString().trim());
			
		} else { throw new Exception("Nao eh possivel criar o post. O limite maximo da mensagem sao 200 caracteres."); }
		
	}
	
	public void setFiles(String files){
		
		for (String i: files.split("<imagem>|</imagem>|<audio>|</audio>| ")) {
			
			if (i.contains("imagens/")) {
				
				stringChest.add(i);
				
			} else if (i.contains("musicas/")) {
				
				stringChest.add(i);
				
			}
		}
	}
	
	public void setHashtags(String hashtags) throws Exception {
		
		for (String k: this.hashtags.split(" ")) {
			
			if (k.contains("#")) {
				
				hashtagList.add(k.trim());

			} else if (k.contains("#") == false) {
				
				throw new Exception("Nao eh possivel criar o post. As hashtags devem comecar com '#'. Erro na hashtag: '" + k + "'.");
				
			}
		}
	}
	
	public String getChest(int index){
		
		if (this.stringChest.get(index).toString().contains("imagens/")){
		
			return ("$arquivo_imagem:" + this.stringChest.get(index).toString());
		
		} else if (this.stringChest.get(index).toString().contains("musicas/")){
			
			return ("$arquivo_audio:" + this.stringChest.get(index).toString());
			
		} else { return this.stringChest.get(index).toString(); }
		
	}
	
	public String getDateTime(){
		
		return this.date.format(dateTimeFormatter);
		
	}
	
	public LocalDate getLocalDate(){
		return this.date.toLocalDate();
	}
	
	public LocalDateTime getData(){
		
		return this.date;
	}
	
	public String getHashtags(){
		
		return hashtagList.toString().replaceAll("\\[|\\]", "").replaceAll("\\s+", "");

	}
	
	public String getText(){
		
		if (this.files.isEmpty() == false) {
			return this.text.toString() + this.files.trim();
		} else { return this.text.toString().trim(); }
	
	}

	public String getMessage(){
		
		return this.getText() + " " + getHashtags().replaceAll(",", " ") + " (" + this.date.format(dateTimeFormatter) + ")";
	
	}
	
	public int getPop(){
		
		return pop;
	}
	
	public int getQtdCurtidas(){
		return this.qtdCurtidas;
	}
	
	public int getQtdRejeicoes(){
		return this.qtdRejeicoes;
	}
	
	public void addPop(int valor){
		
		this.pop = this.pop + valor;
	}
	
	public void addCurtida(int valor){
		this.qtdCurtidas += 1;
	}
	
	public void addRejeicao(int valor){
		this.qtdRejeicoes += 1;
	}

	@Override
	public int compareTo(Post post){
			
		if(date.isBefore(post.getData())){
			return 1;
		}else if(date.isAfter(post.getData())){
			return -1;
		}else{
			return 0;
		}
	}
	
	/*public String toString(){
		
		return message + hashtags + "(" + date.format(dateTimeFormatter) + ")";
	} */
}
