package week2;

import java.util.ArrayList;

public class QuakeSortInPlace {
	
	public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
	
	public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from){
		int minIdx = from;
		for (int i = from+1; i < quakes.size(); i++){
			if(quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()){
				minIdx = i;
			}
		}
		return minIdx;
	}
	
	public void sortByMagnitude(ArrayList<QuakeEntry> inQuakes){
		for(int i = 0; i< inQuakes.size(); i++){
			int minIdx = getSmallestMagnitude(inQuakes, i);
			QuakeEntry currentQe = inQuakes.get(i);
			QuakeEntry minQe = inQuakes.get(minIdx);
			inQuakes.set(i, minQe);
			inQuakes.set(minIdx, currentQe);
		}
	}
	
	public int getLargestDepth(ArrayList<QuakeEntry> quakes, int from){
		int maxIdx = from;
		for(int i= from+1; i< quakes.size(); i++){
			if(quakes.get(i).getDepth() > quakes.get(maxIdx).getDepth()){
				maxIdx = i;
			}
		}
		return maxIdx;
	}
	
	public void sortByLargestDepth(ArrayList<QuakeEntry> in){
		for(int i = 0; i < in.size(); i++){
			if(checkInSortedOrderDepth(in)) {
    			System.out.printf("Number of passes sortByLargestDepth: %d\n", i);
    			break;   			
    		}
			int maxIdx = getLargestDepth(in, i);
			QuakeEntry currentQe = in.get(i);
			QuakeEntry maxQe = in.get(maxIdx);
			in.set(i, maxQe);
			in.set(maxIdx, currentQe);
    		System.out.printf("Number of passes sortByLargestDepth  2: %d\n", i);
            
			if(i == 50){
				break;
			}
			
		}
		int j = 0;
		for(QuakeEntry qe: in) {
			
            System.out.println(j + " " + qe);
            j++;
        }
		System.out.println("# quakes Sorted = sortByLargestDepth "+in.size());
	}
	
	public void testSortMagnitude(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/earthQuakeDataWeekDec6sample1.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        sortByMagnitude(list);
        for(QuakeEntry qe: list) {
            System.out.println(qe);
        }
        System.out.println("# quakes Sorted = "+list.size());

    }
	
	public void testSortDepth(){
		EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/earthQuakeDataDec6sample2.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        sortByLargestDepth(list);
        for(QuakeEntry qe: list) {
            System.out.println(qe);
        }
        System.out.println("# quakes Sorted = sortByLargestDepth "+list.size());
	}
	
	public void onePassBubbleSort(ArrayList<QuakeEntry> quakeData, int numSorted){
		for (int i=0; i < quakeData.size()-1-numSorted;i++) {
    		QuakeEntry q1 = quakeData.get(i);
    		QuakeEntry q2 = quakeData.get(i+1);
    		
    		if (q1.getMagnitude() > q2.getMagnitude()) {
    			quakeData.set(i, q2);
    			quakeData.set(i+1,q1);
    		}
    	}
	}
	
	public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in){
		for (int i=0; i<in.size();i++) {
			/*System.out.println("Printing Quakes after pass " + i );
    		for(QuakeEntry qe: in) {
                System.out.println(qe);
            }*/
    		onePassBubbleSort(in, i);
    		
		}
	}
	
	public void testSortBubbleSort(){
		EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/earthquakeDataSampleSix2.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");
        sortByMagnitudeWithBubbleSort(list);
        for(QuakeEntry qe: list) {
            System.out.println(qe);
        }
        System.out.println("# quakes Sorted = "+list.size());
	}
	
	public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakes){
		
		for (int i=0;i<quakes.size()-1;i++) {
    		if (quakes.get(i).getMagnitude() > 
    				quakes.get(i+1).getMagnitude()
    				) return false;
    	}
    	
    	return true;
	}
	
public boolean checkInSortedOrderDepth(ArrayList<QuakeEntry> quakes){
		
		for (int i=0;i<quakes.size()-1;i++) {
    		if (quakes.get(i+1).getDepth() > 
    		quakes.get(i).getDepth()
    				) return false;
    	}
    	
    	return true;
	}
	
	public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> in){
		for (int i=0; i<in.size();i++) {
    		if (checkInSortedOrder(in)) {
    			System.out.printf("Number of passes: %d\n", i);
    			break;
    		}
    		onePassBubbleSort(in, i);
    	}
	}
	
	public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in){
		int count = 0;
		for (int i=0; i<in.size();i++) {
    		if (checkInSortedOrder(in)) {
    			System.out.printf("Number of passes: %d\n", i);
    			count++;
    			break;
    		}
    		 int minIdx = getSmallestMagnitude(in,i);
             QuakeEntry qi = in.get(i);
             QuakeEntry qmin = in.get(minIdx);
             in.set(i,qmin);
             in.set(minIdx,qi);
    	}
		System.out.println("count --- " + count);
	}
	public void testSortBubbleSortCheck(){
		EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/earthQuakeDataWeekDec6sample1.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");
        sortByMagnitudeWithBubbleSortWithCheck(list);
        for(QuakeEntry qe: list) {
            System.out.println(qe);
        }
        System.out.println("# quakes Sorted = "+list.size());
	}
	
	public void testSortSortCheck(){
		EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/earthQuakeDataWeekDec6sample2.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");
        sortByMagnitudeWithCheck(list);
        for(QuakeEntry qe: list) {
            System.out.println(qe);
        }
        System.out.println("# quakes Sorted = "+list.size());
	}
	
	public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
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
    	QuakeSortInPlace qp = new QuakeSortInPlace();
    	//qp.testSortMagnitude();
    	//qp.testSortDepth();
    	//qp.testSortBubbleSort();
    	qp.testSortBubbleSortCheck();
    	//qp.testSortSortCheck();
    	
    }
  //732814    1 2 3 8 7 4   -31430.00      67  231
  
}
