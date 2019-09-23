package com.vjkratky.stockexchange.api.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Conta {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String email;

	private double valorDisponibilizado;

	public Conta() {
	}

	@JsonCreator
	public Conta(@JsonProperty("id") Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public double getValorDisponibilizado() {
		return valorDisponibilizado;
	}

	public void setValorDisponibilizado(double valorDisponibilizado) {
		this.valorDisponibilizado = valorDisponibilizado;
	}

}
