package users;

public class CelebridadePOP implements UsuarioPadrao{
	
	private int pop;
	
	public CelebridadePOP(){
		
		this.pop = 25;
	}
	
	public void curtirPost(User usuario, String nome, int index){
		
		usuario.getMural().get(index).addPop(pop);
		
		usuario.adicionaPop(pop);
		
		String dataHora = usuario.getMural().get(index).getDateTime();
		
		String notificacao = nome +" curtiu seu post de " + dataHora + ".";
		
		usuario.adicionaNotificacao(notificacao);
	}

	public void rejeitarPost(User usuario, String nome, int index){
		
		usuario.getMural().get(index).addPop(-pop);
		
		usuario.adicionaPop(-pop);
		
		String dataHora = usuario.getMural().get(index).getDateTime();
		
		String notificacao = nome +" rejeitou seu post de " + dataHora + ".";
		
		usuario.adicionaNotificacao(notificacao);
	}
	
}
