import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class JTableCreatingDemo {
  public static void main(String args[]) {
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    Object rowData[][] = { { "Row1-Column1", "Row1-Column2", "Row1-Column3" },
        { "Row2-Column1", "Row2-Column2", "Row2-Column3" } };
    Object columnNames[] = { "File Name", "Packet Received", "Total Packets" };
    JTable table = new JTable(rowData, columnNames);

    JScrollPane scrollPane = new JScrollPane(table);
    frame.add(scrollPane, BorderLayout.CENTER);
    frame.setSize(700, 150);
    frame.setVisible(true);
    try {
		Thread.sleep(600);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    Object newRowData[][] = { { "X", "B", "Row1-Column3" },
            { "Row2-Column1", "N", "T" } };
    
    //table.tableChanged(new TableModelEvent(model, n)); 
    table.setModel(new DefaultTableModel(newRowData,columnNames));
  }
}