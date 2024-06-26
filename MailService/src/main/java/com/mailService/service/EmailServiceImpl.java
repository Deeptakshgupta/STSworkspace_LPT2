package com.mailService.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmailServiceImpl implements EmailService{
	
	private JavaMailSender mailSender;

	public EmailServiceImpl(JavaMailSender mailSender)
	{
		this.mailSender = mailSender;
	}

	
	@Override
	public void sendEmail(String to, String subject, String message) {
		// TODO Auto-generated method stub
//		MimeMessageHelper mimeMsg = new MimeMessageHelper(null);
		SimpleMailMessage simpleMsg = new SimpleMailMessage();
		simpleMsg.setTo(to);
		simpleMsg.setSubject(subject);
		simpleMsg.setText(message);
		simpleMsg.setFrom("springreddit@email.com");
		mailSender.send(simpleMsg);
		
		log.info("Email Sent");
	}

	@Override
	public void sendEmail(String[] to, String subject, String message) {
		// TODO Auto-generated method stub
		SimpleMailMessage simpleMsg = new SimpleMailMessage();
		simpleMsg.setTo(to); // can take lists of mails also
		simpleMsg.setSubject(subject);
		simpleMsg.setText(message);
		simpleMsg.setFrom("springreddit@email.com");
		mailSender.send(simpleMsg);
		
	}

	@Override
	public void sendEmailWithHtml(String to, String subject, String htmlContent) {
		// we have to set  html as while setting the msg 
		MimeMessage mimeMsg = mailSender.createMimeMessage();
		// enabling multi part , and encoding at UTF-8
		try {
			// using helper to set HTML true in message
			MimeMessageHelper helper = new MimeMessageHelper(mimeMsg,true,"UTF-8");
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(htmlContent, true);
			
			mailSender.send(mimeMsg);
			log.debug("Mail with HTML content sent");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			
		
	}

	
	//making request Body id bit tricky as it requires
	// file as attachment
	// && other parameters from as DTO
	// so we use 
	@Override
	public void sendEmailWithFile(String to, String subject, String message, File file) {
		// TODO Auto-generated method stub
		
		
		// for file handling 
		// we can create a file using inputStream
		//directly attach as attachment and use FileSystemresource to get the file name
		
		MimeMessage mimeMsg = mailSender.createMimeMessage();
		
		try {
			MimeMessageHelper helper = new MimeMessageHelper(mimeMsg, true);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(message, true);
			FileSystemResource fileSystemResource = new FileSystemResource(file);
			helper.addAttachment(fileSystemResource.getFilename(), file);
			
			mailSender.send(mimeMsg);
			log.debug("Mail with HTML content sent");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	@Override
	public void sendEmailWithFile(String to, String subject, String message, InputStream is) {
		// TODO Auto-generated method stub
		
		
		// for file handling 
		// we can create a file using inputStream
		//directly attach as attachment and use FileSystemresource to get the file name
		
		MimeMessage mimeMsg = mailSender.createMimeMessage();
		
		try {
			MimeMessageHelper helper = new MimeMessageHelper(mimeMsg, true);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(message, true);
			File file = new File("/MailService/src/main/resources/file1");
			Files.copy(is,file.toPath(),StandardCopyOption.REPLACE_EXISTING);
			// creating a file in the resources folder
			// populate using inputStream
			mailSender.send(mimeMsg);
			log.debug("Mail with HTML content sent");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
