package post;

import java.io.Serializable;

public class Hashtag implements Serializable, Comparable<Hashtag>{
	
	private String hashtag;
	private int ocorrencias;
	
	public Hashtag(String hashtag){
		
		this.hashtag = hashtag;
		this.ocorrencias = 1;
		
	}
	
	public String getHashtag(){
		return hashtag;
	}
	
	public int getOcorrencias(){
		return ocorrencias;
	}
	
	public void adicionaOcorrencia(){
		ocorrencias = ocorrencias + 1;
	}

	@Override
	public int compareTo(Hashtag outraHash) {
		
		if(ocorrencias > outraHash.getOcorrencias()){
			return -1;
		}else if(ocorrencias < outraHash.getOcorrencias()){
			return 1;
		}else{
			return outraHash.getHashtag().compareToIgnoreCase(hashtag);
		}
	}
	
	public boolean equals(Object obj){
		
		if(obj instanceof Hashtag){
			Hashtag novoObj = (Hashtag)obj;
			if(hashtag.equals(novoObj.getHashtag())){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
}
