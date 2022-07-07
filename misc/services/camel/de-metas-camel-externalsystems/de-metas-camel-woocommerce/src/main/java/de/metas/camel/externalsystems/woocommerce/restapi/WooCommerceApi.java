package de.metas.camel.externalsystems.woocommerce.restapi;

import java.util.List;
import java.util.Map;

public  class WooCommerceApi implements WooCommerce  {


	private HttpClient client;
	private OAuthParameter config;
	private String apiVersion;

	public WooCommerceApi(OAuthParameter config, ApiVersion apiVersion) {
		this.config = config;
		this.client = new DefaultHttpClient();
		this.apiVersion = apiVersion.getValue();
	}


	@Override
	public Map get(String endpointBase, int id) {
		String url = String.format("%s/wp-json/wc/%s/%s/%d", config.getUrl(), apiVersion, endpointBase, id);
		String signature = AuthorizationSignature.getAsString(config, url, HttpMethods.GET);
		String securedUrl = String.format("%s?%s", url, signature);
		return client.get(securedUrl);
	}

	@Override
	public List getAll(String endpointBase, Map<String, String> params) {
		String url = String.format("%s/wp-json/wc/%s/%s", config.getUrl(), apiVersion, endpointBase);
		String signature = AuthorizationSignature.getAsString(config, url, HttpMethods.GET, params);
		String securedUrl = String.format("%s?%s", url, signature);
		return client.getAll(securedUrl);
	}

	@Override
	public Map create(String endpointBase, Map<String, Object> object) {
		String url = String.format("%s/wp-json/wc/%s/%s", config.getUrl(), apiVersion, endpointBase);
		return client.post(url, AuthorizationSignature.getAsMap(config, url, HttpMethods.POST), object);
	}




}
