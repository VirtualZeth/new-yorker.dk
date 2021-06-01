package com.example.newyorkerdk.UI;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.newyorkerdk.R;
import com.example.newyorkerdk.databinding.FragmentContactUsBinding;
import com.example.newyorkerdk.entities.Basket;
import com.example.newyorkerdk.entities.ContactForm;
import com.example.newyorkerdk.entities.Request;
import com.example.newyorkerdk.usecase.JavaMailAPI;
import com.example.newyorkerdk.usecase.MailCredentials;
import com.example.newyorkerdk.viewmodels.SharedViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ContactUsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactUsFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private final ContactForm contactForm = new ContactForm();
    private final Basket basket = new Basket();
    private final Request request = new Request(contactForm, basket);
    private SharedViewModel model;


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

            sendMail();
        });

        return binding.getRoot();
    }

    private void sendMail() {

        String mail = "danijelgitanovic@gmail.com";
        String subject = "Forespørgsel fra: " + contactForm.getName();
        String message = "Kontakt oplysninger:\n" + contactForm.getName() + "\n" + contactForm.getEmail() + "\n" + contactForm.getPhonenumber() + "\n" + contactForm.getCity() + "\nValgte leverandør: \n" + contactForm.getSupplier() + "\n\nBesked fra kunden: \n" + contactForm.getNote();


        JavaMailAPI javaMailAPI = new JavaMailAPI(getContext() , mail , subject , message );
        javaMailAPI.execute();

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