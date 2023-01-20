package com.github.fa2bio.api.model.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeInput {

	@ApiModelProperty(example = "Fábio", required = true)
	@NotNull
	@NotBlank
	private String name;
	
	@ApiModelProperty(example = "20020050", required = true)
	@NotNull
	@NotBlank
	private String cep;
	
	@ApiModelProperty(example = "437.177.880-19", required = true)
	@NotNull
	@CPF
	private String cpf;
}
