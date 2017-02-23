package master;

import java.util.HashMap;
import java.util.HashSet;

public class File
{
    int size;

    HashMap<Client, Integer> requestCnt;
    
    HashSet<HeapElement> heapBits;
}
