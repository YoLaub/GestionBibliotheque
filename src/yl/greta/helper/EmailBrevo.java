package yl.greta.helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import sendinblue.ApiClient;
import sendinblue.Configuration;
import sendinblue.auth.ApiKeyAuth;
import sibApi.TransactionalEmailsApi;
import sibModel.*;
import yl.greta.model.Utilisateur;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Properties;

public class EmailBrevo {
    private String sender;
    private String to;
    private String apiKey;
    private String template;


    public static void sendEmail() throws IOException {
        AppConfig configMail = ConfigManager.loadConfig("conf/conf.json");
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        ApiKeyAuth apiKey = (ApiKeyAuth) defaultClient.getAuthentication("api-key");
        apiKey.setApiKey(configMail.getApiKey());

        try {
            TransactionalEmailsApi api = new TransactionalEmailsApi();

            // Expéditeur
            SendSmtpEmailSender sender = new SendSmtpEmailSender();
            sender.setEmail(configMail.getSender());
            sender.setName("John Doe");

            // Destinataire
            List<SendSmtpEmailTo> toList = new ArrayList<>();
            SendSmtpEmailTo to = new SendSmtpEmailTo();
            to.setEmail(configMail.getTo());
            to.setName("Pascale Lamy");
            toList.add(to);

            // Contenu de l'email
            SendSmtpEmail sendSmtpEmail = new SendSmtpEmail();
            sendSmtpEmail.setSender(sender);
            sendSmtpEmail.setTo(toList);
            sendSmtpEmail.setSubject("Etat Bibliothèque");
            sendSmtpEmail.setHtmlContent(configMail.getTemplate());

            // Pièce jointe
            File file = new File("state_of_the_day.pdf");
            if (!file.exists()) {
                throw new IOException("Le fichier state_of_the_day.pdf n'existe pas.");
            }

            byte[] fileBytes = Files.readAllBytes(file.toPath());

            SendSmtpEmailAttachment attachment = new SendSmtpEmailAttachment();
            attachment.setName("state_of_the_day.pdf");
            attachment.setContent(fileBytes);   // juste le contenu encodé

            sendSmtpEmail.setAttachment(List.of(attachment));

            // Envoi
            CreateSmtpEmail response = api.sendTransacEmail(sendSmtpEmail);
            System.out.println("Email envoyé avec succès ! Réponse : " + response);
        } catch (Exception e) {
            System.out.println("Erreur lors de l'envoi : " + e.getMessage());
            e.printStackTrace();
        }
    }




}
