package users;
import java.time.*;
import java.time.format.*;
import java.util.*;

public class Post {
	
	private ArrayList<String> stringChest;
	
	private ArrayList<String> hashtagList;
	private int pop;
	private LocalDateTime date;
	private DateTimeFormatter dateValidator = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss");
	private String text;
	private String files;
	private String hashtags;
	private String message;

	public Post(String message, String date) throws Exception{
		
		this.pop = 0;
		this.date = LocalDateTime.parse(date, dateValidator);
		this.hashtagList = new ArrayList<String>();
		this.stringChest = new ArrayList<String>();
		this.message = message;
		
		stringsManipulator();

	}
	
	public void stringsManipulator() throws Exception{
		
		for (int i = 0; i < message.indexOf("<") || i < message.indexOf("#"); i ++){
			
			this.text = message.substring(0, i);
			this.files = message.substring(i+1, message.indexOf("#"));
			this.hashtags = message.substring(message.indexOf("#")-1, message.length()).trim();
			
			if (i == (message.indexOf("<")-1) || i == (message.indexOf("#")-1)) {
				
				this.text = message.substring(0, i);
				this.files = message.substring(i+1, message.indexOf("#"));
				this.hashtags = message.substring(message.indexOf("#")-1, message.length()).trim();
		
				getText(this.text);
				getFiles(this.files);
				getHashtags(this.hashtags);

			}
		}
	}
	
	public void getText(String text) throws Exception {
		
		if (text.length() < 199) {
			
			stringChest.add(text);
			
		} else { throw new Exception("Nao eh possivel criar o post. O limite maximo da mensagem sao 200 caracteres."); }
		
	}
	
	public void getFiles(String files){
		
		for (String i: files.split("<imagem>|</imagem>|<audio>|</audio>| ")) {
			
			if (i.contains("imagens/")) {
				
				stringChest.add(i);
				
			} else if (i.contains("musicas/")) {
				
				stringChest.add(i);
				
			}
		}
	}
	
	public void getHashtags(String hashtags) throws Exception {
		
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
	
	public String getMessage(){
		
		return this.text + this.files + " " + this.hashtags + " (" + this.date.format(dateTimeFormatter) + ")";
	
	}
	
	public String getDateTime(){
		
		return this.date.format(dateTimeFormatter);
		
	}
	
	public String getHashtags(){
		
		return hashtags.toString().trim().replace("[", "").replace("]", "").replace(" ", ",");

	}
	
	public String getText(){
		
		return this.text;
		
	}
	
	public int getPop(){
		
		return pop;
	}
	
	public void addPop(int valor){
		
		this.pop = this.pop + valor;
	}
	
}
