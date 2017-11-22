package week2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class SortTiming {

	Random random = new Random();
	
	public void runSelect(){
		String[] cats = {"cats", "tiger", "lion", "cheetah", "puma", "leopard"};
		selectSort(cats);
		for(String s: cats){
			System.out.println(s);
		}
	}

	private void selectSort(String[] list) {
		for(int i=0; i < list.length; i++){
			int minIdx = i;
			
			for(int j = i+1; j < list.length; j++){
				if(list[j].compareTo(list[minIdx]) < 0){
				     minIdx = j;
				}
			}
			String temp = list[i];
			list[i] = list[minIdx];
			list[minIdx] = temp;
		}		
	}
	
	private void selectSort(ArrayList<String> list) {
		for(int i=0; i < list.size(); i++){
			int minIdx = i;
			
			for(int j = i+1; j < list.size(); j++){
				if(list.get(j).compareTo(list.get(minIdx)) < 0){
				     minIdx = j;
				}
			}
			Collections.swap(list, i, minIdx);
		}		
	}
	
	public boolean isSorted(ArrayList<String> list) {
		for(int k=1; k < list.size(); k++){
			if (list.get(k).compareTo(list.get(k-1)) < 0) {
				return false;
			}
		}
		return true;
	}
	
	public void timer(int start, int stop, int increment, int trials){
		for(int i = start; i <= stop; i += increment) {
			ArrayList<String> list = makeRandomList(10, i);
			
			double begin = System.nanoTime();
			
			for(int j =0; j < trials; j++){
				ArrayList<String> copy = new ArrayList<String>(list);
				selectSort(copy);
				if(! isSorted(copy)){
				     System.out.println("trouble on sorted select " + i);
				}
			}
			double end = System.nanoTime();
			double stime = (end-begin)/1e9/trials;
			begin =System.nanoTime();
			
			for(int j =0; j < trials; j++){
				ArrayList<String> copy = new ArrayList<String>(list);
                Collections.sort(copy);
                if(! isSorted(copy)){
                	System.out.println("trouble on sorted time " + i);
                }
			}
			end = System.nanoTime();
			double ttime = (end-begin)/1e9/trials;
			System.out.printf("%d\t%3.2f\t%3.2f\n", i, stime, ttime);	
		}	
	}
	
	private ArrayList<String> makeRandomList(int wordSize, int size) {
		ArrayList<String> list = new ArrayList<String>();
		for(int i=0; i < size; i++){
			list.add(makeString(wordSize));
		}
		return list;
	}

	private String makeString(int wordSize) {
		StringBuilder sb = new StringBuilder();
		String alphabet = "abcdefghijklmnopqrstuvwxyz";
		for( int i=0; i < wordSize; i++){
			sb.append(alphabet.charAt(random.nextInt(alphabet.length())));
		}
		return sb.toString();
	}
	
	public void bubbleSort(ArrayList<String> list){
		for(int k=0; k < list.size(); k++) {
			for(int j=0; j < list.size()-k-1; j++) {
				if (list.get(j).compareTo(list.get(j+1)) > 0) {
					Collections.swap(list, j,j+1);
				}
			}
		}
	}
	

	public void runner() {
		timer(10000, 100000, 10000, 2);
	}

	
	public static void main(String[] args) {
		SortTiming st = new SortTiming();
		st.runSelect();
		st.runner();
		
	}
	
}
