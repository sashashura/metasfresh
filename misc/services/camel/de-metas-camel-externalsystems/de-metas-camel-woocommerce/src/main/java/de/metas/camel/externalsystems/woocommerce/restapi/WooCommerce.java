package de.metas.camel.externalsystems.woocommerce.restapi;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public interface WooCommerce {


	Map create(String endpointBase, Map<String, Object> object);

	Map get(String endpointBase, int id);
	List getAll(String endpointBase, Map<String, String> params);

	default List getAll(String endpointBase) {

		return getAll(endpointBase, Collections.emptyMap());
	}



}