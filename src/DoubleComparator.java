

import java.util.Comparator;

final class DoubleComparator implements Comparator<Double> {

	@Override
	public int compare(Double o1, Double o2) {
		if(o1.doubleValue() < o2.doubleValue()){
			return -1;
		}
		else if(o1.doubleValue() == o2.doubleValue()){
			return 0;
		}
		else{
			return 1;
		}
	}
	
}
