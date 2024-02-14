package com.projeto.thymeleaf.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.projeto.thymeleaf.domain.Especialidade;
import com.projeto.thymeleaf.domain.Medico;
import com.projeto.thymeleaf.repository.MedicoRepository;
import com.projeto.thymeleaf.repository.filter.MedicoFilter;
import com.projeto.thymeleaf.service.MedicoService;
	
@Controller
@RequestMapping("/medicos")
public class MedicoController {
	
	private static final String CADASTRO_VIEW = "CadastroMedico";

	@Autowired
	private MedicoRepository medicoRepository;
	
	@Autowired
	private MedicoService medicoService;

	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView modelAndView = new ModelAndView(CADASTRO_VIEW);
		modelAndView.addObject(new Medico());
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Medico medico, Errors errors, RedirectAttributes attributes) {

		if (errors.hasErrors()) {
			return CADASTRO_VIEW;
		}
		
		try {
			medicoService.salvar(medico);
			attributes.addFlashAttribute("mensagem", "Médico salvo com sucesso!");
			return "redirect:/medicos/novo";
		} catch (DataIntegrityViolationException e) {
			errors.rejectValue("cep", "Formato invalido", null, e.getMessage()); //exemplo
			return CADASTRO_VIEW;
		}
	}

	@RequestMapping 
	public ModelAndView pesquisar(@ModelAttribute("filtro") MedicoFilter filtro) {
				
		List<Medico> todosMedicos = medicoService.filtrar(filtro);
		
		ModelAndView modelAndView = new ModelAndView("ListagemMedico");
		modelAndView.addObject("list", todosMedicos);
		return modelAndView;
	}

	@RequestMapping("{id}")
	@Transactional
	public ModelAndView atualizar(@PathVariable("id") Medico medico) {
		System.out.println(">>>>>>>> codigo recebido: " + medico.getId());
		Optional<Medico> medicoAtualizado = medicoRepository.findById(medico.getId());
		
		ModelAndView modelAndView = new ModelAndView(CADASTRO_VIEW);
		modelAndView.addObject(medicoAtualizado.get());

		return modelAndView;
	}

	@RequestMapping(value = "{id}", method = { RequestMethod.POST, RequestMethod.DELETE })
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attributes) {
	//	System.out.println(">>>>>>>> codigo recebido do excluir: " + id);
	
		medicoService.excluir(id);
		attributes.addFlashAttribute("mensagem", "Médico excluído com sucesso!");
		return "redirect:/medicos";
	}
	
	@ModelAttribute("todasEspecialidadeMedico")
	public List<Especialidade> todasEspecialidadeMedico(){
		return Arrays.asList(Especialidade.values());
	}
	
	

}
