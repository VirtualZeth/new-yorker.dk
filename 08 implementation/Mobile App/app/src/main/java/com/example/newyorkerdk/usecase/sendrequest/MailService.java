package com.example.newyorkerdk.usecase.sendrequest;

import android.content.Context;

import com.example.newyorkerdk.entities.Basket;
import com.example.newyorkerdk.entities.ContactForm;
import com.example.newyorkerdk.entities.Request;

/**
 * @author Danijel
 * Ansvarliig for opsætning af mailen
 * Får de nødvendige datere fra {@link Request}
 */

public class MailService {

    public void sendMail(Context context, Request request) {
        ContactForm contactForm = request.getContactForm();
        Basket basket = request.getBasket();

        String mail = MailCredentials.getEMAIL();
        String subject = "Forespørgsel fra: " + contactForm.getName();
        String message = "Kontakt oplysninger:\n" + "Navn: " + contactForm.getName() + "\n" +
                "email: "+ contactForm.getEmail() + "\n" + "Telefonnummer: " + contactForm.getPhonenumber() + "\n" + "By: " + contactForm.getCity() +
                "\nValgt forhandler: " + contactForm.getSupplier() + "\n" + "\nBesked fra kunden: \n" + contactForm.getNote() + "\n\n" +
                "Forespørgelsen indeholder følgende vægge:\n\n" + basket;

        JavaMailAPI javaMailAPI = new JavaMailAPI(context , mail , subject , message );
        javaMailAPI.execute();
    }
}
