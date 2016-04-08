/**
 * This interface will be implemented by all the implementations of Algorithm for LCS 
 * @author Sharma, Deepak DS5930
 * @author Kurra, Sree Lakshmi SK9040
 *
 */
public interface Algo {
/**
 * Profiler will call this method of each implementation of algorithm
 * @param stringA First String
 * @param stringB Second String
 * @return an Object of result class
 */
public Result execute(String stringA,String stringB);
}
