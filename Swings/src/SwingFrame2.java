import javax.swing.*;

public class SwingFrame2 {
    public static void main( String args[] ) {
	JFrame window = new JFrame( "My First GUI Program" );

	window.setSize( 250, 150 );
	window.show();
	window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
} // SwingFrame
