import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class SwingButton implements ActionListener {
	JFrame window = new JFrame( "My First GUI Program" );
	
	JButton button = new JButton( "Click Me!!" );
	JButton buttonnew  = new JButton( "DontClickme!!" );
    public SwingButton() {
    	window.setDefaultCloseOperation(window.DISPOSE_ON_CLOSE);
    	button.addActionListener(this);
    	buttonnew.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		System.out.println("I had no place so got implemeted in inner class");
		}
		});
    	window.getContentPane().setLayout(new BorderLayout());
    	window.getContentPane().add("North",buttonnew);
    	window.getContentPane().add( "South",button );
    	
    	window.pack();
    	window.show();

	}
	public static void main( String args[] ) {
		SwingButton aSwingButton = new SwingButton();
		
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("THis is deepaks work");
		
		
	}
} // SwingFrame
