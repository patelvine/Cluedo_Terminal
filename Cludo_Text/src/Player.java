import java.util.ArrayList;

public class Player {

	private ArrayList<Card> cards; // players hand
	private String name; // players real name
	private String character; // players character 
	private Location location; // current location player is on the board
	private int num; // number the players should be shown on the board

	public Player(String name, String character, Location location, ArrayList<Card> cards, int num) {
		this.name = name;
		this.character = character;
		this.location = location;
		this.cards = cards;
		this.num = num;
	}
	
	public int getNum(){
		return this.num;
	}
	
	public Location getLocation() {
		return this.location;
	}

	public ArrayList<Card> getCards() {
		return this.cards;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getChar(){
		return this.character;
	}

	public void addCard(Card c) {
		this.cards.add(c);
	}
}
