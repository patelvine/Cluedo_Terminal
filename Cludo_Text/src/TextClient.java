import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class TextClient {

	public static void main(String args[]) {
		GameOfCludo game = new GameOfCludo();
		// asks for number of players
		System.out.print("How many players (3-6)?");
		int numPlayers = chooseNumPlayers();
		// creates list of players
		game.getBoard().setPlayers(choosePlayerNames(numPlayers));
		// creates secret envelope
		game.setSecertEnvelope(game);
		// deals out the cards
		game.getBoard().setPlayers(
				game.dealCards(game, game.getBoard().getPlayers()));
		// plays the game
		System.out.println("---Starting Cludo---");
		game.getBoard().draw();
		System.out.println("\nNOTE : w = UP, a = LEFT, s = DOWN, d = RIGHT");
		Random dice = new Random();

		while (1 == 1) { // loops forever
			for (int i = 0; i < game.getBoard().getPlayers().size(); i++) { // for each player
				Player player = game.getBoard().getPlayers().get(i); // current players turn
				int roll = dice.nextInt(6) + 1;
				System.out.println("\n------- " + player.getName()
						+ "'s turn -------");
				System.out.println(player.getName() + " rolls a " + roll);
				System.out.println("Enter direction");
				
				// if player is in a room
				if (player.getLocation().getName() != null) {
					String name = player.getLocation().getName();
					// if the room is a corner room
					if (name == "Spa" || name == "Observatory"
							|| name == "Kitchen" || name == "Guest House") {
						System.out
								.println("Do you want to take the short-cut to the opposite corner room (yes/no)?)");
						String takeShortCut = input();
						//if player wants to take short cut set them into opposite corner room
						if (takeShortCut.equals("yes")) {
							if (name == "Spa") {
								player.getLocation().setLocation(21, 21, "="); //sets player location to the outside of the opposite corner room
								player.getLocation().setName("Guest House"); // sets current room in to opposite corner room
							} else if (name == "Observatory") {
								player.getLocation().setLocation(7, 22, "=");
								player.getLocation().setName("Kitchen");
							} else if (name == "Kitchen") {
								player.getLocation().setLocation(22, 9, "=");
								player.getLocation().setName("Observatory");
							} else if (name == "Guest House") {
								player.getLocation().setLocation(6, 7, "=");
								player.getLocation().setName("Spa");
							}
						} else { // places player outside in front of the door and decrements one move
							player.getLocation().setName(null); // player is not in a room any more so sets location name to null
							game.getBoard().getMap()[player.getLocation()
									.getY()][player.getLocation().getX()] = Integer
									.toString(player.getNum()); // places players token down at the door
							game.getBoard().draw();
							roll = roll - 1;
							game.movePlayer(player, roll);
						}
					} else { // same method as above
						player.getLocation().setName(null);
						game.getBoard().getMap()[player.getLocation().getY()][player
								.getLocation().getX()] = Integer
								.toString(player.getNum());
						game.getBoard().draw();
						roll = roll - 1;
						game.movePlayer(player, roll);
					}
					playerOptions(player, game.getBoard().getPlayers(), game); // calls this method to give player options after they cant move anymore
				} else {
					game.movePlayer(player, roll);
					playerOptions(player, game.getBoard().getPlayers(), game);
				}
			}
		}
	}

	/** method for creating players **/
	private static ArrayList<Player> choosePlayerNames(int numPlayers) {
		ArrayList<Player> temp = new ArrayList<Player>();
		String name = null;
		String ch = null;
		for (int i = 0; i < numPlayers; i++) {
			System.out.println("Player " + (i + 1) + " Enter your name:");
			name = input(); // input for users real name
			// assigns characters to players
			if (i == 0) {
				ch = "Kasandra Scarlett";
			} else if (i == 1) {
				ch = "Jack Mustard";
			} else if (i == 2) {
				ch = "Diane White";
			} else if (i == 3) {
				ch = "Jacob Green";
			} else if (i == 4) {
				ch = "Eleanor Peacock";
			} else if (i == 5) {
				ch = "Victor Plum";
			}
			temp.add(new Player(name, ch, getStartingLocation(ch),
					new ArrayList<Card>(),i+1)); // create a new player
		}
		return temp;
	}

	/** gets the starting location for each player **/
	private static Location getStartingLocation(String charac) {
		if (charac == "Kasandra Scarlett") {
			return new Location(19, 29, "-");
		} else if (charac == "Jack Mustard") {
			return new Location(8, 29, "-");
		} else if (charac == "Diane White") {
			return new Location(1, 20, "-");
		} else if (charac == "Jacob Green") {
			return new Location(1, 10, "-");
		} else if (charac == "Eleanor Peacock") {
			return new Location(7, 1, "-");
		} else if (charac == "Victor Plum") {
			return new Location(21, 1, "-");
		} else {
			return null;
		}
	}

	/** method which gives players options **/
	public static void playerOptions(Player player, ArrayList<Player> players,
			GameOfCludo game) {
		System.out.println("Options");
		System.out
				.println("[1] Print current room location, [2] Print cards in hand, [3] Print list of all cards,");
		System.out
				.println("[4] Make a suggestion, [5] Make accusation, [6] End turn");
		int choice = number(); // this number represents player option
		while (choice != 6) {
			if (choice == 1) { // prints current room player is in
				if (player.getLocation().getName() == null) {
					System.out.println("Not in a room");
				} else {
					System.out.println(player.getLocation().getName());
				}
			}
			if (choice == 2) { // prints current cards in players hand
				for (Card c : player.getCards()) {
					System.out.println(c.getName());
				}
			}
			if (choice == 3) { // prints all cards in the game
				game.printCards();
			}
			if (choice == 4) { // allows user to make a suggestion
				if (player.getLocation().getName() != null) {
					String weapon = null;
					String suspect = null;
					System.out.println();
					game.printCards();
					System.out.println("\nYou are in the "
							+ player.getLocation().getName());

					while (game.checkCardExists(weapon, suspect, player // while the user enters wrong card names
							.getLocation().getName(), game) == false) {
						System.out.println("Enter a Murder Weapon");
						weapon = input();
						System.out.println("Enter a Suspect");
						suspect = input();

						if (game.checkCardExists(weapon, suspect, player // if cards entered exist
								.getLocation().getName(), game)) {
							for (Player p : players) { // check if a player can refute users suggestion
								for (Card c : p.getCards()) {
									if (c.getName().equals(weapon)) { //compares card names
										System.out.println("\n" + p.getName()
												+ " has the " + weapon
												+ " card");
										return;
									}
									if (c.getName().equals(suspect)) {
										System.out.println("\n" + p.getName()
												+ " has the " + suspect
												+ " card");
										return;
									}
									if (c.getName().equals(
											player.getLocation().getName())) {
										System.out.println("\n"
												+ p.getName()
												+ " has the "
												+ player.getLocation()
														.getName() + " card");
										return;
									}
								}
							}
							System.out
									.println("No one could refute your suggestion");
							return;
						} else {
							System.out.println("Please eneter correct cards");
						}
					}
				} else {
					System.out
							.println("Must be in a room to make a suggestion");
				}
			}
			if (choice == 5) { // allows player to make an accusation
				if (player.getLocation().getName() != null
						&& player.getLocation().getName()
								.equals("Swimming Pool")) { // if the player is in the swimming pool room
					String weapon = null;
					String suspect = null;
					String room = null;
					System.out.println();
					game.printCards();
					System.out.println("\nYou are in the "
							+ player.getLocation().getName());

					while (game.checkCardExists(weapon, suspect, room, game) == false) { // while cards entered dont exist (keeps looping till correct guess is made)
						System.out.println("Enter a Suspect");
						suspect = input();
						System.out.println("Enter a Room");
						room = input();
						System.out.println("Enter a Murder Weapon");
						weapon = input();

						if (game.checkCardExists(weapon, suspect, room, game)) { //if all cards entered exist
							boolean p = false;
							boolean w = false;
							boolean r = false;
							// checks if all cards in SecretEnvelope exist
							for (Card c : game.getBoard().getSecretEnvelope()) {
								if (c.getName().equals(suspect)) {
									p = true;
								}
								if (c.getName().equals(weapon)) {
									w = true;
								}
								if (c.getName().equals(room)) {
									r = true;
								}
							}
							// if all cards did exist this game over and user wins
							if (p == true && w == true && r == true) {
								System.out.println("CORRECT! "
										+ player.getName()
										+ " HAS WON THE GAME");
								System.exit(0);
							} else { // if cards did not match then user is eliminated from the game
								System.out
										.println("INCORRECT, YOU HAVE BEEN ELIMINATED... BYE BYE");
								game.getBoard().removePlayer(player); // removes player from game
								if (game.getBoard().getPlayers().size() == 1) { // if only one player remains he/she is the winner
									System.out.println();
									System.out.println(game.getBoard()
											.getPlayers().get(0).getName()
											+ " HAS WON THE GAME!");
									System.exit(0);
								} else {
									return;
								}
							}
						} else {
							System.out.println("Please eneter correct cards");
						}
					}

				} else {
					System.out
							.println("Must be in the Swimming Pool room to make an accusion");
				}

			}
			if (choice == 6) { // ends the players turn
				return;
			}
			if (choice > 6) { // asks for int between 1-6
				System.out.println("Please enter a correct number");
			}
			System.out.println("\nChoose another Option ---- [6] to end turn");
			choice = number();
		}
	}

	/** method to choose number of players playing between 3-6 **/
	private static int chooseNumPlayers() {
		int i = 0;
		while (i < 3 || i > 6) {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					System.in));
			try {
				i = Integer.parseInt(reader.readLine());
			} catch (Exception e) {
				System.out.println("Please enter an integer between 3-6");
				return chooseNumPlayers();
			}
			if (i >= 3 && i <= 6) {
				return i;
			}
			System.out.println("Please enter an integer between 3-6");
		}
		return 0;
	}

	/** method to ask user for a number (used by player options) **/
	private static int number() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));
		try {
			int i = (int) Integer.parseInt(reader.readLine());
			return i;
		} catch (Exception e) {
			System.out.println("Enter a option number between 1-6");
			return number();
		}
	}

	// method to ask user for a string input (used with names and guesses)
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