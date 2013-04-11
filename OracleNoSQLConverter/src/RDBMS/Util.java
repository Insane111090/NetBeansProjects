package RDBMS;

import javax.swing.*;
import net.miginfocom.swing.MigLayout;

public class Util
{

//  public static JPanel MigPanel()
//  {
//    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//  }
  @SuppressWarnings("serial")
  public static class MigPanel extends JPanel
  {

    public MigPanel()
    {
      setLayout(new MigLayout());
    }

    public MigPanel(String constraints)
    {
      setLayout(new MigLayout(constraints));
    }
  }
}
