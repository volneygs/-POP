package facade;

public class Teste {
	
	public static void main(String[] args) throws Exception {
		
		Facade facade = new Facade();
		
		facade.cadastraUsuario("volney", "volney@email.com", "a1s2d5", "17/09/1962");
		facade.cadastraUsuario("will", "will@email.com", "a1s2d5", "17/09/1962");
		
		facade.login("volney@email.com", "a1s2d5");
		facade.adicionaAmigo("will@email.com");
		
		facade.logout();
		
		facade.login("will@email.com", "a1s2d5");
		facade.aceitaAmizade("volney@email.com");
		
		System.out.println(facade.getQtdAmigos());
		
		
	}

}
