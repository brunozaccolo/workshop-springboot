package br.com.springboot.workshop.enumerated;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum BrandType {

	@JsonProperty("fiat")
	FIAT,
	
	@JsonProperty("ford")
	FORD,
	
	@JsonProperty("chevrolet")
	CHEVROLET;

}
