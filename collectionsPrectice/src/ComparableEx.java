/*
 * "Note: this class has a natural ordering
 *        that is inconsistent with equals.
 */

import java.util.*;

public class ComparableEx implements Comparable {
    protected String  firstName;
    protected String  lastName;

    public ComparableEx(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName  = lastName;
    }

    public boolean equals(Object o) {
        if (!(o instanceof ComparableEx))
            return false;
        ComparableEx n = (ComparableEx)o;
        return firstName.equals(n.firstName)	&&
               lastName.equals(n.lastName);
    }

    public int compareTo(Object o) {
        ComparableEx n = (ComparableEx)o;	// cast execption
	return lastName.compareTo(lastName);
    }
    public String toString()	{
		return firstName + "/" + lastName;
    }

    public static void main(String args[]) {
        ComparableEx n[] = {
            new ComparableEx("James", 	"Bond"),
            new ComparableEx("James", 	"Bond"),
            new ComparableEx("Jack", 	"Blues"),
            new ComparableEx("Elwood", 	"Blues")
        };
        List l = Arrays.asList(n);
        Collections.sort(l);
        System.out.println(l);
    }
}

