package com.api.syonet.desafio.service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Value( "${email.port}" )
    private String port;

    @Value( "${email.username}" )
    private String username;

    @Value( "${email.password}" )
    private String password;

    @Value( "${email.host}" )
    private String host;

    public void SendMail ( String mensagem, String assunto, String para ) throws Exception {
        Properties properties = getProperties();
        Session session = Session.getInstance( properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication () {
                return new PasswordAuthentication( username,
                    password );
            }
        } );

        InternetAddress fromAddress = new InternetAddress( username );
        InternetAddress toAddress = new InternetAddress( para );

        Message message = new MimeMessage( session );
        message.setFrom( fromAddress );
        message.setRecipient( Message.RecipientType.TO, toAddress );
        message.setSubject( assunto );
        message.setContent( mensagem, "text/html; charset=utf-8" );
        message.saveChanges();


        Transport transport = session.getTransport();
        // Connect the transport object.
        transport.connect();
        // Send the message.
        transport.sendMessage( message, message.getAllRecipients() );
        // Close the connection.
        transport.close();
    }

    private Properties getProperties () {
        Properties properties = System.getProperties();
        properties.setProperty( "mail.smtp.host", "smtpout.secureserver.net" );
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
        properties.setProperty( "mail.smtp.socketFactory.class", SSL_FACTORY );
        properties.setProperty( "default-encoding", "UTF-8" );

        properties.put( "mail.smtp.host", host );
        properties.put( "mail.smtp.port", port );
        properties.put( "mail.smtp.auth", "true" );
        return properties;
    }
}
