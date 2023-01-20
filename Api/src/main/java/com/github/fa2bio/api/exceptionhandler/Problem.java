package com.github.fa2bio.api.exceptionhandler;

import java.time.LocalDateTime;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Problem{

	@ApiModelProperty(example = "2023-01-15T17:03:04.1198596")
	private LocalDateTime timestamp;
	
	@ApiModelProperty(example = "CepInvalidoException")
	private String type;
	
	@ApiModelProperty(example = "Formato Inválido. CEP deve possuir 8 caracteres")
	private String userMessage;

}