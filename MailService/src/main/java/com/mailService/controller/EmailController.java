package com.mailService.controller;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mailService.dto.CustomResponse;
import com.mailService.dto.EmailRequest;
import com.mailService.service.EmailService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/send")
public class EmailController {

	private final EmailService service; 

	@PostMapping("/email")
	public ResponseEntity<?> sendEmail( @RequestBody EmailRequest request){
		service.sendEmailWithHtml(request.getTo(), request.getSubject(), request.getMessage());
		log.debug("Email Sent => sendEmail in Controller Called");
		return  ResponseEntity.ok().body(CustomResponse.builder()
				.message("Email Sent Successfully !!")
				.httpStatus(HttpStatus.OK)
				.success(true)
				.build());
	}
	
	@PostMapping("/email-with-file")
	public  ResponseEntity<?> sendEmailWithFile( @RequestPart EmailRequest request,@RequestPart MultipartFile file) throws IOException{
		service.sendEmailWithFile(request.getTo(), request.getSubject(), request.getMessage(),file.getInputStream());
		log.debug("Email with File Sent => sendEmailwithfile in Controller Called");
		return  ResponseEntity.ok(CustomResponse.builder()
				.message("Email Sent Successfully !!")
				.httpStatus(HttpStatus.OK)
				.success(true)
				.build());
	
	}
}