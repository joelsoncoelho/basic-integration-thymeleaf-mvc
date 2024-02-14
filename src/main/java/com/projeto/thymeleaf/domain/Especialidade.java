package com.projeto.thymeleaf.domain;

public enum Especialidade {

	ORTOPEDIA("Ortopedia"), CARDIOLOGIA("Cardiologia"), GINECOLOGIA("Ginecologia"), DERMATOLOGIA("Dermatologia"), GENETICISTA("Geneticista");

	private String descricao;

	Especialidade(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
