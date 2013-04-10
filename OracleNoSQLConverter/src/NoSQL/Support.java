package NoSQL;

import oracle.kv.*;

/**
 *
 * @author agavrilov
 */
public class Support
{

  private final KVStore oraStore;

  public Support(String name,
                 String host,
                 String port)
  {
    KVStoreConfig kvConfig = new KVStoreConfig(name, host + ':' + port);
    oraStore = KVStoreFactory.getStore(kvConfig);
    System.out.println("Store opened");
  }

  public KVStore getStore()
  {
    return oraStore;
  }
}
