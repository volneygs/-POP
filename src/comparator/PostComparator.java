package comparator;

import java.util.Comparator;
import users.Post;

public class PostComparator implements Comparator<Post>{

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
