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
	
	// These constants have the integers that will represent the respective types of tokens
	public int BOOLEAN = 0;
	public int BREAK = 1;
	public int CLASS = 2;
	public int DOUBLE = 3;
	public int ELSE = 4;
	public int EXTENDS = 5;
	public int FOR = 6;
	public int IF = 7;
	public int IMPLEMENTS = 8;
	public int INT = 9;
	public int INTERFACE = 10;
	public int NEW = 11;
	public int NEWARRAY = 12;
	public int NULL = 13;
	public int PRINTLN = 14;
	public int READLN = 15;
	public int RETURN = 16;
	public int STRING = 17;
	public int VOID = 18;
	public int WHILE = 19;
	public int PLUS = 20;
	public int MINUS = 21;
	public int MULTIPLICATION = 22;
	public int DIVISION = 23;
	public int MOD = 24;
	public int LESS = 25;
	public int LESSEQUAL = 26;
	public int GREATER = 27;
	public int GREATEREQUAL = 28;
	public int EQUAL = 29;
	public int NOTEQUAL = 30;
	public int AND = 31;
	public int OR = 32;
	public int NOT = 33;
	public int ASSIGNOP = 34;
	public int SEMICOLON = 35;
	public int COMMA = 36;
	public int PERIOD = 37;
	public int LEFTPAREN = 38;
	public int RIGHTPAREN = 39;
	public int LEFTBRACKET = 40;
	public int RIGHTBRACKET = 41;
	public int LEFTBRACE = 42;
	public int RIGHTBRACE = 43;
	public int INTCONSTANT = 44;
	public int DOUBLECONSTANT = 45;
	public int STRINGCONSTANT = 46;
	public int BOOLEANCONSTANT = 47;
	public int ID = 48;
	
	Trie keywords;// this trie is used to search if an identifier is a keyword
	
	// index that will be used to access a state function from the array
	private int nextState = 0; // starting at state0
	
	Scanner scn;// used to read the source file
	String line;// used to work on a line read from the file
	
	String output;// used to store the tokens to be displayed as names and not numbers
	
	String[] tokens;
	Trie _tokens;
	public LexicalAnalyzer() {
		_tokens = new Trie();
		tokens = new String[49];
		keywords = new Trie();
		Scanner scanner = null;
	    File file = new File("keywords.txt"); 
	    try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	    while (scanner.hasNextLine())
			keywords.trie(scanner.nextLine());
	    
	    scanner.close();
	    keywords.output();
	    file = new File("tokens.txt");
	    try {
	    	scanner = new Scanner(file);
	    }catch(FileNotFoundException e) {
	    	e.printStackTrace();
	    }
	    
	    int temp = 0;
	    while(scanner.hasNextLine())
	    	tokens[temp++] = scanner.nextLine();
	    
//	    for(int j = 0; j < 49; j++)
//	    	System.out.println("public int " + tokens[j].toUpperCase() + " = " + j + ";");
	}
	
	// main function that will generate tokens
	private int i;
	private int start; // start of token
	private boolean error; //
	public String tokenize(String fileName) {
		line = output = "";
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
	    	output += "\n";
	    }
	    System.out.println("******************************************************************************");
		return output;
	}
	
	private void state0(char c) {
		if( c == ' ' || c == '\t') start = i+1;
		else if('a' <= c && c <= 'z' || 'A' <= c && c <= 'Z' || c == '_') nextState = 1;
		else if('1' <= c && c <= '9') nextState = 2;
		else if(c == '0') nextState = 7;
		else if(c == '"') nextState = 10;
		else if(c == '+') {
			System.out.println(line.substring(i, start = i+1));
			output += tokens[PLUS] + " " ;
			// generate plus token
		}
		else if(c == '-') {
			System.out.println(line.substring(i, start = i+1));
			output += tokens[MINUS] + " ";
			// generate minus token
			
		}
		else if(c == '*') {
			System.out.println(line.substring(i, start = i+1));
			output += tokens[MULTIPLICATION] + " ";
			// generate multiplication token
		}
		else if(c == '/') nextState = 11;
		else if(c == '%') {
			System.out.println(line.substring(i, start = i+1));
			output += tokens[MOD] + " ";
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
			output += tokens[SEMICOLON] + " ";
			// generate semicolon token
		}
		else if(c == ',') {
			System.out.println(line.substring(i, start = i+1) );
			output += tokens[COMMA] + " ";
			// generate comma token
		}
		else if(c == '.') {
			System.out.println(line.substring(i, start = i+1) );
			output += tokens[PERIOD] + " ";
			// generate period token
		}
		else if(c == '{') {
			System.out.println(line.substring(i, start = i+1) );
			output += tokens[LEFTBRACE] + " ";
			// generate token
		}
		else if(c == '}') {
			System.out.println(line.substring(i, start = i+1) );
			output += tokens[RIGHTBRACE] + " ";
			// generate token			
		}
		else if(c == '(') {
			System.out.println(line.substring(i, start = i+1) );
			output += tokens[LEFTPAREN] + " ";
			// generate token
		}
		else if(c == ')') {
			System.out.println(line.substring(i, start = i+1) );
			output += tokens[RIGHTPAREN] + " ";
			// generate token
		}
		else if(c == '[') {
			System.out.println(line.substring(i, start = i+1) );
			output += tokens[LEFTBRACKET] + " ";
			// generate token
		}
		else if(c == ']') {
			System.out.println(line.substring(i, start = i+1) );
			output += tokens[RIGHTBRACKET] + " ";
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
			output += identifier + " ";
			start = i+1;
			// is identifier a boolean constant?
			if(identifier.compareTo("true") == 0) {
				output += "booleanconstant" + " ";
			}
			else if(identifier.compareTo("false") == 0) {
				output += "booleanconstant" + " ";
			}
			// a key word?
			else if(keywords.search(identifier)) {
				output += identifier + " ";
			}
			// a user identifier?
			else {
				//_tokens.trie(identifier);
				output += "id ";
			}
			// generate token
			nextState = 0;
		}
	}
	
	private void state2(char c) {
		if(c == '.') nextState = 3;
		else if(!('0' <= c && c <= '9')){
			System.out.println(line.substring(start, i--));
			output += "intconstant" + " ";
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
			output += "doubleconstant"  + " ";
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
			output += "doubleconstant" + " ";
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
			output += "intconstant" + " ";
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
			output += "intconstant" + " ";
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
		output += "stringconstant" + " ";
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
			output += "division" + " ";
			// generate division token
		}
	}
	
	private void state12(char c) {
		if(c == '=') {
			System.out.println(line.substring(start, start = i + 1));
			output += "lessequal" + " ";
			// generate token
		}
		else {
			System.out.println(line.substring(start, start = i));
			output += "less" + " ";
			i--;
			// generate token
		}
		nextState = 0;
	}
	
	private void state13(char c) {
		if(c == '=') {
			System.out.println(line.substring(start, start = i + 1));
			output += "greaterequal" + " ";
			// generate token
		}
		else {
			System.out.println(line.substring(start, start = i));
			output += "greater" + " ";
			i--;
			// generate token
		}
		nextState = 0;
	}
	
	private void state14(char c) {
		if(c == '=') {
			System.out.println(line.substring(start, start = i + 1));
			output += "equals" + " ";
			// generate token
		}
		else {
			System.out.println(line.substring(start, start = i));
			output += "assignop" + " ";
			i--;
			// generate token
		}
		nextState = 0;
	}
	private void state15(char c) {
		if(c == '=') {
			System.out.println(line.substring(start, start = i + 1));
			output += "notequals" + " ";
			// generate token
		}
		else {
			System.out.println(line.substring(start, start = i));
			output += "not" + " ";
			i--;
			// generate token
		}
		nextState = 0;
	}
	private void state16(char c) {
		if(c == '&') {
			System.out.println(line.substring(start, start = i + 1));
			output += "and" + " ";
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
			output += "or" + " ";
			// generate token
		}
		else {
			// throw error
		}
		nextState = 0;
	}
}
