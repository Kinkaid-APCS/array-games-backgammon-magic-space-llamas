import java.util.Scanner;

/**
 * The Referee class keeps track of the board, any dice, and all interactions
 * with the players. It keeps track of whose turn it is, displays the board,
 * rolls dice, and asks the users to make their moves. 
 */
public class Referee {

	private Board myBoard;
	private int bar;
	private boolean player1Turn;
	private DiceCup myDiceCup;
	private Scanner keyReader;
	
	public Referee() {
		keyReader = new Scanner(System.in);
		myBoard = new Board();
		player1Turn = false;
		myDiceCup = new DiceCup();
		bar = 0;
	}

	public void playGame()
	{
		// TODO: you write the Referee's playGame method.
		System.out.println("Playing game."); // placeholder code.
        player1Turn = !player1Turn;
		myDiceCup.roll();
		myDiceCup.calculateAvailableMoves();
		System.out.println(myDiceCup.toString());
		//code for if player is bearing off :


		//if not bearing off:
		//check for bar pieces
		if (player1Turn) {
			bar = 0;
		}
		else {
			bar = 25;
		}
		if (myBoard.playerHasPieceAtLocation(player1Turn, bar)) {

		}



	}

	public void playerMove() {

	}

	public void getPlayerMove() {

	}
	public void playerBear() {

	}

	//TODO:
	public boolean checkWinner() {
        if (myBoard.gameIsOver()) {
			//check who wins
			return true;
		}
        return false;
    }
}
