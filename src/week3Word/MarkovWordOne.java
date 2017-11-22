package week3Word;

import java.util.ArrayList;
import java.util.Random;

import week3Word.IMarkovModel;

public class MarkovWordOne implements IMarkovModel {

	private String[] myText;
	private Random myRandom;
	
	public MarkovWordOne() {
		myRandom = new Random();
	}
	@Override
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
		int index = myRandom.nextInt(myText.length-1);
		
		String key = myText[index];
		sb.append(key);
		sb.append(" ");
		for(int k=0; k < numWords-1; k++){
			ArrayList<String> follows = getFollows(key);
			// System.out.println(key + " " + follows);
			if(follows.size() == 0){
				break;
			}
			index = myRandom.nextInt(follows.size());
			String next = follows.get(index);
			sb.append(next);
			sb.append(" ");
			key = next;
		}
		return sb.toString().trim();
	}

	public ArrayList<String> getFollows(String key){
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
			String next = myText[start+1];
			follows.add(next);
			pos = start + 1;
			
		}
		
		return follows;
		
	}
	private int indexOf(String[] words, String target, int start) {
		for(int i = start; i < words.length; i++){
			if(words[i].equals(target)){
				return i;
			}
		}
		return -1;
	}
	
	
	public String toString(){
		return String.format(" MarkovWordOneModel of order %d", 1);
	}
	
	/*public void testIndexOf(){
		String t = "this is just a test yes this is a simple test";
		String words[] = t.split("\\s+");
		System.out.print(indexOf(words, "this", 0) + "\n");
		System.out.print(indexOf(words, "this", 3) + "\n");
		System.out.print(indexOf(words, "frog", 0) + "\n");
		System.out.print(indexOf(words, "frog", 5) + "\n");
		System.out.print(indexOf(words, "simple", 2) + "\n");
		System.out.print(indexOf(words, "test", 5) + "\n");
		
	} */
	/*public static void main(String[] args) {
	   MarkovWordOne mr = new MarkovWordOne();
	   String s = "this is just a test yes this is a simple test";
	   mr.setTraining(s);
	   
	}*/
}
