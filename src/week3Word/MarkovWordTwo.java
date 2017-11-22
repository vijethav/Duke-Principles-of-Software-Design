package week3Word;

import java.util.ArrayList;
import java.util.Random;
import week3Word.IMarkovModel;

public class MarkovWordTwo implements IMarkovModel{
	private String[] myText;
	private Random myRandom;
	
	public MarkovWordTwo() {
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
		int index = myRandom.nextInt(myText.length-2);
		
		String key1 = myText[index];
		String key2 = myText[index+1];
		sb.append(key1);
		sb.append(" ");
		sb.append(key2);
		sb.append(" ");
		for(int k=0; k < numWords-2; k++){
			ArrayList<String> follows = getFollows(key1, key2);
			// System.out.println(key + " " + follows);
			if(follows.size() == 0){
				break;
			}
			index = myRandom.nextInt(follows.size());
			String next = follows.get(index);
			sb.append(next);
			sb.append(" ");
			key1 = key2;
			key2 = next;
		}
		return sb.toString().trim();
	}

	public ArrayList<String> getFollows(String key1, String key2){
		ArrayList<String> follows = new ArrayList<String>(); 
		int pos = 0;
		while(pos < myText.length){
			int start = indexOf(myText, key1, key2, pos);
			if(start == -1){
				break;
			}
			if(start >= myText.length-1){
				break;
			}
			String next = myText[start+2];
			follows.add(next);
			pos = start + 1;
			
		}
		
		return follows;
		
	}
	private int indexOf(String[] words, String target1, String target2, int start) {
		for(int i = start; i < words.length; i++){
			if(words[i].equals(target1) && words[i+1].equals(target2) ){
				return i;
			}
		}
		return -1;
	}
	
	
	public String toString(){
		return String.format(" MarkovWordTwoModel of order %d", 2);
	}
	
	/*public void testIndexOf() {
		String s = "this is just a test yes this is a simple test";
		String[] words = s.split("\\s+");
        System.out.print(indexOf(words, "this", "is", 0) + "\n");
        System.out.print(indexOf(words, "is", "just", 0) + "\n");
        System.out.print(indexOf(words, "this", "is", 1) + "\n");
	}
	public static void main(String[] args) {
		MarkovWordTwo m2 = new MarkovWordTwo();
		m2.testIndexOf();
	}*/
}
