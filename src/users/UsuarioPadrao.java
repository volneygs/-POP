package users;

public interface UsuarioPadrao {
	
	public void curtirPost(User usuarioRecebe, String nome, int index);	
	public void rejeitarPost(User usuarioRecebe, String nome, int index);
	
}
