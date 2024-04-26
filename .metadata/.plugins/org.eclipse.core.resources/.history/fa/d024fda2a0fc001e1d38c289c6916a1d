package com.mailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mailService.service.EmailService;

@SpringBootTest
public class EmailSenderTest {

	@Autowired
	private EmailService service;
	
	void emailSendTest() {
		System.out.println("Send mail test");
		service.sendEmail("gpulkit62@gmail.com", "email Check", "body msg");
	}
}
