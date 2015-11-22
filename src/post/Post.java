package post;

import java.io.Serializable;
import java.nio.file.FileSystemLoopException;
import java.time.*;
import java.time.format.*;
import java.util.*;

import javax.swing.text.StyledEditorKit.ForegroundAction;

public class Post implements Serializable, Comparable<Post> {

	private List<String> hashTagList;
	private List<String> filesList;
	private List<String> mensagem;
	private String texto;
	private LocalDateTime date;
	private int pop;
	private int qtdCurtidas;
	private int qtdRejeicoes;

	public Post(String message, String date) throws Exception {

		DateTimeFormatter dateValidator = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

		this.pop = 0;
		this.qtdCurtidas = 0;
		this.qtdRejeicoes = 0;
		this.date = LocalDateTime.parse(date, dateValidator);
		this.filesList = new ArrayList<String>();
		this.hashTagList = new ArrayList<String>();
		this.mensagem = new ArrayList<String>();
		this.texto = "";

		stringsManipulator(message);

	}

	public void stringsManipulator(String message) throws Exception {

		String[] manipulator = message.split(" ");

		for (String string : manipulator) {

			if (!(hashTagList.isEmpty())) {
				hashTagList.add(string);

			} else if (string.startsWith("<imagem>") || string.startsWith("<audio>")) {
				filesList.add(string);

			} else if (string.startsWith("#") && hashTagList.isEmpty()) {
				hashTagList.add(string);

			} else {
				mensagem.add(string);
			}
		}

		int i = 1;

		for (String string : mensagem) {

			if (i != mensagem.size()) {
				texto = texto + string + " ";
			} else {
				texto = texto + string;
			}

			i++;
		}

		if (texto.length() >= 200) {
			throw new Exception("Nao eh possivel criar o post. O limite maximo da mensagem sao 200 caracteres.");
		}

		for (String string : hashTagList) {
			if (!(string.startsWith("#"))) {
				throw new Exception(
						"Nao eh possivel criar o post. As hashtags devem comecar com '#'. Erro na hashtag: '" + string
								+ "'.");
			}
		}
	}
	
	public List<String> getHashtagList(){
		return hashTagList;
	}
	
	public List<String> getFilesList(){
		return filesList;
	}

	public String getMensagem() {

		String mensagem = texto;

		if (filesList.isEmpty()) {
			return mensagem;
		} else {
			mensagem = mensagem + " ";

			int i = 1;
			for (String string : filesList) {
				if (i != filesList.size()) {
					mensagem = mensagem + string + " ";
				} else {
					mensagem = mensagem + string;
				}

				i++;
			}
		}

		return mensagem;
	}
	
	public String getConteudoPost(int indice) {

		String mensagem;

		if (indice == 0) {
			return texto;
		} else {
			mensagem = filesList.get(indice - 1).replace("<imagem>", "$arquivo_imagem:").replace("</imagem>", "")
					.replace("<audio>", "$arquivo_audio:").replace("</audio>", "");
		}

		return mensagem;
	}
	
	public String getDateTime() {

		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss");

		return this.date.format(dateTimeFormatter);

	}

	public LocalDate getLocalDate() {
		
		return this.date.toLocalDate();
	}

	public LocalDateTime getData() {

		return this.date;
	}
	
	public String getOnlyText(){
		return texto;
	}

	public String getOnlyFiles() {
		
		String mensagem = "";
		
		int i = 1;
		for (String string : filesList) {
			if (i != filesList.size()) {
				mensagem = mensagem + string + " ";
			} else {
				mensagem = mensagem + string;
			}

			i++;
		}
		
		return mensagem;
	}
	
	public String getOnlyHashtag(){
		
		String mensagem = "";
		
		int i = 1;
		for (String string : hashTagList) {
			if (i != hashTagList.size()) {
				mensagem = mensagem + string + " ";
			} else {
				mensagem = mensagem + string;
			}

			i++;
		}
		
		return mensagem;
	}

	public int getPop() {

		return pop;
	}

	public int getQtdCurtidas() {
		return this.qtdCurtidas;
	}

	public int getQtdRejeicoes() {
		return this.qtdRejeicoes;
	}

	/**
	 * adiciona a hashtag "#epicwin caso não haja nenhuma ao post
	 */

	public void addEpicWin() {

		if (this.hashTagList.contains("#epicwin") == false) {
			this.hashTagList.add("#epicwin");
		}
	}

	/**
	 * adiciona a hashtag "#epicfail caso não haja nenhuma ao post
	 */

	public void addEpicFail() {
		if (this.hashTagList.contains("#epicfail") == false) {
			this.hashTagList.add("#epicfail");
		}
	}

	/**
	 * cria a mensagem do post
	 * 
	 * @param text
	 *            string que irá formar o post
	 * @throws Exception
	 *             lança exceção caso o post tenha muitos caracteres
	 */


	public void addPop(int valor) {

		this.pop = this.pop + valor;
	}

	public void addCurtida() {
		this.qtdCurtidas += 1;
	}

	public void addRejeicao() {
		this.qtdRejeicoes += 1;
	}

	@Override
	public int compareTo(Post post) {

		if (date.isBefore(post.getData())) {
			return 1;
		} else if (date.isAfter(post.getData())) {
			return -1;
		} else {
			return 0;
		}
	}

	public String toString() {

		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss");
		String mensagem = this.texto;
		int i;

		if (!(filesList.isEmpty())) {
			mensagem = mensagem + " ";
		}

		i = 1;
		for (String string : filesList) {
			if (i != filesList.size()) {
				mensagem = mensagem + string + " ";
			} else {
				mensagem = mensagem + string;
			}

			i++;
		}
		
		if (!(hashTagList.isEmpty())) {
			mensagem = mensagem + " ";
		}

		i = 1;
		for (String string : hashTagList) {
			if (i != hashTagList.size()) {
				mensagem = mensagem + string + " ";
			} else {
				mensagem = mensagem + string;
			}

			i++;
		}

		mensagem = mensagem + " " + "(" + date.format(dateTimeFormatter) + ")";

		return mensagem;
	}
}
