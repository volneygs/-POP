package users;
import java.time.*;
import java.time.format.*;
import java.util.*;
import java.lang.*;

public class Post {
	
	private ArrayList<String> stringChest;
	
	private ArrayList<String> postList;
	private ArrayList<String> textFiles;
	private ArrayList<String> imageFiles;
	private ArrayList<String> audioFiles;
	private ArrayList<String> hashtagList;
	private int pop;
	private User user;
	private LocalDateTime date;
	private DateTimeFormatter dateTimeFormatPost = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss");
	private String text;
	private String files;
	private String hashtags;
	private String message;

	public Post(String message, String date) throws Exception{
		
		this.pop = 0;
		this.date = LocalDateTime.parse(date, dateTimeFormatPost);
		this.textFiles = new ArrayList<String>();
		this.imageFiles = new ArrayList<String>();
		this.audioFiles = new ArrayList<String>();
		this.hashtagList = new ArrayList<String>();
		this.postList = new ArrayList<String>();
		this.stringChest = new ArrayList<String>();
		
		for (int i = 0; i < message.indexOf("<") || i < message.indexOf("#"); i ++){
			
			if (i == (message.indexOf("<")-1) || i == (message.indexOf("#")-1)) {
				
				this.text = message.substring(0, i);
				this.files = message.substring(i+1, message.indexOf("#"));
				this.hashtags = message.substring(message.indexOf("#")-1, message.length()).trim();
					
				if (this.text.length() < 199) {
					
					textFiles.add(this.text);
					stringChest.add(this.text);
					
				} else { throw new Exception("Nao eh possivel criar o post. O limite maximo da mensagem sao 200 caracteres."); }
					
				for (String j: this.files.split("<imagem>|</imagem>|<audio>|</audio>| ")) {
					
					if (j.contains("imagens/")) {
						
						imageFiles.add(j);
						stringChest.add(j);
						
					} else if (j.contains("musicas/")) {
						
						audioFiles.add(j);
						stringChest.add(j);
						
					}
				
				}
					
				for (String k: this.hashtags.split(" ")) {
					
					if (k.contains("#")) {
						
						hashtagList.add(k.trim());
						
					} else if (k.contains("#") == false) {
						
						throw new Exception("Nao eh possivel criar o post. As hashtags devem comecar com '#'. Erro na hashtag: '" + k + "'.");
						
					}
					
				}
			
			} 
		
		}

		this.message = this.text + this.files + " " + this.hashtags + " (" + this.date.format(dateTimeFormatter) + ")";
		
	}
	
	public String getChest(int index){
		
		if (this.stringChest.get(index).toString().contains("imagens/")){
		
			return ("$arquivo_imagem:" + this.stringChest.get(index).toString());
		
		} else if (this.stringChest.get(index).toString().contains("musicas/")){
			
			return ("$arquivo_audio:" + this.stringChest.get(index).toString());
			
		} else { return this.stringChest.get(index).toString(); }
		
	}
	
	public String getMessage(){
		
		return this.message;
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
	
	public String getNamePost(){
		
		return user.getName();
	}
	
	public String visualizePost(){
	
		return user.getName() + ": " + this.text;
	}	
}
