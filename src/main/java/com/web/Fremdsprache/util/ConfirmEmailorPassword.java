package com.web.Fremdsprache.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class ConfirmEmailorPassword {

	

	
	public static void sendEmail(String code, JavaMailSender javaMailSender) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("BestJavaDeveloper24@gmail.com");

        msg.setSubject("Dolphi");
        msg.setText("Ps...closer....yet closer...there is code: \n"+code);

        javaMailSender.send(msg);

    }
	
}
