package com.projeto.thymeleaf.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;

	private String email;

	private String telefone;

	@NotBlank
	@Pattern(regexp = "\\d{4,6}")
	private String crm;

	@Column(columnDefinition = "VARCHAR(50)")
	@Enumerated(EnumType.STRING)
	private Especialidade especialidade;

	@Embedded
	private Endereco endereco;

	private Boolean ativo;

	public void excluir() {
		this.ativo = false;
	}

	public Medico(Long id, String nome, String email, String telefone,
			@NotBlank @Pattern(regexp = "\\d{4,6}") String crm, Especialidade especialidade, Endereco endereco,
			Boolean ativo) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.crm = crm;
		this.especialidade = especialidade;
		this.endereco = endereco;
		this.ativo = ativo;
	}
}
