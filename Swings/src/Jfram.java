import javax.swing.JFrame;


public class Jfram {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame j = new JFrame("my fram");
		j.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//j.setSize(320,240);
		j.setVisible(true);
		pannel aPannel = new pannel();
		j.add(aPannel);
		jlable aJlable = new jlable();
		aJlable.setText("hello everyone");
		//j.add(aJlable);

		j.getContentPane().add(aJlable);
		j.pack();

	}

}
