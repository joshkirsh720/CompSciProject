//Truck class
import java.util.ArrayList;

public class Truck {
    private int x, y, workers;
    
	public Truck(int w) {
		x = 249;
		y = 219;
		workers = w;
	}
	
	public void pathfind(ArrayList<Location> list) {
		//entire loop, goes through the whole list and pathfinds to each one
		System.out.println(list.size());
		for(int i = 0; i < list.size(); i++) {
			
			Location optimalLocation = null;
			int optimalManDis = 0;
			int indexOfOptimalLocation = -1;
			//finds the optimal location to pathfind to based off of manhattan distance from the truck
			for(int a = 0; a < list.size(); a++) {
				if(list.get(a) != null) {
					if(optimalLocation == null) {
						optimalLocation = list.get(a);
						optimalManDis = Math.abs(optimalLocation.getX() - this.getX()) + Math.abs(optimalLocation.getY() - this.getY());
						indexOfOptimalLocation = a;
					}
					else {
						int testManDis = Math.abs(list.get(a).getX() - this.getX()) + Math.abs(list.get(a).getY() - this.getY());
						
						if(testManDis < optimalManDis) {
							optimalLocation = list.get(a);
							optimalManDis = testManDis;
							indexOfOptimalLocation = a;
						}
					}
				}
				else {
					continue;
				}
			}
			list.set(indexOfOptimalLocation, null);
			
			
			//AFTER OPTIMAL LOCATION IS FOUND
			//pathfinds to optimal location
			
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
