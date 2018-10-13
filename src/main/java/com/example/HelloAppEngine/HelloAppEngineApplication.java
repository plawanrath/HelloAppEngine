package com.example.HelloAppEngine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@SpringBootApplication
@RestController
public class HelloAppEngineApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloAppEngineApplication.class, args);
	}

	@GetMapping("/")
	public String hello() {
		return "Hello Spring Boot";
	}

	@GetMapping("/name/{name}")
	public String name(@PathVariable String name) {
		return "My name is " + name;
	}

	@GetMapping("/email/{from}/{to}/{subject}/{message}")
	public String email(@PathVariable String from, @PathVariable String to, @PathVariable String subject, @PathVariable String message) {
		String res;
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);
		try {
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(from));
			msg.addRecipient(Message.RecipientType.TO,
							 new InternetAddress(to));
			msg.setSubject(subject);
			msg.setText(message);
			res = "Message was sent!!";
		} catch(Exception e) {
			res = e.getMessage();
		}
		return res;
	}
}
