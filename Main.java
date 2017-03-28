//Main class 

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    
    
	public static void main(String[] args) throws IOException {
		System.out.println("Hey");
        System.out.println(readFile());
		//TODO
	}
    
    
    
    public static ArrayList<String> readFile() throws IOException {
        ArrayList<String> list = new ArrayList<String>();

        try {

            File f = new File("Untitled.txt");
            
            BufferedReader bufferedReader = new BufferedReader(new FileReader(f));
            
            String readLine = "";
            
            System.out.println("YAS");
            
            while ((readLine = bufferedReader.readLine()) != null) {
                System.out.println(readLine);
                list.add(readLine);
                
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    
    }
    
}
