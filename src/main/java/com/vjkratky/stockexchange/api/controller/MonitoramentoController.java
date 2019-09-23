package com.vjkratky.stockexchange.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vjkratky.stockexchange.api.entity.Empresa;
import com.vjkratky.stockexchange.api.entity.Monitoramento;
import com.vjkratky.stockexchange.api.repository.EmpresaRepository;
import com.vjkratky.stockexchange.api.repository.MonitoramentoRepository;

@RestController
public class MonitoramentoController {

	@Autowired
	private MonitoramentoRepository monitoramentoRepository;

	@Autowired
	private EmpresaRepository empresaRepository;

	@RequestMapping("/monitoramentos")
	public List<Monitoramento> listMonitoramento() {
		return monitoramentoRepository.findAll();
	}

	@RequestMapping("/monitoramentos/{id}")
	public Optional<Monitoramento> findMonitoramento(@PathVariable Long id) {
		return monitoramentoRepository.findById(id);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/monitoramentos/{id}")
	public Monitoramento saveMonitoramento(@PathVariable Long id, @RequestBody Monitoramento monitoramento) {
		Optional<Empresa> empresa = empresaRepository.findById(id);
		monitoramento.setEmpresa(empresa.get());
		return this.monitoramentoRepository.save(monitoramento);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/monitoramentos/{id}/{idEmpresa}")
	public Monitoramento updateMonitoramento(@PathVariable Long id, @PathVariable Long idEmpresa,
			@RequestBody Monitoramento monitoramento) {
		Optional<Empresa> empresa = empresaRepository.findById(idEmpresa);
		monitoramento.setEmpresa(empresa.get());
		return monitoramentoRepository.save(monitoramento);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/monitoramentos/{id}")
	public void deleteMonitoramento(@PathVariable Long id) {
		monitoramentoRepository.deleteById(id);
	}
}
