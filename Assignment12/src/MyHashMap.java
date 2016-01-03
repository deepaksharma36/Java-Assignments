import java.net.InetAddress;

public class MyHashMap {
    
    private final int SIZE;
    private Slot map[]; 
    public  MyHashMap()
    {
 	   SIZE=256;
 	  map = new Slot[SIZE];
    }
    
   public  MyHashMap(int size)
   {
	   SIZE=size;
	   map = new Slot[SIZE];
   }
   
 
    public void put(InetAddress key, ClientRequestHandler value) {
        int hashCode = Math.abs(key.hashCode()) % SIZE;
        if (hashCode<0)
           hashCode*=-1;
        Slot slot = map[hashCode];
        if(slot != null) {
            if(slot.getKey().equals(key)) {
                slot.setValue(value) ;
            }
            
            while(slot.nextSlot != null) {
                slot =slot.nextSlot;
            }
            Slot newslot = new Slot(key, value);
            slot.nextSlot = newslot;
            
        
        } 
         else {
            Slot entryInNewBucket = new Slot(key, value);
            map[hashCode] = entryInNewBucket;
        }
        
        
        
    }
    
    public Slot get(InetAddress findKey) {
        int hashCode =findKey.hashCode() % SIZE;
        if (hashCode<0)
        	hashCode=hashCode*(-1);
        Slot slot = map[hashCode];
            if(slot.getKey()==findKey) {
                return slot;
        }

        
        while(slot != null) {
            if(slot.getKey().equals(findKey)) {
                return slot;
            }
            slot = slot.nextSlot;
        }
        return null;
    }
}
    
    
    
    class Slot {
        private final InetAddress key;
        private ClientRequestHandler value;
        public Slot nextSlot;
 
        Slot(InetAddress k, ClientRequestHandler v) {
            key = k;
            value = v;
            
        }
 
        public ClientRequestHandler getValue() {
            return value;
        }
 
        public void setValue(ClientRequestHandler value) {
            this.value = value;
        }
 
        public InetAddress getKey() {
            return key;
        }
    }
    