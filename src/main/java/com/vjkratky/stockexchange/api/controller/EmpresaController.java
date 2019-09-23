package com.vjkratky.stockexchange.api.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vjkratky.stockexchange.api.entity.Conta;
import com.vjkratky.stockexchange.api.entity.Empresa;
import com.vjkratky.stockexchange.api.repository.ContaRepository;
import com.vjkratky.stockexchange.api.repository.EmpresaRepository;

@RestController
public class EmpresaController {

	@Autowired
	private ContaRepository contaRepository;

	@Autowired
	private EmpresaRepository empresaRepository;

	@RequestMapping(value = "/empresas/{id}/contas", method = RequestMethod.POST)
	public Empresa saveEmpresa(@PathVariable Long id, @RequestBody Empresa empresa) {
		Optional<Conta> conta = contaRepository.findById(id);
		empresa.setConta(conta.get());
		return empresaRepository.save(empresa);
	}
}
