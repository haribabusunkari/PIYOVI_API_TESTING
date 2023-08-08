package com.piyovi.util;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Attachments;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class EmailUtil {

    public void sendEmail() throws IOException {
        Email from = new Email("hari@piyovi.com");
        String subject = "PIYOVI Carrier API Automation Report";
        String[] toEmails = {"krishna@piyovi.com"};
        Personalization personalization = new Personalization();

        for (int i = 0, size = toEmails.length; i < size; i++) {
            personalization.addTo(new Email(toEmails[i]));
        }
        String toEmailIDs = "krishna@piyovi.com,hari@piyovi.com,rajesh.sarma@piyovi.com,manasa.madala@piyovi.com,adithya@piyovi.com,sriram.sadineni@piyovi.com,monica@piyovi.com";
        Email to = new Email(toEmailIDs);
        Content content = new Content("text/plain", "Please find attached API Automation Execution Report.");
        Mail mail1 = new Mail(from, subject, to, content);
        Mail mail = EmailUtil.addAttachment(mail1);
        mail.addPersonalization(personalization);
        System.setProperty("apikey","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");

        SendGrid sg = new SendGrid(System.getProperty("apikey"));
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static Mail addAttachment(Mail mail) throws IOException {
        try (final InputStream inputStream = Files.newInputStream(Paths.get("extentReport.html"))) {
            final Attachments attachments = new Attachments
                    .Builder("extentReport.html", inputStream)
                    .withType("application/html")
                    .build();
            mail.addAttachments(attachments);
        }
        return mail;
    }
}
