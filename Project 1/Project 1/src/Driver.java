import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Driver {
	static Scanner scn;
	public static void main(String[] args) {

	    File file = new File("code.txt"); 
	    try {
			scn = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
	  
	    while (scn.hasNextLine()) 
	      System.out.println(scn.nextLine());
	}

}
