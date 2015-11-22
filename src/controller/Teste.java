package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.text.StyledEditorKit.ForegroundAction;

public class Teste {
	
	public static void main(String[] args) throws Exception {
		
	/*	List<String> hashTags = new ArrayList<String>();
		
		String post = "adeus ferias!!! Ate nunca mais :( #cordarCedo #UFCG #minhaVida";
		
		
		
		String[] novaString = post.split(" ");
		
		for (String string : novaString) {
			
			if(string.startsWith("#")){
				hashTags.add(string);
			}
			
		}
		
		for (String string : hashTags) {
			System.out.println(string);
		} */
		
		/*String data = "03/08/2015 16:00:00";
		
		DateTimeFormatter dateValidator = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss");
		
		LocalDateTime dataf = LocalDateTime.parse(data, dateValidator);
		
		System.out.println(dataf.format(dateValidator)); */
		
	/*	List<User> lista = new ArrayList<User>();

		
		User usuario1 = new User("volney", "volney@email.com", "123456", "01/12/1993", "foto");
		User usuario2 = new User("lucas", "volney@email.com", "123456", "01/12/1993", "foto");
		User usuario3 = new User("william", "volney@email.com", "123456", "01/12/1993", "foto");
		User usuario4 = new User("chang", "volney@email.com", "123456", "01/12/1993", "foto");
		//User usuario5 = new User("amanda", "volney@email.com", "123456", "01/12/1993", "foto");
	//	User usuario6 = new User("luis", "volney@email.com", "123456", "01/12/1993", "foto");
		
		lista.add(usuario1);
		lista.add(usuario2);
		lista.add(usuario3);
		lista.add(usuario4);
	//	lista.add(usuario5);
	//	lista.add(usuario6);
		
		
		
		File arquivoUsuarios = new File("todos_os_usuarios.dat");
		
		try{
			
			ObjectOutputStream write = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("todos_os_usuarios.dat")));
			
			write.writeObject(lista);
			
			write.close();
			
			System.out.println("escrever funcionou");
			
		}catch(Exception e){
			
			System.out.println("escrever nao funcionou");
		}
		
		List<User> novaLista = new ArrayList<User>();
		
		try{
			
			ObjectInputStream read = new ObjectInputStream(new BufferedInputStream(new FileInputStream("todos_os_usuarios.dat")));
			
			novaLista = (ArrayList<User>)read.readObject();

			read.close();
			
		}catch(Exception e){
			
			System.out.println("lançou exceção");
			
		}
		
		for (User user : novaLista) {
			
			System.out.println(user.getName());
		}
	 */
		
		/*Controller controller;
		
		IniciaSistema iniciaSistema = new IniciaSistema();
		FechaSistema fechaSistema = new FechaSistema();
		
		controller = iniciaSistema.leArquivo();
		
		controller.registerUser("volney", "vlney@email.com", "123456", "01/12/1993");
		
		controller.login("vlney@email.com", "123456");
		
		controller.criaPost("asdfaesf", "01/12/2014 12:12:12");
		controller.atualizaPerfil("Data de nascimento", "01/12/1994");
		
		controller.logout();
		
		fechaSistema.escreveArquivo(controller.getLogged(), controller); */		
		
		
		Controller controller = new Controller();
		
		controller.registerUser("volney", "vlney@email.com", "123456", "01/12/1993");
		controller.login("vlney@email.com", "123456");
		
		controller.criaPost("adfjkldasfj #aaa #bbb", "01/12/2014 12:12:12");
		controller.criaPost("adfjkldasfj #bbb #aaa", "01/12/2014 12:12:12");
		controller.criaPost("adfjkldasfj #ccc #aaa", "01/12/2014 12:12:12");
		
		System.out.println(controller.atualizaTrendingTopics());
		
	}

}
