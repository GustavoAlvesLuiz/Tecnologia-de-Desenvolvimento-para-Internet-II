package br.edu.ifsuldeminas.mch.webii.crudmanager.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.edu.ifsuldeminas.mch.webii.crudmanager.model.Querie;
import br.edu.ifsuldeminas.mch.webii.crudmanager.repo.PatientRepository;
import br.edu.ifsuldeminas.mch.webii.crudmanager.repo.QuerieRepository;
import jakarta.validation.Valid;

@Controller
public class QuerieController {
	
	@Autowired
	private QuerieRepository querieRepo;
	
	@Autowired
	private PatientRepository patientRepo;
	
	@GetMapping("/queries")
	public String listQueries(Model model) {
		List<Querie> queries = querieRepo.findAll();
		
		model.addAttribute("consultas", queries);
		
		return "querie";
	}
	
	@GetMapping("/queries/form")
	public String querieForm(Model model) {
		model.addAttribute("consulta", new Querie());
		model.addAttribute("pacientes", patientRepo.findAll());
		return "queries_form";
	}
	
	@PostMapping("/queries/register")
	public String querieNew(@Valid
							@ModelAttribute("consulta")
							Querie querie,
							BindingResult erros) {
		if (erros.hasErrors()) {
			return "queries_form";
		}
		
		patientRepo.save(querie.getPaciente());
		querieRepo.save(querie);
		
		return "redirect:/queries";
	}
	
	@GetMapping("/queries/update/{id}")
	public String querieUpdate(@PathVariable("id")
							   Integer id,
							   Model model) {
		
		Optional<Querie> querieOpt = querieRepo.findById(id);
		Querie querie;
		
		if(!querieOpt.isPresent()) {
			querie = new Querie();
		} else {
			querie = querieOpt.get();
		}
		
		model.addAttribute("consulta", querie);
		model.addAttribute("pacientes", patientRepo.findAll());
		return "queries_form";
	}
	
	@GetMapping("/queries/delete/{id}")
	public String querieDelete(@PathVariable("id") Integer id) {
		
		querieRepo.delete(new Querie(id));
		
		return "redirect:/queries";
	}
	
}
