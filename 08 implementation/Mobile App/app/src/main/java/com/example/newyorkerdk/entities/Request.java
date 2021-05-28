package com.example.newyorkerdk.entities;


import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import java.io.IOException;


public class Request {

    private ContactForm contactForm;

    private Basket basket;


    public Request(ContactForm contactForm, Basket basket) {
        this.contactForm = contactForm;
        this.basket = basket;

    }

    String apiKey = "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";

    public void SendRequest() throws IOException {

        Email from = new Email("dani025a@edu.easj.dk");
        Email to = new Email("dani025a@edu.easj.dk");
        String subject = ("Forespørgsel fra: " + contactForm.getName());
        Content content = new Content("text/plain", "Kontakt informationer: \n" + contactForm.getName() + "\n" + contactForm.getEmail() + "\n" + contactForm.getPhonenumber() + "\n" + contactForm.getCity() + "\n" + contactForm.getSupplier() + "\n" + contactForm.getNote());
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(apiKey);
        com.sendgrid.Request request = new com.sendgrid.Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            throw ex;
        }
    }
}