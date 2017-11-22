package week3Abstract;

import java.util.ArrayList;
import java.util.Random;

public class MarkovModel extends AbstractMarkovModel{
	
	private int myOrder;
	
	public MarkovModel(int order) {
		myRandom = new Random();
		myOrder = order;
	}
	
	public void setRandom(int seed){
		myRandom = new Random(seed);
	}
	
	public void setTraining(String s){
		myText = s.trim();
	}
	
	public String getRandomText(int length){
		if (myText == null){
			return "";
		}
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length() - myOrder);
		String key = myText.substring(index, index + myOrder);
		sb.append(key);
		for(int k=0; k < length-myOrder; k++){
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
	
	
	
	@Override
	public String toString(){
		return String.format("MarkovModel of order %d", myOrder);
	}
}
