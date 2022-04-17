package com.dbc.emailhandler.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
//import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.dbc.emailhandler.dto.Operation;
import com.dbc.emailhandler.dto.UserDTO;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailService { 

	
    private final freemarker.template.Configuration fmConfiguration;

    @Value("${spring.mail.username}")
    private String from;
    private final JavaMailSender emailSender;
	private File dirPath = new File("src/main/resources/templates");
    
    
    public void sendEmail(UserDTO user, Operation operation, String email) {
    	MimeMessage mimeMessage = emailSender.createMimeMessage();
        try {

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(email);
            mimeMessageHelper.setSubject(operation.toString());
            mimeMessageHelper.setText(geContentFromTemplate(user, operation), true);

            emailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (MessagingException | IOException | TemplateException e) {
            e.printStackTrace();
        }
    }
    
    public String geContentFromTemplate(UserDTO user, Operation operation) throws IOException, TemplateException {
        Map<String, Object> dados = new HashMap<>();
        Template template = null;
        dados.put("name", user.getNome());
        dados.put("dataatual", LocalDateTime.now().getYear());
        dados.put("key", user.getToken());
        
        fmConfiguration.setDirectoryForTemplateLoading(dirPath);
        if(operation.equals(Operation.REGISTER)) {
        	template = fmConfiguration.getTemplate("/mailcreate-template.ftl");
        }else if(operation.equals(Operation.DELETE)){
        	template = fmConfiguration.getTemplate("/maildelete-template.ftl");
        }
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, dados);
        return html;
    }
}