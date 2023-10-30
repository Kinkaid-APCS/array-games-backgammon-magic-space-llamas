import java.util.Random;
/**
 * The dice cup is a class that takes care of rolling two dice, determining whether 
 * doubles were rolled, and keeping track of which moving numbers have been used.
 *
 */
public class DiceCup {
	private int die1, die2;
	private int[] availableMoves; // this will be four numbers, though positions 
								 //     2 and 3 will often be zero.
	
	public DiceCup()
	{
		//--------------------
		// TODO: insert your code here.
		die1 = -1;
		die2 = -1;
		availableMoves = new int[4];
		//--------------------
	}
	/**
	 * calculateAvailableMoves - based on the information stored in die1 and die2,
	 * determines which moves could be made by the player and stores them in an
	 * array. This is pretty straightforward, if you don't roll doubles.
	 * If the dice are doubles, then the player gets 4 iterations of the value of
	 *  the faces on the dice.
	 * 
	 * So if die1 = 5 and die2 = 2, then availableMoves is [5,2,0,0]. (in any order)
	 * If die1 = 3 and die3 = 3, then availableMoves is [3,3,3,3]. 
	 */
	public void calculateAvailableMoves()
	{
		//--------------------
		// TODO: insert your code here.
		if(die1 == die2) {
			for (int i = 0; i < 4; i++) {
				availableMoves[i] = die1;
			}
		}
		else {
			availableMoves[0] = die1;
			availableMoves[1] = die2;
		}
		//--------------------
	}
	/**
	 * pick two numbers for the dice, and copy these to the available moves. In the
	 * case of doubles, there are four instances. For instance, if the
	 * dice are 3 and 5, then availableMoves = [3, 5, 0, 0]. If the dice are 6 and 6, then 
	 * availableMoves = [6, 6, 6, 6].
	 */
	public void roll()
	{
		//--------------------
		// TODO: insert your code here.
		Random rand = new Random();
		die1 = rand.nextInt(1,7);
		die2 = rand.nextInt(1, 7);
		// Hint: (int)(Math.random()*10) gives a random number from 0 to 9, inclusive.
		// Hint: write calculateAvailableMoves() before you write this one.
		//--------------------
	}
	/**
	 * returns a string describing the dice, the available (non-zero) moves and whether this 
	 * was doubles.
	 * For example, if the player just rolled a 2 and a 5 the board might look like:
	 *   +-+ +-+
	 *   |2| |5|
	 *   +-+ +-+
	 *   Available: 2, 5
	 *   (although availableMoves might be [2, 5, 0, 0].)
	 * If the player rolled a pair of threes and has already used one three, the board
	 * might look like:
	 *   +-+ +-+
	 *   |3| |3| doubles
	 *   +-+ +-+
	 *   Available: 3, 3, 3
	 * 	(although availableMoves might be [0, 3, 3, 3].)
	 * @return a string describing the state of the dice and available non-zero moves.
	 */
	public String toString()
	{
		String result = "";
		//--------------------
		// TODO: insert your code here.
		result += "+-+ +-+\n";
		result += "|" + die1 + "|" + " " + "|"+ die2 + "|";
		if (isDoubles()) {
			result += "\tdoubles";
		}
		result += "\n";
		result += "+-+ +-+\n";
		result += "Available: ";
		for (int i = 0; i < availableMoves.length; i++) {
			if (availableMoves[i] != 0) {
				result += String.valueOf(availableMoves[i]) +", ";
			}
		}
		result = result.substring(0, result.length() - 2);
		//--------------------
		return result;
		
	}
	/**
	 * isLegal - given a proposed move, determines whether this number is an option.
	 * @param amountToMove
	 * @return - whether the player can move this amount.
	 */
	public boolean isLegal(int amountToMove)
	{
		boolean legal = false;
		for (int i = 0; i < availableMoves.length; i++) {
			if (availableMoves[i] == amountToMove) {
				legal = true;
			}
		}
		//--------------------
		return legal;
	}
	/**
	 * isDoubles - indicates whether the two dice match.
	 * @return
	 */
	public boolean isDoubles()
	{
		boolean doubles = false;
		//--------------------
		// TODO: insert your code here.
		if (die1 == die2) {
			doubles = true;
		}
		//--------------------
		return doubles;
	}
	
	/**
	 * finds the first instance of the amountToMove in availableMoves and resets
	 * it to zero.
	 * @param amountToMove
	 * precondition: amountToMove is a legal move.
	 */
	public void moveMade(int amountToMove)
	{
		if (isLegal(amountToMove)) {
			for (int i = 0; i < availableMoves.length; i++) {
				if (availableMoves[i] == amountToMove) {
					availableMoves[i] = 0;
					break;
				}
			}
		}
	}

	public void delAvailableMove() {
		for (int i = 0; i < availableMoves.length; i++) {
			if (availableMoves[i] > 0) {
				availableMoves[i] = 0;
				break;
			}
		}
	}
	
	/**
	 * hasMovesLeft - indicates whether there are still non-zero
	 * values in availableMoves.
	 * @return whether moves are still available.
	 */
	public boolean hasMovesLeft()
	{
		boolean hasMoves = false;
		//--------------------
		// TODO: insert your code here.
		for (int i = 0; i < availableMoves.length; i++) {
			if (availableMoves[i] > 0) {
				hasMoves = true;
			}
		}
		//--------------------
		return hasMoves;
	}
	
	//----------------------
	// FOR TESTING & DEBUGGING ONLY
	/**
	 * sets the dice to a and b, and calculates the availableMoves.
	 * @param a
	 * @param b
	 */
	public void debugSetDice(int a, int b)
	{
		die1=a;
		die2=b;
		calculateAvailableMoves();
	}
	public int[] debugGetDice()
	{
		int[] dice = {die1, die2};
		return dice;
	}
	
	public int[] getAvailableMoves()
	{
		return availableMoves;
	}
}
