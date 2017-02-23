package master;

public class HeapElement
{
    int value;
    
    Cache cacheRef;
    File fileRef;
    
    public HeapElement()
    {
        
    }
    
    public HeapElement(Cache c, File f)
    {
        cacheRef = c;
        fileRef = f;
        value = determineValue(c, f);
        c.heapBits.add(this);
        f.heapBits.add(this);
    }
    
    public int determineValue(Cache c, File f)
    {
        int res = 0;
        for(Client cli : c.clients)
        {
            int refcnt = cli.requestCnt.get(f);
            int rootDist = cli.rootDist;
            int cacheDist = cli.parents.get(c);
            if(rootDist > cacheDist)
                res += refcnt*(rootDist-cacheDist);
        }
        return res;
    }
}
