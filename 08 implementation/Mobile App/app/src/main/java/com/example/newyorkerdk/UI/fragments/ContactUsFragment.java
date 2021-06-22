package com.example.newyorkerdk.UI.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.newyorkerdk.R;
import com.example.newyorkerdk.databinding.FragmentContactUsBinding;
import com.example.newyorkerdk.entities.Basket;
import com.example.newyorkerdk.entities.ContactForm;
import com.example.newyorkerdk.entities.Request;

import com.example.newyorkerdk.usecase.sendrequest.MailService;
import com.example.newyorkerdk.viewmodels.SharedViewModel;

/**
 * @author Danijel
 * Et fragment {@link Fragment} subclass.
 * Benyt {@link ContactUsFragment#newInstance} factory metode til
 * at skabe en ny instans af dette fragment, som er ansvarlig for at håndtere kontakt us UI
 */
public class ContactUsFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private final ContactForm contactForm = new ContactForm();
    private Basket basket;
    private Request request;
    private final MailService mailService = new MailService();
    private SharedViewModel model;

    String failText = "Feltet skal udfyldes";


    FragmentContactUsBinding binding;
    public ContactUsFragment() {
        // Required empty public constructor
    }

    public static ContactUsFragment newInstance() {

        return new ContactUsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        basket = model.getBasket().getValue();
        request = new Request(contactForm, basket);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentContactUsBinding.inflate(getLayoutInflater());

        Spinner spinnerSupplier = binding.spinnerSupplier;
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(requireActivity(), R.array.supplier, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSupplier.setAdapter((adapter));
        spinnerSupplier.setOnItemSelectedListener(this);

        binding.sendRequestButton.setOnClickListener(v -> {
            EditText editTextName = requireActivity().findViewById(R.id.editTextName);
            String name = editTextName.getText().toString();
            contactForm.setName(name);

            EditText editTextEmail = requireActivity().findViewById(R.id.editTextEmail);
            String email = editTextEmail.getText().toString();
            contactForm.setEmail(email);

            EditText editTextPhoneNumber = requireActivity().findViewById(R.id.editTextNumber);
            String phoneNumber = editTextPhoneNumber.getText().toString();
            contactForm.setPhonenumber(phoneNumber);

            EditText editTextCity = requireActivity().findViewById(R.id.editTextCity);
            String city = editTextCity.getText().toString();
            contactForm.setCity(city);

            EditText editTextMessage = requireActivity().findViewById(R.id.editTextMessage);
            String message = editTextMessage.getText().toString();
            contactForm.setNote(message);


            sendMail(getContext(), request);

        });

        return binding.getRoot();
    }

    private void sendMail(Context context, Request request) {

        if ((contactForm.getName().equals("")) || (contactForm.getEmail().equals("")) || (contactForm.getPhonenumber().equals("")) || (contactForm.getCity().equals("")) || (contactForm.getSupplier().equals("Vælg leverandør"))){

            TextView nameFail = requireActivity().findViewById(R.id.textViewNameFail);
            TextView emailFail = requireActivity().findViewById(R.id.textViewEmailFail);
            TextView numberFail = requireActivity().findViewById(R.id.textViewNumberFail);
            TextView cityFail = requireActivity().findViewById(R.id.textViewCityFail);
            TextView supplierFail = requireActivity().findViewById(R.id.textViewSupplierFail);

            if (contactForm.getName().equals("")){nameFail.setText(failText);}
            else nameFail.setText("");

            if (contactForm.getEmail().equals("")){emailFail.setText(failText);}
            else emailFail.setText("");

            if (contactForm.getPhonenumber().equals("")){numberFail.setText(failText);}
            else numberFail.setText("");

            if (contactForm.getCity().equals("")){cityFail.setText(failText);}
            else cityFail.setText("");

            if (contactForm.getSupplier().equals("Vælg leverandør")){supplierFail.setText("Vælg en leverandør");}
            else supplierFail.setText("");
        }

       else {
            try {
                mailService.sendMail(context, request);
                model.clearWallsFromBasket();
            } catch (Exception e) {
                Log.d("ContactUsFragment", e.toString());
            }
       displayMainFragment();
       }
    }

    private void displayMainFragment() {
        MainFragment mainFragment = MainFragment.newInstance();
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();

        fragmentTransaction.replace(R.id.fragment_container,
                mainFragment).addToBackStack(null).commit();
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String choosenSupplier = parent.getItemAtPosition(position).toString();
        contactForm.setSupplier(choosenSupplier);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

}