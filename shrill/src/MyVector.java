public class MyVector<E> {
     E[] data = null;
     public MyVector( int size ) {
          data = (E[])new Object[size] ;
     }
     public E get( int i ) {
          return data[i];
     }
     public void set( int i, E val ) {
          data[i] = val;
     }
}