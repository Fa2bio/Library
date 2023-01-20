package com.github.fa2bio.api.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientBookModel extends ClientModel{

	private List<BookAbstractModel> books;
}
