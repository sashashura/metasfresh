package de.metas.camel.externalsystems.woocommerce.authorization;

import lombok.Getter;
import lombok.NonNull;


public final class OAuthParameter {

	@Getter
	private final String url;

	@Getter
	private final String consumerKey;
	@Getter
	private final String consumerSecret;


	public OAuthParameter(@NonNull String baseUrl, @NonNull String consumerKey, @NonNull String consumerSecret) {
		/*if (baseUrl == null || baseUrl.isEmpty() ||
				consumerKey == null || consumerKey.isEmpty() ||
				consumerSecret == null || consumerSecret.isEmpty()) {
			throw new IllegalArgumentException("URL, Consumerkey and Consumersecret are required");
		}*/
		this.url = baseUrl;
		this.consumerKey = consumerKey;

		this.consumerSecret = consumerSecret;
	}


	public String getUrl() {
		return url;
	}

	public String getConsumerKey() {
		return consumerKey;
	}

	public String getConsumerSecret() {
		return consumerSecret;
	}

}