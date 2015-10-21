package users;

public class Notification {
	
	private String email;
	private String message;
	
	public Notification(String email){
		
		this.email = email;
		this.message = email + " quer sua amizade.";
		
	}
	
	public String getNextNotificacao(){
		return message;
	}

	public String getEmail(){
		return email;
	}
}
