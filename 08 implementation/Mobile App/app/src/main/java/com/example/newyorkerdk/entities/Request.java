package com.example.newyorkerdk.entities;


import com.example.newyorkerdk.usecase.JavaMailAPI;

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