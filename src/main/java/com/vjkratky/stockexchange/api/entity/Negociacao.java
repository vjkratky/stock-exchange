package com.vjkratky.stockexchange.api.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.annotation.CreatedDate;

@Entity
public class Negociacao {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String empresa;

	private double valorNegociado;

	private int quantidade;

	@CreatedDate
	private Date data;

	public Negociacao() {
	}

	public Negociacao(String empresa, double valorNegociado, int quantidade) {
		this.empresa = empresa;
		this.valorNegociado = valorNegociado;
		this.quantidade = quantidade;
		this.data = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public double getValorNegociado() {
		return valorNegociado;
	}

	public void setValorNegociado(double valorNegociado) {
		this.valorNegociado = valorNegociado;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

}