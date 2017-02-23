package master;

import java.util.ArrayList;
import java.util.HashMap;

public class Algo
{
//    ParseInput parser;
    int cacheCnt = 100;
    int fileCnt = 100;
    
    Cache [] caches = new Cache[cacheCnt];
    File [] files = new File[fileCnt];

    ArrayList<HeapElement> heap;
    
    HashMap<Integer, Integer> elementToIdx;
    
    void sift(int idx)
    {
        int loc = heap.get(idx).value;
        Boolean swap = false;
        int swapidx = 0;
        if(2*idx >= heap.size())
        {
            return;
        }
        if(loc < heap.get(2*idx).value)
        {
            swap = true;
            swapidx = 2*idx;
        }
        if(2*idx > heap.size())
        {
            if(swap)
            {
                
            }
            heap[idx]
        }
       
    }
    
}
