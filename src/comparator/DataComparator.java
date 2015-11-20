package comparator;

import java.time.LocalDateTime;
import java.util.Comparator;

public class DataComparator implements Comparator<LocalDateTime>{

	@Override
	public int compare(LocalDateTime data1, LocalDateTime data2) {

		if(data1.compareTo(LocalDateTime.now()) == 1){
			
		}
		
		return 0;
	}

}
