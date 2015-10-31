package users;

public class Normal implements UsuarioPadrao{
	
	private int pop;
	
	public Normal(){
		
		this.pop = 10;
	}
	
	public void curtirPost(User usuario, String nome, int index){
		
		usuario.getMural().get(index).addPop(pop);
		
		usuario.adicionaPop(pop);
		
		String dataHora = usuario.getMural().get(index).getDateTime();
		
		String notificacao = nome +" curtiu seu post de " + dataHora + ".";
		
		usuario.adicionaNotificacao(notificacao);
	}

}