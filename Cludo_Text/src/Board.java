import java.util.ArrayList;


public class Board {
	private ArrayList<Card> cards = new ArrayList<Card>();
	private ArrayList<Card> secretEnvelope = new ArrayList<Card>();
	private ArrayList<Room> rooms = new ArrayList<Room>();
	private ArrayList<Weapon> weapons = new ArrayList<Weapon>();
	private ArrayList<Person> characters = new ArrayList<Person>();
	private ArrayList<Player> players = new ArrayList<Player>();
	private String [][] map;
		
	
	public Board(){
		makeMap();
		
		characters.add(new Person("Kasandra Scarlett","person"));		 //red
		characters.add(new Person("Jack Mustard","person")); 		  	//yellow
		characters.add(new Person("Diane White","person")); 			//white
		characters.add(new Person("Jacob Green","person")); 		  	//green
		characters.add(new Person("Eleanor Peacock","person"));		 //blue
		characters.add(new Person("Victor Plum","person"));			 //purple
		
		weapons.add(new Weapon("Rope", "weapon"));
		weapons.add(new Weapon("CandleStick", "weapon"));
		weapons.add(new Weapon("Knife", "weapon"));
		weapons.add(new Weapon("Pistol", "weapon"));
		weapons.add(new Weapon("Baseball Bat", "weapon"));
		weapons.add(new Weapon("Dumbbell", "weapon"));
		weapons.add(new Weapon("Trophy", "weapon"));
		weapons.add(new Weapon("Posion", "weapon"));
		weapons.add(new Weapon("Axe", "weapon"));
		
		rooms.add(new Room("Spa","room")); 
		rooms.add(new Room("Patio","room"));
		rooms.add(new Room("Kitchen","room"));
		rooms.add(new Room("Theatre","room"));
		rooms.add(new Room("Living Room","room"));
		rooms.add(new Room("Observatory","room"));
		rooms.add(new Room("Hall","room"));
		rooms.add(new Room("Guest House","room"));
		rooms.add(new Room("Dinning Room","room"));
		rooms.add(new Room("Swimming Pool","room"));
		// sets cards array
		cards.addAll(rooms);
		cards.addAll(weapons);
		cards.addAll(characters);
	}
	/** map layout **/
	public void makeMap(){
		this.map = new String[][] {
				{"#","#","#","#","#","#","#","#","#","#","#","#","#","#","#","#","#","#","#","#","#","#","#","#","#","#"},//0
				{"#","#","#","#","#","#","#","5","-","#","#","#","#","#","-","#","#","#","#","#","#","6","-","#","#","#"},//1
				{"#","#"," "," "," "," ","#","-","-","#"," "," "," ","#","-","#"," "," "," "," ","#","-","-","#"," ","#"},//2
				{"#","#"," "," "," "," ","#","-","-","#"," "," "," ","#","-","#"," "," "," "," ","#","-","-","#"," ","#"},//3
				{"#","#"," "," "," "," ","#","-","-","#"," "," "," ","#","-","#"," "," "," "," ","#","-","-","#"," ","#","Spa, Theatre, Living Room, Observatory"},//4
				{"#","#"," "," "," "," ","#","-","-","#"," "," "," ","#","-","#"," "," "," "," ","#","-","-","#"," ","#"},//5
				{"#","#"," "," "," ","#","S","-","-","#"," "," "," ","#","-","#"," "," "," "," ","#","-","-","#"," ","#"},//6
				{"#","#"," "," "," ","#","=","-","-","#"," ","#"," ","#","-","#"," "," "," "," ","#","-","-","#"," ","#"},//7
				{"#","#","#","#","#","#","-","-","-","#","#","T","#","#","-","#"," ","#"," ","#","#","-","-","#"," ","#"},//8
				{"#","-","-","-","-","-","-","-","-","-","-","=","-","-","-","-","#","L","#","-","-","-","=","O","#","#"},//9
				{"#","4","-","-","-","-","-","-","-","-","-","-","-","-","-","-","-","=","-","-","-","-","-","-","-","#"},//10
				{"#","#","#","#","A","=","-","-","-","-","-","-","-","-","-","=","-","-","-","-","-","-","-","=","-","#"},//11
				{"#","#"," "," ","#","#","#","#","#","-","-","#","#","#","#","P","#","#","#","-","#","#","#","H","#","#"},//12
				{"#","#"," "," "," "," "," "," ","#","-","-","#"," "," "," ","#"," "," ","#","-","#"," "," ","#","#","#"},//13
				{"#","#"," "," "," "," "," "," ","#","-","-","#"," "," "," "," "," "," ","#","=","H","#"," "," ","#","#"},//14
				{"#","#"," "," "," "," "," "," ","#","-","-","#"," "," "," "," "," "," ","#","=","H","#"," "," ","#","#","Patio, Swimming Pool, Hall"},//15
				{"#","#"," "," "," "," "," "," ","#","-","-","#"," "," "," "," "," "," ","#","-","#"," "," "," ","#","#"},//16
				{"#","#"," "," "," "," "," "," ","#","-","-","P","#","#","#","#","#","#","P","-","#"," "," "," ","#","#"},//17
				{"#","#"," "," "," ","#","#","#","#","-","-","=","-","-","-","-","-","-","=","-","#","#","#","#","#","#"},//18
				{"#","#","#","#","#","-","-","-","-","-","-","-","-","=","-","-","-","-","-","-","-","-","-","-","-","#"},//19
				{"#","3","-","-","-","-","-","-","-","-","-","#","#","D","#","#","#","-","-","-","-","-","-","-","-","#"},//20
				{"#","-","-","-","-","-","-","-","-","-","-","#"," ","#"," "," ","#","-","-","-","-","=","G","#","#","#"},//21
				{"#","#","#","#","#","#","#","=","-","-","-","#"," "," "," ","#","D","=","-","-","-","G","#"," ","#","#"},//22
				{"#","#"," "," "," "," ","#","K","-","-","-","#"," "," "," "," ","#","-","-","-","-","#"," "," ","#","#"},//23
				{"#","#"," "," "," "," "," ","#","-","-","#"," "," "," "," "," "," ","#","-","-","-","#"," "," ","#","#"},//24
				{"#","#"," "," "," "," "," ","#","-","-","#"," "," "," "," "," "," ","#","-","-","-","#"," "," ","#","#"},//25
				{"#","#"," "," "," "," "," ","#","-","-","#"," "," "," "," "," "," ","#","-","-","-","#"," "," ","#","#","Kitchen, Dinning Room, Guest Room"},//26
				{"#","#"," "," "," "," "," ","#","-","-","#"," "," "," "," "," "," ","#","-","-","-","#"," "," ","#","#"},//27
				{"#","#"," "," "," "," "," ","#","-","-","#"," "," "," "," "," "," ","#","-","-","-","#"," "," ","#","#"},//28
				{"#","#","#","#","#","#","#","#","2","-","#","#","#","#","#","#","#","#","-","1","-","#","#","#","#","#"},//29
				{"#","#","#","#","#","#","#","#","#","#","#","#","#","#","#","#","#","#","#","#","#","#","#","#","#","#"},//30
		};
	}
	
	/** draws map by going through each value in map array and printing the string inside **/
	public void draw() {
		for(String[] s : this.map){
			for(String t : s){
				System.out.print(t);
				System.out.print(" "); // puts a space between each value (makes it neater)
			}
			System.out.println();
		}
	}
	
	public String[][] getMap() {
		return this.map;
	}
	
	public ArrayList<Card> getCards() {
		return this.cards;
	}

	public ArrayList<Room> getRooms() {
		return this.rooms;
	}

	public ArrayList<Weapon> getWeapons() {
		return this.weapons;
	}

	public ArrayList<Person> getPersons() {
		return this.characters;
	}
	
	public ArrayList<Card> getSecretEnvelope() {
		return this.secretEnvelope;
	}
	
	public void setEnvelope(ArrayList<Card> e) {
		this.secretEnvelope = e;
	}
	
	public void removeCard(Card c) {
		this.cards.remove(c);
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}
	
	public void removePlayer(Player player) {
		this.players.remove(player);
	}
}