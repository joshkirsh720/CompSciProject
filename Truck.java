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
			int manDisToLocation = 0;
			for(int a = 0; a < list.size(); a++) {
				if(optimalLocation == null) {
					optimalLocation = list.get(a);
					manDisToLocation = Math.abs(optimalLocation.getX() - this.getX ) + Math.abs(optimalLocation.getY() - this.getY)
                    
        
				}
				else {
                    
                    
					
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
