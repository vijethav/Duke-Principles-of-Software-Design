package week3Word;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class EfficientMarkovWord implements IMarkovModel {
	private String[] myText;
	private Random myRandom;
	private int myOrder;
	private HashMap<WordGram, ArrayList<String>> myMap;
	
	public EfficientMarkovWord(int order) {
		myMap = new HashMap<WordGram, ArrayList<String>>();
		myOrder = order;
	}
	
	public void setTraining(String text) {
		myText = text.split("\\s+");
		buildMap();
		printHashMapInfo();

	}
	
	private void printHashMapInfo() {
		System.out.println("Number of keys in the HashMap of WordGram " + myMap.size());
		int maxSize = 0;
		int maxIndex = 0;
		for(WordGram w :myMap.keySet()){
			maxSize = Math.max(maxSize, myMap.get(w).size());
			
		//	System.out.println("Wordgram: " + w + "  values: " + myMap.get(w));
		}
		
		System.out.println("The Maximum number of elements following a key is " + maxSize );
		
		System.out.println("Keys with the Maximum size value: ");
		for(WordGram wg : myMap.keySet()){
			if(myMap.get(wg).size() == maxSize){
				System.out.println("Wordgram with Max Values: " + wg + "  values: " + myMap.get(wg));
			}
		}
	}

	private void buildMap() {
        for(int i=0; i<myText.length-(myOrder-1); i++){
    		WordGram wg = new WordGram(myText,i,myOrder);
            String next = "";
            
          if(i+myOrder < myText.length){
        	  next = myText[i+myOrder];
          }
          if(myMap.containsKey(wg)){
        	  myMap.get(wg).add(next);
          }
          else{
  			ArrayList<String> list = new ArrayList<String>();
            list.add(next);
            myMap.put(wg, list);
          }
        }
		
	}
	
	ArrayList<String> getFollows(WordGram key){
		return myMap.get(key);
	}
	
	@Override
	public void setRandom(int seed) {
		myRandom = new Random(seed);
	}

	@Override
	public String getRandomText(int numWords) {
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length-myOrder);
		WordGram key = new WordGram(myText,index, myOrder);
			//System.out.println(i+ "\t"+ wg.length()+ "\t"+ wg);
		    sb.append(key.toString());
		    sb.append(" ");
		    for(int i = 0; i < numWords - myOrder; i++) {
		    
				ArrayList<String> follows = getFollows(key);
				// System.out.println(key + " " + follows);
				if(follows == null){
					break;
				}
				index = myRandom.nextInt(follows.size());
				String next = follows.get(index);
				sb.append(next);
				sb.append(" ");
				key = key.shiftAdd(next);
			}
		
		
		
		return sb.toString().trim();
	}

	
	
	
	public String toString(){
		return String.format(" EfficientMarkovWord of order %d", myOrder);
	}
}
