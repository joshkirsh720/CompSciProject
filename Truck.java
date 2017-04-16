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
			this.navigateTo(optimalLocation);
		}
	}
	
	private void navigateTo(Location location) {
		int xMovement = location.getX() - this.getX();
		int yMovement = location.getY() - this.getY();
		
		//moves the truck on to an avenue based off of where the truck's destination is
		if(this.getY() % 10 != 0) {
			//MAKE SURE TO TRACK TIME AND MONEY ON THIS PART
			if(yMovement < 0) {
				System.out.println("NEGATIVE MOVEMENT");
				System.out.println(yMovement);
				System.out.println(this.getY());
				int roundedY = this.getY() / 10 * 10;
				int difference = this.getY() - roundedY;
				System.out.println(difference);
				this.move(0, -1 * difference);
				yMovement +=  difference;
				System.out.println(yMovement);
				System.out.println(this.getY());
			}
			else if(yMovement > 0) {
				System.out.println("POSITIVE MOVEMENT");
				System.out.println(yMovement);
				System.out.println(this.getY());
				int roundedY = this.getY() / 10 * 10;
				int difference = this.getY() - roundedY;
				System.out.println(difference);
				int up = 10 - difference;
				System.out.println(up);
				this.move(0, up);
				yMovement -= up;
				System.out.println(yMovement);
				System.out.println(this.getY());
			}
			else {
				
			}
		}
		System.out.println("(" + this.getX() + ", " + this.getY() + ")");
		this.move(xMovement, yMovement);
		System.out.println("(" + this.getX() + ", " + this.getY() + ")" + "\n");
		if(this.getX() == location.getX() && this.getY() == location.getY()) System.out.println("it works :)");
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
	
	public void move(int xMove, int yMove) {
		x += xMove;
		y += yMove;
	}
	
	public int getWorkers() {
		return workers;
	}
}
