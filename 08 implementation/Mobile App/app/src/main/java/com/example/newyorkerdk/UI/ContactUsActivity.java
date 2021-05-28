package com.example.newyorkerdk.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.newyorkerdk.R;
import com.example.newyorkerdk.entities.Basket;
import com.example.newyorkerdk.entities.ContactForm;
import com.example.newyorkerdk.entities.Request;

import java.io.IOException;

public class ContactUsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    private final ContactForm contactForm = new ContactForm();
    private final Basket basket = new Basket();

    private final Request request = new Request(contactForm, basket);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        Spinner spinnerSupplier = findViewById(R.id.spinnerSupplier);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.supplier, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSupplier.setAdapter((adapter));
        spinnerSupplier.setOnItemSelectedListener(this);

    }




    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String choosenSupplier = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), choosenSupplier, Toast.LENGTH_SHORT).show();
        contactForm.setSupplier(choosenSupplier);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }


    public void onButtonSendRequestClick(View view){
        EditText editTextName = findViewById(R.id.editTextName);
        String name = editTextName.getText().toString();
        contactForm.setName(name);

        EditText editTextEmail =  findViewById(R.id.editTextEmail);
        String email = editTextEmail.getText().toString();
        contactForm.setEmail(email);

        EditText editTextPhoneNumber = findViewById(R.id.editTextNumber);
        String phoneNumber = editTextPhoneNumber.getText().toString();
        contactForm.setPhonenumber(phoneNumber);

        EditText editTextCity = findViewById(R.id.editTextCity);
        String city = editTextCity.getText().toString();
        contactForm.setCity(city);

        EditText editTextMessage = findViewById(R.id.editTextMessage);
        String message = editTextMessage.getText().toString();
        contactForm.setNote(message);

        Spinner editTextSupplier = findViewById(R.id.spinnerSupplier);
        String supplier = editTextSupplier.getContext().toString();
        contactForm.setNote(supplier);

        try {
            request.SendRequest();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}