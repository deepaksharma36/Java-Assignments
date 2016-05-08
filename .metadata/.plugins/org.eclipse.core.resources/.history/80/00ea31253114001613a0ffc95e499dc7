import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class SwingGridLayout {
    public static void main( String args[] ) {
	JFrame window = new JFrame( "My First GUI Program" );
	window.addWindowListener(new WindowAdapter() {
		
	});

	window.getContentPane().setLayout( new GridLayout(10,1) );
	//window.getContentPane().setLayout( new GridLayout(10,10) );
	//window.getContentPane().setLayout( new GridLayout(4,4) );

	for ( int i = 0; i < 10; i++ )
	    window.getContentPane().add( 
		new JButton( String.valueOf( i ) ) );

	window.pack();
	window.show();
    }
} // SwingFrame
