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


    public Request(ContactForm contactForm, Basket basket) throws IOException {
        this.contactForm = contactForm;
        this.basket = basket;

        Email from = new Email(contactForm.getEmail());
        Email to = new Email("dani025a@edu.easj.dk");
        String subject = ("Kontakt informationer: \n" + contactForm.getEmail() + "\n" + contactForm.getEmail() + "\n" + contactForm.getPhonenumber() + "\n" + contactForm.getCity() + "\n" + contactForm.getSupplier() + "\n" + contactForm.getNote());
        Content content = new Content("text/plain", "and easy to do anywhere, even with Java");
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(System.getenv("i99y76uiQHmcKycCWtJ00g"));
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
