package com.github.fa2bio.api.model;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VolumeInfoModel {
	
	@ApiModelProperty(example = "1984", required = true)
	private String title;
	
	@ApiModelProperty(example = "2009", required = true)
	private String publishedDate;
	
	@ApiModelProperty(example = "Publicada originalmente em 1949, a distopia futurista 1984 é um dos romances mais influentes do século XX.", required = true)
	private String description;
	
	@ApiModelProperty(example = "pt-BR", required = true)
	private String language;
	
	@ApiModelProperty(example = "414", required = true)
	private int pageCount;
	
	@ApiModelProperty(example = "8535914846", required = true)
	private String isbn_10;
	
	@ApiModelProperty(example = "9788535914849", required = true)
	private String isbn_13;
	private List<String> authors;
}
