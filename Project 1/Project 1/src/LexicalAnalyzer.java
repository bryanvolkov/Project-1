import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LexicalAnalyzer {
	
	// This interface is used to make an array of functions
	private interface checkState{
		void check(char c);
	}
	
	// Array of functions
	// Contains the functions that represent the states 
	private checkState[] states = new checkState[] {
		new checkState() {public void check(char c) {state0(c);}},
		new checkState() {public void check(char c) {state1(c);}},
		new checkState() {public void check(char c) {state2(c);}},
		new checkState() {public void check(char c) {state3(c);}},
		new checkState() {public void check(char c) {state4(c);}},
		new checkState() {public void check(char c) {state5(c);}},
		new checkState() {public void check(char c) {state6(c);}},
		new checkState() {public void check(char c) {state7(c);}},
		new checkState() {public void check(char c) {state8(c);}},
		new checkState() {public void check(char c) {state9(c);}},
		new checkState() {public void check(char c) {state10(c);}},
		new checkState() {public void check(char c) {state11(c);}},
		new checkState() {public void check(char c) {state12(c);}},
		new checkState() {public void check(char c) {state13(c);}},
		new checkState() {public void check(char c) {state14(c);}},
		new checkState() {public void check(char c) {state15(c);}},
		new checkState() {public void check(char c) {state16(c);}},
		new checkState() {public void check(char c) {state17(c);}}
	};
	
	// index that will be used to access a state function from the array
	private int nextState = 0; // starting at state0
	Scanner scn;
	String line;
	
	String tokens;
	
	// main function that will generate tokens
	private int i;
	private int start; // start of token
	private boolean error; //
	public String tokenize(String fileName) {
		line = tokens = "";
		error = false;
	    File file = new File(fileName); 
	    try {
			scn = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	    
		// check each character from the source code
	    while (scn.hasNextLine()) {
	    	line = scn.nextLine();
	    	for(i = start = 0; i < line.length(); i++) {
	    		states[nextState].check(line.charAt(i));
	    		if(error) return "ERROR";
	    	}
	    	tokens += "\n";
	    }
	    System.out.println("******************************************************************************");
		return tokens;
	}
	
	private void state0(char c) {
		if( c == ' ' || c == '\t') start = i+1;
		else if('a' <= c && c <= 'z' || 'A' <= c && c <= 'Z' || c == '_') nextState = 1;
		else if('1' <= c && c <= '9') nextState = 2;
		else if(c == '0') nextState = 7;
		else if(c == '"') nextState = 10;
		else if(c == '+') {
			System.out.println(line.substring(i, start = i+1));
			tokens += "plus" + " " ;
			// generate plus token
		}
		else if(c == '-') {
			System.out.println(line.substring(i, start = i+1));
			tokens += "minus" + " ";
			// generate minus token
			
		}
		else if(c == '*') {
			System.out.println(line.substring(i, start = i+1));
			tokens += "multiplication" + " ";
			// generate multiplication token
		}
		else if(c == '/') nextState = 11;
		else if(c == '%') {
			System.out.println(line.substring(i, start = i+1));
			tokens += "modulus" + " ";
			// generate modulus token
		}
		else if(c == '<') nextState = 12;
		else if(c == '>') nextState = 13;
		else if(c == '=') nextState = 14;
		else if(c == '!') nextState = 15;
		else if(c == '&') nextState = 16;
		else if(c == '|') nextState = 17;
		else if(c == ';') {
			System.out.println(line.substring(i, start = i+1) );
			tokens += "semicolon" + " ";
			// generate semicolon token
		}
		else if(c == ',') {
			System.out.println(line.substring(i, start = i+1) );
			tokens += "comma" + " ";
			// generate comma token
		}
		else if(c == '.') {
			System.out.println(line.substring(i, start = i+1) );
			tokens += "period" + " ";
			// generate period token
		}
		else if(c == '{') {
			System.out.println(line.substring(i, start = i+1) );
			tokens += "leftbrace" + " ";
			// generate token
		}
		else if(c == '}') {
			System.out.println(line.substring(i, start = i+1) );
			tokens += "rightbrace" + " ";
			// generate token			
		}
		else if(c == '(') {
			System.out.println(line.substring(i, start = i+1) );
			tokens += "leftparen" + " ";
			// generate token
		}
		else if(c == ')') {
			System.out.println(line.substring(i, start = i+1) );
			tokens += "rightparen" + " ";
			// generate token
		}
		else if(c == '[') {
			System.out.println(line.substring(i, start = i+1) );
			tokens += "leftbracket" + " ";
			// generate token
		}
		else if(c == ']') {
			System.out.println(line.substring(i, start = i+1) );
			tokens += "rightbracket" + " ";
			// generate token
		}
		else {
			// throw error
			System.out.println("ERROR: found character not belonging to alphabet!");
			error = true;
		}
	}
	
	private void state1(char c) {
		if(!('a' <= c && c <= 'z' || 'A' <= c && c <= 'Z' || c == '_' || '0' <= c && c <= '9')){
			String identifier = line.substring(start, i--);
			System.out.println(identifier);
			tokens += identifier + " ";
			start = i+1;
			// is identifier a boolean constant?
			if(identifier.compareTo("true") == 0) {
				tokens += "booleanconstant" + " ";
			}
			else if(identifier.compareTo("false") == 0) {
				tokens += "booleanconstant" + " ";
			}
			// a key word?
			// a user identifier?
			else {
				
			}
			// generate token
			nextState = 0;
		}
	}
	
	private void state2(char c) {
		if(c == '.') nextState = 3;
		else if(!('0' <= c && c <= '9')){
			System.out.println(line.substring(start, i--));
			tokens += "intconstant" + " ";
			start = i+1;
			// generate int constant
			nextState = 0;
		}
	}
	
	private void state3(char c) {
		if (c == 'E' || c == 'e') 
			nextState = 4;
		else if(!('0' <= c && c <= '9')){
			System.out.println(line.substring(start, i--));
			tokens += "doubleconstant"  + " ";
			start = i+1;
			// generate double constant
			nextState = 0;
		}
	}
	
	private void state4(char c) {
		if('0' <= c && c <= '9')
			nextState = 6;
		else if(c == '+' || c == '-')
			nextState = 5;
		else {
			// throw error
		}
	}
	private void state5(char c) {
		if('0' <= c && c <= '9')
			nextState = 6;
		else {
			// throw error
		}
	}
	
	private void state6(char c) { 
		if(!('0' <= c && c <= '9')) {
			System.out.println(line.substring(start, i--));
			tokens += "doubleconstant" + " ";
			start = i+1;
			// generate double constant
			nextState = 0;
		}
	}
	
	private void state7(char c) {
		if('0' <= c && c <= '9')
			nextState = 2;
		else if(c == '.')
			nextState = 3;
		else if(c == 'x' || c == 'X')
			nextState = 8;
		else {
			System.out.println(line.substring(start, i--));
			tokens += "intconstant" + " ";
			start = i+1;
			// generate constant int 0
			nextState = 0;
		}
	}
	private void state8(char c) {
		if('a' <= c && c <= 'f' || 'A' <= c && c <= 'F' || '0' <= c && c <= '9')
			nextState = 9;
		else {
			// throw error
			System.out.println("ERROR: Invalid character found");
			error = true;
			nextState = 0;
		}
	}
	private void state9(char c) {
		if(!('a' <= c && c <= 'f' || 'A' <= c && c <= 'F' || '0' <= c && c <= '9')) {
			System.out.println(line.substring(start, i--));
			tokens += "intconstant" + " ";
			start = i+1;
			// generate int constant
			nextState = 0;
		}
	}
	
	private void state10(char c) {
		// find "
		for(start = i++; i < line.length(); i++)
			if(line.charAt(i) == '"') break;
		if(i == line.length()) {
			// throw error
			System.out.println("ERROR: ending \" not found" );
			error = true;
			return;
		}
		System.out.println(line.substring(start, i));
		tokens += "stringconstant" + " ";
		nextState = 0;
	}
	private void state11(char c) {
		nextState = 0;
		if(c == '/')
			i = line.length(); // move on to next line
		else if(c == '*') {
			// find */
			for(i++; i < line.length(); i++)
				if(line.charAt(i) == '*' && (i+1)< line.length() && line.charAt(i+1) == '/') {
					start = i = i + 2;
					return;}
			
			while(scn.hasNext()) {
				line = scn.nextLine();
				for(i = 0; i < line.length(); i++)
					if(line.charAt(i) == '*' && (i+1)< line.length() && line.charAt(i+1) == '/') {
						start = i = i + 2;
						return;}
			}
		}
		else {
			System.out.println(line.substring(start, start = i));
			tokens += "division" + " ";
			// generate division token
		}
	}
	
	private void state12(char c) {
		if(c == '=') {
			System.out.println(line.substring(start, start = i + 1));
			tokens += "lessequal" + " ";
			// generate token
		}
		else {
			System.out.println(line.substring(start, start = i));
			tokens += "less" + " ";
			i--;
			// generate token
		}
		nextState = 0;
	}
	
	private void state13(char c) {
		if(c == '=') {
			System.out.println(line.substring(start, start = i + 1));
			tokens += "greaterequal" + " ";
			// generate token
		}
		else {
			System.out.println(line.substring(start, start = i));
			tokens += "greater" + " ";
			i--;
			// generate token
		}
		nextState = 0;
	}
	
	private void state14(char c) {
		if(c == '=') {
			System.out.println(line.substring(start, start = i + 1));
			tokens += "equals" + " ";
			// generate token
		}
		else {
			System.out.println(line.substring(start, start = i));
			tokens += "assignop" + " ";
			i--;
			// generate token
		}
		nextState = 0;
	}
	private void state15(char c) {
		if(c == '=') {
			System.out.println(line.substring(start, start = i + 1));
			tokens += "notequals" + " ";
			// generate token
		}
		else {
			System.out.println(line.substring(start, start = i));
			tokens += "not" + " ";
			i--;
			// generate token
		}
		nextState = 0;
	}
	private void state16(char c) {
		if(c == '&') {
			System.out.println(line.substring(start, start = i + 1));
			tokens += "and" + " ";
			// generate token
		}
		else {
			// throw error
		}
		nextState = 0;
	}
	private void state17(char c) {
		if(c == '|') {
			System.out.println(line.substring(start, start = i + 1));
			tokens += "or" + " ";
			// generate token
		}
		else {
			// throw error
		}
		nextState = 0;
	}
}
