/*
 * #%L
 * de-metas-common-rest_api
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

package de.metas.common.rest_api.v2.project.workorder;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.function.Function;

import static de.metas.common.rest_api.v2.SwaggerDocConstants.RESOURCE_IDENTIFIER_DOC;

@Getter
@ToString
@EqualsAndHashCode
public class JsonWorkOrderResourceUpsertItemRequest
{
	@ApiModelProperty(position = 10,
			required = true,
			value = RESOURCE_IDENTIFIER_DOC + "\n"
					+ "Note that `C_Project_WO_Resource.S_Resource_ID` is needed for the calendar view!") //
	@Setter
	String resourceIdentifier;

	@ApiModelProperty(required = true)
	@Setter
	LocalDate assignDateFrom;

	@ApiModelProperty(required = true)
	@Setter
	LocalDate assignDateTo;

	@ApiModelProperty(value = "If not specified but required (e.g. because a new resource is created), then `true` is assumed")
	Boolean isActive;

	@ApiModelProperty(hidden = true)
	boolean activeSet;

	Boolean isAllDay;

	@ApiModelProperty(hidden = true)
	boolean allDaySet;

	BigDecimal duration;

	@ApiModelProperty(hidden = true)
	boolean durationSet;

	JsonDurationUnit durationUnit;

	@ApiModelProperty(hidden = true)
	boolean durationUnitSet;

	String testFacilityGroupName;

	@ApiModelProperty(hidden = true)
	boolean testFacilityGroupNameSet;

	String externalId;

	@ApiModelProperty(hidden = true)
	boolean externalIdSet;

	public void setActive(final Boolean active)
	{
		isActive = active;
		this.activeSet = true;
	}

	public void setIsAllDay(final Boolean isAllDay)
	{
		this.isAllDay = isAllDay;
		this.allDaySet = true;
	}

	public void setDuration(final BigDecimal duration)
	{
		this.duration = duration;
		this.durationSet = true;
	}

	public void setDurationUnit(final JsonDurationUnit durationUnit)
	{
		this.durationUnit = durationUnit;
		this.durationUnitSet = true;
	}

	public void setTestFacilityGroupName(final String testFacilityGroupName)
	{
		this.testFacilityGroupName = testFacilityGroupName;
		this.testFacilityGroupNameSet = true;
	}

	public void setExternalId(final String externalId)
	{
		this.externalId = externalId;
		this.externalIdSet = true;
	}

	@JsonIgnore
	@NonNull
	public <T> T mapResourceIdentifier(@NonNull final Function<String, T> mappingFunction)
	{
		return mappingFunction.apply(resourceIdentifier);
	}
}