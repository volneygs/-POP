package users;
import java.io.Serializable;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

public class CelebridadePOP implements Serializable, UsuarioPadrao{
	
	private int pop;
	private String data;
	private DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT);
	
	public CelebridadePOP(){
		
		this.pop = 25;
		this.data = LocalDate.now().format(dateFormat);

	}
	
	public void curtirPost(User usuario, String nome, int index){
		
		usuario.getMural().get(index).addCurtida();
	
		if (this.data.equals(usuario.getMural().get(index).getDateTime())) {
			usuario.getMural().get(index).addPop(pop+10);
			usuario.adicionaPop(pop+10);
		
		} else {
			
			usuario.getMural().get(index).addPop(pop);
			usuario.adicionaPop(pop);
			
		}
		
		String dataHora = usuario.getMural().get(index).getDateTime();
		
		String notificacao = nome +" curtiu seu post de " + dataHora + ".";
		
		usuario.adicionaNotificacao(notificacao);
	}

	public void rejeitarPost(User usuario, String nome, int index){
		
		usuario.getMural().get(index).addRejeicao();
		
		if (this.data.equals(usuario.getMural().get(index).getDateTime())) {
			usuario.getMural().get(index).addPop(-pop-10);
			usuario.adicionaPop(-pop-10);
		
		} else {
		
			usuario.adicionaPop(-pop);
			usuario.getMural().get(index).addPop(-pop);
		
		}
		
		String dataHora = usuario.getMural().get(index).getDateTime();
		
		String notificacao = nome +" rejeitou seu post de " + dataHora + ".";
		
		usuario.adicionaNotificacao(notificacao);
	}
	
}
