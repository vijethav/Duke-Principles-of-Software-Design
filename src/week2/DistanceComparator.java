package week2;

import java.util.Comparator;

public class DistanceComparator implements Comparator<QuakeEntry> {

	Location fromWhere;
	
	public DistanceComparator(Location where){
		fromWhere = where;
	}
	
	@Override
	public int compare(QuakeEntry qe1, QuakeEntry qe2) {
		double dist1 = qe1.getLocation().distanceTo(fromWhere);
		double dist2 = qe2.getLocation().distanceTo(fromWhere);
		
		return Double.compare(dist1, dist2);
	}
	
	
}
