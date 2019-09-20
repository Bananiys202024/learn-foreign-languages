package com.web.Fremdsprache.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class ConfirmEmailorPassword {

	

	
	public static void sendEmail(String code, String  email, JavaMailSender javaMailSender) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email);

        msg.setSubject("Dolphi");
        msg.setText("Ps...closer....yet closer...there is code: \n"+code);

        javaMailSender.send(msg);

    }
	
}
