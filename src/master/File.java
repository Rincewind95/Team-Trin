package master;

import java.util.HashMap;
import java.util.HashSet;

public class File {
	int fileID;
    int size;

    HashSet<HeapElement> heapBits;
    HashMap<Client, Integer> requestCnt = new HashMap<Client, Integer>();
    
    public File(int id, int s) {
    	this.fileID = id;
    	this.size = s;
    	
    }
    
    public void addRequest(Client c, int i) {
    	this.requestCnt.put(c, i);
    }
    
    
}
