package com.example.newyorkerdk.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.newyorkerdk.R;

public class ContactUsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

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
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}