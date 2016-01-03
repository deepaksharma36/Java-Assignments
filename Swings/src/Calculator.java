/**  
  * This class implements the model (logic) of a simple 4 function  
  * calculator.  The calculator provides integer only arithmetic  
  * and a clear key.  
  *  
  * @version    $Id$  
  *  
  * @author     Paul Tymann  
  *  
  * Revisions:  
  *     $Log$  
  */  
  
public class Calculator {  
  
    // Valid digits  
  
    private static final String DIGITS = "0123456789" ;  
  
    private boolean firstDigit = true;      // State for State machine  
    private int lOperand = 0;               // "Left" operator  
    private String operator = "=";          // Last operator  
  
    private CalcGui gui;                    // The "view"  
  
    /**  
      * Constructor for a Calculator.  
      *  
      * @param String name to go into window frame.  
      */  
  
    public Calculator( String name ) {  
        gui = new CalcGui( this, name );  
    }  
  
    /**  
      * Routine to handle button presses.  Everytime the view detects  
      * that a button has been pressed, this routine will be called.  
      * The parameter will be the label that appears on the button  
      * (i.e. "0", "1", "2", ... "C")  
      *  
      * @param String label of the button pressed.  
      */  
  
    public void handleButton( String keyPressed ) {  
	// Figure out what type of button was pressed and  
	// take care of it  
  
	// The first digit pressed after an operand marks the  
	// beginning of a new number.  
  
	if ( DIGITS.indexOf( keyPressed ) != -1 ) { // It is a digit  
	    if ( firstDigit ) {                     // first digit is just  
		firstDigit = false;                 // stuffed into the  
		gui.setDisplay( keyPressed );       // display  
	    }  
	    else {
		gui.setDisplay( gui.getDisplay() + keyPressed ); // add to end  
	    } // end inner if/else
	}  
	else if ( keyPressed.equals( "C" ) ) {      // Clear  
	    firstDigit = true;  
	    lOperand = 0;  
	    operator = "=";  
	    gui.setDisplay( "0" );  
	}  
	else {                                      // An operator  
	    if ( !firstDigit ) {  
		compute( Integer.parseInt( gui.getDisplay() ) );  
		firstDigit = true;  
	    }  
  
	    operator = keyPressed;  
	}  // end else
    }  // end handle button
  
    /**  
     * Appply the last operator added to the numbers entered by  
     * the user and display the result on the calculator.  
     *  
     * @param rOperand the last number entered by the user  
     */  
      
    private void compute( int rOperand ) {  
	// The variable lOperand is used to store the number that was  
	// entered by the user before the last operator.  
  
        switch( operator.charAt( 0 ) ) {  
	case '=':  
	    lOperand = rOperand ;  
	    break ;  
	case '+':  
	    lOperand = lOperand + rOperand ;  
	    break ;  
	case '-':  
	    lOperand = lOperand - rOperand ;  
	    break ;  
	case 'X':  
	    lOperand = lOperand * rOperand ;  
	    break ;  
	case '/':  
	    lOperand = lOperand / rOperand ;  
	    break ;  
	}  //end switch
	  
	// Update the display  
  
	gui.setDisplay( String.valueOf( lOperand ) );  
    }  
  
    /**  
     * Create a calculator and let it do its thing.  
     *  
     * @param args[] command line arguments (ignored)  
     */  
      
    public static void main( String args[] ) {  
	new Calculator( "JavaCalc" );  
    }  
  
} // Calculator  
