package master;

import java.util.HashMap;

public class Client
{
    int clientID;
    double rootDist;
    HashMap<Cache, Integer> parents;
    
    HashMap<File, Integer> requestCnt;
}
