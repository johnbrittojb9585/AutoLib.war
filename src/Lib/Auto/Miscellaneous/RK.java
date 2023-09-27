package Lib.Auto.Miscellaneous;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import org.apache.commons.io.FileUtils;

import java.awt.print.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
 
public class RK {
 

    public static void main(String args[]) throws IOException {
    	
    	
    	
    	StringBuffer sb = new StringBuffer();
		
		sb.append("                 Issue Receipt \n");
		sb.append("Member Code:11BE585    Name:SaravanaKaruppaiah \n");
		sb.append("Access No:B0001  \nTitle:Java 2 Enterprise Edition - by James Walker\n");
		sb.append("Issue Date: 24-03-2014   Due Date: 23-05-2014 \n");
		sb.append("\n\n");
		sb.append(" Lib Staff                            Borrower");
		
		/**
    	FileUtils.writeStringToFile(new File("c:\\test.txt"), sb.toString());
    	
    	
    	 try {
             String str = sb.toString();
             File newTextFile = new File("C:\\text.txt");

             FileWriter fw = new FileWriter(newTextFile);
             fw.write(str);
             fw.close();

         } catch (IOException iox) {
             //do stuff with exception
             iox.printStackTrace();
         }    
         
         */
         
         
    // Good to write with http://www.mkyong.com/java/how-to-write-to-file-in-java-fileoutputstream-example/	
         
        FileOutputStream fop = null;
 		File file;
 		String content = sb.toString();
  
 		
 		
 		try {
  
 			file = new File("c:/newfile.txt");
 			fop = new FileOutputStream(file);  
 			
 			File file1 = new File("c:/newfile1111111.txt");
 			
 			/**PrintWriter writer = new PrintWriter(file1);
 	 		writer.println(content);
 	 		writer.flush();*/
 			
 	 		
 	 		FileWriter writer = new FileWriter(file1);
 	 		//writer.write("The line\n sfsdfasd");
 	 		//writer.write("546546 \n 454546");
  	 	    //writer.write(content);
 	 		//writer.flush();
 	 		BufferedWriter out = new BufferedWriter(writer);
 	 		out.write(String.format("%n", sb.toString()));
 	 		out.flush();
 	 		
 	 		/**PrintWriter out = new PrintWriter(file1);
 	 		out.println("sadfasddddd");
 	 		out.println("1222522");
 	 		out.flush();*/
 	 			
 	 		
 	 		PrintStream fileStream = new PrintStream(new File("c:\\file.txt"));
 	 		fileStream.println(String.format("%n","your data \n sdafasdf"));
 	 		fileStream.flush();
 	 		
 	 		
 			// if file doesnt exists, then create it
 			if (!file.exists()) {
 				file.createNewFile();
 			}
  
 			// get the content in bytes
 			byte[] contentInBytes = content.getBytes();
  
 			fop.write(contentInBytes);
 			fop.flush();
 			fop.close();
  
 			System.out.println("Done");
 			
 			
 			
 			
 			
 			
 			
 			
 			
 			
 			System.out.print("Path:"+System.getProperty("user.home"));
 			
 			
 			
 			
 			
 			
 			
 			
 			
 			
 			
  
 		} catch (IOException e) {
 			e.printStackTrace();
 		} finally {
 			try {
 				if (fop != null) {
 					fop.close();
 				}
 			} catch (IOException e) {
 				e.printStackTrace();
 			}
 		}  
    	
 		
 	
    	
    }
}