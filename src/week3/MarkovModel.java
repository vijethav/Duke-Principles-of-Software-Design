package week3;

import java.util.ArrayList;
import java.util.Random;

public class MarkovModel {
	private String myText;
	//myText = "this is a test yes this is a test.";
	private Random myRandom;
	private int numChar;
	
	public MarkovModel(int n) {
		myRandom = new Random();
		numChar = n;
	}
	
	public void setRandom(int seed){
		myRandom = new Random(seed);
	}
	
	public void setTraining(String s){
		myText = s.trim();
	}
	
	public String getRandomText(int numChars){
		if (myText == null){
			return "";
		}
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length()-numChar);
		String key = myText.substring(index, index+numChars);
		sb.append(key);
		for(int k=0; k < numChars-numChar; k++){
			ArrayList<String> follows = getFollows(key);
			if(follows.size() == 0){
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
		ArrayList<String> result = new ArrayList<String>(); 
		int len = key.length();
		for(int i =0; i<myText.length()-len; i++){
			if(key.equals(myText.substring(i, i+len))){
				result.add(myText.substring(i+len, i+len+1));
			}
			
		}
		
		return result;
		
	}
	 
	
	@Override
	public String toString(){
		return String.format("MarkovModel of order %d", numChar);
	}
}
