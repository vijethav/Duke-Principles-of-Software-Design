package week3;

import edu.duke.FileResource;

public class MarkovRunner {

	public void runMarkovZero() {
		FileResource fr = new FileResource("dataweek3/confucius.txt");
		String st = fr.asString();
		//String st = "vijetha";
		st = st.replace('\n', ' ');
		MarkovZero markov = new MarkovZero();
		//MarkovOne markov = new MarkovOne();
		markov.setRandom(1024);
		markov.setTraining(st);
		for(int k=0; k < 3; k++){
			
			String text = markov.getRandomText(1000);
			printOut(text);
		}
	}
	public void runMarkovOne() {
		FileResource fr = new FileResource("dataweek3/confucius.txt");
		String st = fr.asString();
		st = st.replace('\n', ' ');
		//MarkovZero markov = new MarkovZero();
		MarkovOne markov = new MarkovOne();
		markov.setRandom(42);
		markov.setTraining(st);
		for(int k=0; k < 3; k++){
			String text = markov.getRandomText(500);
			printOut(text);
		}
	}
	public void runMarkovFour(){
		FileResource fr = new FileResource("dataweek3/confucius.txt");
		String st = fr.asString();
		st = st.replace('\n', ' ');
		MarkovFour markov = new MarkovFour();
		markov.setRandom(25);
		markov.setTraining(st);
		for(int k=0; k < 3; k++){
			String text = markov.getRandomText(500);
			printOut(text);
		}
	}
	
	public void runMarkovModel(){
		FileResource fr = new FileResource("dataweek3/confucius.txt");
		String st = fr.asString();
		st = st.replace('\n', ' ');
		
		MarkovModel markov = new MarkovModel(6);
		markov.setRandom(38);
		markov.setTraining(st);
		for(int k=0; k < 3; k++){
			String text = markov.getRandomText(500);
			printOut(text);
		}
	}
	
	private void printOut(String s){
		String[] words = s.split("\\s+");
		int psize = 0;
		System.out.println("----------------------------------");
		for(int k=0; k < words.length; k++){
			System.out.print(words[k]+ " ");
			psize += words[k].length() + 1;
			if (psize > 60) {
				System.out.println();
				psize = 0;
			}
		}
		System.out.println("\n----------------------------------");
	}
	
//	public void runModel(IMarkovModel markov, String text, int size){
//		markov.setTraining(text);
//		System.out.println("Running with "+ markov);
//		for(int i=0; i<3; i++){
//			String st = markov.getRandomText(size);
//			printOut(st);
//		}
//	}
	
	public static void main(String[] args) {
		MarkovRunner markRunner = new MarkovRunner();
		markRunner.runMarkovZero();
		//markRunner.runMarkovOne();
		//markRunner.runMarkovFour();
		//markRunner.runMarkovModel();
		
		

	}
}
