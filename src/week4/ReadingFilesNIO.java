package week4;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ReadingFilesNIO {
	
	public void readAndPrint() throws IOException{
		Path p = Paths.get("/Users/vijetha/Documents/workspace/Duke-Principles-of-Software-Design/src/data/hello_unicode.txt");
		BufferedReader reader = Files.newBufferedReader(p);
		while(true){ 
			String line = reader.readLine();
			if(line == null){
				break;
			}
			System.out.println(line);
		}
	}

	public void readURL() throws IOException{
		URL source = new URL("http://www.dukelearntoprogram.com/java/hello_unicode.txt");
		BufferedReader reader = new BufferedReader(new InputStreamReader(source.openStream()));
		while(true){ 
			String line = reader.readLine();
			if(line == null){
				break;
			}
			System.out.println(line);
		}
	}

	public static void main(String[] args) throws IOException {
		ReadingFilesNIO rf = new ReadingFilesNIO();
		rf.readAndPrint();
		//rf.readURL();

	}

}
