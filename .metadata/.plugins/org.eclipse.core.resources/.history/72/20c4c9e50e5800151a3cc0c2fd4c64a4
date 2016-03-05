/**
 * This program determines all possible subsets for the given integer number.
 * The complexity of the method is O(n*2^n)
 * 
 * @author Sharma, Deepak DS5930
 * @author Kurra, Sree Lakshmi SK9040
 * 
 */
public class Subsets {
	/**
	 * This Main Method takes number of element from command line for
	 * calculating power set.
	 * 
	 * @param args
	 *            args stores the user input from command line in a String array
	 */
	public static void main(String[] args) {
		int numberOfElements = 0;
		try {
			numberOfElements = Integer.parseInt(args[0]);
		} catch (Exception e) {
			System.out.print(e);
		}
		// converting the elements to integer format using ParseInt method
		// of the Integer class.
		int numberOfSubsets = (int) Math.pow(2, numberOfElements);
		// the number of subsets for n elements are 2^n
		int indexCounterI = 0, indexCounterJ = 0, indexCounterK = 0;
		String subSets[] = new String[numberOfSubsets];
		// SubSets string array stores all 2^n Subsets
		subSets[0] = "";
		// for generating all the combination of subsets, every element
		// can either join a subset or not which makes it 2^n combination
		// for n elements.
		// to implement the same by Using nested loop we accessed all
		// the present subsets in SubSets array and added new
		// element,hence creating further combinations and stored them in
		// Subsets array.

		for (indexCounterI = 1; indexCounterI <= numberOfElements; indexCounterI++) {
			indexCounterK = 0;
			for (indexCounterJ = 0; indexCounterJ < (int) Math.pow(2,
					(indexCounterI - 1)); indexCounterJ++) {
				subSets[(int) Math.pow(2, (indexCounterI - 1)) + indexCounterK] = subSets[indexCounterJ]
						.concat(Integer.toString((indexCounterI)));

				indexCounterK = indexCounterK + 1;
			}
		}

		int counter = 0;

		// Printing 2^n combination of elements
		System.out.print("{  ");
		for (counter = 0; counter < subSets.length; counter++) {
			if (counter == subSets.length - 1)
				System.out.print("{" + subSets[counter] + "} ");
			else
				System.out.print("{" + subSets[counter] + "} ,");

			// for printing ten subsets in each line
			if (counter % 10 == 0 && counter > 1)
				System.out.println("");
		}
		System.out.print("  }");
	}

}