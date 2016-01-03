/**  
  * This class implements a Swing Frame that displays "My First GUI Program"
  * in the frame name. 
  *  
  * @version    $Id$  
  *  
  * @author     Paul Tymann  
  *  
  * Revisions:  
  *     $Log$  
  */  

import javax.swing.*;
import java.awt.event.*;

public class SwingFrame {

    public static void main( String args[] ) {

	JFrame win = new JFrame( "My First GUI Program" );

	win.addWindowListener(
	    new WindowAdapter() {
	        public void windowClosing( WindowEvent e ) {
	            System.exit ( 0 );
		} 
		}
		); // end addWindowListener

	win.setSize( 250, 150 );
	win.show();
    }
} // SwingFrame
