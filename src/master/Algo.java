package master;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

public class Algo
{
//    ParseInput parser;
    static int cacheCnt = 100;
    static int fileCnt = 100;
    
    static Cache [] caches = new Cache[cacheCnt];
    static File [] files = new File[fileCnt];

    static ArrayList<HeapElement> heap;
    static int heapSize = 0; //TO CHANGE
    
    static HashMap<HeapElement, Integer> elementToIdx;
    
    public static void main()
    {
        // init part
        heap = new ArrayList<HeapElement>();
        elementToIdx = new HashMap<HeapElement, Integer>();
        for(Cache c : caches)
        {
            for(File f : files)
            {
                HeapElement h = new HeapElement(c, f);
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
            }
            
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
    
}
