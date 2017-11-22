package week3Abstract;

import java.util.ArrayList;
import java.util.Random;

public abstract class AbstractMarkovModel implements IMarkovModel {

	protected String myText;
	protected Random myRandom;
	protected int myOrder;
	
	public AbstractMarkovModel() {
		myRandom = new Random();
		
	}
	public AbstractMarkovModel(int n) {
		myRandom = new Random();
		myOrder = n;
		
	}
 	@Override
	public void setTraining(String text) {
		myText = text.trim();
	}

 	public void setRandom(int seed) {
 		myRandom = new Random(seed);
 	}
 	
	@Override
	abstract public String getRandomText(int numChars);
	
	protected ArrayList<String> getFollow(String key){
		return null;
	}
	
	protected ArrayList<String> getFollows(String key){
		ArrayList<String> result = new ArrayList<String>(); 
		int len = key.length();
		for(int i =0; i<myText.length()-len; i++){
			if(key.equals(myText.substring(i, i+len))){
				result.add(myText.substring(i+len, i+len+1));
			}
			
		}
		
		return result;
		
	}

}
