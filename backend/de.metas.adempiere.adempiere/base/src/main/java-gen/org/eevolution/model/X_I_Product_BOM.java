// Generated Model - DO NOT CHANGE
package org.eevolution.model;

import javax.annotation.Nullable;
import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for I_Product_BOM
 *  @author metasfresh (generated) 
 */
@SuppressWarnings("unused")
public class X_I_Product_BOM extends org.compiere.model.PO implements I_I_Product_BOM, org.compiere.model.I_Persistent
{

	private static final long serialVersionUID = -1591740181L;

    /** Standard Constructor */
    public X_I_Product_BOM(final Properties ctx, final int I_Product_BOM_ID, @Nullable final String trxName)
    {
      super (ctx, I_Product_BOM_ID, trxName);
    }

    /** Load Constructor */
    public X_I_Product_BOM(final Properties ctx, final ResultSet rs, @Nullable final String trxName)
    {
      super (ctx, rs, trxName);
    }


	/** Load Meta Data */
	@Override
	protected org.compiere.model.POInfo initPO(final Properties ctx)
	{
		return org.compiere.model.POInfo.getPOInfo(Table_Name);
	}

	@Override
	public void setM_Product_ID (final int M_Product_ID)
	{
		if (M_Product_ID < 1) 
			set_Value (COLUMNNAME_M_Product_ID, null);
		else 
			set_Value (COLUMNNAME_M_Product_ID, M_Product_ID);
	}

	@Override
	public int getM_Product_ID() 
	{
		return get_ValueAsInt(COLUMNNAME_M_Product_ID);
	}

	@Override
	public void setName (final String Name)
	{
		set_Value (COLUMNNAME_Name, Name);
	}

	@Override
	public String getName()
	{
		return get_ValueAsString(COLUMNNAME_Name);
	}

	@Override
	public void setPP_Product_BOM_ID (final int PP_Product_BOM_ID)
	{
		if (PP_Product_BOM_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_PP_Product_BOM_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_PP_Product_BOM_ID, PP_Product_BOM_ID);
	}

	@Override
	public int getPP_Product_BOM_ID() 
	{
		return get_ValueAsInt(COLUMNNAME_PP_Product_BOM_ID);
	}

	@Override
	public void setValue (final String Value)
	{
		set_Value (COLUMNNAME_Value, Value);
	}

	@Override
	public String getValue()
	{
		return get_ValueAsString(COLUMNNAME_Value);
	}
}