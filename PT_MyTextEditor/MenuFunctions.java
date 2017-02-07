package dannycodejam;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

public class MenuFunctions {
	
	// File Chooser to select file name and directory to save to
	JFileChooser fc = new JFileChooser();
	FileSystemView fsw;

	public void cutText(){
		System.out.println("Cut Clicked ...\n");
	}
	public void copyText(){
		System.out.println("Copy Clicked ...\n");
	}
	public void pasteText(){
		System.out.println("Paste Clicked ...\n");
	}
	
	public void newFile(){
		System.out.println("New File Clicked ...\n");
	}
	
	/*
	 * openFile() method
	 */
	public void openFile(){
		System.out.println("Opening File ...\n");
		
		int returnVal = fc.showOpenDialog(new JPanel());
		 
		// Set up file names
		String fileName = "";
		String dirName = "";
		String absoluteName = "";
        if (returnVal == JFileChooser.APPROVE_OPTION) {
        	
        	// Absolute path is very important, or else it will only return "Desktop", ect..
        	File file = fc.getSelectedFile();
            absoluteName = file.getAbsolutePath();	

            try{
                //Build a buffer reader OF a file reader OF our file we are opening
            	FileReader fr = new FileReader(file);
            	BufferedReader br = new BufferedReader(fr);
            	try {
            		// Create a string builder and fill it with the next line from our buffer reader
            		StringBuilder sb = new StringBuilder();
            		String line = br.readLine();

            		// Continue to pull line by line out of our buffer to fill up our string builder
            		while (line != null) {
            			sb.append(line);
            			sb.append(System.lineSeparator());
            			line = br.readLine();
            		}
            		
            		// Convert our string builder's total text it has gathered into 1 lump string
            		String everything = sb.toString();
            		
            		// Set that string to our textArea's current text in the textField
            		Main.textArea.setText(everything);
            	} finally {
            		br.close();
            	}
            }catch(IOException e){
            	e.getMessage();
            }
            
        }
	}
	
	/*
	 * saveFile() method
	 */
	public void saveFile() throws IOException{
		
		// Show the dialog box
		int returnVal = fc.showSaveDialog(new JPanel());
		
		// Set up file names
		String absoluteName = "";
		
		// If approved to place a file in your desired location
        if (returnVal == JFileChooser.APPROVE_OPTION) {
        	
        	// Create a file as your desired file name
            File file = fc.getSelectedFile();
            absoluteName = file.getAbsolutePath();
        } 
        if(absoluteName != ""){
        	
        	// Traditional Writing Structure
        	File outFile = new File (absoluteName + ".txt");
    	    FileWriter fWriter = new FileWriter (outFile, true);
    	    PrintWriter pWriter = new PrintWriter (fWriter);
    	    pWriter.write(Main.textArea.getText());
    	    pWriter.close();
        }
	}
	
	public void playButtonAction() {
		System.out.println("Play Button Clicked.\n");
		
	}
	public void boldHighlightedText() {
		System.out.println("Bold Button Clicked. \n");
		
	}
	public void italicizeHighlightedText() {
		System.out.println("Italicize Button Clicked. \n");
		
	}
	public void underlineHighlightedText() {
		System.out.println("Underline Button Clicked. \n");
		
	}
}
