package com.github.fa2bio.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddressModel {

	@ApiModelProperty(example = "20020050", required = true)
	private String cep;
	
	@ApiModelProperty(example = "Rua do Catete - de 197/198 ao fim", required = true)
	private String logradouro;
	
	@ApiModelProperty(example = "", required = true)
	private String complemento;
	
	@ApiModelProperty(example = "Catete", required = true)
	private String bairro;
	
	@ApiModelProperty(example = "Rio De Janeiro", required = true)
	private String localidade;

}
