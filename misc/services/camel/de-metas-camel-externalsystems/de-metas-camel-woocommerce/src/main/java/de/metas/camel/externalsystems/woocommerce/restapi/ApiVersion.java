package de.metas.camel.externalsystems.woocommerce.restapi;

import lombok.Getter;


public enum ApiVersion {
	V1("v1"),
	V2("v2"),
	V3("v3");
	@Getter
	private String value;

	ApiVersion(String value) {
		this.value = value;
	}


}