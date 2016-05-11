import java.awt.BorderLayout;


import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


 class GUI {
	JFrame frame = new JFrame();
	Object columns[] = { "File Name", "Packet Received", "Total Packets" };
	JTable torrentDataTable;
	GUI(){init();}
	 
	private void init()
	{
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    torrentDataTable = new JTable();
	    JScrollPane scrollPane = new JScrollPane(torrentDataTable);
	    frame.setSize(700, 150);
	    frame.add(scrollPane, BorderLayout.CENTER);
	    frame.setVisible(true);
	
	}
		 
		 
	public void inform(Object[][] data){
		//int progress=numberofPeices/totalNumberofPeices;
		torrentDataTable.setModel(new DefaultTableModel(data,columns));	
		
	}	 

}
