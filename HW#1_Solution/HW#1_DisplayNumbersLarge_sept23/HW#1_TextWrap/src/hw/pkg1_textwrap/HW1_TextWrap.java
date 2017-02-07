/*
 * HW1_TextWrap.java
 * Due: September 23rd, 2015
 * Written by: Blake Conrad
 * Email: conradbm@uindy.edu
 * Content: 
 * This program takes user input and converts that user input to a textArea
 * using:
 *         outputTextArea.setLineWrap(true);
 *         outputTextArea.setWrapStyleWord(true);
 */

package hw.pkg1_textwrap;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class HW1_TextWrap extends JFrame {

    // For numberical storage
    private int n;
    private int[] arr;
    
    // For GUI
    private JPanel mainPanel;
    private JPanel subPanelNorth;
    private JPanel subPanelCenter;
    private JLabel messageLabel;
    private JTextField inputTextField;
    private JTextArea outputTextArea;
    private JButton clickButton;
    
    public static void main(String[] args) {
       
        new HW1_TextWrap();
        
    }
    
    public HW1_TextWrap()
    {
        init();
    }
    void init()
    {
        setupWindow();
    }
    
    void setupWindow()
    {
        // Construct Panels    
        constructPanels();
        
        // add panel to our current frame
        this.add(mainPanel);
        
        // General housekeeping
        housekeeping();

    }    
    
    private void housekeeping()
    {
        super.setSize(100,100);
        super.setResizable(false);
        this.setTitle("List Divisors");
        this.setSize(new Dimension(400,300));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    public void constructPanels()
    {
        // Main Panel Setup
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Create Sub Panel NORTH
        createSubPanelNorth();
        
        // Create Sub Panel CENTER
        createSubPanelCenter();

        // Add Sub Panels to Main Panel
        mainPanel.add(subPanelNorth, BorderLayout.NORTH);
        mainPanel.add(subPanelCenter, BorderLayout.CENTER);
    }
    
    private class ButtonListener implements ActionListener
    {
            
        public void actionPerformed(ActionEvent e) 
        {
            String input;
            int parsedInput;

            input = inputTextField.getText();
            messageLabel.setText("You entered: " + input);
            parsedInput = Integer.parseInt(input);
            findDivisors(parsedInput);
        }

        private void findDivisors(int n)
        {
            StringBuilder sb = new StringBuilder();
            for(int index = 1; index <= n; ++index)
            {
                // If the number goes in evenly
                if(n % index == 0)
                {
                sb.append(Integer.toString(index));
                sb.append(" ");
             }
          }
        outputTextArea.setText(sb.toString());
        }
    } // end action listener
        
    
        // center: JTextArea;
        void createSubPanelCenter() 
        {
            // sub panel center
            subPanelCenter = new JPanel();
            subPanelCenter.setLayout(new BorderLayout());
        
            // components of subpanel center
            outputTextArea = new JTextArea(10,10);
        
            // position as desired
            subPanelCenter.add(outputTextArea, BorderLayout.CENTER);
        
            // set all components desired specs that lie within this subpanel
            setSubPanelCenterSpecs();
        }
        
        void setSubPanelCenterSpecs(){
            
        // sub Panel center components spec set area
            
        outputTextArea.setSize(10,this.getWidth());
        outputTextArea.setLineWrap(true);
        outputTextArea.setWrapStyleWord(true);
        }
        
        //east: JLabel
        //west: JTextField
        //center: JButton
        void createSubPanelNorth() 
        {
            // Create subpanel
            subPanelNorth = new JPanel();   
            subPanelNorth.setLayout(new BorderLayout());
            
            // components of subpanel north
            messageLabel = new JLabel("Enter a number");
            inputTextField = new JTextField(10); // 10=width
            clickButton = new JButton("Click");
            clickButton.addActionListener(new ButtonListener()); 
        
             // position as desired
            subPanelNorth.add(messageLabel, BorderLayout.EAST);
            subPanelNorth.add(clickButton, BorderLayout.CENTER);
            subPanelNorth.add(inputTextField, BorderLayout.WEST);
        
            // set all components desired specs that lie within this subpanel
            setSubPanelNorthSpecs();
        }
        
        void setSubPanelNorthSpecs()
        {    
            // sub Panel north components spec set area
            inputTextField.setSize(10,10);
            clickButton.setSize(10, 10);
        }
}
