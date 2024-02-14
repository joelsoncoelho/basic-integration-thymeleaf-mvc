package com.projeto.thymeleaf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.projeto.thymeleaf.domain.Medico;
import com.projeto.thymeleaf.repository.MedicoRepository;
import com.projeto.thymeleaf.repository.filter.MedicoFilter;

@Service
public class MedicoService {
	
	@Autowired
	private MedicoRepository medicoRepository;
	
	public void salvar(Medico medico) {
		try {
			medicoRepository.save(medico);
			
		} catch (DataIntegrityViolationException e) {
			throw new IllegalArgumentException("Formatdo inválido");
		}
	}

	public void excluir(Long id) {
		
		var medico = medicoRepository.getReferenceById(id);
		//	medico.excluir(); // exclusao lógica
		medicoRepository.delete(medico);
		
	}
	
	public List<Medico> filtrar(MedicoFilter filtro){
		String nome = filtro.getNome() == null ? "%" : filtro.getNome();
		return medicoRepository.searchByNomeLike(nome);
	}

}
