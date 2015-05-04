/*
 * Hunt the Wumpus
 * Group members: Dillon Jeffers, Kevin Bonno, Emily Leones, Michelle Yung
 * 
 * Class Wumpus: Models a Hunt the Wumpus game.  
 */

package model;

import java.util.Random;
import java.util.Scanner;

public class Wumpus {

	private boolean gameOver;
	private boolean hasWon;
	private int hunterROW, hunterCOL;
	private Room[][] board;

	private final int MAXROWS = 10;
	private final int MAXCOLS = 10;

	public Wumpus() { // Wumpus Constructor

		gameOver = false;
		hasWon = false;
		board = new Room[MAXROWS][MAXCOLS];
		Random rand = new Random();

		for (int i = 0; i < MAXROWS; i++) {
			for (int j = 0; j < MAXCOLS; j++) {
				board[i][j] = new Room();
			}
		}

		int wumpusRow = rand.nextInt(MAXROWS);
		int wumpusCol = rand.nextInt(MAXCOLS);

		board[wumpusRow][wumpusCol].setDanger("W");
		getNeighbors(wumpusRow, wumpusCol, "B");

		int slimePitRow;
		int slimePitCol;

		do {
			slimePitRow = rand.nextInt(MAXROWS);
			slimePitCol = rand.nextInt(MAXCOLS);
		} while (slimePitRow == wumpusRow && slimePitCol == wumpusCol);

		board[slimePitRow][slimePitCol].setDanger("P");
		getNeighbors(slimePitRow, slimePitCol, "S");

		do {
			hunterROW = rand.nextInt(MAXROWS);
			hunterCOL = rand.nextInt(MAXCOLS);
		} while (!board[hunterROW][hunterCOL].getDanger().equals(" "));

		board[hunterROW][hunterCOL].setHunter(true);
		board[hunterROW][hunterCOL].setVisibility(true);
	}

	// Populate rooms adjacent to the Wumpus or Slime Pit with blood or slime
	public void getNeighbors(int row, int col, String danger) {
		if (board[wrapIndexRow(row + 1)][(col)].getDanger() == "B") {
			board[wrapIndexRow(row + 1)][(col)].setDanger("G");
		} else if (!board[wrapIndexRow(row + 1)][col].getDanger().equals("W")) {
			board[wrapIndexRow(row + 1)][(col)].setDanger(danger);
		}

		if (board[wrapIndexRow(row - 1)][(col)].getDanger() == "B") {
			board[wrapIndexRow(row - 1)][(col)].setDanger("G");
		} else if (!board[wrapIndexRow(row - 1)][col].getDanger().equals("W")) {
			board[wrapIndexRow(row - 1)][(col)].setDanger(danger);
		}

		if (board[(row)][wrapIndexCol(col + 1)].getDanger() == "B") {
			board[(row)][wrapIndexCol(col + 1)].setDanger("G");
		} else if (!board[row][wrapIndexCol(col + 1)].getDanger().equals("W")) {
			board[(row)][wrapIndexCol(col + 1)].setDanger(danger);
		}

		if (board[(row)][wrapIndexCol(col - 1)].getDanger() == "B") {
			board[(row)][wrapIndexCol(col - 1)].setDanger("G");
		} else if (!board[row][wrapIndexCol(col - 1)].getDanger().equals("W")) {
			board[(row)][wrapIndexCol(col - 1)].setDanger(danger);
		}

	}

	// Firing arrow
	public void fireArrow(String direction) {

		if (direction.equals("w")) {
			fireArrowUp();
		} else if (direction.equals("a")) {
			fireArrowLeft();
		} else if (direction.equals("s")) {
			fireArrowDown();
		} else if (direction.equals("d")) {
			fireArrowRight();
		} else {
			System.out.println("Invalid input. Please enter a direction to fire (W, A, S, or D)");
			Scanner keyboard = new Scanner(System.in);
			String choice = keyboard.nextLine();
			fireArrow(choice);
			return;
		}
		// Added if statement
		if (!hasWon) {
			System.out.println("You missed!\nThe sound alerts the Wumpus and you have died..");
		}
		gameOver = true;
	}

	private void fireArrowRight() {
		for (int j = hunterCOL + 1; j < MAXCOLS; j++) {
			if (board[hunterROW][j].getDanger().equals("W")) {
				hasWon = true;
				break;
			}
		}
	}

	private void fireArrowDown() {
		for (int i = hunterROW + 1; i < MAXROWS; i++) {
			if (board[i][hunterCOL].getDanger().equals("W")) {
				hasWon = true;
				break;
			}
		}
	}

