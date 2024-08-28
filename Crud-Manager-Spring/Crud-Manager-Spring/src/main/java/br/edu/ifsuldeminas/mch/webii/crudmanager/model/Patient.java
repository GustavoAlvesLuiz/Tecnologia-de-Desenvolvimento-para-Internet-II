package br.edu.ifsuldeminas.mch.webii.crudmanager.model;

import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "patients")
public class Patient {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@NotBlank(message = "O nome do paciente não pode ser vazio!")
	@Pattern(regexp = "^[A-Za-zÀ-ÿ\\s]+$", message = "O nome não pode conter números ou caracteres especiais")
	private String nome;
	
	@NotBlank(message = "A data de nascimento do paciente não pode ser vazia!")
	@Pattern(regexp = "\\d{2}/\\d{2}/\\d{4}", message = "A data deve estar no formato dd/MM/yyyy e não pode conter letras.")
	private String dataNascimento;
	
	@NotBlank(message = "O telefone do paciente não pode estar vazio!")
	@Pattern(regexp = "\\(\\d{2}\\)\\s?\\d{8,9}", message = "O telefone deve estar no formato (XX) XXXXXXXXX")
	private String telefone;
	
	@NotBlank(message = "O e-mail do paciente não pode ser vazio!")
	@Email(message = "E-mail inválido!")
	private String email;
	
	@OneToMany(mappedBy = "paciente")
	private List <Querie> queries;
	
	public Patient() {}
	
	public Patient(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Querie> getQueries() {
		return queries;
	}

	public void setQueries(List<Querie> queries) {
		this.queries = queries;
	}
	
}
