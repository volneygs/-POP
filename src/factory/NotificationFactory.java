package factory;

import users.Notification;

	public class NotificationFactory {
	
	public NotificationFactory(){
		
	}
	
	public Notification createNotification(String email){
		
		Notification notificacao = new Notification(email);
		
		return notificacao;
		
	}

}
