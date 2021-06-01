package com.example.newyorkerdk.usecase;

import android.content.Context;

import com.example.newyorkerdk.entities.Basket;
import com.example.newyorkerdk.entities.ContactForm;
import com.example.newyorkerdk.entities.Request;

public class MailService {


    public void sendMail(Context context, Request request) {

        ContactForm contactForm = request.getContactForm();
        //Basket basket = request.getBasket();

        String mail = MailCredentials.getEMAIL();
        String subject = "Forespørgsel fra: " + contactForm.getName();
        String message = "Kontakt oplysninger:\n" + contactForm.getName() + "\n" + contactForm.getEmail() + "\n" + contactForm.getPhonenumber() + "\n" + contactForm.getCity() + "\nValgte leverandør: \n" + contactForm.getSupplier() + "\n\nBesked fra kunden: \n" + contactForm.getNote();


        JavaMailAPI javaMailAPI = new JavaMailAPI(context , mail , subject , message );
        javaMailAPI.execute();
    }
}
