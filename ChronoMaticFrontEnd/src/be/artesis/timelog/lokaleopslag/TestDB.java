package be.artesis.timelog.lokaleopslag;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TestDB {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		//System.out.println(LocalDatabaseWriter.URL);
		final File file = new File("db\\piet");	
		//System.out.println(file.getAbsolutePath());
		//public static final String URL = file.getAbsolutePath();	
		//File file = new File( "C:\\Users\\Yolan\\Desktop\\piet");
		FileWriter f = new FileWriter(file);
		f.write("piet");
		f.flush();
		f.close();
		
		
		}

}
