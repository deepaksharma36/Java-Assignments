import javax.swing.*;
import java.awt.*;

public class SwingBoarder {
    public static void main( String args[] ) {
	JFrame window = new JFrame( "My First GUI Program" );
	Container content = window.getContentPane();

	content.setLayout( new BorderLayout() );
	content.add( "North", new JButton( "North" ) );
	content.add( "South", new JButton( "South" ) );
	content.add( "East", new JButton( "East" ) );
	content.add( "West", new JButton( "West" ) );
	content.add( "South", new JButton( "South" ) );
	content.add( "Center", new JButton( "Center" ) );

	window.pack();  
	window.show();
	
    }
} // SwingBoarder
