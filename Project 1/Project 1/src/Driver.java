import java.util.Scanner;

public class Driver {
	static Scanner scn;
	public static void main(String[] args) {
		Trie trie = new Trie();
		LexicalAnalyzer lexer = new LexicalAnalyzer();
	    System.out.println(lexer.tokenize("code.txt"));

	}

}
