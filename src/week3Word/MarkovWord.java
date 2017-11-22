package week3Word;

import java.util.ArrayList;
import java.util.Random;

public class MarkovWord implements IMarkovModel{

	private String[] myText;
	private Random myRandom;
	private int myOrder;
	
	public MarkovWord(int order) {
		myOrder = order;
		myRandom = new Random();
	}
	
	public void setTraining(String text) {
		myText = text.split("\\s+");

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
				if(follows.size() == 0){
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

	public ArrayList<String> getFollows(WordGram key){
		ArrayList<String> follows = new ArrayList<String>(); 
		int pos = 0;
		while(pos < myText.length){
			int start = indexOf(myText, key, pos);
			if(start == -1){
				break;
			}
			if(start >= myText.length -1){
				break;
			}
			String next = myText[start+myOrder];
			follows.add(next);
			pos = start + 1;
			
		}
		
		return follows;
		
	}
	private int indexOf(String[] words, WordGram target, int start) {
		for(int i = start; i < words.length-myOrder; i++){
			WordGram wg = new WordGram(words, i, myOrder);
			if(wg.equals(target)){
				return i;
			}
		}
		return -1;
	}
	
	public String toString(){
		return String.format(" MarkovWord of order %d", myOrder);
	}
}
