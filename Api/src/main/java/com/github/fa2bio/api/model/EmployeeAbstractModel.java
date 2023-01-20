package com.github.fa2bio.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeAbstractModel {

	@ApiModelProperty(example = "85debe05-c2d6-4566-b5cc-61feabadedc8", required = true)
	private String uuiCode;
	
	@ApiModelProperty(example = "Fábio", required = true)
	private String name;

	@ApiModelProperty(example = "437.177.880-19", required = true)
	private String cpf;
}
