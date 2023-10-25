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
        while (!myBoard.gameIsOver()) {
			player1Turn = !player1Turn; //switches player turn
			if (player1Turn) System.out.println("Player 1 Turn:"); else System.out.println("Player 2 Turn:");

			myDiceCup.roll();
			myDiceCup.calculateAvailableMoves();

			//code for if player is bearing off :


			//if not bearing off:
			//check for bar pieces
			if (player1Turn) {
				bar = 0;
			} else {
				bar = 25;
			}
	//		if (myBoard.playerHasPieceAtLocation(player1Turn, bar)) {
	//		}

			while (myDiceCup.hasMovesLeft()) {
				System.out.println(myBoard);
				System.out.println(myDiceCup.toString());
				playerMove();
			}
		}
	}

	public void playerMove() {
		int[] playerInputs = getPlayerMove();
		myBoard.makeMove(player1Turn,playerInputs[1],playerInputs[0]);
		myDiceCup.moveMade(playerInputs[0]);
	}

	public int[] getPlayerMove() {
		int numSpaces = -1;
		while (!myDiceCup.isLegal(numSpaces)) {
			System.out.println("Input number of spaces to move (from available moves): ");
			numSpaces = keyReader.nextInt();
		}

		System.out.println("Input starting location of piece to move: ");
		int startingLocation = keyReader.nextInt();
		int dest;
		if (player1Turn) dest = startingLocation + numSpaces; else dest = startingLocation - numSpaces;

		while (!myBoard.isLegal(player1Turn, dest) || !myBoard.playerHasPieceAtLocation(player1Turn, startingLocation)) {
			System.out.println("Input starting location of piece to move: ");
			startingLocation = keyReader.nextInt();

			if (player1Turn) dest = startingLocation + numSpaces; else dest = startingLocation - numSpaces;
		} //TODO: come back to this

		return new int[]{numSpaces, startingLocation};
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
