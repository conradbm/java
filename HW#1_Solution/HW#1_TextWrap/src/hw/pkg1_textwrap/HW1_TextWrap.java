/*
 * HW1_TextWrap.java
 * Due: September 23rd, 2015
 * Written by: Blake Conrad
 * Email: conradbm@uindy.edu
 * Content: 
 * This program takes user input and converts that user input to a textArea
 * using JTextArea.myObject.textWrap(true);
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

/**
 *
 * @author conradbm
 */
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
       
        HW1_TextWrap test = new HW1_TextWrap();
        
    }
    
    public HW1_TextWrap(){
        super.setSize(100,100);
        super.setResizable(false);
        init();
    }
    
    void init()
    {
        setupWindow();
    }
    
    void setupWindow()
    {
        // Construct panels
        constructPanels();
        
        // add mainPanel to our current frame
        this.add(mainPanel);
        
        // general housekeeping
        this.setTitle("List Divisors");
        this.setSize(new Dimension(400,300));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }    
    
    public void constructPanels()
    {
        // JPanel Specifications
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Create Subpanel North
        createSubPanelNorth();
        
        // Create Subpanel Center
        createSubPanelCenter();

        // Add subpanels to mainPanel
        mainPanel.add(subPanelNorth, BorderLayout.NORTH);
        mainPanel.add(subPanelCenter, BorderLayout.CENTER);
    }
    
    private class ButtonListener implements ActionListener
    {
            
            public void actionPerformed(ActionEvent e) 
            {
                String input;
                int n;
                
                input = inputTextField.getText();
                messageLabel.setText("You entered: " + input);
                n = Integer.parseInt(input);
                findDivisors(n);
                
                /*
                 try{ int i = parseInt(input); }
                    catch (numberconversionexception e){ 
                        try{ long l = Long.parseLong(input); }}
                            catch(numberconversionexception e){ 
                                try{ double d = Double.parseDouble(input);}}
                                    catch(numberconversionexception e){ System.out.println("There is actually a problem."); }
                    }catch(numberconversionexception e){ System.out.println("There is actually a problem");}
                 */
                
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
            } // end findDivisors
    } // end action listener
    
    
    /***************************/
    /* CREATE SUBPANEL METHODS */
    /***************************/
    
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
    
        
    void createSubPanelNorth()
    {
        // sub panel north
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
    
    /*****************/
    /* SPECS METHODS */
    /*****************/
    
    void setSubPanelCenterSpecs()
    {
        
        // sub Panel center components spec set area
        
        outputTextArea.setSize(10,this.getWidth());
        outputTextArea.setLineWrap(true);
        outputTextArea.setWrapStyleWord(true);
    }
    
    void setSubPanelNorthSpecs()
    {
        // sub Panel north components spec set area
        inputTextField.setSize(10,10);
        clickButton.setSize(10, 10);
    }
}
