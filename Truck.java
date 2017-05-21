//Truck class
//shalom
import java.util.ArrayList;

public class Truck {
    private int x, y, workers, time;
	private int bartDeliveries, lisaDeliveries;
    
    
	public Truck(int w, int bD, int lD) {
		x = 249;
		y = 219;
		workers = w;
		bartDeliveries = bD;
		lisaDeliveries = lD;
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
			if(yMovement < 0 && xMovement != 0) {
				int roundedY = this.getY() / 10 * 10;
				int difference = this.getY() - roundedY;
				this.move(0, -1 * difference);
                addTime(Math.abs(difference)*3);
				yMovement +=  difference;
			}
			else if(yMovement > 0 && xMovement != 0) {
				int roundedY = this.getY() / 10 * 10;
				int difference = this.getY() - roundedY;
				int up = 10 - difference;
				this.move(0, up);
                addTime(up*3);
				yMovement -= up;
			}
			else if(yMovement == 0 && xMovement != 0){
				int roundedY = this.getY() / 10 * 10;
				int difference = this.getY() - roundedY;
				
				if(difference >= 5) {
					int moveUp = 10 - difference;
					this.move(0, moveUp);
                    addTime(Math.abs(moveUp)*3);
					yMovement -= moveUp;
				}
				else {
					this.move(0, -1 * difference);
                    addTime(Math.abs(difference)*3);
					yMovement += difference;
				}
			}
		}
		this.move(xMovement, yMovement);
        addTime((Math.abs(yMovement)*3)+(Math.abs(xMovement)*3));
		if(location.getType() == 0) {
			addPackageTime();
		}
		else if(location.getType() == 1){
			for(int i = 0; i < bartDeliveries; i++) {
				addPackageTime();
			}
		}
		else if(location.getType() == 2) {
			for(int i = 0; i < lisaDeliveries; i++) {
				addPackageTime();
			}
		}
        //System.out.println("The time in Hours Is " + ( this.getTime()/3600 ));
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
    public void addTime(int MoreTime){
        time += MoreTime;
    }
	public void addPackageTime() {
		if (this.getWorkers() == 1) {
            addTime(60);
        }
		else {
			addTime(30);
		}
	}
	
    public double getTime(){
        return (double)time;
    
    }
    
	
	public void move(int xMove, int yMove) {
		x += xMove;
		y += yMove;
	}
	
	public int getWorkers() {
		return workers;
	}
}
