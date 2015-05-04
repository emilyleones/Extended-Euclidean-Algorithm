/*
 * Hunt the Wumpus
 * Group members: Dillon Jeffers, Kevin Bonno, Emily Leones, Michelle Yung
 * 
 * Class HuntTheWumpus: Main method for the console game. Play Hunt The Wumpus from this class.  
 *
 */

package game;

import java.util.Scanner;

import model.Wumpus;

public class HuntTheWumpus {
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		String choice = "";

		do {
			// begin new game
			Wumpus newGame = new Wumpus();
			choice = "";
			newGame.setGameOver(false);
			newGame.setHasWon(false);

			newGame.displayMenu();

			while (!newGame.getGameOver()) {
				System.out.println(newGame.toString());
				choice = keyboard.nextLine().toLowerCase();

				// Interpret user input
				if ((choice.equals("w")) || (choice.equals("a")) || (choice.equals("s")) || (choice.equals("d"))) {
					// move hunter according to input
					newGame.moveHunter(choice);
				} else if (choice.equals("f")) { // fire arrow
					System.out.println("Please enter a direction to fire.");
					String direction = keyboard.nextLine().toLowerCase();
					newGame.fireArrow(direction);
				} else if (choice.equals("q")) { // quit program
					newGame.setGameOver(true);
				} else if (choice.equals("n")) { // start new game
					break;
				} else {
					System.out.println("Invalid input. No move was made. Please enter W, A, S, D, F, N, or Q.");
				}
			} // end of one game

			if ((!choice.equals("n")) && (!choice.equals("q"))) {
				if (newGame.getHasWon()) {
					System.out.println("\nYou have killed the Wumpus! You win!");
					System.out.println(newGame.showMap());
				} else {
					System.out.println("You lose... :( GG.");
					System.out.println(newGame.showMap());
				}
				
				System.out.println("Press 'N' to play again, or 'Q' to quit program...  ");
				choice = keyboard.nextLine().toLowerCase();
			}

		} while (!choice.equals("q")); // end of whole program
		System.out.println("Farewell, Hunter!");
		keyboard.close();
	} // end of main

}
