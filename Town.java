//shalom
 
public class Town {
	private Location[][] town = new Location[500][500];
	
	public Town() {
	}
	
	public void init() {
		for(int x = 0; x <500; x++) {
			for(int y = 0; y < 500; y++) {
				town[x][y] = new Location(x,y,0);
			}
		}
		for(int x = 0; x<500; x++) {
			for(int y =0; y<500; y++) {
				
				//setting bart complex location
				if((x>2 && x<=4) && (y>20 && y<=30)) {
					town[x][y].setType(1);
				}
				
				//setting lisa complex location
				if((x>296 && x<=298) && (y>320 && y<=330)) {
					town[x][y].setType(2);
				}
				
				//setting delivery center location
				if((x>248 && x<=250) && (y>210 && y<=220)) {
					town[x][y].setType(3);
				}
			}
		}
		
		return;
	}
}
