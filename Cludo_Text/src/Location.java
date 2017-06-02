public class Location {
	private int x; // x position in the map array
	private int y; //  position in the map array
	private String on; // original value of the array player is on
	private String name; // room the payer is currently in

	public Location(int x, int y, String on) {
		this.x = x;
		this.y = y;
		this.on = on;
		this.name = null;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOn() {
		return this.on;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public void setLocation(int x, int y, String on) {
		this.x = x;
		this.y = y;
		this.on = on;
	}
}
