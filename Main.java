//Main class 

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    
	static int bartDeliveries, lisaDeliveries, packagesToDeliver;
    
	public static void main(String[] args) throws IOException {
		ArrayList<String> list = readFile();
		convertStringArrayToLocations(list);
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
	
	public static void convertStringArrayToLocations(ArrayList<String> list) {
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
		System.out.println(separatedList);
		
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
		System.out.println(cleanedList);
		
		//creates locations based off of what is now in the cleaned list and adds them to the final list
	}
    
}
