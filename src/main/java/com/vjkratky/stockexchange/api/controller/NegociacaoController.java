package com.vjkratky.stockexchange.api.controller;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vjkratky.stockexchange.api.entity.Monitoramento;
import com.vjkratky.stockexchange.api.entity.Negociacao;
import com.vjkratky.stockexchange.api.mail.Mailer;
import com.vjkratky.stockexchange.api.repository.ContaRepository;
import com.vjkratky.stockexchange.api.repository.EmpresaRepository;
import com.vjkratky.stockexchange.api.repository.MonitoramentoRepository;
import com.vjkratky.stockexchange.api.repository.NegociacaoRepository;

@EnableScheduling
@RestController
public class NegociacaoController {

	private int i = 0;

	@Autowired
	private ContaRepository contaRepository;

	@Autowired
	private EmpresaRepository empresaRepository;

	@Autowired
	private NegociacaoRepository negociacaoRepository;

	@Autowired
	private MonitoramentoRepository monitoramentoRepository;

	@Autowired
	private Mailer mailer;

	// private Random random = new Random();

	@RequestMapping("/negociacoes")
	public List<Negociacao> listNegociacao() {
		return negociacaoRepository.findAll();
	}

	@Scheduled(fixedRate = 5000)
	public void connectionSimulation() throws IOException {
		if (i < 100) {

			double preco;
			double valorParaCaixa;
			int quantidadeAcoes;
			boolean mudancaDeStatus;

			List<Monitoramento> monitoramentos = this.monitoramentoRepository.findAll();

			Negociacao negociacaoVenda;
			Negociacao negociacaoCompra;

			for (Monitoramento monitoramento : monitoramentos) {
				mudancaDeStatus = false;
				preco = 10 + (1 * Math.random());

				if (monitoramento.getPrecoVenda() >= preco) {
					if (monitoramento.getEmpresa().getAcoes() > 0) {
						mudancaDeStatus = true;
						valorParaCaixa = monitoramento.getEmpresa().getAcoes() * preco;

						negociacaoVenda = new Negociacao(monitoramento.getEmpresa().getNome(), preco,
								monitoramento.getEmpresa().getAcoes());

						Negociacao n = negociacaoRepository.save(negociacaoVenda);

						monitoramento.getEmpresa().getConta().setValorDisponibilizado(valorParaCaixa);
						monitoramento.getEmpresa().setAcoes(0);

						mailer.sendMail(monitoramento.getEmpresa().getConta().getEmail(), n, "venda", preco);
					}
				}

				if (monitoramento.getPrecoCompra() <= preco) {
					quantidadeAcoes = (int) Math
							.floor(monitoramento.getEmpresa().getConta().getValorDisponibilizado() / preco);

					if (quantidadeAcoes > 0) {
						mudancaDeStatus = true;

						if ((quantidadeAcoes * preco) != monitoramento.getEmpresa().getConta()
								.getValorDisponibilizado()) {
							valorParaCaixa = (monitoramento.getEmpresa().getConta().getValorDisponibilizado()
									- (quantidadeAcoes * preco));
						} else {
							valorParaCaixa = 0;
						}

						negociacaoCompra = new Negociacao(monitoramento.getEmpresa().getNome(), preco, quantidadeAcoes);

						Negociacao n = negociacaoRepository.save(negociacaoCompra);

						monitoramento.getEmpresa().getConta().setValorDisponibilizado(valorParaCaixa);
						monitoramento.getEmpresa().setAcoes(quantidadeAcoes);

						mailer.sendMail(monitoramento.getEmpresa().getConta().getEmail(), n, "compra", preco);
					}
				}

				if (mudancaDeStatus) {
					contaRepository.save(monitoramento.getEmpresa().getConta());
					empresaRepository.save(monitoramento.getEmpresa());
				}
			}

			i++;
		}

		if (i == 100) {
			i = 101;

			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			SimpleDateFormat data = new SimpleDateFormat("dd-MM-yy");
			SimpleDateFormat hora = new SimpleDateFormat("HH:mm:ss");

			FileWriter relatorio = new FileWriter("relatorios/relatorio" + sdf.format(new Date()) + ".txt");
			PrintWriter construirRelatorio = new PrintWriter(relatorio);

			construirRelatorio.println("Relatório de Negociações - " + sdf.format(new Date()));
			construirRelatorio.println("---------------------------------------------------------------------------");

			List<Negociacao> negociacoes = this.negociacaoRepository.findAll();

			for (Negociacao negociacao : negociacoes) {

				construirRelatorio.println(negociacao.getId() + " | " + negociacao.getEmpresa() + " | "
						+ negociacao.getQuantidade() + " |" + negociacao.getValorNegociado() + " | "
						+ data.format(negociacao.getData()) + " | " + hora.format(negociacao.getData()));

			}

			construirRelatorio.close();
		}
	}

}
