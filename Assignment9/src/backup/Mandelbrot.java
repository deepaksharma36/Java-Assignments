package backup;
// original from: http://rosettacode.org/wiki/Mandelbrot_set#Java
// modified for color
//import statements are here
import java.awt.Color;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

/**
 * Converted Mandelbrot.java into a multi threaded version by creating each
 * pixel through one new thread.
 * 
 * @modified deepak sharma ds5930
 * @modified sree lakshmi kurra sk9040
 */
public class Mandelbrot extends JFrame implements Runnable {

	private final int MAX = 5000;
	private final int LENGTH = 800;
	private final double ZOOM = 1000;
	private BufferedImage theImage;
	private int[] colors = new int[MAX];
	private boolean firstTime=true;
	int counter=0;
	// ThreadComplitionCount keeps track of the number of
	// the threads for those whose creatSet2 methods execution is completed
	private volatile int ThreadComplitionCount = 0;
	private volatile int ThreadStartCount = 0;
    private int NumberOfProcessers=Runtime.getRuntime().availableProcessors();
    private Thread[] aMandelbrotsArray=new Thread[3];
    private int iCounter=0,jCounter=0,kCounter=0;
    private volatile boolean[] isWorkCompleted=new boolean[3];
    Object[] locks = new Object[3];
    boolean run=true;
    
    
	/**
	 * this constructor initializes parameters for painting.
	 */
	public Mandelbrot() {
		super("Mandelbrot Set");
		 
		initColors();
		setBounds(100, 100, LENGTH, LENGTH);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		while(iCounter<3)
			aMandelbrotsArray[iCounter++]= new Thread(this);
		while(jCounter<3)
		    locks[jCounter++] = new Object();
		while(kCounter<3)
		    isWorkCompleted[kCounter++] = true;
	}

	/**
	 * this method implements the run method of the runnable interface. creating
	 * each pixel through a new thread by invoking creatSet2 method for parallel
	 * processing.
	 */
	public void run() {
		
		while(run){
			String xy = Thread.currentThread().getName();
			String[] XY = xy.split(",");
			int x=Integer.parseInt(XY[0]);
			int y=Integer.parseInt(XY[2]);
			int z=Integer.parseInt(XY[3]);
		    //System.out.println(XY[0]+XY[1]);
		synchronized (locks[z]) {
				
	  
	    	if(!isWorkCompleted[z])
	    	{
		creatSet2(x,y);
		isWorkCompleted[z]=true;}
			
		}
		}
		}
	

	/**
	 * this creatSet2 method creates pixel wise painting.
	 * 
	 * @param x
	 *            x coordinate of the pixel
	 * @param y
	 *            y coordinate of the pixel
	 */
	public void creatSet2(int x, int y) {
		double zx, zy, cX, cY;
		zx = zy = 0;
		cX = (x - LENGTH) / ZOOM;
		cY = (y - LENGTH) / ZOOM;
		int iter = 0;
		double tmp;
		while ((zx * zx + zy * zy < 10) && (iter < MAX - 1)) { // this is the
																// part for the
																// parallel part
			tmp = zx * zx - zy * zy + cX; // this is the part for the parallel
											// part
			zy = 2.0 * zx * zy + cY; // this is the part for the parallel part
			zx = tmp; // this is the part for the parallel part
			iter++; // this is the part for the parallel part
		} // this is the part for the parallel part
		if (iter > 0) // this is the part for the parallel part
			theImage.setRGB(x, y, colors[iter]); // this is the part for the
													// parallel part
		else
			// this is the part for the parallel part
			theImage.setRGB(x, y, iter | (iter << 8)); // this is the part for
														// the parallel part
	}

	/**
	 * This method creates one new thread for each pixel and calls createSet2
	 * method by invoking the start method of the pixel thread.
	 */
	public void createSet() {
		System.out.println("I was here");
		theImage = new BufferedImage(getWidth(), getHeight(),
				BufferedImage.TYPE_INT_RGB);

		for (int y = 0; y < getHeight(); y++) {
			for (int x = 0; x < getWidth(); x++) {
				
				synchronized () {
					
				}
				
				if(firstTime)
				{	aMandelbrotsArray[counter].setName(Integer.toString(x) + ","
						+ Integer.toString(y)+","+counter);
					aMandelbrotsArray[counter].start();
					 isWorkCompleted[counter]=false;
					counter++;

					 
					if (counter==3) firstTime=false;
				}
				else{
				 out: while(true)
				 {
					 for(int i=0;i<3;i++)
					 {
						 if(isWorkCompleted[i])
						 { 
							 aMandelbrotsArray[i].setName(Integer.toString(x) + ","
										+ Integer.toString(y)+","+i);
							 isWorkCompleted[i]=false; 
							 break out;
						 }
					 }
				 }}
				//System.out.println(x+" "+y);
		}
		}
		repaint();
		// waiting for the executions of all threads so that picture can be
		// shown by repaint method.
				
	}

	/**
	 * initialization of the painting
	 */
	public void initColors() {
		for (int index = 0; index < MAX; index++) {
			colors[index] = Color.HSBtoRGB(index / 256f, 1, index
					/ (index + 8f));
		}
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(theImage, 0, 0, this);
	}

	/**
	 * in this main method we create an object of the Mandelbrot class to invoke
	 * its createSet method to paint.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
	  System.out.println("This is running");
		Mandelbrot aMandelbrot = new Mandelbrot();
		System.out.println("This is done");
		aMandelbrot.setVisible(true);
		System.out.println("This is done");
		aMandelbrot.createSet();
	    aMandelbrot.repaint();
		
	}
}
