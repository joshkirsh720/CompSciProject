//Truck class

public class Truck {
    private int x, y, workers;
    
	public Truck(int w) {
		x = 249;
		y = 219;
		workers = w;
	}
	
	public void pathfind(ArrayList<Location> list) {
		for(int i = 0; i < list.size(); i++) {
			
			Location optimalLocation = null;
			for(int a = 0; a < list.size(); a++) {
				if(optimalLocation == null) {
					optimalLocation = list.get(a);
				}
				else if() {
					
				}
			}
		}
	}
	
	
	public int getX() {
		return x;
	}
	public void setX(int xVal) {
		x = xVal;
	}
	
	public int getY() {
		return y;
	}
	public void setY(int yVal) {
		y = yVal;
	}
	
	public int getWorkers() {
		return workers;
	}
}
