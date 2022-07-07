/*
 * #%L
 * de.metas.adempiere.adempiere.base
 * %%
 * Copyright (C) 2022 metas GmbH
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 2 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/gpl-2.0.html>.
 * #L%
 */

package de.metas.bom.impexp;

import de.metas.impexp.processing.ImportRecordsSelection;
import de.metas.impexp.processing.SimpleImportProcessTemplate;
import lombok.NonNull;
import org.adempiere.ad.trx.api.ITrx;
import org.adempiere.model.InterfaceWrapperHelper;
import org.adempiere.util.lang.IMutable;
import org.eevolution.model.I_I_Product_BOM;
import org.eevolution.model.I_PP_Product_BOM;
import org.eevolution.model.X_I_Product_BOM;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class BOMProductImportProcess extends SimpleImportProcessTemplate<I_I_Product_BOM>
{

	@Override
	public Class<I_I_Product_BOM> getImportModelClass()
	{
		return I_I_Product_BOM.class;
	}

	@Override
	public String getImportTableName()
	{
		return I_I_Product_BOM.Table_Name;
	}

	@Override
	protected String getTargetTableName()
	{
		return I_PP_Product_BOM.Table_Name;
	}

	@Override
	protected String getImportOrderBySql()
	{
		return I_I_Product_BOM.COLUMNNAME_Value;
	}

	@Override
	protected I_I_Product_BOM retrieveImportRecord(final Properties ctx, final ResultSet rs) throws SQLException
	{
		return new X_I_Product_BOM(ctx, rs, ITrx.TRXNAME_ThreadInherited);
	}

	@Override
	protected void updateAndValidateImportRecords()
	{
		final ImportRecordsSelection selection = getImportRecordsSelection();

		// sql for matching columns, like product, uom, etc
	}

	@Override
	protected ImportRecordResult importRecord(@NonNull final IMutable<Object> state, @NonNull final I_I_Product_BOM importRecord, final boolean isInsertOnly)
	{
		// First line to import or this line does NOT have the same value
		// => create a new BOM or update the existing one



		//
		// Same value like previous line
		// means that is a line from bom and we need to create a new bomline


		return ImportRecordResult.Inserted;
	}

	@Override
	protected void markImported(@NonNull final I_I_Product_BOM importRecord)
	{
		// importRecord.setI_IsImported(X_I_Product_BOM.I_ISIMPORTED_Imported);
		// importRecord.setProcessed(true);
		InterfaceWrapperHelper.save(importRecord);
	}

	@Override
	protected void afterImport()
	{
		// maybe check BOM
	}
}
