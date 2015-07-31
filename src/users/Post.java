package users;

public class Post {
	
	private String message;
	private int likeCounter;
	private User user;
	
	public Post(User user, String message){
		
		this.message = message;
		this.likeCounter = 0;
		this.user = user;
	}
	
	public String getMessage(){
		
		return message;
	}
	
	public int getLikeCounter(){
		
		return likeCounter;
	}
	
	public String getNamePost(){
		
		return user.getName();
	}
	
	public String visualizePost(){
		
		return user.getName() + ": " + this.message;
	}
	
	

}
