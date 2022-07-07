package de.metas.camel.externalsystems.woocommerce.restapi;

import lombok.Getter;



@Getter
public enum PathSegmentsEnum {

	COUPONS("coupons"),
	CUSTOMERS("customers"),
	ORDERS("orders"),
	PRODUCTS("products"),
	PRODUCTS_ATTRIBUTES("products/attributes"),
	PRODUCTS_CATEGORIES("products/categories"),
	PRODUCTS_SHIPPING_CLASSES("products/shipping_classes"),
	PRODUCTS_TAGS("products/tags"),
	REPORTS("reports"),
	REPORTS_SALES("reports/sales"),
	REPORTS_TOP_SELLERS("reports/top_sellers"),
	TAXES("taxes"),
	TAXES_CLASSES("taxes/classes"),
	WEBHOOKS("webhooks");

	@Getter
	private String value;


	PathSegmentsEnum(String value) {
		this.value = value;
	}


}