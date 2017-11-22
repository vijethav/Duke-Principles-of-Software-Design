package week3;

import java.util.ArrayList;

import edu.duke.FileResource;

public class Tester {

	public ArrayList<String> testGetFollows(){
		MarkovOne markone = new MarkovOne();
		String trainingTest = "this is a test yes this is a test.";
		markone.setTraining(trainingTest);
		ArrayList<String> follows = markone.getFollows("t.");	
		return follows;
	}
	
	public ArrayList<String> testGetFollowsWithFile(){
		FileResource fr = new FileResource("dataweek3/confucius.txt");
		String st = fr.asString();
		st = st.replace('\n', ' ');
		MarkovOne markone = new MarkovOne();
		markone.setTraining(st);
		ArrayList<String> follows = markone.getFollows("he");
		return follows;
	}
	public static void main(String[] args) {
		Tester ts = new Tester();
		//ArrayList<String> follows = ts.testGetFollows();
		ArrayList<String> follows = ts.testGetFollowsWithFile();
		System.out.println(follows.size());
	
		for(int i=0; i < follows.size(); i++){
			//System.out.println(follows.get(i));	
		}

	}

}
