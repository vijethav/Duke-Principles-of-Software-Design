package week2;

import java.util.ArrayList;

public class QuakeSortWithTwoArrayLists {
	
	public QuakeSortWithTwoArrayLists(){
		
	}
	public QuakeEntry getSmallestMagnitude(ArrayList<QuakeEntry> quakes){
		QuakeEntry min = quakes.get(0);
		for(QuakeEntry qe : quakes){
			if(qe.getMagnitude() < min.getMagnitude()){
				min = qe;
			}
		}
		return min;
	}
	
	public ArrayList<QuakeEntry> sortByMagnitude(ArrayList<QuakeEntry> quakeEntryIn){
		ArrayList<QuakeEntry> quakeEntryOut = new ArrayList<QuakeEntry>();
		
		while(!quakeEntryIn.isEmpty()){
			QuakeEntry minElement = getSmallestMagnitude(quakeEntryIn);
			quakeEntryIn.remove(minElement);
			quakeEntryOut.add(minElement);
		}
		return quakeEntryOut;
	}
	
	public void testSort(){
		EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        list = sortByMagnitude(list);
        for(QuakeEntry qe: list) {
            System.out.println(qe);
        }
        System.out.println("# quakes Sorted = "+list.size());
	}
	
	public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();     
       // String source = "data/nov20quakedatasmall.atom";
        String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }
    
    public void dumpCSV(ArrayList<QuakeEntry> list) {
		System.out.println("Latitude,Longitude,Magnitude,Info");
		for(QuakeEntry qe : list){
			System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
			                  qe.getLocation().getLatitude(),
			                  qe.getLocation().getLongitude(),
			                  qe.getMagnitude(),
			                  qe.getInfo());
	    }
		
	}
    
    public static void main(String[] args){
    	QuakeSortWithTwoArrayLists qst = new QuakeSortWithTwoArrayLists();
    	qst.testSort();
    }
}
