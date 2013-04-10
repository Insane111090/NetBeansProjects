package NoSQL;

import java.io.FileNotFoundException;
import java.io.IOException;
import oracle.kv.*;

/**
 *
 * @author agavrilov
 */
public class Main
{

  static String port = "5000";
  static String host = "localhost";
  static String store = "kvstore";

  public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException
  {

    Support orastore = new Support(store, host, port);
    KVStore myStore = orastore.getStore();

    myStore.close();
    System.out.println("Store closed");
  }
}
