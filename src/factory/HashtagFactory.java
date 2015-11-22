package factory;

import java.io.Serializable;

import post.Hashtag;

public class HashtagFactory implements Serializable {
	
	public HashtagFactory(){
		
	}
	
	public Hashtag criaHashtag(String hashtag){
		
		Hashtag objeto = new Hashtag(hashtag);
		
		return objeto;
		
	}

}
