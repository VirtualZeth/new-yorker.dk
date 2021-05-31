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

    public ContactForm getContactForm() {
        return contactForm;
    }

    public Basket getBasket() {
        return basket;
    }
}