
public class LexicalAnalyzer {
	
	private interface checkState{
		void check();
	}
	
	private checkState[] states = new checkState[] {
		new checkState() {public void check() {state1();}},
		new checkState() {public void check() {state2();}}
	};
	
	public String tokenize(String code) {
		for(int i = 0; i < code.length(); i++) {
			checkState[i]
		}
		return "";
	}
	
	private void state1() {
		
	}
	
	private void state2() {
		
	}
	
	private void state3() {
		
	}
}
