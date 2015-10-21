package users;

import java.time.format.DateTimeFormatter;

public class Notification {
	
	private String nome;
	
	public Notification(String nome){
	
		this.nome = nome;
	}
	
	@Override
	public String toString(){
		
		return nome + " quer sua amizade.";
	}

}
