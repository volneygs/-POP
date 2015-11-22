package inputOutput;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;

import controller.Controller;

public class IniciaSistema implements Serializable {
	
	public IniciaSistema(){
	
		
	}
	
	public Controller leArquivo() throws Exception{
		
		File arquivoUsuario = new File("todos_os_usuarios.dat");
		
		if(arquivoUsuario.exists()){
			
			try{
				
				ObjectInputStream read = new ObjectInputStream(new BufferedInputStream(new FileInputStream("todos_os_usuarios.dat")));
				
				Controller controller = (Controller)read.readObject();
				
				read.close();
				
				return controller;
				
			}catch(Exception e){
				
				throw new Exception("arquivo nao foi lido.");
			}
			
		}else{
			
			Controller controller = new Controller();
			
			return controller;	
		}
		
		
	}
}
