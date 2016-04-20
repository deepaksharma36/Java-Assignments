/**
 * This is an implementation of Abstract Factory Design Pattern 
 * at the run time this static method will provide object of 
 * Requisite implementation for LCS algorithm
 * @author Sharma, Deepak DS5930@g.rit.edu
 * @author Kurra, Sree Lakshmi SK9040@g.rit.edu
 *
 */
class AlgoFactory {
/**
 * Create an Object of the implementation of requisite algorithm 
 * @param algoType Name of the algorithm 
 * @return Object of implementation
 */
       public static Algo createAlgo (String algoType) {
       if (algoType. equalsIgnoreCase ("Naive")){
              return new sampleAlgo();
       }else if(algoType. equalsIgnoreCase ("Dynamic")){
    	   return new sampleAlgo();
       }else if(algoType. equalsIgnoreCase ("Recursive")){
    	   return new sampleAlgo();
        }
       else if(algoType. equalsIgnoreCase ("Memoization")){
    	   return new sampleAlgo();
        }
       else if(algoType. equalsIgnoreCase ("HSB")){
    	   return new sampleAlgo();
       }
       throw new IllegalArgumentException("No such Algorithm exist");
       }
}


