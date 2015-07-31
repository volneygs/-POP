package users;

public class Post {
	
	private String message;
	private int likeCounter;
	
	public Post(String message){
		
		this.message = message;
		this.likeCounter = 0;
	}
	
	public String getMessage(){
		
		return message;
	}
	
	public int getLikeCounter(){
		
		return likeCounter;
	}
	
	

}
