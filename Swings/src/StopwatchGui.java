/*  
 * StopwatchGui.java  
 *  
 * Version:  
 *     $Id$  
 *  
 * Revisions:  
 *     $Log$  
 */  
  
import javax.swing.*;  
import java.awt.event.*;  
import java.awt.*;  
  
/**  
 * A simple GUI based stop watch application  
 *  
 * @author        Paul Tymann (ptt@cs.rit.edu)  
 */  
  
public class StopwatchGui implements ActionListener {  
    private JFrame win;          // The main window for the app  
    private TimerLabel theTime;  // Place to dosplay the time  
  
    JButton lapReset;            // Lap/Reset button  
    JButton startStop;           // Start/Stop button  
  
    boolean stopped = true;      // True if the stopwatch is running  
    boolean lap = false;         // True if the stopwatch is in lap mode  
  
    /**  
     * Create a new stop watch and display the resulting GUI.  
     *  
     * @param title title displayed in the title bar  
     */  
      
    public StopwatchGui( String title ) {  
	win = new JFrame( title );  
  
	// Frames do not handle close by default ...  
  
	win.addWindowListener(  
	    new WindowAdapter() {  
		public void windowClosing( WindowEvent event ) {  
		    System.exit( 0 );  
                }  
            }  
        );  
  
	// Create the label to display the time  
  
	theTime = new TimerLabel();  
	theTime.setFont( new Font( "Courier", Font.BOLD, 25 ) );  
  
	// Create the buttons and put them in a panel  
  
	JPanel buttonPanel = new JPanel();  
  
	lapReset = new JButton( "Reset" );  
	lapReset.setActionCommand( "lapReset" );  
	lapReset.addActionListener( this );  
  
	startStop = new JButton( "Start" );  
	startStop.setActionCommand( "startStop" );  
	startStop.addActionListener( this );  
  
	buttonPanel.add( lapReset );  
	buttonPanel.add( startStop );  
  
	// Assemble the frame  
  
	Container content = win.getContentPane();  
  
	content.setLayout( new BorderLayout() );  
  
	content.add( "North", theTime );  
	content.add( "South", buttonPanel );  
  
	// Display it  
   
	win.pack();  
	win.show();  
    }  
  
    /**  
     * Handle the buttons on the GUI.  
     *  
     * When the watch is not running the reset and start buttons  
     * are active.  Pressing the start button will start the watch  
     * from the current time.  Pressing reset does not start the watch,  
     * it simply resets the time back to 0.  
     *  
     * If the watch is running the lap and stop buttons will be displayed.  
     * When the lap button is pressed, the display will freeze at the current  
     * time but the watch will continue to run.  Pressing lap a second time  
     * will cause the current time to be displayed.  Pressing stop will  
     * stop the watch.  
     *  
     * @param e the event that caused the listener to become active  
     */  
      
    public void actionPerformed( ActionEvent event ) {  
	// Get the string that identifies the button that was pressed  
  
	String cmd = event.getActionCommand();  
  
	if ( stopped ) {  
	    if ( cmd.equals( "startStop" ) ) { 
		startTheTimer();  
	    } else {
		resetTheTimer();  
	    }
	} else {  
	    if ( cmd.equals( "startStop" ) ) { 
		stopTheTimer();  
	    } else {  
		if ( lap ) { 
		    startTheTimer();  
		} else { 
		    pauseTheTimer();  
		}// end inner if/else
	    }  // end else
	}  // end outer if/else
    }  // end actionPerformed
  
    /**  
     * Start the watch running.  Set the labels on the buttons to  
     * stop and lap.  
     */  
      
    private void startTheTimer() {  
	startStop.setText( "Stop" );  
	lapReset.setText( "Lap" );  
	  
	stopped=false;  
	lap=false;  
	  
	theTime.timerStart();  
    }  
  
    /**  
     * Reset the timer.  Set the labels on the buttons to start  
     * and reset.  
     */  
      
    private void resetTheTimer() {  
	startStop.setText( "Start" );  
	lapReset.setText( "Reset" );  
	  
	stopped=true;  
	lap=false;  
	  
	theTime.timerReset();  
    }  
  
    /**  
     * Stop the timer.  Set the labels on the buttons to start  
     * and reset.  
     */  
      
    private void stopTheTimer() {  
	theTime.timerStop();  
	theTime.updateText();  
	  
	startStop.setText( "Start" );  
	lapReset.setText( "Reset" );  
	stopped=true;  
    }  
      
    /**  
     * Freeze the display but let the watch continue to run.  
     */  
      
    private void pauseTheTimer() {  
	theTime.timerNoUpdate();  
	lap=true;  
    }  
  
    /**  
     * Create a stopwatch and let it run!!  
     *  
     * @param args[] command line arguments (ignored).  
     */  
      
    public static void main( String args[] ) {  
	StopwatchGui gui = new StopwatchGui( "Demo" );  
    }  
  
} // StopWatchGui  
