package com.telusko.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.telusko.demo.model.Declaration;
import com.telusko.demo.model.Person;
import com.telusko.demo.repository.DeclarationRepository;
import com.telusko.demo.service.PersonService;

import lombok.var;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart; 

@RestController
public class DeclarationController {

    @Autowired
    DeclarationRepository repo;

    @Autowired
    //private PersonService service;
    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/declaration")
    public Declaration save(@RequestBody Declaration d/*, @RequestBody Person p*/){
    	
    	//Declaration decl= ObjectMapper.readValue(d,Declaration.class);
    	
    	
    	//var ob=(var) savePerson(p);
    	//sendEmail();
        return repo.save(d);
    }
    
   /* public Person savePerson(@RequestBody Person p) {
    	 return service.save(p);
    }*/
    
    public void sendEmail() {
    	
  	  // Recipient's email ID needs to be mentioned.
      String to = "pheellomoetsela@gmail.com";

      // Sender's email ID needs to be mentioned
      String from = "pheellomoetsela@gmail.com";
      final String username = "Pheello Moetsela";//change accordingly
      final String password = "PSMoetsela@05$";//change accordingly

      // Assuming you are sending email through relay.jangosmtp.net
      String host = "relay.jangosmtp.net";

      Properties props = new Properties();
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.host", host);
      props.put("mail.smtp.port", "25");

      Session session = Session.getInstance(props,
         new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
               return new PasswordAuthentication(username, password);
            }
         });

      try {

         // Create a default MimeMessage object.
         Message message = new MimeMessage(session);

         // Set From: header field of the header.
         message.setFrom(new InternetAddress(from));

         // Set To: header field of the header.
         message.setRecipients(Message.RecipientType.TO,
            InternetAddress.parse(to));

         // Set Subject: header field
         message.setSubject("Testing Subject");

         // This mail has 2 part, the BODY and the embedded image
         MimeMultipart multipart = new MimeMultipart("related");

         // first part (the html)
         BodyPart messageBodyPart = new MimeBodyPart();
         String htmlText = "<H1>Dear Applicant,\n" + 
         		"\n" + 
         		"This e-mail serves to acknowledge receipt of your application(s).\n" + 
         		"\n" + 
         		"Your application will be given due consideration along with all other applications. "
         		+ "Should you not receive any feedback from the CCMA within six (6) weeks of your application, please consider your application unsuccessful.\n" + 
         		"\n" + 
         		"Thank you for your interest in this position. \n" + 
         		"\n" + 
         		"Yours sincerely, \n" + 
         		"\n" + 
         		"The CCMA Recruitment Team\n" + 
         		"</H1><img src=\"cid:image\">";
         messageBodyPart.setContent(htmlText, "text/html");
         // add it
         multipart.addBodyPart(messageBodyPart);

         // second part (the image)
         messageBodyPart = new MimeBodyPart();
         DataSource fds = new FileDataSource(
            "/home/manisha/javamail-mini-logo.png");

         messageBodyPart.setDataHandler(new DataHandler(fds));
         messageBodyPart.setHeader("Content-ID", "<image>");

         // add image to the multipart
         multipart.addBodyPart(messageBodyPart);

         // put everything together
         message.setContent(multipart);
         // Send message
         Transport.send(message);

         System.out.println("Sent message successfully....");

      } catch (MessagingException e) {
         throw new RuntimeException(e);
      }
    }
}