import javax.swing.*;
import java.awt.*;

public class SwingFlowLayout {
    public static void main( String args[] ) {
	JFrame window = new JFrame( "My First GUI Program" );

	window.getContentPane().setLayout( new FlowLayout() );

	for ( int i = 0; i < 10; i++ )
	    window.getContentPane().add( 
		new JButton( String.valueOf( i ) ) );

	window.pack();
	window.show();
    }
} // SwingFrame
