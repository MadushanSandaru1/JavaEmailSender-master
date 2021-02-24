/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emailsender;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 *
 * @author sanda
 */
public class EmailSender {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String senderEmailAddress = YOUR_EMAIL_ADDRESS;
        String senderEmailPassword = YOUR_EMAIL_PASSWORD;
        String receiverEmailAddress = RECEIVER_EMAIL_ADDRESS;
        String emailSubject = "Test";
        String emailContent = "<h3>New Password in ChefGuru Account</h3><br>Dear Sir/Madam,<br><br><p>Hi...</p><br>Thank You!<br><pre>Tel: +94 57 22 30 500<br>Email: mevangurusinghe2@gmail.com</pre>";
        
        new EmailSender().sendEmail(senderEmailAddress, senderEmailPassword, receiverEmailAddress, emailSubject, emailContent);
    }
    
    public void sendEmail(String senderEmailAddress, String senderEmailPassword, String receiverEmailAddress, String emailSubject, String emailContent) {
        
        Properties props=new Properties();
        
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth","true");
        props.put("mail.smtp.port", "465");

        Session session=Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication(){
                        return new PasswordAuthentication(senderEmailAddress,senderEmailPassword);
                    }}
        );
        
        try{
            
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmailAddress));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiverEmailAddress));
            message.setSubject(emailSubject);
            //message.setText(emailContent);
            message.setContent(emailContent, "text/html");
            Transport.send(message);
            
            System.out.println("Mail Sent"+" "+"to "+" "+receiverEmailAddress);
        
        }catch(MessagingException e){
            System.out.println(e.getMessage());
        }
    
    }
}
