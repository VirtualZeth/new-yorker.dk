package com.example.newyorkerdk.UI;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import com.example.newyorkerdk.R;
import com.example.newyorkerdk.UI.util.MinMaxInputFilter;
import com.example.newyorkerdk.databinding.ActivityBuildWallBinding;
import com.example.newyorkerdk.entities.Wall;
import com.example.newyorkerdk.viewmodels.BuildWallViewModel;

import java.util.ArrayList;

import static java.lang.Double.parseDouble;

public class BuildWallActivity extends AppCompatActivity {

    private ActivityBuildWallBinding binding;
    private final Wall currentWall = new Wall();
    BuildWallViewModel model;
    private final ArrayList<EditText> listOfInputFields = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBuildWallBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        attachSeekBarListener(binding.seekBarHeight);
        attachSeekBarListener(binding.seekBarWidth);
        attachEditFieldListener(binding.editTextHeight);
        attachEditFieldListener(binding.editTextWidth);
        setFilter(binding.editTextHeight, 1, 250);
        listOfInputFields.add(binding.editTextHeight);
        listOfInputFields.add(binding.editTextWidth);

        model = new ViewModelProvider(this).get(BuildWallViewModel.class);
        model.getPriceEstimate().observe(this, priceEstimate ->
                binding.priceValueTextfield.setText(getString(R.string.price, priceEstimate)));

    }

    private void setFilter(EditText inputField, double min, double max) {
        inputField.setFilters(new InputFilter[]{ new MinMaxInputFilter(min, max)});
    }

    private void attachEditFieldListener(EditText inputField) {

        inputField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (allFieldsFilled()) {
                    calculatePriceEstimate();
                }
            }
        });
    }

    private void attachSeekBarListener(SeekBar seekBar) {
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (allFieldsFilled()) {
                    calculatePriceEstimate();
                }
            }
        });
    }
    private boolean allFieldsFilled() {

        for (EditText inputfield:listOfInputFields) {
            if (inputfield.getText().length() == 0) {
                return false;
            }
        }
        return true;

    }

    public void calculatePriceEstimate() {
        setupWall();
        model.calculatePriceEstimate(currentWall);
    }

    private void setupWall() {
        currentWall.setHeight(parseDouble(binding.editTextHeight.getText().toString()));
        currentWall.setWidth(parseDouble(binding.editTextWidth.getText().toString()));
        currentWall.setNumberOfGlassFieldsWidth(binding.seekBarWidth.getProgress());
        currentWall.setNumberOfGlassFieldsHeight(binding.seekBarHeight.getProgress());
    }

    private void fillFieldsWithWallData(Wall wall) {

        binding.editTextHeight.setText(String.valueOf(wall.getHeight()));
        binding.editTextWidth.setText(String.valueOf(wall.getWidth()));
        binding.seekBarHeight.setProgress(wall.getNumberOfGlassFieldsHeight());
        binding.seekBarWidth.setProgress(wall.getNumberOfGlassFieldsWidth());
    }
}