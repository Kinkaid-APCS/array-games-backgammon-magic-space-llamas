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
			if (player1Turn) System.out.println("Player 1 (O) Turn:"); else System.out.println("Player 2 (X) Turn:");

			myDiceCup.roll();
			myDiceCup.calculateAvailableMoves();

			while (!canMakeMove() && myBoard.canBearOff(player1Turn) && myDiceCup.hasMovesLeft()) {
				System.out.println(myBoard);
				System.out.println(myDiceCup.toString());
				System.out.println("You are forced to bear off.");
				myBoard.forceBear(player1Turn);
				myDiceCup.delAvailableMove();
			}

			if (!canMakeMove() && !myBoard.gameIsOver()) {
				System.out.println("You cannot make any possible moves. Your turn has ended. ");
				continue;
			}

			//check for bar pieces
			if (player1Turn) {
				bar = 0;
			} else {
				bar = 25;
			}


			while (myDiceCup.hasMovesLeft()) {
				System.out.println(myBoard);
				System.out.println(myDiceCup.toString());
				while (!canMakeMove() && myBoard.canBearOff(player1Turn) && myDiceCup.hasMovesLeft()) {
					System.out.println("You are forced to bear off.");
					myBoard.forceBear(player1Turn);
					myDiceCup.delAvailableMove();
				}
				if (myDiceCup.hasMovesLeft()) {
					playerMove();
				}
			}
		}

		if (myBoard.gameIsOver()) {
			if (player1Turn) System.out.println("Player 1 wins!"); else System.out.println("Player 2 wins!");
		}
	}

	public boolean canMakeMove() {
		int[] availableMoves = myDiceCup.getAvailableMoves();
		int[] board = myBoard.getPoints();
		if (myBoard.playerHasPieceAtLocation(player1Turn, bar)) {
			for (int j = 0; j < availableMoves.length; j++) {
				if (!(availableMoves[j] == 0)) {
					if (player1Turn) {
						if (myBoard.isLegal(player1Turn, 0 + availableMoves[j])) {
							return true;
						}
					} else {
						if (myBoard.isLegal(player1Turn, 25 - availableMoves[j])) {
							return true;
						}
					}
				}
			}
		}
		else {
			for (int i = 0; i < board.length; i++) {
				if (myBoard.playerHasPieceAtLocation(player1Turn, i)) {
					for (int j = 0; j < availableMoves.length; j++) {
						if (!(availableMoves[j] == 0)) {
							if (player1Turn) {
								if (myBoard.isLegal(player1Turn, i + availableMoves[j])) {
									return true;
								}
							} else {
								if (myBoard.isLegal(player1Turn, i - availableMoves[j])) {
									return true;
								}
							}
						}
					}
				}
			}
		}
		return false;
	}

	public void playerMove() {
		int[] playerInputs = getPlayerMove();
		myBoard.makeMove(player1Turn,playerInputs[1],playerInputs[0]);
		myDiceCup.moveMade(playerInputs[0]);
	}

	public int[] getPlayerMove() {
		int numSpaces = -1;
		if (myBoard.playerHasPieceAtLocation(player1Turn, bar)) {
			System.out.println("You need to make a bar move.");
		}
		while (!myDiceCup.isLegal(numSpaces) || numSpaces == 0) {
			System.out.println("Input number of spaces to move (from available moves): ");
			numSpaces = keyReader.nextInt();
		}

		if (myBoard.playerHasPieceAtLocation(player1Turn, bar)) {
			return new int[]{numSpaces, bar};
		}

		else {
			System.out.println("Input starting location of piece to move: ");
			int startingLocation = keyReader.nextInt();
			int dest;
			if (player1Turn) dest = startingLocation + numSpaces;
			else dest = startingLocation - numSpaces;

			while (!myBoard.isLegal(player1Turn, dest) || !myBoard.playerHasPieceAtLocation(player1Turn, startingLocation)) {
				System.out.println("Input starting location of piece to move: ");
				startingLocation = keyReader.nextInt();

				if (player1Turn) dest = startingLocation + numSpaces;
				else dest = startingLocation - numSpaces;
			}

			return new int[]{numSpaces, startingLocation};
		}
	}
}
