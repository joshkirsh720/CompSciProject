//shalom
 
public class Location {
    private int x, y;
	
	//0=house
	//1=Bart Complex
	//2=Lisa Complex
	//3=Delivery Center
	private int locationType;
	
	public Location(int xVal, int yVal, int locT) {
		x = xVal;
		y = yVal;
		locationType = locT;
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getType() {
		return locationType;
	}
	public void setType(int lt) {
		locationType = lt;
	}
	public int[] getCoordinates() {
		int[] coord = {x,y};
		return coord;
	}
	
	public String toString() {
		String string = "(" + this.getX() + ", " + this.getY() + ")";
		return string;
	}
}
