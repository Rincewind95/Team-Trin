package master;

import java.util.HashMap;
import java.util.HashSet;

public class Client {
	
	int clientID;
    int rootDist;	// Datacentre latency
    HashMap<Cache, Integer> parents = new HashMap<Cache, Integer>();	// Cache & latency to cache
    
    HashMap<File, Integer> requestCnt = new HashMap<File, Integer>();
	
	public Client(int id) {
		this.clientID = id;
	}
	
	public void updateParents(Cache c, int i) {
		parents.put(c, i);
	}
    
}
