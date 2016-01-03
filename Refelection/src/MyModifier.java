                 import java.lang.reflect.*;
           
                 public final class MyModifier {
           
                         void printName(Object o) {
                                 Class c = o.getClass();
                                 String s = c.getName();
                                 System.out.println(s);
                         }
          
                        public void printModifiers(Object o) {
                        Class c = o.getClass();
                        int m = c.getModifiers();
                        System.out.println(m);
                        if (Modifier.isPrivate(m))
                                System.out.println("private");
                        if (Modifier.isPublic(m))
                                System.out.println("public");
                        if (Modifier.isAbstract(m))
                                System.out.println("abstract");
                        if (Modifier.isFinal(m))
                                System.out.println("final");
                        }
          
                        public static void main(String[] args) {
                                MyModifier aM = new MyModifier();
                                aM.printName(aM);
                                aM.printModifiers(aM);
                       }
          
                }