package master;

import java.util.HashMap;
import java.util.HashSet;

public class Client
{
    int clientID;
    double rootDist;
    HashMap<Cache, Integer> parents;
    
    HashMap<File, Integer> requestCnt;
   
    
    HashSet<HeapElement> heapBits;
}
