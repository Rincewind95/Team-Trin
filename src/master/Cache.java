package master;

import java.util.HashSet;

public class Cache
{
    int cacheID;
    int totalVolume;
    int volumeLeft;
    
    HashSet<Client> clients;
    
    HashSet<File> files;
}