	public void fireArrowLeft() {
		for (int j = hunterCOL - 1; j >= 0; j--) {
			if (board[hunterROW][j].getDanger().equals("W")) {
				hasWon = true;
				break;
			}
		}
	}

	public void fireArrowUp() {
		for (int i = hunterROW - 1; i >= 0; i--) {
			if (board[i][hunterCOL].getDanger().equals("W")) {
				hasWon = true;
				break;
			}
		}
	}

	public void displayMenu() {
		System.out.println("\n\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("Welcome to Hunt the Wumpus");
		System.out.println("Find and kill the Wumpus.");
		System.out.println("------------------------------------------");
		System.out.println("Press 'W' to move up.");
		System.out.println("Press 'A' to move left.");
		System.out.println("Press 'S' to move down.");
		System.out.println("Press 'D' to move right.");
		System.out
				.println("Press 'F' to initiate fire sequence, and the desired direction (W, A, S, or D) to fire.");
		System.out.println("Press 'N' to start a new game.");
		System.out.println("Press 'Q' to end game.");
		System.out.println("------------------------------------------");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	}

	// Method that accounts for wraparound for row index
	private int wrapIndexRow(int i) {
		return (i + MAXROWS) % MAXROWS;
	}

	// Method that accounts for wraparound for column index
	private int wrapIndexCol(int j) {
		return (j + MAXCOLS) % MAXCOLS;
	}

	// Convert gameboard to a string for console view
	public String toString() {
		String map = "";

		for (int i = 0; i < MAXROWS; i++) {
			for (int j = 0; j < MAXCOLS; j++) {
				if (board[i][j].getHunter()) {
					map += "[O] ";
				} else if (board[i][j].getVisibility()) {
					map += "[" + board[i][j].getDanger() + "] ";
				} else {
					map += "[X] ";
				}
			}
			map += "\n";
		}
		return map;
	}

	// Method that reveals map, called when gameOver is true
	public String showMap() {

		for (int i = 0; i < MAXROWS; i++) {
			for (int j = 0; j < MAXCOLS; j++) {
				board[i][j].setHunter(false);
				board[i][j].setVisibility(true);
			}
		}
		return toString();
	}

	// Method that accounts for Hunter's movement based on user's input
	public void moveHunter(String pchoice) {
		board[hunterROW][hunterCOL].setHunter(false);

		if (pchoice.equals("w")) { // moves up
			hunterROW = wrapIndexRow(hunterROW - 1);
		} else if (pchoice.equals("a")) { // moves left
			hunterCOL = wrapIndexCol(hunterCOL - 1);
		} else if (pchoice.equals("s")) { // moves down
			hunterROW = wrapIndexRow(hunterROW + 1);
		} else { // moves right
			hunterCOL = wrapIndexCol(hunterCOL + 1);
		}

		// move Hunter
		Room newRoom = board[hunterROW][hunterCOL];
		newRoom.setHunter(true);
		newRoom.setVisibility(true);

		if (newRoom.getDanger().equals("W")) {
			gameOver = true;
			System.out.println("You have stumbled into the Wumpus' lair!");
		} else if (newRoom.getDanger().equals("P")) {
			gameOver = true;
			System.out
					.println("You have fallen into a pit full of slime..gross.. #sorrynotsorry");
		} else if (newRoom.getDanger().equals("B")) {
			System.out.println("The room is covered in blood!");
		} else if (newRoom.getDanger().equals("S")) {
			System.out.println("The room is covered in slime!");
		} else if (newRoom.getDanger().equals("G")) {
			System.out.println("The room is covered in slime and blood!");
		} else {
			System.out.println("Nothing notable about this room..");
		}

	}

	/*
	 * The following methods are for the console view in HuntTheWumpus class
	 */

	public void setGameOver(boolean status) {
		gameOver = status;
	}

	public void setHasWon(boolean status) {
		hasWon = status;
	}

	public boolean getGameOver() {
		return gameOver;
	}

	public boolean getHasWon() {
		return hasWon;
	}

	/*
	 * The following methods are purely for testing purposes
	 */

	public int getHunterROW() {
		return hunterROW;
	}

	public int getHunterCOL() {
		return hunterCOL;
	}

	public Room[][] getBoard() {
		return board;
	}

	public int getMAXROWS() {
		return MAXROWS;
	}

	public int getMAXCOLS() {
		return MAXCOLS;
	}

}