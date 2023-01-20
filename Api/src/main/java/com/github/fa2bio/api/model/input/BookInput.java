package com.github.fa2bio.api.model.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookInput {
	
	@ApiModelProperty(example = "8535914846", required = true)
	@NotNull
	@NotBlank
	private String isbn;
}
