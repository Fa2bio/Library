package com.github.fa2bio.api.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IncidenceModel implements Comparable<IncidenceModel>{
	
	private String cep;
	private int qtdEmployees;
	private final List<String> names = new ArrayList<>();
	
	public void addList(String nome) {
		this.names.add(nome);
	}

	@Override
	public int compareTo(IncidenceModel o2) {
		if(this.qtdEmployees > o2.getQtdEmployees()) return -1;
		else if(this.qtdEmployees < o2.getQtdEmployees()) return 1;
		else return 0;
	}
}
