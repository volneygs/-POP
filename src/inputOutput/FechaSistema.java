package inputOutput;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import controller.Controller;
import users.User;

public class FechaSistema implements Serializable{
	
	public FechaSistema(){
		
	}
	
	public void escreveArquivo(User logged, Controller controller) throws Exception{
		
		if(logged == null){
			
			File arquivoUsuario = new File("todos_os_usuarios.dat");
			
			if(arquivoUsuario.exists()){
				arquivoUsuario.delete();
			}
			
			try{
				
				ObjectOutputStream write = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("todos_os_usuarios.dat")));
				
				write.writeObject(controller);
				
				write.close();
				
			}catch(Exception e){
				
				throw new Exception("aconteceu algum erro no arquivo.");
			}
		}else{
			
			throw new Exception("Nao foi possivel fechar o sistema. Um usuarix ainda esta logadx.");
		}
		
	}

}
