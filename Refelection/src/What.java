       import java.lang.reflect.*;
 
       class What {
               public int publicVar;;
               private int privateVar;;
               static int staticVar;;
 
               static void printFieldNames(Object o) {
                      Class c = o.getClass();
                      
                      Class What =  What.class;
                      Field[] publicFields = c.getFields();
                      for (int i = 0; i < publicFields.length; i++) {
                              String fieldName = publicFields[i].getName();
                              Class typeClass = publicFields[i].getType();
                              String fieldType = typeClass.getName();
                              System.out.println("\tName: " + fieldName +
                                  ", Type: " + fieldType);
                      }
              }

              public static void main(String[] args) {
                      String aS = new String();
                      Thread aT = new Thread();
                      What aW = new What();
                      System.out.println("String: ");
                      printFieldNames(aS);

                      System.out.println("Thread: ");
                      printFieldNames(aT);

                      System.out.println("What: ");
                      printFieldNames(aW);
              }
      }