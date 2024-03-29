
public class BackgammonRunner {

	public static void main(String[] args) 
	{
		boolean testingMode = false;
		Board board = new Board();
		DiceCup dicecup = new DiceCup();
		if (testingMode)
		{
			System.out.println("Start tests");
			// enter test code here.
			dicecup.roll();
			dicecup.calculateAvailableMoves();
			System.out.println(dicecup.toString());
			System.out.println(dicecup.hasMovesLeft());
			System.out.println(board.toString());
			System.out.println("End tests");
		}
		else
		{
			Referee ref = new Referee();
			System.out.println("Start game");
			ref.playGame();
			System.out.println("End game");
		}
	}

}
