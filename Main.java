//Main class 

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    
	static int bartDeliveries = 0, lisaDeliveries = 0, packagesToDeliver, cost = 0;
	static Location bartComplexLocation = new Location(3,29,1);
	static Location lisaComplexLocation = new Location(297,329,2);
    static double OriginalCost = 0;
	public static void main(String[] args) throws IOException {
		//System.out.println("ay lmao");
		Town town = new Town();
		town.init();
		
		Truck truck1 = new Truck(2);
		Truck truck2 = new Truck(2);
		Truck truck3 = new Truck(2);
		cost += 300000;
		Truck[] truckList = {truck1, truck2, truck3};
		
		for(int cycle = 1; cycle <=10; cycle++) {
			
			String fileName = "cycles/cycle" + cycle + ".txt";
			int optimalTrucks = -1;
			//this loop is just to figure out how many trucks are necessarys
			for(int III = 1; III < 100; III++){
				int trucks = III;
				
				ArrayList<String> list = readFile(fileName);
				ArrayList<Location> deliveryLocationList = convertStringArrayToLocations(list);
				ArrayList<Location>[] chunks = splitList(deliveryLocationList, trucks);
				
				double max = -1;
				if(trucks == 1) {
					Truck truck = new Truck(2, bartDeliveries, lisaDeliveries);
					truck.setPackages(packagesToDeliver);
					truck.pathfind(deliveryLocationList);
					max = truck.getTime();
				}
				else {
					for(int i = 0; i < trucks; i++) {
						Truck truck = new Truck(2, bartDeliveries, lisaDeliveries);
						truck.setPackages(packagesToDeliver);
						truck.pathfind(chunks[i]);
						if(max == -1) {
							max = truck.getTime();
						}
						else if(truck.getTime() > max) {
							max = truck.getTime();
						}
					}
				}
				
					if((max/3600) < 24 ){
						System.out.println("Cycle " + cycle + ": " + trucks + " trucks in " + (max/3600) + " hours");
                        System.out.println("Day's Cost" +  (cost -  OriginalCost));

                        optimalTrucks = trucks;
						break;
					}      
			}
			
			//resets location
			for(int a = 0; a < 3; a++) {
				truckList[a].setX(249);
				truckList[a].setX(219);
			}
			
			//this does the actual run
			ArrayList<String> list = readFile(fileName);
			ArrayList<Location> deliveryLocationList = convertStringArrayToLocations(list);
			ArrayList<Location>[] chunks = splitList(deliveryLocationList, optimalTrucks);
             OriginalCost = cost;
            
			if(optimalTrucks <= 2) {
				for(int m = 0; m < optimalTrucks; m++) {
					truckList[m].setPackages(packagesToDeliver);
					truckList[m].pathfind(chunks[m]);
					truckList[m].calculateDistance();
					double time = truckList[m].getTime() / 3600;
					time -= 8;
					cost += 2 * ((30 * 8) + (time * 45));
				}
			}
			else {
				for(int m = 0; m < 2; m++) {
					truckList[m].setPackages(packagesToDeliver);
					truckList[m].pathfind(chunks[m]);
					truckList[m].calculateDistance();
					double time = truckList[m].getTime() / 3600;
					time -= 8;
					cost += 2 * ((30 * 8) + (time * 45));
				}
				
				//pathfind fourth rented one and add cost
                for (int m = 2; m < optimalTrucks - 2; m++){
                
                    cost += 15000;
                    
        
                    Truck truck = new Truck(m);
                    truck.setPackages(packagesToDeliver);
                    truck.pathfind(chunks[m]);
                    double distance = truck.calculateDistance();
                    double time = truck.getTime() / 3600;
                    time -= 8;
                    cost += 2 * ((30 * 8) + (time * 45));
                    cost += distance * 5;
                    

                
                }
                

              
                
			}
            
            
		}
		
		for(int i = 0; i < truckList.length; i++) {
			cost += truckList[i].getDistance() * 10;
        }
		

		System.out.println("Total Cost: $" + cost);
		//TODO
	}
	
	
	
    public static ArrayList<String> readFile(String fileName) throws IOException {
        ArrayList<String> list = new ArrayList<String>();
		

        try {

            File f = new File(fileName);
            
            BufferedReader bufferedReader = new BufferedReader(new FileReader(f));
            
            String readLine = "";
            
			int counter=0;
			boolean bartGet=false,lisaGet=false;
            while ((readLine = bufferedReader.readLine()) != null) {
                //System.out.println(readLine);
                counter++;
				if(counter==1) {
					continue;
				}
				else if(counter==2) {
					packagesToDeliver = Integer.parseInt(readLine);
					continue;
				}
                
				if(readLine.equals("Bart Complex")) {
					bartGet=true;
					continue;
				}
				else if(readLine.equals("Lisa Complex")) {
					lisaGet=true;
					continue;
				}
				if(bartGet==true) {
					bartDeliveries=Integer.parseInt(readLine);
					bartGet=false;
					continue;
				}
				else if(lisaGet==true) {
					lisaDeliveries=Integer.parseInt(readLine);
					lisaGet=false;
					continue;
				}
				
				list.add(readLine);
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    
    }
	
	public static ArrayList<Location> convertStringArrayToLocations(ArrayList<String> list) {
		ArrayList<String> separatedList = new ArrayList<String>();
		ArrayList<String> cleanedList = new ArrayList<String>();
		ArrayList<Location> finalList = new ArrayList<Location>();
		
		//separates original list elements into their own spot in the array
		//ex. "1s,44a,EE" gets separated to "1s","44a","EE"
		for(int i = 0; i < list.size(); i++) {
            // System.out.println(list.size() + "Thats so raven");

			String arrayGet = list.get(i);
			int startGrab = 0;
			for(int x = 0; x < arrayGet.length(); x++) {
                
				if(arrayGet.charAt(x) == ',') {
					separatedList.add(arrayGet.substring(startGrab, x));
					startGrab = x+1;
				}
				else if (x == arrayGet.length()-1) {
					separatedList.add(arrayGet.substring(startGrab, x+1));
				}
			}
		}
		//System.out.println(1 + "" + separatedList);
		
		//removes all unecessary letters like the s and a at the end of the first 2 elements in each location
		for(int i = 0; i < separatedList.size(); i++) {
            // System.out.println(separatedList.size() + "uadhaoiwfeiouwiu");
			
			String arrayGet = separatedList.get(i);
			StringBuilder sb = new StringBuilder(arrayGet);
			String result = "";
			for(int x = 0; x < arrayGet.length(); x++) {
				if(arrayGet.charAt(x) == 's' || arrayGet.charAt(x) == 'a') {
					sb.deleteCharAt(x);
					result = sb.toString();
				}
				else {
					result = arrayGet;
				}
			}
			cleanedList.add(result);
		}
		//System.out.println(2 + "" + cleanedList);
		
		//creates locations based off of what is now in the cleaned list and adds them to the final list
        // System.out.println(bartDeliveries+lisaDeliveries + "YAYAYAYAYYAYAYAYAY JOAWSH");
		int countTo = packagesToDeliver - (bartDeliveries+lisaDeliveries) ;
		int beginning = 0;
		for(int i = 1; i < countTo  ; i++) {
			ArrayList<String> houseLocation = new ArrayList<String>();
            // System.out.println(countTo / 3.0 + "LOLZ");
            
			int end = 3*i;
            // System.out.println(end / 3.0 + "LadOLZ");

			for(int x = beginning; x <= end; x++) {
                // System.out.println(x + "x=");
                
                cleanedList.get(x);
				// System.out.println(cleanedList.size());
                houseLocation.add(cleanedList.get(x));
			}
			beginning = end;
			//System.out.println(3 + "" + houseLocation);
			
			finalList.add(determineLocation(houseLocation));
		}
        /* System.out.println("YAS JOSH"); 
        System.out.println(bartDeliveries);
        System.out.println(lisaDeliveries);*/

        
		if(bartDeliveries != 0) {
            //System.out.println("fwhiuegwfihwuihwiufhiwhif");
			finalList.add(bartComplexLocation);
		}
		if(lisaDeliveries != 0) {
            //System.out.println("fwhiuegwfihwuihwiufhiwhif");

			finalList.add(lisaComplexLocation);
		}
		
		return finalList;
	}
    
	public static Location determineLocation(ArrayList<String> list) {
		Location finalLocation;
		int street = Integer.parseInt(list.get(0));
		int avenue = Integer.parseInt(list.get(1));
		String houseLetter = list.get(2);
		char houseLetterAsChar = houseLetter.charAt(0);
		
		int x, y;
		
		//using street to get x
		x = 2 * (street-1);
		//figuring out what side of the street the house is on and adding one if it is on the right side.
		if(houseLetter.length() == 2) {
			x++;
		}
		//System.out.println(3.5 + " " + x);
		
		//using avenue to get y
		y = 10 * (avenue -1);
		//System.out.println(4 + " " + y);
		//using ASCII value of the letter to find where the house is vertically in between avenues.
		y += (int)houseLetterAsChar - 65;
		//System.out.println(5 + " " + y);
		
		finalLocation = new Location(x,y,0);
		return finalLocation;
	}

	public static ArrayList<Location>[] splitList(ArrayList<Location> list, int n) {
		int split = list.size() / n;
		int beginning=0, end=split;
		int counter=0;
		ArrayList[] splitList = new ArrayList[n];
		
		for(int i = beginning; i < list.size() ; i += split) {
            
			splitList[counter] = new ArrayList<Location>(list.subList(beginning, end));
            
			beginning = end;
			end = i+split;
			if(end+split  > list.size() ) {
				splitList[counter] = new ArrayList<Location>(list.subList(end, list.size() ));
                



                
				break;
			}
            counter++;

		}
		
		
		return splitList;
	}
}
