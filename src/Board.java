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
//		points[1] = 2;
		points[6] = -5;
		points[8] = -3;
//		points[12] = 5;
		points[13] = -5;
//		points[17] = 3;
//		points[19] = 5;
		points[24] = -2;

		points[12] = -103;
		points[19] = 55;
		points[17] = 64;
		points[1] = 15;
//
//
//		points[1] = -3;
//		points[2] = -3;
//		points[3] = -3;
//		points[4] = -3;
//		points[5] = -2;
//		points[6] = -1;
//
//		points[19] = 0;
//		points[20] = 0;
//		points[21] = 0;
//		points[22] = 0;
//		points[23] = 0;
//		points[24] = 5;
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

		for (int i = 0; i < 19; i++) {
			result += "\n";
			switch (i) {
				case 0 -> result += "  1 1 1 1 1 1   1 2 2 2 2 2  ";
				case 1 -> result += "  3 4 5 6 7 8   9 0 1 2 3 4  ";
				case 17 -> result += "  1 1 1                      ";
				case 18 -> result += "  2 1 0 9 8 7   6 5 4 3 2 1  ";
			}
			for (int j = 0; j < 15; j++) {
				switch (i) {
					case 2 -> result += "__";
					case 16 -> result += "‾‾";
					case 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 -> {
						if (i >= 3 && i < 9 && j >= 1 && j < 7 && Math.abs(points[j - 1 + 13]) > i - 3) { // yes some conditions below are redundant such as i >= 3 but help to illustrate which quadrant we're working in
							int tP = Math.abs(points[j - 1 + 13]);
							if (i == 3 && tP > 6 && tP < 15) result += tP - 5 + " ";
							else if (i == 4 && tP > 14 && tP < 104) result += tP % 10 + " ";
							else if (i == 3 && tP > 14 && tP < 104) result += ((int) Math.floor((tP - 4) / 10)) + " ";
							else if (tP > 0) result += "O ";
							else result += "X ";
						}
						else if (i >= 3 && i < 9 && j >= 8 && j < 14 && Math.abs(points[j - 8 + 19]) > i - 3) {
							int tP = Math.abs(points[j - 8 + 19]);
							if (i == 3 && tP > 6 && points[j - 8 + 19] < 15) result += Math.abs((points[j - 8 + 19] - 5)) + " ";
							else if (i == 4 && points[j - 8 + 19] > 14 && points[j - 8 + 19] < 104) result += (Math.abs(points[j - 8 + 19] - 4) % 10) + " ";
							else if (i == 3 && points[j - 8 + 19] > 14 && points[j - 8 + 19] < 104) result += ((int) Math.floor(Math.abs(points[j - 8 + 19] - 4) / 10)) + " ";
							else if (points[j - 8 + 19] > 0) result += "O ";
							else result += "X ";
						}
						else if (i >= 10 && i < 16 && j >= 8 && j < 14 && Math.abs(points[6 - (j - 8)]) > (5 - i) + 10) {
							int tP = Math.abs(points[6 - (j - 8)]); //thisPoint
							if (i == 15 && tP > 6 && tP < 15) result += (tP - 5) + " ";
							else if (i == 15 && tP > 14 && tP < 104) result += ((tP - 4) % 10) + " ";
							else if (i == 14 && tP > 14 && tP < 104) result += ((int) (Math.floor((tP - 4) / 10))) + " ";
							else if (points[6 - (j - 8)] > 0) result += "O ";
							else result += "X ";
						}
						else if (i >= 10 && i < 16 && j >= 1 && j < 7 && Math.abs(points[12 - (j - 1)]) > (5 - i) + 10) {
							int tP = Math.abs(points[12 - (j - 1)]);
							if (i == 15 && tP > 6 && tP < 15) result += (tP - 5) + " ";
							else if (i == 15 && tP > 14 && tP < 104) result += ((tP - 4) % 10) + " ";
							else if (i == 14 && tP > 14 && tP < 104) result += ((int) (Math.floor((tP - 4) / 10))) + " ";
							else if (tP > 0) result += "O ";
							else result += "X ";
						}
						else if (j == 0 ) {
							result += "| ";
						}
						else if (j == 14) {
							result += " |";
						}
						else if (j == 7) {
							result += "| ";
						}
						else if (i == 9) {
							result += "--";
						}
						else {
							result += "  ";
						}
					}
				}
			}
		}

//
//		for (int i = 0; i < 26; i++) //points is only 26 long
//		{
//			if (i == 0 || i == 25) {
//				result = result + i + " (BAR) " + piecesToString(points[i]);
//            } else {
//				if (i >= 10) {
//					result = result + i + " " + piecesToString(points[i]);
//				}
//				else {
//					result = result + i + "  " + piecesToString(points[i]);
//				}
//            }
//            result += "\n";
//        }

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
		if (((location <= 24) && (location >= 1)) || (canBearOff(playerTurn) && (location == 25 || location == 0))) {
			if ((playerHasPieceAtLocation(playerTurn, location)) || (playerHasPieceAtLocation(!playerTurn, location) && Math.abs(points[location]) <= 1) || (points[location] == 0))  {
				legal = true;
			}
		}

		return legal;
		
	}

	public boolean canBearOff(boolean player1Turn) {
		if (player1Turn) {
			for (int i = 0; i <= 18; i++) {
				if (points[i] > 0) {
					return false;
				}
			}
		}
		else {
			for (int i = 25; i >= 7; i--) {
				if (points[i] < 0) {
					return false;
				}
			}
		}
		return true;
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

			if (canBearOff(playerTurn) && (location == 25 || location == 0)) {
				if (playerTurn) {
					points[startingSpace] -= 1;
				}
				else {
					points[startingSpace] += 1;
				}
			}
			else if (isLegal(playerTurn, location)) {
				if (playerHasPieceAtLocation(!playerTurn, location)) {
					if (playerTurn) points[25] += 1; else points[0] += 1;
					points[location] = 0;
				}
				if (playerTurn) points[location] += 1; else points[location] -= 1;
				if (playerTurn) points[startingSpace] -= 1; else points[startingSpace] += 1;
			}
		}
	}

	public void forceBear(boolean playerTurn) {
		if (playerTurn) {
			for (int p = 19; p < 25; p++) {
				if (playerHasPieceAtLocation(playerTurn, p)) {
					points[p] -= 1;
					break;
				}
			}
		}
		else {
			for (int p = 6; p > 0; p--) {
				if (playerHasPieceAtLocation(playerTurn, p)) {
					points[p] += 1;
					break;
				}
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
