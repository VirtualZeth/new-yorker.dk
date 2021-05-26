package com.example.newyorkerdk.entities;

public class Request {

    private ContactForm contactForm;

    private Basket basket;

    public Request(ContactForm contactForm, Basket basket) {
        this.contactForm = contactForm;
        this.basket = basket;
    }
}
