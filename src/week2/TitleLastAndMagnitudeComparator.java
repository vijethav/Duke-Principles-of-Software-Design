package week2;

import java.util.Comparator;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry>{

	@Override
	public int compare(QuakeEntry qe1, QuakeEntry qe2) {
		
		String qe1LastWord = qe1.getInfo().substring(qe1.getInfo().lastIndexOf(" ")+1);
		String qe2LastWord = qe2.getInfo().substring(qe2.getInfo().lastIndexOf(" ")+1);
		
		if(qe1LastWord.compareTo(qe2LastWord) == 0){
		       return Double.compare(qe1.getMagnitude(), qe2.getMagnitude());
		}
		return qe1LastWord.compareTo(qe2LastWord);
	}

}
