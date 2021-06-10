package com.example.newyorkerdk.entities;
/**
 * Ansvarlig for at indeholde {@link Basket} og {@link ContactForm},
 * således at {@link com.example.newyorkerdk.usecase.sendrequest.MailService}
 * kan sende mail indeholdende nødvendig information
 * @author Mike
 */
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