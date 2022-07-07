package de.metas.camel.externalsystems.woocommerce.authorization;
import lombok.Getter;


public enum EncodedSymbols {

	AMP("&", "%26"),
	EQUAL("=", "%3D"),
	PLUS("+", "%2B"),
	STAR("*", "%2A"),
	TILDE("~", "%7E");

	@Getter
	private String plain;
	@Getter
	private String encoded;

	EncodedSymbols(String plain, String encoded) {
		this.plain = plain;
		this.encoded = encoded;
	}


}