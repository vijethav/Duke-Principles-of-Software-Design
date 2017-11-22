package week3Abstract;

import java.util.ArrayList;
import java.util.HashMap;

public class EfficientMarkovModel extends AbstractMarkovModel{
	private HashMap<String, ArrayList<String>> myMap;
	private int myOrder;
	public EfficientMarkovModel(int n) {
		myOrder = n;
		myMap = new HashMap<String, ArrayList<String>>();
	}
	
	public void setTraining(String s){
		super.setTraining(s);
		buildMap();
		//printHashMapInfo();
	}
	public void buildMap(){ 

		for (int i = 0; i < myText.length() - (myOrder-1); i++) {

			String current = myText.substring(i, i+myOrder);
			//			System.out.println(current);
			String follow ="";
			
			if(i+myOrder < myText.length())
				follow = myText.substring(i+myOrder, i+myOrder+1);

			if (myMap.containsKey(current)) {
				myMap.get(current).add(follow);
			}
			
			else {
				ArrayList<String> list = new ArrayList<String>();
				list.add(follow);
				myMap.put(current, list);
			}
		}

	}
	
	@Override
	public String getRandomText(int numChars) {
		if (myText == null){
			return "";
		}
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length()-myOrder);
		String key = myText.substring(index, index+myOrder);
		sb.append(key);
		for(int k=0; k < numChars-myOrder; k++){
			ArrayList<String> follows = getFollows(key);
			if(follows == null){
				break;
			}
			index = myRandom.nextInt(follows.size());
			String next = follows.get(index);
			sb.append(next);
			key = key.substring(1) + next;
		}
		
		return sb.toString();
	}
	
	public ArrayList<String> getFollows(String key){
		return myMap.get(key);
	}

	public void printHashMapInfo(){
		System.out.println("It has " +  myMap.size() + " keys in the HashMap");
		int maxSize = 0;
		for (String key : myMap.keySet()) {
			maxSize = Math.max(maxSize, myMap.get(key).size());
//			System.out.printf("Key:\t[%s]\tvalues: ", key);
//			System.out.println(myMap.get(key));
		}
		System.out.println("The maximum number of keys following a key is " + maxSize);
		ArrayList<String> keys = new ArrayList<String>();
		for (String key : myMap.keySet()) {
			if(myMap.get(key).size() == maxSize){
				keys.add(key);
			}
		}
		System.out.println("Keys that have the largest ArrayList are: " + keys );
	}
	
	@Override
	public String toString(){
		return String.format("EfficientMarkovModel of order %d", myOrder);
	}
	
}
