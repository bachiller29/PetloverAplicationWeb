/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.petlovers.utilities;

import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author SENA
 */
public class Email {

    public static void sendModificacion(String para, String Nombres, String nombUsu, String clave) {
        final String user = "";//cambiará en consecuencia al servidor utilizado
        final String pass = "";

//1st paso) Obtener el objeto de sesión
        Properties props = new Properties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com"); // envia 
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.port", "25");
        props.setProperty("mail.smtp.starttls.required", "false");
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
        });

//2nd paso)compose message
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(para));
            message.setSubject("Actualizacion de datos en Banco-Pedagogico");
            java.util.Date fecha = new Date();

            message.setContent(
                    "<center><img src='http://fs5.directupload.net/images/160530/khs5cmdc.jpg' title='Banco Pedagogico'></center>"
                    + "<h3> Actualizacion de datos en Banco-Pedagogico. "
                    + Nombres
                    + "</h3>"
                    + "Datos de Ingreso: "
                    + "<h4> Nombre Usuario : "
                    + nombUsu
                    + "</h4>"
                    + "<h4> Documento Usuario : "
                    + clave
                    + " </h4>"
                    + "Ultima Modificacion"
                    + fecha.getHours() + ":" + fecha.getMinutes() + ":" + fecha.getSeconds() + " - "
                    + fecha.getDay() + "/" + fecha.getMonth() + "/" + fecha.getYear(), "text/html");

            //3rd paso)send message
            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

    public static void sendClaves(String para, String Nombres, String emailUsu, String contrasena) {
        final String user = "comunicaciones.petlovers@gmail.com";//cambiará en consecuencia al servidor utilizado
        final String pass = "12345HolaMundo";

//1st paso) Obtener el objeto de sesión
        Properties props = new Properties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com"); // envia 
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.port", "587");
//        props.setProperty("mail.smtp.starttls.required", "true");
        props.setProperty("mail.smtp.auth", "true");
//        props.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
        });

//2nd paso)compose message
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(para));
            message.setSubject("Recordatorio Claves PetLovers");

            message.setContent(
                    "<center><img src='https://www.batiburrillo.net/wp-content/uploads/2019/06/SterJo-KeyFinder-para-recuperar-claves-perdidas.jpg' title='Recordatorio Claves Petlovers'></center>"
                    + "<h3> Recordatorio Claves. "
                    + Nombres
                    + "</h3>"
                    + "Datos de Ingreso: "
                    + "<h4> Correo Usuario : "
                    + emailUsu
                    + "</h4>"
                    + "<h4> Contraseña Usuario : "
                    + contrasena
                    + " </h4>", "text/html");

            //3rd paso)send message
            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

    public static void sendBienvenido(String para, String Nombres, String Email, String Contrasena) {
        final String user = "comunicaciones.petlovers@gmail.com";//cambiará en consecuencia al servidor utilizado
        final String pass = "12345HolaMundo";

//1st paso) Obtener el objeto de sesión
//        Properties props = new Properties();
//        props.setProperty("mail.smtp.host", "smtp.gmail.com"); // envia 
//        props.setProperty("mail.smtp.starttls.enable", "true");
//        props.setProperty("mail.smtp.port", "587");
//        props.setProperty("mail.smtp.starttls.required", "false");
//        props.setProperty("mail.smtp.auth", "true");
//        props.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");
Properties props = new Properties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com"); // envia 
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.port", "587");
//        props.setProperty("mail.smtp.starttls.required", "true");
        props.setProperty("mail.smtp.auth", "true");
//        props.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
        });

//2nd paso)compose message
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(para));
            message.setSubject("Bienvenido a Pet Lovers");

            message.setContent(
                    "<center><img src='https://elsignificadode.net/wp-content/uploads/2017/10/BIENVENIDO.jpg' title='Bienvenidos'></center>"
                    + "<h3> Bienvenido. "
                    + Nombres
                    + "</h3>"
                    + "Datos de Ingreso: "
                    + "<h4> Correo Usuario : "
                    + Email
                    + "</h4>"
                    + "<h4> Clave usuario : "
                    + Contrasena
                    + " </h4>", "text/html");

            //3rd paso)send message
            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

    public static void send(String para, String Nombres, String Email, String Contrasena) {

        final String user = "comunicaciones.petlovers@gmail.com";//cambiará en consecuencia al servidor utilizado
        final String pass = "12345HolaMundo";

//1st paso) Obtener el objeto de sesión
        Properties props = new Properties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com"); // envia 
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.port", "25");
        props.setProperty("mail.smtp.starttls.required", "false");
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
        });

//2nd paso)compose message
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(para));
            message.setSubject("Ultimos Descuentos Pet Lovers");

            message.setContent(
                    "<center><img src='https://www.kanu.pet/arquivos/Banner-10-off-Kanu%20mobile.jpg?v=637189372128400000' title='Descuentos'></center>"
                    + "<h3> Bienvenido. "
                    + Nombres
                    + "</h3>"
                    + "Datos de Ingreso: "
                    + "<h4> Correo Usuario : "
                    + Email
                    + "</h4>"
                    + "<h4> Clave usuario : "
                    + Contrasena
                    + " </h4>", "text/html");
            
            //3rd paso)send message
            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

     public static void sendWelcome(String para, String Nombres, String emailUsu, String contrasena) {
        final String user = "comunicaciones.petlovers@gmail.com";//cambiará en consecuencia al servidor utilizado
        final String pass = "12345HolaMundo";

//1st paso) Obtener el objeto de sesión
        Properties props = new Properties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com"); // envia 
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.port", "587");
//        props.setProperty("mail.smtp.starttls.required", "true");
        props.setProperty("mail.smtp.auth", "true");
//        props.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
        });

//2nd paso)compose message
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(para));
            message.setSubject("Bienvenido a Pet Lovers");

            message.setContent(
                    "<center><img src='https://elsignificadode.net/wp-content/uploads/2017/10/BIENVENIDO.jpg' title='Bienvenidos'></center>"
                    + "<h3> Bienvenido. "
                    + Nombres
                    + "</h3>"
                    + "Datos de Ingreso: "
                    + "<h4> Correo Usuario : "
                    +  emailUsu
                    + "</h4>"
                    + "<h4> Clave usuario : "
                    + contrasena
                    + " </h4>", "text/html");

            //3rd paso)send message
            Transport.send(message);

            System.out.println("Done ");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }
     
     public static void sendDiscounts(String para, String Nombres, String emailUsu, String contrasena) {
        final String user = "comunicaciones.petlovers@gmail.com";//cambiará en consecuencia al servidor utilizado
        final String pass = "12345HolaMundo";

//1st paso) Obtener el objeto de sesión
        Properties props = new Properties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com"); // envia 
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.port", "587");
//        props.setProperty("mail.smtp.starttls.required", "true");
        props.setProperty("mail.smtp.auth", "true");
//        props.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
        });

//2nd paso)compose message
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(para));
            message.setSubject("Ultimos Descuentos Pet Lovers");

            message.setContent(
                    "<center><img src='https://www.kanu.pet/arquivos/Banner-10-off-Kanu%20mobile.jpg?v=637189372128400000' title='Descuentos'></center>"
                    + "<h3> Bienvenido. "
                    + Nombres
                    + "</h3>"
                    + "Datos de Ingreso: "
                    + "<h4> Correo Usuario : "
                    + emailUsu
                    + "</h4>"
                    + "<h4> Clave usuario : "
                    + contrasena
                    + " </h4>", "text/html");

            //3rd paso)send message
            Transport.send(message);

            System.out.println("Done ");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }
    
}
