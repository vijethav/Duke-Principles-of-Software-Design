package week2;

import java.util.ArrayList;
import java.util.Collections;

public class DifferentSorters {

	public void sortByTitleAndDepth() {
		EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/earthQuakeDataWeekDec6sample2.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        
		Location where = new Location(35.9886, -78.9072);
		Collections.sort(list, new TitleAndDepthComparator());
		for(QuakeEntry qe : list){
        	//System.out.println(qe);
        }	
		int quakeNumber = 500;
		   System.out.println("Print quake entry in position sortByTitleAndDepth:  " + quakeNumber);
		   System.out.println(list.get(quakeNumber));
	}
	
	public void sortQuakeCompareTo() {
		 EarthQuakeParser parser = new EarthQuakeParser();
	        //String source = "data/nov20quakedata.atom";
	        String source = "data/earthQuakeDataWeekDec6sample1.atom";
	        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
	        ArrayList<QuakeEntry> list  = parser.read(source);
           Collections.sort(list);
           
           for(QuakeEntry qe : list){
           	//System.out.println(qe);
           }
           
           int quakeNumber = 600;
   		   System.out.println("Print quake entry in position sortQuakeCompareTo: " + quakeNumber);
   		   System.out.println(list.get(quakeNumber));
	}

	public void sortByMagnitude() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/earthQuakeDataDec6sample2.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        Collections.sort(list, new MagnitudeComparator());
        for(QuakeEntry qe: list) {
            System.out.println(qe);
        }

    }
	
	public void sortByDistance(){
		EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        
		Location where = new Location(35.9886, -78.9072);
		Collections.sort(list, new DistanceComparator(where));
		for(QuakeEntry qe : list){
        	System.out.println(qe);
        }	
	}
	
	public void sortByLastWordInTitleThenByMagnitude() {
		EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/earthQuakeDataWeekDec6sample1.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        
        Collections.sort(list, new TitleLastAndMagnitudeComparator());
        
        for(QuakeEntry qe : list){
           	//System.out.println(qe);
           } 
           int quakeNumber = 500;
   		   System.out.println("Print quake entry in position sortByLastWordInTitleThenByMagnitude: " + quakeNumber);
   		   System.out.println(list.get(quakeNumber));
        
	}
	
	public static void main(String[] args){
		DifferentSorters ds = new DifferentSorters();
		//ds.sortQuakeCompareTo();
		//ds.sortByTitleAndDepth();
		
		ds.sortByLastWordInTitleThenByMagnitude();
		
	}
}
