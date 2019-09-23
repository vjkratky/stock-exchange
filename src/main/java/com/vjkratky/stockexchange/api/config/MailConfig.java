package com.vjkratky.stockexchange.api.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
@PropertySource("classpath:environment/mail.properties")
public class MailConfig {

	@Autowired
	private Environment environment;

	@Bean
	public JavaMailSender mailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

		mailSender.setHost(environment.getProperty("mail.smtp.host"));
		mailSender.setPort(environment.getProperty("mail.smtp.port", Integer.class));
		mailSender.setUsername(environment.getProperty("mail.smtp.username"));
		mailSender.setPassword(environment.getProperty("mail.smtp.password"));

		Properties properties = new Properties();
		properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		properties.put("mail.transport.protocol", "smtp");
		properties.put("mail.smtp.auth", true);
		properties.put("mail.smtp.starttls.enable", true);
		properties.put("mail.smtp.connectiontimeout", 10000);

		mailSender.setJavaMailProperties(properties);

		return mailSender;
	}

}
