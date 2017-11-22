package week3Word;

public class WordGram {

	private String[] myWords;
	private int myHash;
	
	public WordGram(String[] source, int start, int size){
		myWords = new String[size];
		System.arraycopy(source, start, myWords, 0, size);
	}
	
	public String wordAt(int index){
		if (index < 0 || index >= myWords.length ){
			throw new IndexOutOfBoundsException("bad index in workAt " + index);
		}
		return myWords[index];
		
	}
	
	public int length(){
        // TODO: Complete this method
        return myWords.length;
    }
	
	public String toString(){
        String ret = "";
        for(int i=0; i< myWords.length; i++){
        	ret += myWords[i] + " ";
        }

        return ret.trim();
    }

    public boolean equals(Object o) {
        WordGram other = (WordGram) o;
        if(this.length() != other.length()){
        	return false;
        }
        for(int i=0; i < myWords.length; i++){
        	if(! myWords[i].equals(other.wordAt(i))){
        		return false;
        	}
        }
        return true;

    }
    /*shift all words one towards 0 and add word at the end. 
       you lose the first word */
    public WordGram shiftAdd(String word) {	
       String[] newWords = new String[this.length()];
       for(int i=0; i< newWords.length-1; i++){
    	   String w = this.wordAt(i+1);
    	   newWords[i] = w;
       }
        newWords[myWords.length-1] = word;
        WordGram out = new WordGram(newWords, 0, newWords.length);
        return out;
    }
    
    public int hashCode(){
    	return toString().hashCode();
    }
}
