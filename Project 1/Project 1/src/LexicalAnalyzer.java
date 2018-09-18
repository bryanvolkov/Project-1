
public class LexicalAnalyzer {
	
	// This interface is used to make an array of functions
	private interface checkState{
		void check();
	}
	
	// Array of functions
	// Contains the functions that represent the states 
	private checkState[] states = new checkState[] {
		new checkState() {public void check() {state0();}},
		new checkState() {public void check() {state1();}},
		new checkState() {public void check() {state2();}},
		new checkState() {public void check() {state3();}}
	};
	
	// index that will be used to access a state function from the array
	private int state = 0; // starting at state0
	
	// main function that will generate tokens
	public String tokenize(String code) {
		// check each character from the source code
		for(int i = 0; i < code.length(); i++) {
			states[state].check();
		}
		return "";
	}
	
	private void state0() {
		
	}
	
	private void state1() {
		
	}
	
	private void state2() {
		
	}
	
	private void state3() {
		
	}
}
