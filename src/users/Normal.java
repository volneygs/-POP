package users;

import java.io.Serializable;

public class Normal implements Serializable, UsuarioPadrao{
	
	private int pop;
	
	public Normal(){
		
		this.pop = 10;
	}
	
	public void curtirPost(User usuario, String nome, int index){
		
		usuario.getMural().get(index).addPop(pop);
		usuario.getMural().get(index).addCurtida();
		
		usuario.adicionaPop(pop);
		
		String dataHora = usuario.getMural().get(index).getDateTime();
		
		String notificacao = nome +" curtiu seu post de " + dataHora + ".";
		
		usuario.adicionaNotificacao(notificacao);
	}

	public void rejeitarPost(User usuario, String nome, int index){
		
		usuario.getMural().get(index).addPop(-pop);
		usuario.getMural().get(index).addRejeicao();
		
		usuario.adicionaPop(-pop);
		
		String dataHora = usuario.getMural().get(index).getDateTime();
		
		String notificacao = nome +" rejeitou seu post de " + dataHora + ".";
		
		usuario.adicionaNotificacao(notificacao);
	}
	
}