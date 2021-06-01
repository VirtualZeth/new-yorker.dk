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
import com.example.newyorkerdk.usecase.RequestSender;
import com.example.newyorkerdk.viewmodels.SharedViewModel;

import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ContactUsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactUsFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private SharedViewModel model;
    private final RequestSender requestSender = new RequestSender();
    private final ContactForm contactForm = new ContactForm();


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

        binding.sendRequestButton.setOnClickListener(v -> sendRequest());

        return binding.getRoot();
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

    public void sendRequest() {

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

        Spinner editTextSupplier = requireActivity().findViewById(R.id.spinnerSupplier);
        String supplier = editTextSupplier.getContext().toString();
        contactForm.setNote(supplier);

        try {
            requestSender.sendRequest(new Request(contactForm, model.getBasket().getValue()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}