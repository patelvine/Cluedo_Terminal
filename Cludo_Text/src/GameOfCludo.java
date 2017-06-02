import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class GameOfCludo {
	//sets up the board
	private Board board = new Board(); 

	public Board getBoard() {
		return board;
	}

	/** method to move a player in any direction (makes code shorter) **/
	public void move(int x, int y, Player player, Location loc, String[][] map) {
		String hold = map[loc.getY() + y][loc.getX() + x]; // holds the value currently in the spot to be moved
		map[loc.getY() + y][loc.getX() + x] = map[loc.getY()][loc.getX()];  // moves the player
		map[loc.getY()][loc.getX()] = loc.getOn(); // replaces the value the player was previously on
		player.getLocation().setLocation(loc.getX() + x, loc.getY() + y, hold); // sets the new value the player is on
	}

	/** method to find the players direction and move them according to that direction **/
	public void movePlayer(Player player, int roll) {
		String dir = null;
		boolean moved = false;
		// while the player can move
		while (roll != 0) {
			// while moved is false keep asking for a move (each move direction method sets moved. e.g. moved = moveUp(player))
			while (moved == false) {
				dir = input(); // direction input from player
				if (dir.equals("w") || dir.equals("W")) { // up
					moved = moveUp(player);
				} else if (dir.equals("a") || dir.equals("A")) { // left
					moved = moveLeft(player);
				} else if (dir.equals("s") || dir.equals("S")) { // down
					moved = moveDown(player);
				} else if (dir.equals("d") || dir.equals("D")) { // right
					moved = moveRight(player);
				} else { // if player enter incorrect input
					System.out
							.println("Wrong key entered. ---- w = UP, a = LEFT, s = DOWN, d = RIGHT");
					roll++;
				}
				 // if player enters a room then return
				if (player.getLocation().getName() != null && moved == true) {
					board.draw();
					return;
				}
			}
			moved = false;
			roll--; // take away a move
			board.draw();
		}
	}

	/** moves the player to the right by one**/
	public boolean moveRight(Player player) {
		Location loc = player.getLocation();
		String[][] map = board.getMap();
		String nextChar = map[loc.getY()][loc.getX() + 1]; // stores the value that is in the square to be moved

		if (loc.getX() != 23) { // if X is not out of bounds
			if (nextChar != "#") { // # represents a wall 
				// if player is in room
				if (Character.isLetter(loc.getOn().charAt(0))) { // if character is in a room and on the door
					if (nextChar == "=") { // + represents the way a player must exit or enter a room
						move(1, 0, player, loc, map); 
						player.getLocation().setName(null); // player moved out of the room
						return true;
					}
					// if the next square is a door (entering a room)
				} else if (Character.isLetter(nextChar.charAt(0))){ // is next value in map is a door
					if (loc.getOn() == "=") { // player must be on a = to enter any room
						player.getLocation().setName(
								curRoom(map[loc.getY()][loc.getX() + 1])); // sets name of room player is in
						map[loc.getY()][loc.getX()] = player.getLocation()
								.getOn(); // replaces the = the player was on 
						return true;
					}
				} else if (nextChar == "-" || nextChar == "=") { // if next value is - or = (free to move squares) then can move
					move(1, 0, player, loc, map);
					return true;
				}
			}
		}

		return false;
	}

	/** moves the player to the right by one**/       //NOTE ALL COMMECTS FOR THIS METHOD ARE IN moveRight method
	public boolean moveDown(Player player) {
		Location loc = player.getLocation();
		String[][] map = board.getMap();
		String nextChar = map[loc.getY() + 1][loc.getX()];

		if (loc.getX() != 28) {
			if (nextChar != "#") {
				if (Character.isLetter(loc.getOn().charAt(0))) {
					if (nextChar == "=") {
						move(0, 1, player, loc, map);
						player.getLocation().setName(null);
						return true;
					}
				} else if (Character.isLetter(nextChar.charAt(0))) {
					if (loc.getOn() == "=") {
						player.getLocation().setName(
								curRoom(map[loc.getY() + 1][loc.getX()]));
						map[loc.getY()][loc.getX()] = player.getLocation()
								.getOn();
						return true;
					}
				} else if (nextChar == "-" || nextChar == "=") {
					move(0, 1, player, loc, map);
					return true;
				}
			}
		}
		return false;
	}

	/** moves the player to the right by one**/
	public boolean moveLeft(Player player) {       //NOTE ALL COMMECTS FOR THIS METHOD ARE IN moveRight method
		Location loc = player.getLocation();
		String[][] map = board.getMap();
		String nextChar = map[loc.getY()][loc.getX() - 1];

		if (loc.getX() != 0) {
			if (nextChar != "#") {
				if (Character.isLetter(loc.getOn().charAt(0))) {
					if (nextChar == "=") {
						move(-1, 0, player, loc, map);
						player.getLocation().setName(null);
						return true;
					}
				} else if (Character.isLetter(nextChar.charAt(0))) {
					if (loc.getOn() == "=") {
						player.getLocation().setName(
								curRoom(map[loc.getY()][loc.getX() - 1]));
						map[loc.getY()][loc.getX()] = player.getLocation()
								.getOn();
						return true;
					}
				} else if (nextChar == "-" || nextChar == "=") {
					move(-1, 0, player, loc, map);
					return true;
				}
			}
		}
		return false;
	}

	/** moves the player to the right by one**/
	public boolean moveUp(Player player) {       //NOTE ALL COMMECTS FOR THIS METHOD ARE IN moveRight method
		Location loc = player.getLocation();
		String[][] map = board.getMap();
		String nextChar = map[loc.getY() - 1][loc.getX()];

		if (loc.getX() != 0) {
			if (nextChar != "#") {
				if (Character.isLetter(loc.getOn().charAt(0))) {
					if (nextChar == "=") {
						move(0, -1, player, loc, map);
						player.getLocation().setName(null);
						return true;
					}
				} else if (Character.isLetter(nextChar.charAt(0))) {
					if (loc.getOn() == "=") {
						player.getLocation().setName(
								curRoom(map[loc.getY() - 1][loc.getX()]));
						map[loc.getY()][loc.getX()] = player.getLocation()
								.getOn();
						return true;
					}
				} else if (nextChar == "-" || nextChar == "=") {
					move(0, -1, player, loc, map);
					return true;
				}
			}
		}
		return false;
	}

	/** finds room that corresponds to the door the player is about to enter **/
	private static String curRoom(String on) {
		String room = null;
		if (on == "S") {
			room = "Spa";
		}
		if (on == "T") {
			room = "Theatre";
		}
		if (on == "L") {
			room = "Living Room";
		}
		if (on == "O") {
			room = "Observatory";
		}
		if (on == "A") {
			room = "Patio";
		}
		if (on == "P") {
			room = "Swimming Pool";
		}
		if (on == "H") {
			room = "Hall";
		}
		if (on == "G") {
			room = "Guest House";
		}
		if (on == "D") {
			room = "Dinning Room";
		}
		if (on == "K") {
			room = "Kitchen";
		}
		return room;
	}

	/** when player makes a guess this method checks if the cards exist **/
	public boolean checkCardExists(String weapon, String suspect, String room,
			GameOfCludo game) {
		boolean s = false;
		boolean w = false;
		boolean r = false;

		/** checks if the person use selected is a valid card **/
		for (Card c : game.getBoard().getPersons()) {
			if (c.getName().equals(suspect)) {
				s = true;
				break;
			}
		}
		
		/** checks if the weapon use selected is a valid card **/
		for (Card c : game.getBoard().getWeapons()) {
			if (c.getName().equals(weapon)) {
				w = true;
				break;
			}
		}
		
		/** checks if the room use selected is a valid card **/
		for (Card c : game.getBoard().getRooms()) {
			if (c.getName().equals(room)) {
				r = true;
				break;
			}
		}

		/** if all cards exist the return true. else return false **/
		if (s == true && w == true && r == true) {
			return true;
		} else {
			return false;
		}
	}

	/** deals out the cards to each player randomly **/
	public ArrayList<Player> dealCards(GameOfCludo game,
			ArrayList<Player> players) {
		ArrayList<Player> temp = players;

		Random random = new Random();
		while (!game.getBoard().getCards().isEmpty()) { // while cards not empty
			for (Player p : temp) { // for each player
				if (!game.getBoard().getCards().isEmpty()) { // if cards not empty
					ArrayList<Card> listOfCards = game.getBoard().getCards(); // gets remaining cards list
					int pileSize = listOfCards.size();
					int r = random.nextInt(pileSize); // creates a new random int between 0 - cards list size
					p.addCard(listOfCards.get(r)); // adds the random card to the player
					game.getBoard().removeCard(listOfCards.get(r)); // removes the added card from list
				} else {
					break; // if cards is empty the end
				}
			}
		}
		return temp;
	}
	/** sets the secert envelope at the start **/
	public void setSecertEnvelope(GameOfCludo game) {
		ArrayList<Card> temp = new ArrayList<Card>();

		boolean weapon = false;
		boolean room = false;
		boolean person = false;

		Random random = new Random();
		while (weapon == false || room == false || person == false) {
			ArrayList<Card> listOfCards = game.getBoard().getCards();
			int pileSize = listOfCards.size();
			int r = random.nextInt(pileSize); // random int used as index for card array
			// gets the room
			if (listOfCards.get(r).getType() == "room" && room == false) { // if card at index is a room and roon has not already been choosen
				temp.add(listOfCards.get(r)); // adds the card to envelope
				game.getBoard().removeCard(listOfCards.get(r)); // removes the card from the list
				room = true;
			}
			// gets the weapon
			if (listOfCards.get(r).getType() == "weapon" && weapon == false) {
				temp.add(listOfCards.get(r));
				game.getBoard().removeCard(listOfCards.get(r));
				weapon = true;
			}
			//gets the person
			if (listOfCards.get(r).getType() == "person" && person == false) {
				temp.add(listOfCards.get(r));
				game.getBoard().removeCard(listOfCards.get(r));
				person = true;
			}
		}
		game.getBoard().setEnvelope(temp);
	}

	/** method that prints out all card names **/
	public void printCards() {
		// prints out all room
		System.out.println("----- Rooms -----");
		for (Card c : getBoard().getRooms()) {
			System.out.println(c.getName());
		}
		System.out.println();
		// prints out all persons
		System.out.println("----- Characters -----");
		for (Card c : getBoard().getPersons()) {
			System.out.println(c.getName());
		}
		System.out.println();
		// prints out all weapons
		System.out.println("----- Weapons -----");
		for (Card c : getBoard().getWeapons()) {
			System.out.println(c.getName());
		}
	}

	/** method to get user input **/
	private static String input() {
		BufferedReader input = new BufferedReader(new InputStreamReader(
				System.in));
		try {
			return input.readLine();
		} catch (IOException e) {
			System.out.println("IO Error");
		}
		return null;
	}
}
