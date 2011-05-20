package cg.gwt.components.client.ui;

import cg.gwt.components.shared.data.UIPairData;

import com.google.gwt.user.client.ui.Panel;

public abstract class UIPair< D1, D2, U extends Panel, U1, U2 > extends UIComposite< UIPairData< D1, D2 >, U >
{
  public UIPair( D1 data1, D2 data2, U1 ui1, U2 ui2 )
  {
    setData( new UIPairData< D1, D2 >( data1, data2 ) );
  }
}
