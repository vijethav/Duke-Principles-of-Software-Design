package week3Word;

import edu.duke.FileResource;
import week3.MarkovModel;
import week3.MarkovRunner;
import week3Abstract.EfficientMarkovModel;
import week3Word.IMarkovModel;

public class MarkovRunnerWord {

	public void runModel(IMarkovModel markov, String text, int size){ 
        markov.setTraining(text); 
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 
	
	 public void runModel(IMarkovModel markov, String text, int size, int seed){ 
	        markov.setTraining(text); 
	        markov.setRandom(seed);
	        System.out.println("running with " + markov); 
	        for(int k=0; k < 3; k++){ 
	            String st = markov.getRandomText(size); 
	            printOut(st); 
	        } 
	    } 
	 
	 public void runMarkovOne() { 
//       FileResource fr = new FileResource(); 
       FileResource fr = new FileResource("dataweek3/confucius.txt"); 
       String st = fr.asString(); 
       //String st = "this is just a test yes this is a simple test";
       st = st.replace('\n', ' '); 
       MarkovWordOne markovWordOne = new MarkovWordOne(); 
      // runModel(markovWord, st, 200);  
         runModel(markovWordOne, st, 120, 139); 
        //runModel(markovWordOne, st, 120, 139); 
   } 
	
	 public void runMarkovTwo() { 
 
       FileResource fr = new FileResource("dataweek3/confucius.txt"); 
       String st = fr.asString(); 
       st = st.replace('\n', ' '); 
       MarkovWordTwo markovWord = new MarkovWordTwo(); 
      // runModel(markovWord, st, 200);  
         runModel(markovWord, st, 643, 832);  
      } 
	 
	 public void runMarkovWord() { 
		 
	       FileResource fr = new FileResource("dataweek3/confucius.txt"); 
	       String st = fr.asString(); 
	       st = st.replace('\n', ' '); 
	       MarkovWord markovWord = new MarkovWord(5); 
	      // runModel(markovWord, st, 200);  
	         runModel(markovWord, st, 643, 844);  
	      } 
	 
	 public void runEfficientMarkovWord() { 
		 
	       FileResource fr = new FileResource("dataweek3/confucius.txt"); 
	       String st = fr.asString(); 
	      // String st = "this is a test yes this is really a test yes a test this is wow";
	       st = st.replace('\n', ' '); 
	       EfficientMarkovWord markovWord = new EfficientMarkovWord(5); 
	      // runModel(markovWord, st, 200);  
	         runModel(markovWord, st, 1000, 531);  
	      } 
	 
	 public void compareMethods(){
		FileResource fr = new FileResource("dataweek3/hawthorne.txt");
		String st = fr.asString();
	  //  String st ="yes-this-is-a-thin-pretty-pink-thistle";
		st = st.replace('\n', ' ');
		int size = 100;
		int seed = 42;
		double startTime = System.nanoTime();
		MarkovWord markovWord = new MarkovWord(2); 
		runModel(markovWord, st, size, seed); 
		double endTime = System.nanoTime();
		 System.out.println("The running time of MarkovWord is " + 
		                   (endTime-startTime)/ 1000000000.0 + " seconds");
		 startTime = System.nanoTime();
		 
		 EfficientMarkovWord emw = new EfficientMarkovWord(2);
		 runModel(emw, st, size, seed);
		 endTime = System.nanoTime();
		 System.out.println("The running time of EfficientMarkovWord is " + 
                 (endTime-startTime)/ 1000000000.0 + " seconds");
	}
	 

	public static void main(String[] args) {
		MarkovRunnerWord markRunner = new MarkovRunnerWord();
		//markRunner.runMarkovWord();
		markRunner.runEfficientMarkovWord();
		//markRunner.compareMethods();
		
		
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
}
