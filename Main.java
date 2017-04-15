//Main class 

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    
	static int bartDeliveries, lisaDeliveries, packagesToDeliver;
	static Location bartComplexLocation = new Location(3,29,1);
	static Location lisaComplexLocation = new Location(297,329,2);
    
	public static void main(String[] args) throws IOException {
		ArrayList<String> list = readFile();
		ArrayList<Location> deliveryLocationList = convertStringArrayToLocations(list);
		Town town = new Town();
		Truck truck = new Truck(2);
		town.init();
		truck.pathfind(deliveryLocationList);
		//TODO
	}
    
    
    
    public static ArrayList<String> readFile() throws IOException {
        ArrayList<String> list = new ArrayList<String>();
		

        try {

            File f = new File("Untitled.txt");
            
            BufferedReader bufferedReader = new BufferedReader(new FileReader(f));
            
            String readLine = "";
            
			int counter=0;
			boolean bartGet=false,lisaGet=false;
            while ((readLine = bufferedReader.readLine()) != null) {
                System.out.println(readLine);
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
		int countTo = packagesToDeliver - (bartDeliveries+lisaDeliveries);
		int beginning = 0;
		for(int i = 1; i <= countTo; i++) {
			ArrayList<String> houseLocation = new ArrayList<String>();
			
			int end = (3 * i);
			for(int x = beginning; x < end; x++) {
				houseLocation.add(cleanedList.get(x));
			}
			beginning = end;
			//System.out.println(3 + "" + houseLocation);
			
			finalList.add(determineLocation(houseLocation));
		}
		
		if(bartDeliveries != 0) {
			finalList.add(bartComplexLocation);
		}
		if(lisaDeliveries != 0) {
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
}
