package project4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileIO {

	public FileIO() {
		
	}
	
	public void readFile(File file, EMPHandler obj) {
		
		Scanner fileScanner = null;
		
		try {
			
			String last, first, position, site, idGen;
			
			fileScanner = new Scanner(file);
			
			while(fileScanner.hasNext()) {
				
				last = fileScanner.next();
				first = fileScanner.next();
				position = fileScanner.next();
				site = fileScanner.nextLine().trim();
				
				idGen = "";
				idGen += site.substring(0, 1).toUpperCase();
				idGen += "-";
				idGen += last.substring(0, 3).toUpperCase();
				idGen += first.substring(0, 1).toUpperCase();
				idGen += "-";
				idGen += "01";
				
				EmpRecord empObj = new EmpRecord(idGen, last, first, position, site);
				obj.addEmployee(empObj);
			}
			
		}
		catch (FileNotFoundException e) {
			
			System.out.println(e.getMessage());
			
		}
		
		finally {
			
			if(fileScanner != null) {
				
				fileScanner.close();
				
			}
		}
	}
	
	public boolean write(String[] s) {
		
		try {
			
			FileWriter empFile = new FileWriter("empUpdate.dat");
			
			for(int i = 0; i < s.length; i++) {
				
				empFile.write(s[i]);
				
			}
			
			empFile.close();
			
			return true;
		}
		
		catch(IOException e) {
			
			e.printStackTrace();
			
			return false;
		}
	}
}
