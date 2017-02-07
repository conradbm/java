package dannycodejam;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Listener implements ActionListener{

	
	MenuFunctions myFunctions = new MenuFunctions();
	
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "New":
			myFunctions.newFile();
			break;
		case "Open":
			myFunctions.openFile();
			break;
		case "Save":
			try {
				myFunctions.saveFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		case "Cut":
			myFunctions.cutText();
			break;
		case "Copy":
			myFunctions.copyText();
			break;
		case "Paste":
			myFunctions.pasteText();
			break;
		case "Play":
			myFunctions.playButtonAction();
			break;
		case "Bold":
			myFunctions.boldHighlightedText();
			break;
		case "Italicize":
			myFunctions.italicizeHighlightedText();
			break;
		case "Underline":
			myFunctions.underlineHighlightedText();
			break;
		default:
			break;
		}
	}
	/*
	 * cutItem.addActionListener(listener);
		copyItem.addActionListener(listener);
		pasteItem.addActionListener(listener);
		playItem.addActionListener(listener);
		boldItem.addActionListener(listener);
		italicizeItem.addActionListener(listener);
		underlineItem.addActionListener(listener);
	 */

}
