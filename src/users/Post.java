package users;
import java.time.*;
import java.time.format.*;

public class Post {
	
	private String message;
	private int popularity;
	private User user;
	private LocalDate dateTime;
	private DateTimeFormatter dateFormatPost = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	
	public Post(String message, String date){
		
		this.message = message;
		this.popularity = 0;
		this.dateTime = LocalDate.parse(date, dateFormatPost);
		
	}
	
	public String getMessage(){
		
		return message;
	}
	
	public int getPopularityr(){
		
		return popularity;
	}
	
	public String getNamePost(){
		
		return user.getName();
	}
	
	public String visualizePost(){
		
		return user.getName() + ": " + this.message;
	}
	
	

}
