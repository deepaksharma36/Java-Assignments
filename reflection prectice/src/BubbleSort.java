public class BubbleSort {

  public static void printIt(String aCollection[] )     {
          for (int index=0; index<aCollection.length; index++)
                System.out.println(index + "\t" + aCollection[index] );
  }

  public static void sort(String aCollection[] )     {
    for (int index=0; index < aCollection.length - 1; index++)     {
      for (int walker=0; walker < aCollection.length - 1; walker++)  {
        if ( aCollection[walker].compareTo(aCollection[walker+1]) > 0 )        {
                        String tmp = aCollection[walker];
                        aCollection[walker] = aCollection[walker + 1];
                        aCollection[walker+1] = tmp;
        }
      }
    }
  }

  public static void main( String args[] ) {
    String[] aCollection = new String[3];
    aCollection[0] =  "c";
    aCollection[1] =  "b";
    aCollection[2] =  "a";
        
    sort(aCollection);
    printIt(aCollection);
  }
}
