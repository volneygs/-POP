package users;

import java.io.Serializable;

public class IconePOP implements Serializable, UsuarioPadrao{
	
	private int pop;
	
	public IconePOP(){
		
		this.pop = 50;
	}
	
	public void curtirPost(User usuario, String nome, int index){
		
		usuario.getMural().get(index).addPop(pop);
		usuario.getMural().get(index).addCurtida();
		usuario.getMural().get(index).addEpicWin();
		
		usuario.adicionaPop(pop);
		
		String dataHora = usuario.getMural().get(index).getDateTime();
		
		String notificacao = nome +" curtiu seu post de " + dataHora + ".";
		
		usuario.adicionaNotificacao(notificacao);
	}
	
	public void rejeitarPost(User usuario, String nome, int index){
		
		usuario.getMural().get(index).addPop(-pop);
		usuario.getMural().get(index).addRejeicao();
		usuario.getMural().get(index).addEpicFail();
		
		usuario.adicionaPop(-pop);
		
		String dataHora = usuario.getMural().get(index).getDateTime();
		
		String notificacao = nome +" rejeitou seu post de " + dataHora + ".";
		
		usuario.adicionaNotificacao(notificacao);
	}

}
