package com.projeto.thymeleaf.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.projeto.thymeleaf.domain.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

	@Query("SELECT m FROM Medico m WHERE m.nome LIKE %:nome%")
	List<Medico> searchByNomeLike(String nome);
}
