package RSA;

import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

/**
 * Created by Connor on 3/30/2015.
 */
public class DataReader {

    Scanner s;
	
	//read integers from file and allow one-by-one access
    public DataReader(String filename)throws Exception{
        File f = new File(filename);
        s = new Scanner(new FileReader(f));
    }
    int getNext() {return s.nextInt();}

    boolean hasNext(){return s.hasNext();}
}
