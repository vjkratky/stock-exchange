package com.vjkratky.stockexchange.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vjkratky.stockexchange.api.entity.Conta;
import com.vjkratky.stockexchange.api.repository.ContaRepository;

@RestController
public class ContaController {

	@Autowired
	private ContaRepository contaRepository;

	@RequestMapping(value = "/contas", method = RequestMethod.GET)
	public List<Conta> listConta() {
		return this.contaRepository.findAll();
	}

	@RequestMapping(value = "/contas", method = RequestMethod.POST)
	public Conta saveConta(@RequestBody Conta conta) {
		return this.contaRepository.save(conta);
	}
}
