package RDBMS;

import javax.swing.*;
import net.miginfocom.swing.MigLayout;

public class Util
{

  static final class MigPanel extends JPanel
  {

    MigPanel()
    {
      setLayout(new MigLayout());
    }

    MigPanel(String constraints)
    {
      setLayout(new MigLayout(constraints));
    }
  }
}
