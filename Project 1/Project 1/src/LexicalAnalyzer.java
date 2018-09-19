
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
		new checkState() {public void check(char c) {state3(c);}}
	};
	
	// index that will be used to access a state function from the array
	private int currentState = 0; // starting at state0
	
	// main function that will generate tokens
	public String tokenize(String code) {
		// check each character from the source code
		for(int i = 0; i < code.length(); i++) {
			states[currentState].check(code.charAt(i));
		}
		return "";
	}
	
	private void state0(char c) {
		if('a' <= c && c <= 'z' || 'A' <= c && c <= 'Z' || c == '_')
			currentState = 1;
		
	}
	
	private void state1(char c) {
		if('a' <= c && c <= 'z' || 'A' <= c && c <= 'Z' || c == '_' || '0' <= c && c <= '9')
			currentState = 1;
		else if(c == ' ') {
			// is identifier a key word?
			// a boolean constant?
			// a user identifier?
			// generate token
			currentState = 0;
		}
		
	}
	
	private void state2(char c) {
		
	}
	
	private void state3(char c) {
		
	}
}
