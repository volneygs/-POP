package users;

public class Mural {
	
	private String message;
	private int likeCounter;
	
	public Mural(String message){
		
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
