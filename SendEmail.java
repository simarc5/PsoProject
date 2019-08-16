/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

/**
 *
 * @author kahma
 */
import com.sun.mail.smtp.SMTPTransport;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import userInterface.AreaMapJPanel;


public class SendEmail {

	// for example, smtp.mailgun.org
    public static String to = "simarchhabra5@gmail.com";
    public static String subject = "PSA Project";
    public static String msg ="Testing";
    public static final String from ="pepperguruaed@gmail.com";
    public static final  String password ="Psaproject@123";
    static AreaMapJPanel map;

    public static void sendEmail() throws NoSuchProviderException, AddressException, MessagingException {

    Properties props = new Properties();  
    props.setProperty("mail.transport.protocol", "smtp");     
    props.setProperty("mail.host", "smtp.gmail.com");  
    props.put("mail.smtp.auth", "true");  
    props.put("mail.smtp.port", "465");  
    props.put("mail.debug", "true");  
    props.put("mail.smtp.socketFactory.port", "465");  
    props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");  
    props.put("mail.smtp.socketFactory.fallback", "false");  
    Session session = Session.getDefaultInstance(props,  
    new javax.mail.Authenticator() {
       protected PasswordAuthentication getPasswordAuthentication() {  
       return new PasswordAuthentication(from,password);  
   }  
   });  

   //session.setDebug(true);  
   Transport transport = session.getTransport();  
   InternetAddress addressFrom = new InternetAddress(from);  

   MimeMessage message = new MimeMessage(session);  
   message.setSender(addressFrom);  
   message.setSubject(subject);  
   message.setContent(msg, "text/plain");  
   message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));  

   // Create the message part
         BodyPart messageBodyPart = new MimeBodyPart();

         // Now set the actual message
         messageBodyPart.setText("This is message body: "+map.path);

         // Create a multipar message
         Multipart multipart = new MimeMultipart();

         // Set text message part
         multipart.addBodyPart(messageBodyPart);

         // Part two is attachment
         messageBodyPart = new MimeBodyPart();
         String filename = "C:\\Users\\kahma\\OneDrive\\Desktop\\Shot.jpg";
         DataSource source = new FileDataSource(filename);
         messageBodyPart.setDataHandler(new DataHandler(source));
         messageBodyPart.setFileName(filename);
         multipart.addBodyPart(messageBodyPart);

         // Send the complete message parts
         message.setContent(multipart);
   transport.connect();  
   Transport.send(message);  
   transport.close();
   }  
}