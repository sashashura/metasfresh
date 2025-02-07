/*
 * #%L
 * de.metas.serviceprovider.base
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

package de.metas.serviceprovider.external.label;

import com.google.common.collect.ImmutableList;
import de.metas.i18n.TranslatableStrings;
import de.metas.reflist.ReferenceId;
import de.metas.serviceprovider.issue.IssueId;
import de.metas.util.Services;
import lombok.NonNull;
import org.adempiere.ad.service.IADReferenceDAO;
import org.springframework.stereotype.Service;

import java.util.Set;

import static de.metas.serviceprovider.model.X_S_IssueLabel.LABEL_AD_Reference_ID;

@Service
public class IssueLabelService
{
	private final IADReferenceDAO referenceDAO = Services.get(IADReferenceDAO.class);

	private final IssueLabelRepository issueLabelRepository;

	public IssueLabelService(@NonNull final IssueLabelRepository issueLabelRepository)
	{
		this.issueLabelRepository = issueLabelRepository;
	}

	public void persistLabels(@NonNull final IssueId issueId, @NonNull final ImmutableList<IssueLabel> issueLabels)
	{
		final Set<String> existingLabelValues = referenceDAO.retrieveListValues(LABEL_AD_Reference_ID);

		issueLabels.stream()
				.filter(label -> !existingLabelValues.contains(label.getValue()))
				.map(IssueLabelService::buildRefList)
				.forEach(referenceDAO::saveRefList);

		issueLabelRepository.persistLabels(issueId, issueLabels);
	}

	@NonNull
	public LabelCollection getByIssueId(@NonNull final IssueId issueId)
	{
		return issueLabelRepository.getByIssueId(issueId);
	}

	@NonNull
	private static IADReferenceDAO.ADRefListItemCreateRequest buildRefList(@NonNull final IssueLabel issueLabel)
	{
		return IADReferenceDAO.ADRefListItemCreateRequest
				.builder()
				.name(TranslatableStrings.constant(issueLabel.getValue()))
				.value(issueLabel.getValue())
				.referenceId(ReferenceId.ofRepoId(LABEL_AD_Reference_ID))
				.build();
	}
}
