package users;

import java.util.ArrayList;

public class User {
	
	private String email;
	private String name;
	private String password;
	private String birthdate;
	private String image;
	private int phone;
	private int pop;
	private ArrayList<Mural> mural;
	
	
	public User(String email, String name, String password, String birthdate, String image, int phone) throws Exception{
		
		
		if(email.equals("")){
			throw new Exception("Invalid email.");
			
		}else if(name.equals("")){				
			throw new Exception("Invalid name.");
			
		}else if(password.equals("")){
			throw new Exception("Invalid password.");
			
		}else if(birthdate.equals("")){
			throw new Exception("Invalid birthdate.");
			
		}else if(phone <= 99999999 && phone > 1000000000){
			throw new Exception("Invalid phone.");
		}

		
		this.email = email;
		this.name = name;
		this.password = password;
		this.birthdate = birthdate;
		this.image = image;
		this.phone = phone;
		this.mural = new ArrayList<Mural>();
	}

}
