package com.example.newyorkerdk.entities;

public class Request {

    private final ContactForm contactForm;

    private final Basket basket;

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