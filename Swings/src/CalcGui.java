/**  
  * A Graphic view for a simple 4 function calculator.  
  *  
  * @version    $Id$  
  *  
  * @author     Paul Tymann  
  *  
  * Revisions  
  *     $Log$  
  */  
  
import javax.swing.*;  
import java.awt.*;  
import java.awt.event.*;  
  
public class CalcGui implements ActionListener {  
    // Labels for the buttons  
  
    private static final String labels = "789X456/123-0C=+" ;  
  
    private static final int NUMROWS = 4 ;       // Rows and cols for buttons  
    private static final int NUMCOLS = 4 ;  
  
    private Calculator myCalc ;                  // The "model"  
  
    private JLabel display ;                     // The display  
  
    /**  
      * Constructor for the calculator view.  
      *  
      * @param  Calculator      the model for this view.  
      * @param  String          name for calculator frame.  
      */  
  
    /**  
     * Create a new Calculator GUI (view).  
     *  
     * @param calc the model for this view  
     * @param name stirng to appear as the title in the main frame  
     */  
      
    public CalcGui( Calculator calc, String name ) {  
	// Keep a reference to the model  
  
        myCalc = calc ;  
  
	// A Frame for the calculator  
  
	JFrame win = new JFrame( name );  
  
	// Frames do not handle close by default ...  
  
	win.addWindowListener(  
	    new WindowAdapter() {  
	        public void windowClosing( WindowEvent e ) {   
                    System.exit( 0 );   
                }  
	    }  
        );  
  
	// Create the button panel  
  
	JPanel buttons = new JPanel() ;  
	buttons.setLayout( new GridLayout( NUMROWS, NUMCOLS ) );  
  
	JButton buttn ;  
  
	// Create the buttons, place them into the panel, and  
	// register this object as the action listener for the  
	// buttons  
  
	for ( int i = 0 ; i < labels.length() ; i++ ) {  
	    buttn = new JButton( labels.substring( i, i + 1 ) );  
	    buttn.addActionListener( this );  
	    buttons.add( buttn );  
	}  
  
	// Create the display  
  
	display = new JLabel( "0", JLabel.RIGHT );  
  
	// "Assemble" the calculator  
  
	Container content = win.getContentPane();  
  
	content.setLayout( new BorderLayout() );  
  
	content.add( "North", display );  
	content.add( "Center", buttons );  
  
	// Display it and let the user run with it :-)  
  
	win.pack();  
	win.show();  
    }  
  
    /**  
      * Return the current value in the display.  
      *  
      * @return A string representation of the current display.  
      */  
  
    public String getDisplay() {  
      return new String( display.getText() );  
    }  
  
    /**  
      * Change the display.  
      *  
      * @param String New value to be displayed on the calculator  
      */  
  
    public void setDisplay( String text ) {  
      display.setText( text );  
    }  
  
    /**  
      * Button event handler.  Calls handleButton() in the model  
      * to do the actual processing.  
      *  
      * @param ActionEvent The button event  
      */  
  
    public void actionPerformed( ActionEvent event ) {  
	String str = event.getActionCommand();  // Label of button pressed  
  
        myCalc.handleButton( str );  
    }  
  
} // CalcGui  
