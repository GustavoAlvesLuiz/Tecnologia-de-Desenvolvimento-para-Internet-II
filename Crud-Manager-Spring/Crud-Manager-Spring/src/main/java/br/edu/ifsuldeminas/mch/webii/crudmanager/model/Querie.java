package br.edu.ifsuldeminas.mch.webii.crudmanager.model;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "queries")
public class Querie {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@NotBlank(message = "A data da consulta não pode ser vazia!")
	@Pattern(regexp = "\\d{2}/\\d{2}/\\d{4}", message = "A data deve estar no formato dd/MM/yyyy e não pode conter letras.")
	private String dataConsulta;
	
	@NotBlank(message = "O motivo da consulta não pode ser vazio!")
	private String motivo;
	
	@NotBlank(message = "O diagnostico da consulta não pode ser vazio!")
	private String diagnostico;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "patient_id")
	private Patient paciente;

	public Patient getPaciente() {
		return paciente;
	}

	public void setPaciente(Patient paciente) {
		this.paciente = paciente;
	}
	
	public Querie() {}
	
	public Querie(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDataConsulta() {
		return dataConsulta;
	}

	public void setDataConsulta(String dataConsulta) {
		this.dataConsulta = dataConsulta;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public String getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}
	
}
