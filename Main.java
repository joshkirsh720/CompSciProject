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
		ArrayList<String> newList = new ArrayList<String>();
		
		for(int i = 0; i < list.size(); i++) {
			
			String arrayGet = list.get(i);
			int startGrab = 0;
			for(int x = 0; x < arrayGet.length(); x++) {
				if(arrayGet.charAt(x) == ',') {
					newList.add(arrayGet.substring(startGrab, x));
					startGrab = x+1;
				}
				else if (x == arrayGet.length()-1) {
					newList.add(arrayGet.substring(startGrab, x+1));
				}
			}
		}
	}
    
}
