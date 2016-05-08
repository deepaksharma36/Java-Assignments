import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class SwingGridLayout implements ActionListener {
	
	public void loadGUI(){
		JFrame window = new JFrame("LCS Profiler");
		window.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		JPanel[][] panelHolder = new JPanel[5][5];    
		String[] algoSet = { "Naive Recursive", "Recursive Memoization", "HSB","Dynamic" };
		window.getContentPane().setLayout(
		new GridLayout(5,5, 0,0));
		// window.getContentPane().setLayout( new GridLayout(10,10) );
		// window.getContentPane().setLayout( new GridLayout(4,4) );
		for(int m = 0; m < 5; m++) {
			   for(int n = 0; n < 5; n++) {
			      panelHolder[m][n] = new JPanel();
			      window.getContentPane().add(panelHolder[m][n]);
			   }}
		panelHolder[0][0].add(new JLabel("Select Algorithm"));
		panelHolder[0][1].add(new JCheckBox("Naive Recursive"));
		panelHolder[0][2].add(new JCheckBox("Recursive Memoization"));
		panelHolder[0][3].add(new JCheckBox("HSB"));
		panelHolder[0][4].add(new JCheckBox("Dynamic"));

		panelHolder[1][0].add(new JLabel("Number of test Cases"));
		panelHolder[1][1].add(new JTextField("100"));
		
		panelHolder[2][0].add(new JLabel("Types of Elements"));
		panelHolder[2][1].add(new JCheckBox("Binary[0-1]"));
		panelHolder[2][2].add(new JCheckBox("alphabetics[a-z]"));
		panelHolder[2][3].add(new JCheckBox("DNA[A,C,G,T]"));
		panelHolder[2][4].add(new JCheckBox("Numaric[0-9]"));
		
		panelHolder[3][0].add(new JLabel("Path/Name of Experiment File"));
		panelHolder[3][1].add(new JTextField("Results.xls"));
	    
		JButton submit =new JButton(String.valueOf("Start Experiment"));
		submit.addActionListener(this);
		panelHolder[3][4].add(submit);
		
		
		window.pack();
		window.show();
	}
	
	public static void main(String args[]) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}
} // SwingFrame
