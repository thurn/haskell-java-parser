/* TransferHandler.java --
   Copyright (C) 2004 Free Software Foundation, Inc.

This file is part of GNU Classpath.

GNU Classpath is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation; either version 2, or (at your option)
any later version.

GNU Classpath is distributed in the hope that it will be useful, but
WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
General Public License for more details.

You should have received a copy of the GNU General Public License
along with GNU Classpath; see the file COPYING.  If not, write to the
Free Software Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
02111-1307 USA.

Linking this library statically or dynamically with other modules is
making a combined work based on this library.  Thus, the terms and
conditions of the GNU General Public License cover the whole
combination.

As a special exception, the copyright holders of this library give you
permission to link this library with independent modules to produce an
executable, regardless of the license terms of these independent
modules, and to copy and distribute the resulting executable under
terms of your choice, provided that you also meet, for each linked
independent module, the terms and conditions of the license of that
module.  An independent module is a module which is not derived from
or based on this library.  If you modify this library, you may extend
this exception to your version of the library, but you are not
obligated to do so.  If you do not wish to do so, delete this
exception statement from your version. */

package javax.swing;

import java.io.Serializable;
import java.awt.event.InputEvent;
import java.awt.datatransfer.*;

public class TransferHandler implements Serializable
{
  private static final long serialVersionUID = -7908749299918704233L;

  public static final int NONE = 0;
  public static final int COPY = 1;
  public static final int MOVE = 2;
  public static final int COPY_OR_MOVE = 3;

  static Action getCopyAction ()
  {
    return null;
  }

  static Action getCutAction ()
  {
    return null;
  }

  static Action getPasteAction ()
  {
    return null;
  }


  protected TransferHandler()
  {
    // Do nothing here.
  }

  public TransferHandler(String property)
  {
  }

  public boolean canImport (JComponent c, DataFlavor[] flavors)
  {
    return false;
  }

  public Transferable createTransferable(JComponent c) 
  {
    return null;
  }

  public void exportAsDrag (JComponent c, InputEvent e, int action) 
  {    
  }

  protected void exportDone (JComponent c, Transferable data, int action) 
  {
  }

  public void exportToClipboard(JComponent c, Clipboard clip, int action) 
  {
  } 

  public int getSourceActions (JComponent c)
  {
    return 0;
  }

  public Icon getVisualRepresentation (Transferable t)
  {
    return null;
  }

  public boolean importData (JComponent c, Transferable t) 
  {
    return false;
  }

}
