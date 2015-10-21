package users;
import java.time.*;
import java.time.format.*;
import java.util.*;

public class Post {
	
	private ArrayList<String> postList;
	private ArrayList<String> textFiles;
	private ArrayList<String> imageFiles;
	private ArrayList<String> audioFiles;
	private ArrayList<String> hashtagList;
	private int popularity;
	private User user;
	private LocalDate dateTime;
	private DateTimeFormatter dateFormatPost = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	private String text;
	private String files;
	private String hashtags;
	private Mural timeline;

	public Post(String message, String date) throws Exception{
		
		this.popularity = 0;
		this.dateTime = LocalDate.parse(date, dateFormatPost);
		this.textFiles = new ArrayList<String>();
		this.imageFiles = new ArrayList<String>();
		this.audioFiles = new ArrayList<String>();
		this.hashtagList = new ArrayList<String>();
		this.postList = new ArrayList<String>();
		
		for (int i = 0; i < message.indexOf("<") || i < message.indexOf("#"); i ++) {
			
			if (i == (message.indexOf("<")-1) || i == (message.indexOf("#")-1)) {
				
				this.text = message.substring(0, i);
				this.files = message.substring(i+1, message.indexOf("#"));
				this.hashtags = message.substring(message.indexOf("#")-1, message.length());
					
				if (this.text.length() < 199) {
					
					textFiles.add(this.text);
				
				} else { throw new Exception("Nao eh possivel criar o post. O limite maximo da mensagem sao 200 caracteres."); }
					
				for (String j: this.files.split("<imagem>|</imagem>|<audio>|</audio>| ")) {
					
					if (j.contains("imagens/")) {
						
						imageFiles.add(j);
						
					} else if (j.contains("musicas/")) {
						
						audioFiles.add(j);
					
					}
				
				}
					
				for (String k: this.hashtags.trim().split(" ")) {
					
					if (k.contains("#")) {
						
						hashtagList.add(k);
						
					} else if (k.contains("#") == false) {
						
						throw new Exception("Nao eh possivel criar o post. As hashtags devem comecar com '#'. Erro na hashtag: '" + k + "'.");
						
					}
					
				}
			
			}
			
		}
		
	}
	
	public String getMessage(){
		
		return this.text;
	}
	
	public int getPopularityr(){
		
		return popularity;
	}
	
	public String getNamePost(){
		
		return user.getName();
	}
	
	public String visualizePost(){
		
		return user.getName() + ": " + this.text;
	}
	
	

}
