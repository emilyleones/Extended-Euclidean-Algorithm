/*
 * Hunt the Wumpus
 * Group members: Dillon Jeffers, Kevin Bonno, Emily Leones, Michelle Yung
 * 
 * Class WumpusTests: Tests various methods within the Wumpus and Room classes 
 *
 */

package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import model.Room;
import model.Wumpus;

import org.junit.Test;

public class WumpusTests {

	// Tests getNeighbor method for a Wumpus that spawns in the middle of the gameboard
	@Test
	public void testgetNeighborsNoWrap() {
		Wumpus game = new Wumpus();
		for (int i = 0; i < game.getMAXROWS(); i++) {
			for (int j = 0; j < game.getMAXCOLS(); j++) {
				game.getBoard()[i][j] = new Room();
			}
		}
		game.getNeighbors(4, 4, "B");
		assertEquals("B", game.getBoard()[3][4].getDanger());
		assertEquals("B", game.getBoard()[4][3].getDanger());
		assertEquals("B", game.getBoard()[4][5].getDanger());
		assertEquals("B", game.getBoard()[5][4].getDanger());
	}

	// Tests getNeighbor method for a Wumpus that spawns in the top left corner
	// of the gameboard for wraparound
	@Test
	public void testgetNeighborsWrap() {
		Wumpus game = new Wumpus();
		for (int i = 0; i < game.getMAXROWS(); i++) {
			for (int j = 0; j < game.getMAXCOLS(); j++) {
				game.getBoard()[i][j] = new Room();
			}
		}
		game.getNeighbors(0, 0, "B");
		assertEquals("B", game.getBoard()[0][9].getDanger());
		assertEquals("B", game.getBoard()[1][0].getDanger());
		assertEquals("B", game.getBoard()[0][1].getDanger());
		assertEquals("B", game.getBoard()[9][0].getDanger());
	}

	// Tests getNeighbor method for a Wumpus that spawns near the bottom right
	// corner of the gameboard
	@Test
	public void testgetNeighborsCorner() {
		Wumpus game = new Wumpus();
		for (int i = 0; i < game.getMAXROWS(); i++) {
			for (int j = 0; j < game.getMAXCOLS(); j++) {
				game.getBoard()[i][j] = new Room();
			}
		}
		game.getNeighbors(6, 7, "B");
		assertEquals("B", game.getBoard()[6][6].getDanger());
		assertEquals("B", game.getBoard()[7][7].getDanger());
		assertEquals("B", game.getBoard()[6][8].getDanger());
		assertEquals("B", game.getBoard()[5][7].getDanger());
	}

	/* 
	 * @author: Dillon Methods: testMoveHunter() 
	 * chooseDirectionFromInt(int randomNumber)
	 */
	@Test
	public void testMoveHunter() {
		Wumpus W = new Wumpus();
		Random randomgenerator = new Random();
		Room[][] R = W.getBoard();

		for (int i = 0; i < 10000; i++) { // run the test ten thousand times, just because
			int hunterX = W.getHunterROW(); // get the hunter's current row space
			int hunterY = W.getHunterCOL(); // get the hunter's current col space

			assertTrue(R[hunterX][hunterY].getHunter()); // assert that the hunter is in his spot

			int randomNumber = randomgenerator.nextInt(4); // generate a random number to choose a random direction
			String direction = chooseDirectionFromInt(randomNumber); // get a random direction from the local method
			W.moveHunter(direction); // move the hunter in the direction
			// assert that the new current location is not the same as the old
			assertFalse(R[hunterX][hunterY].getHunter());
			assertTrue(R[hunterX][hunterY].getVisibility()); // assert that the room is now visible
		}

		int roomsVisible = 0;
		int rooms = 0;
		for (int r = 0; r < W.getMAXROWS(); r++) {
			for (int c = 0; c < W.getMAXCOLS(); c++) {
				rooms++;
				if (R[r][c].getVisibility() == true) {
					roomsVisible++;
				}
			}
		}
		assertEquals(rooms, roomsVisible); // assert that we have visited all of the rooms
	}

	// Dillon uses this method for testMoveHunter() ^
	private String chooseDirectionFromInt(int pchoice) {
		String direction;
		switch (pchoice) { // randomizes the direction for the hunter
		case 0:
			direction = "w";
			break;
		case 1:
			direction = "a";
			break;
		case 2:
			direction = "s";
			break;
		case 3:
			direction = "d";
			break;
		default:
			direction = null;
			break;
		}

		switch (pchoice) { // this is to randomize the case of the inputed direction
		case 0:
			direction.toUpperCase();
			break;
		case 3:
			direction.toUpperCase();
			break;
		default:
			break;
		}

		return direction;
	}

	// end of Dillon

}
