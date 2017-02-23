package master;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Algo {
	
	int cacheCnt;
    int fileCnt;
    Client[] clients;
    static ArrayList<HeapElement> heap;
    static int heapSize = 0; //TO CHANGE
    
    static Cache [] caches;
    static File [] files;
    static HashMap<HeapElement, Integer> elementToIdx;
	
    int [][] valueMatrix = new int[cacheCnt][fileCnt];
	
    public Algo() {
    	this.parse(getClass().getResourceAsStream("kittens.in"));
		
	}
    
 
    
    
    public static void main(String[] args)   {
        // init part
    	Algo a = new Algo();
        heap = new ArrayList<HeapElement>();
        elementToIdx = new HashMap<HeapElement, Integer>();
    }
        

    void intialiseMatrix() {
        for(int i = 0; i < cacheCnt; i++)
        {
            for(File f : files)
            {
                HeapElement h = new HeapElement(caches[i], f);
                heap.add(h);
                heapSize++;
                percolate(heapSize-1);
                elementToIdx.put(h, heap.size()-1);
            }
        }
        
        while(heapSize > 0)
        {
            HeapElement top = pop();
            Cache c = top.cacheRef;
            File f = top.fileRef;
            
            if(c.volumeLeft >= f.size)
            {
                // it fits so we consider it
            
                // modify all the relevant elements
                HashSet<HeapElement> toConsider = new HashSet();
                for(Client cli : c.clients)
                {
                    if(cli.requestCnt.containsKey(f))
                    {
                        
                    }
                }
            }
        }
        
    }
        
    
    static void sift(int idx)
    {
        int val = heap.get(idx).value;
        int swapidx = idx;
        if(2*idx >= heapSize)
        {
            return;
        }
        if(val < heap.get(2*idx).value)
        {
            swapidx = 2*idx;
        }
        if(2*idx + 1 < heapSize && heap.get(swapidx).value < heap.get(2*idx+1).value)
        {
            swapidx = 2*idx + 1;
        }
        if(swapidx != idx)
        {
            elementToIdx.put(heap.get(idx),swapidx);
            elementToIdx.put(heap.get(swapidx),idx);
            Collections.swap(heap, idx, swapidx);
            sift(swapidx);
        }       
    }
    
    static void percolate(int idx)
    {
        if(idx == 0)
        {
            return;
        }
        int father = idx/2;
        if(heap.get(idx).value > heap.get(father).value)
        {
            elementToIdx.put(heap.get(idx),father);
            elementToIdx.put(heap.get(father),idx);
            Collections.swap(heap, idx, father);
            percolate(father);
        }
    }
    
    static HeapElement pop()
    {
        HeapElement result = heap.get(0);
        int swapidx = heapSize - 1;
        elementToIdx.put(heap.get(0),swapidx);
        elementToIdx.put(heap.get(swapidx),0);
        Collections.swap(heap, 0, swapidx);
        heapSize--;
        sift(0);
        return result;
    }
    
    
    public void parse(InputStream in) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
			
			String firstLine = br.readLine();
			String[] firstLineArray = firstLine.split(" ");
			
			String secondLine = br.readLine();
			String[] secondLineArray = secondLine.split(" ");
			
			this.fileCnt = Integer.parseInt(firstLineArray[0]);
			this.files = new File[this.fileCnt];
			for (int i=0; i<this.fileCnt; i++) {
				files[i] = new File(i, Integer.parseInt(secondLineArray[i]));
			}
			
			int numEndpoints = Integer.parseInt(firstLineArray[1]);
			this.clients = new Client[numEndpoints];
			for (int i=0; i<numEndpoints; i++) {
				clients[i] = new Client(i);
			}
			
			int numRequests = Integer.parseInt(firstLineArray[2]);
			
			this.cacheCnt = Integer.parseInt(firstLineArray[3]);
			int cacheSize = Integer.parseInt(firstLineArray[4]);
			this.caches = new Cache[this.cacheCnt];
			for (int i=0; i<this.cacheCnt; i++) {
				this.caches[i] = new Cache(i, cacheSize);
			}
			
			
			
			for (int i=0; i<numEndpoints; i++) {
				String endpoint = br.readLine();
				String[] endpointArray = endpoint.split(" ");
				int datacentreLatency = Integer.parseInt(endpointArray[0]);
				this.clients[i].rootDist = datacentreLatency;
				int numConnectedCaches = Integer.parseInt(endpointArray[1]);
				for (int j=0; j<numConnectedCaches; j++) {
					String cacheLatency = br.readLine();
					String[] cacheLatencyArray = cacheLatency.split(" ");
					int cacheID = Integer.parseInt(cacheLatencyArray[0]);
					int latency = Integer.parseInt(cacheLatencyArray[1]);
					
					// Update Cache to contain clientID
					this.caches[cacheID].updateClients(this.clients[i]);
					
					//Update Client to contain caches connected to it and latency
					this.clients[i].updateParents(this.caches[cacheID], latency);
				
				}
			}
			
			for (int i=0; i<numRequests; i++) {
				String requestLine = br.readLine();
				String[] requestLineArray = requestLine.split(" ");
				int videoID = Integer.parseInt(requestLineArray[0]);
				int endpointID = Integer.parseInt(requestLineArray[1]);
				int freqRequests = Integer.parseInt(requestLineArray[2]);
				this.files[videoID].addRequest(clients[endpointID], freqRequests);
			}
			
			br.close();
			
		}
		catch (IOException e) {
			System.out.println("HELP");
		}
		

	}
    
}
