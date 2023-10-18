import java.util.Scanner;

/**
 * The Referee class keeps track of the board, any dice, and all interactions
 * with the players. It keeps track of whose turn it is, displays the board,
 * rolls dice, and asks the users to make their moves. 
 */
public class Referee {

	Board myBoard;
	boolean player1Turn;
	DiceCup myDiceCup;
	Scanner keyReader;
	
	public Referee() {
		keyReader = new Scanner(System.in);
		myBoard = new Board();
		player1Turn = true;
		myDiceCup = new DiceCup();
	}

	public void playGame()
	{
		// TODO: you write the Referee's playGame method.
		System.out.println("Playing game."); // placeholder code.
	}
}
