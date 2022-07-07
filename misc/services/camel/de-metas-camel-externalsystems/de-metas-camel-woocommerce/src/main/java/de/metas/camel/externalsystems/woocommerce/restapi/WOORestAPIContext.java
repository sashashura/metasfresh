

package de.metas.camel.externalsystems.woocommerce.restapi;

import de.metas.common.externalsystem.JsonExternalSystemRequest;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
public class WOORestAPIContext
{
	@NonNull
	JsonExternalSystemRequest request;
}
