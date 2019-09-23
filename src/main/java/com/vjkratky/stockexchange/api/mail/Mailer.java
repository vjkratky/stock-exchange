package com.vjkratky.stockexchange.api.mail;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.vjkratky.stockexchange.api.entity.Negociacao;

@Component
public class Mailer {

	@Autowired
	private JavaMailSender javaMailSender;

	public void sendMail(String email, Negociacao negociacao, String tipo, double valoEstabelecido) {

		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(email);

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy-HH-mm-ss");

		msg.setSubject("Transação nº" + negociacao.getId() + "-" + sdf.format(negociacao.getData()));
		msg.setText("Boa noite " + negociacao.getEmpresa() + ",");
		msg.setText("Houve uma transação de " + tipo + " as " + sdf.format(negociacao.getData()));
		msg.setText("Valor estabelecido para o preço de " + tipo + ": " + valoEstabelecido);
		msg.setText("Valor negociado: " + negociacao.getValorNegociado());

		javaMailSender.send(msg);
	}
}
