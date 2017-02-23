package master;

import java.util.HashSet;

public class Cache {
	
	int cacheID;
    int totalVolume;
    int volumeLeft;
    HashSet<Client> clients = new HashSet<Client>();
    HashSet<File> files = new HashSet<File>();	// They work out
    HashSet<HeapElement> heapBits = new HashSet<HeapElement>();
	
	public Cache(int id, int s) {
		this.cacheID = id;
		this.totalVolume = s;
		this.volumeLeft = s;
		
	}
	
	public void updateClients(Client c) {
		this.clients.add(c);
	}
	
    
}
