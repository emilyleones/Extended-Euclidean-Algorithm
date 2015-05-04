/*
 * Hunt the Wumpus
 * Group members: Dillon Jeffers, Kevin Bonno, Emily Leones, Michelle Yung
 * 
 * Class Room: Models a room in the Wumpus gameboard.  
 * Instance variables: 
 * 		visible - boolean value indicating whether room has been visited by the hunter and is visible on the console
 * 		danger - String representing attributes of the room, if any (blood(B), slime(S), wumpus(W), slime pit(P), empty(" ")) 
 * 		hasHunter - boolean value that indicates whether the hunter is in the room
 */

package model;

public class Room {

	private boolean visible;
	private String danger;
	private boolean hasHunter;

	// Constructor for the Room object. Sets the danger to a blank space,
	// meaning the room is empty.
	public Room() {
		danger = " ";
	}

	// Gets the string representing what is in the room
	public String getDanger() {
		return danger;
	}

	// Sets the danger to the parameter string
	public void setDanger(String d) {
		danger = d;
	}

	// Returns whether the room has been visited, meaning is is now visible
	public boolean getVisibility() {
		return visible;
	}

	// Sets visible to boolean parameter
	public void setVisibility(boolean v) {
		visible = v;
	}

	// Sets hasHunterto boolean parameter
	public void setHunter(boolean phunter) {
		hasHunter = phunter;
	}

	// Returns boolean of whether hunter is in the room
	public boolean getHunter() {
		return hasHunter;
	}
}
