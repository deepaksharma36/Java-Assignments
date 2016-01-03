import javax.swing.*;

public class SwingFrame3 {
    public static void main( String args[] ) {
	JFrame window = new JFrame( "My First GUI Program" );

	JLabel label = new JLabel( "Hello World" );

	window.getContentPane().add( label );
	window.setDefaultCloseOperation(window.DISPOSE_ON_CLOSE);

	window.pack();
	window.show();
    }
} // SwingFrame
