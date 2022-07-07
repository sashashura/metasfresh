package de.metas.camel.externalsystems.woocommerce.authorization;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;
import java.util.stream.Collectors;


public class AuthorizationSignature {



	private AuthorizationSignature() {}

	public static Map<String, String> getAsMap(OAuthParameter parameter, String endpoint, HttpMethods httpMethod, Map<String, String> params) {
		if (parameter == null || endpoint == null || httpMethod == null) {
			return Collections.emptyMap();
		}
		Map<String,String> authParams = new HashMap<>();
		authParams.put(AuthorizationHeader.OAUTH_CONSUMER_KEY.getValue(), parameter.getConsumerKey());
		authParams.put(AuthorizationHeader.OAUTH_TIMESTAMP.getValue(), String.valueOf(System.currentTimeMillis() / 1000L));
		authParams.put(AuthorizationHeader.OAUTH_NONCE.getValue(), UUID.randomUUID().toString());
		authParams.put(AuthorizationHeader.OAUTH_SIGNATURE_METHOD.getValue(), "HMAC-SHA256");
		authParams.putAll(params);


		if (HttpMethods.DELETE.equals(httpMethod)) {
			authParams.put("force", Boolean.TRUE.toString());
		}
		String oAuthSignature = generateSignature(parameter.getConsumerSecret(), endpoint, httpMethod, authParams);
		authParams.put(AuthorizationHeader.OAUTH_SIGNATURE.getValue(), oAuthSignature);
		return authParams;
	}



	public static Map<String, String> getAsMap(OAuthParameter config, String endpoint, HttpMethods httpMethod) {
		return getAsMap(config, endpoint, httpMethod, Collections.emptyMap());
	}

	public static String getAsString(OAuthParameter config, String endpoint, HttpMethods httpMethod, Map<String, String> params) {
		if (config == null || endpoint == null || httpMethod == null) {
			return "";
		}
		Map<String, String> oauthParameters = getAsMap(config, endpoint, httpMethod, params);
		String encodedSignature = oauthParameters.get(AuthorizationHeader.OAUTH_SIGNATURE.getValue())
				.replace(EncodedSymbols.PLUS.getPlain(), EncodedSymbols.PLUS.getEncoded());
		oauthParameters.put(AuthorizationHeader.OAUTH_SIGNATURE.getValue(), encodedSignature);
		return mapToString(oauthParameters, EncodedSymbols.EQUAL.getPlain(), EncodedSymbols.AMP.getPlain());
	}

	public static String getAsString(OAuthParameter config, String endpoint, HttpMethods httpMethod) {
		return getAsString(config, endpoint, httpMethod, Collections.emptyMap());
	}

	private static String generateSignature(String customerSecret, String endpoint, HttpMethods httpMethod, Map<String, String> parameters) {
		String signatureBaseString = getSignatureBaseString(endpoint, httpMethod.name(), parameters);
		String secret = customerSecret + EncodedSymbols.AMP.getPlain();
		return signBaseString(secret, signatureBaseString);
	}

	private static String signBaseString(String secret, String signatureBaseString) {
		Mac macInstance;
		try {
			macInstance = Mac.getInstance("HmacSHA256");
			SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256");
			macInstance.init(secretKey);
			return Base64.encodeBase64String(macInstance.doFinal(signatureBaseString.getBytes("UTF-8")));
		} catch (NoSuchAlgorithmException | InvalidKeyException | UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}
	private static Map<String, String> percentEncodeParameters(Map<String, String> parameters) {
		Map<String, String> encodedParamsMap = new HashMap<>();

		for (Map.Entry<String, String> parameter : parameters.entrySet()) {
			String key = parameter.getKey();
			String value = parameter.getValue();
			encodedParamsMap.put(percentEncodeString(key), percentEncodeString(value));
		}

		return encodedParamsMap;
	}

	private static String encodeUrl(String s) {
		try {
			return URLEncoder.encode(s, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	private static String getSignatureBaseString(String url, String method, Map<String, String> parameters) {
		String requestURL = encodeUrl(url);
		// function for percent encoding strings as described in RFC 3986, Section 2.1
		Map<String, String> encodedParameters = percentEncodeParameters(parameters);

		// Sort the parameters alphabetically
		encodedParameters = getSortedParameters(encodedParameters);
		String paramsString = mapToString(encodedParameters, EncodedSymbols.EQUAL.getEncoded(), EncodedSymbols.AMP.getEncoded());
		return String.format("%s&%s&%s", method, requestURL, paramsString);
	}

	private static String mapToString(Map<String, String> paramsMap, String keyValueDelimiter, String paramsDelimiter) {
		return paramsMap.entrySet().stream()
				.map(entry -> entry.getKey() + keyValueDelimiter + entry.getValue())
				.collect(Collectors.joining(paramsDelimiter));
	}



	private static String percentEncodeString(String s) {
		try {
			return URLEncoder.encode(s, "UTF-8")
					.replace(EncodedSymbols.PLUS.getPlain(), EncodedSymbols.PLUS.getEncoded())
					.replace(EncodedSymbols.STAR.getPlain(), EncodedSymbols.STAR.getEncoded())
					.replace(EncodedSymbols.TILDE.getEncoded(), EncodedSymbols.TILDE.getPlain());
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	private static Map<String, String> getSortedParameters(Map<String, String> parameters) {
		return new TreeMap<>(parameters);
	}

}