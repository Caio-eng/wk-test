package com.tecnico.test.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Doador implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 100)
	private String nome;

	@CPF(message = "CPF é inválido")
	private String cpf;

	private String rg;

	private String data_nasc;

	private String sexo;
	private String nomeMae;
	private String nomePai;

	@Email(message = "Email é inválido")
	@Column(unique = true)
	private String email;
	private String cep;
	private String endereco;
	private String numero;
	private String bairro;
	private String cidade;
	private String estado;
	private String telefoneFixo;
	private String celular;
	private float altura;
	private float peso;
	private String tipoSanguineo;

	public Doador() {
		super();
	}

	public Doador(String nome, String cpf, String rg, String data_nasc, String sexo, String nomeMae, String nomePai,
			String email, String cep, String endereco, String numero, String bairro, String cidade, String estado,
			String telefoneFixo, String celular, float altura, float peso, String tipoSanguineo) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.rg = rg;
		this.data_nasc = data_nasc;
		this.sexo = sexo;
		this.nomeMae = nomeMae;
		this.nomePai = nomePai;
		this.email = email;
		this.cep = cep;
		this.endereco = endereco;
		this.numero = numero;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		this.telefoneFixo = telefoneFixo;
		this.celular = celular;
		this.altura = altura;
		this.peso = peso;
		this.tipoSanguineo = tipoSanguineo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getDataNascimento() {
		return data_nasc;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getNomeMae() {
		return nomeMae;
	}

	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}

	public String getNomePai() {
		return nomePai;
	}

	public void setNomePai(String nomePai) {
		this.nomePai = nomePai;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getTelefoneFixo() {
		return telefoneFixo;
	}

	public void setTelefoneFixo(String telefoneFixo) {
		this.telefoneFixo = telefoneFixo;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public float getAltura() {
		return altura;
	}

	public void setAltura(float altura) {
		this.altura = altura;
	}

	public float getPeso() {
		return peso;
	}

	public void setPeso(float peso) {
		this.peso = peso;
	}

	public String getTipoSanguineo() {
		return tipoSanguineo;
	}

	@JsonProperty("tipo_sanguineo")
	public void setTipoSanguineo(String tipoSanguineo) {
		this.tipoSanguineo = tipoSanguineo;
	}

	@JsonProperty("data_nasc")
	public void setDataNasc(String data_nasc) {
		this.data_nasc = data_nasc;
	}

	public int getIdade() {
		LocalDate dataNascimento = LocalDate.parse(data_nasc, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		LocalDate dataAtual = LocalDate.now();
		Period periodo = Period.between(dataNascimento, dataAtual);
		return periodo.getYears();
	}

}
