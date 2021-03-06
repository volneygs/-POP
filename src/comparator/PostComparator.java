package comparator;

import java.io.Serializable;
import java.util.Comparator;

import post.Post;

public class PostComparator implements Serializable, Comparator<Post>{

	@Override
	public int compare(Post post1, Post post2) {
		
		if(post1.getPop() > post2.getPop()){
			return 1;
		}else if(post1.getPop() < post2.getPop()){
			return -1;
		}else{
			return 0;
		}
	}
}
