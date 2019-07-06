package com.qbis.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@SuppressWarnings("deprecation")
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class RelatedResources {
private Sale sale;

public Sale getSale() {
	return sale;
}

public void setSale(Sale sale) {
	this.sale = sale;
}
}
