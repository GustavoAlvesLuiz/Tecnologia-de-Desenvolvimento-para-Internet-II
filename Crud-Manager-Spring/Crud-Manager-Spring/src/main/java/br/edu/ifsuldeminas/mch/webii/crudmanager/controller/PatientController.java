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

import br.edu.ifsuldeminas.mch.webii.crudmanager.model.Patient;
import br.edu.ifsuldeminas.mch.webii.crudmanager.repo.PatientRepository;
import jakarta.validation.Valid;

@Controller
public class PatientController {
	
	@Autowired
	private PatientRepository patientRepo;
	
	@GetMapping("/patients")
	public String listPatients(Model model) {
		
		List<Patient> patients = patientRepo.findAll();
		
		model.addAttribute("pacientes", patients);
		
		return "patient";
	}
	
	@GetMapping("/patients/form")
	public String patientForm(@ModelAttribute("paciente") Patient patient) { 			
		return "patients_form";
	}
	
	@PostMapping("/patients/register")
	public String patientNew(@Valid
							 @ModelAttribute("paciente")
							 Patient patient,
							 BindingResult erros) {
		if (erros.hasErrors()) {
			return "patients_form";
		}
		patientRepo.save(patient);
		return "redirect:/patients";
	}
	
	@GetMapping("/patients/update/{id}")
	public String patientUpdate(@PathVariable("id")
								Integer id,
								Model model) {
		
		Optional<Patient> patientOpt = patientRepo.findById(id);
		Patient patient;
		if (!patientOpt.isPresent()) {
			patient = new Patient();
		} else {
			patient = patientOpt.get();
		}
		
		model.addAttribute("paciente", patient);
		
		return "patients_form";
	}
	
	@GetMapping("/patients/delete/{id}")
	public String patientDelete(@PathVariable("id") Integer id) {
		
		patientRepo.delete(new Patient(id));
		
		return "redirect:/patients";  
	}
	
}
