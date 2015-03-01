package dataStructures;
/**
 * @author Sarick Shah
 * This Class houses my main method which runs the LanguageChecker class and tests your strings for each possible language.
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFileChooser;

public class LanguageCheckerMain {

	public static void main(String[] args) {
		boolean test = true;	
		// while loop in order to ask if you want to read another file.
		while(test){
			System.out.println("Enter your string in the form of capitalized A's and B's ONLY:");
			//I'm using the jfileuser java
			JFileChooser filesave = new JFileChooser();
		    filesave.showDialog(null,"Please Select the File");
		    filesave.setVisible(true);
		    
			try {
				// gets the file that you click on and reads it into a line and while there are lines, 
				// the program runs. 
				File file = filesave.getSelectedFile();
				FileReader fileReader = new FileReader(file);
				BufferedReader bufferedReader = new BufferedReader(fileReader);
				StringBuffer stringBuffer = new StringBuffer();
				String line;
				while ((line = bufferedReader.readLine()) != null) {
					
					// Create a new Language Checker which takes the input as a parameter.
					LanguageChecker languageChecker = new LanguageChecker(line);
					//Tests the language using the languageTest method
					languageChecker.languageTest();	
					
					stringBuffer.append(line);
					stringBuffer.append("\n");
					
					//At the end, you are prompted to test again. 
					System.out.println("If your file has multiple lines, enter Y to test again.");
					System.out.println("If you have multiple files with a single line on each, enter Y to pick a new file");
					System.out.println("Enter Y or N");
					Scanner scanner = new Scanner(System.in);
					String input = scanner.nextLine();			
					if(input.equals("N")){
						test = false;
					}					
				}
				fileReader.close();
			} catch (IOException e) {
				
			}
		}		
	}
}
