/**
 * The board class keeps track of 24 spaces (the skinny triangles on 
 * a regular board) and the bar for each player. It keeps track of 
 * these values and determines whether a given move is legal or not.
 */
public class Board {

	// TODO: decide which private member variables the Board class needs, and declare them here.
	// suggestion - for the 24 spaces, I suggest an array that holds the number
	// of pieces on each square. One player will have positive numbers, and
	// the other will have negative numbers. So if space (5) contains 3, that
	// means that there are 3 white pieces trying to move to higher numbered
	// spaces, and if space (7) contains -2, that means that there are 2 black
	// pieces trying to move to lower numbered spaces. 
	private int[] points;

	// Locations 0 and 25 are the bars (penalty zones) for the two teams - if 
	// the "negative" team is trying to move its pieces to smaller numbers,
	// then moving them to 0 or less actually removes them from the board - they
	// don't go to position 0. On the other hand if a "positive-moving" piece is
	// captured, it goes to position 0, the farthest point from it's goal of 25 or
	// more.
	
	/**
	 * constructor - set up the starting locations of the pieces.
	 */
	public Board()
	{
		//--------------------
		// TODO: insert your code here.
		points = new int[26];
		points[1] = 2;
		points[6] = -5;
		points[8] = -3;
		points[12] = 5;
		points[13] = -5;
		points[17] = 3;
		points[19] = 5;
		points[24] = -2;
		
		//--------------------
	}
	
	/**
	 * toString - create a string representing the state of the board.
	 * @return a string containing the board state.
	 * for example, it might look like:
	 * 0 (BAR) O
	 * 1
	 * 2 OO
	 * 3 OOO
	 * 4 XX
	 * 5 
	 * 6 XXXXX
	 * ....
	 * 23 O
	 * 24 XX
	 * 25 (BAR) XX
	 */
	public String toString()
	{
		String result = "";

		for (int i = 0; i < 26; i++) //points is only 26 long
		{
			if (i == 0 || i == 25) {
				result = result + i + " (BAR) " + piecesToString(points[i]);
            } else {
				if (i >= 10) {
					result = result + i + " " + piecesToString(points[i]);
				}
				else {
					result = result + i + "  " + piecesToString(points[i]);
				}
            }
            result += "\n";
        }

		return result;

	}

	public String piecesToString(int pieces)
	{
		String result = "";

		for (int n = 0; n < Math.abs(pieces); n++) {
			if (pieces < 0) {
				result = result + "X";
			} else {
				result = result + "O";
			}
		}

		return result;
	}
	
	/**
	 * playerHasPieceAtLocation - determines whether the player has at 
	 * least one chip at the given space.
	 * @param playerTurn - who is playing this turn
	 * @param location - the number of the space in question.
	 * @return whether (true/false) the player has a chip of his/her own 
	 * color at this space.
	 */
	public boolean playerHasPieceAtLocation(boolean playerTurn, int location)
	{
		return (playerTurn && (points[location] > 0)) || (!(playerTurn) && (points[location] < 0));
	}
	
	/**
	 * isLegal - determines whether a chip at the given space can move
	 * the desired number of spaces
	 * @param - startingSpace
	 * @param - numSpacesToMove (this is a positive number, but might be 
	 * a move up or down, depending on what chip is in the starting space)
	 * @return whether (true/false) the player is allowed to make such a move.
	 * precondition: yes, there's at least one chip in the space.
	 * postcondition: the board is unchanged.
	 */
	public boolean isLegal(boolean playerTurn, int location)
	{
		boolean legal = false;
		if ((location <= 24) && (location >= 1)) {
			if ((playerHasPieceAtLocation(playerTurn, location)) || (playerHasPieceAtLocation(!playerTurn, location) && Math.abs(points[location]) <= 1) || (points[location] == 0))  {
				legal = true;
			}
		}

		return legal;
		
	}

	public int[] getPoints() {
		return points;
	}
	
	/**
	 * makeMove - moves one chip from the given space by the specified amount;
	 * @param - startingSpace
	 * @param - numSpacesToMove (this is a positive number, but might be 
	 * a move up or down, depending on what chip is in the starting space)
	 * precondition: there is a chip at the starting space
	 * postcondition: the chip may be moved to a different space, or off the board.
	 * If the chip lands on a single enemy piece, that piece is sent to its bar.
	 */
	public void makeMove(boolean playerTurn, int startingSpace, int numSpaces)
	{
		if (points[startingSpace] != 0) {
			int location;
			if (playerTurn) location = startingSpace + numSpaces; else location = startingSpace - numSpaces;

			if (isLegal(playerTurn, location)) {
				if (playerHasPieceAtLocation(!playerTurn, location)) {
					if (playerTurn) points[25] += 1; else points[0] += 1;
					points[location] = 0;
				}
				if (playerTurn) points[location] += 1; else points[location] -= 1;
				if (playerTurn) points[startingSpace] -= 1; else points[startingSpace] += 1;
			}
		}
	}
	
	
	/**
	 * gameIsOver - determines whether either player has removed all 
	 * his/her pieces from the board.
	 * @return - whether (true/false) the game is over.
	 */
	public boolean gameIsOver()
	{
		boolean gameOver = false;
		boolean player1GameOver = true;
		boolean player2GameOver = true;
		for (int i = 0; i < points.length; i++) {
			if (points[i] > 0) {
				player1GameOver = false;
				break;
			}
		}
		for (int i = 0; i < points.length; i++) {
			if (points[i] < 0) {
				player2GameOver = false;
				break;
			}
		}
		if (player1GameOver || player2GameOver) {
			gameOver = true;
		}

		return gameOver;
	}
}
