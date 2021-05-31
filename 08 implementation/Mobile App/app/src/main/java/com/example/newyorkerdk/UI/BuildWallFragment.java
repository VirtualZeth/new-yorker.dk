package com.example.newyorkerdk.UI;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.newyorkerdk.R;
import com.example.newyorkerdk.UI.util.MinMaxInputFilter;
import com.example.newyorkerdk.databinding.FragmentBuildWallBinding;
import com.example.newyorkerdk.entities.Wall;
import com.example.newyorkerdk.viewmodels.SharedViewModel;

import java.util.ArrayList;

import static java.lang.Double.parseDouble;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BuildWallFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BuildWallFragment extends Fragment {

    private FragmentBuildWallBinding binding;
    private Wall currentWall;
    private SharedViewModel model;
    private final ArrayList<EditText> listOfInputFields = new ArrayList<>();

    public BuildWallFragment() {
        // Required empty public constructor
    }

    public static BuildWallFragment newInstance() {
        return new BuildWallFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentWall = new Wall();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentBuildWallBinding.inflate(getLayoutInflater());

        attachSeekBarListener(binding.seekBarHeight, binding.seekbarHeightTextfield);
        attachSeekBarListener(binding.seekBarWidth, binding.seekbarWidthTextfield);
        attachEditFieldListener(binding.editTextHeight);
        attachEditFieldListener(binding.editTextWidth);
        setFilter(binding.editTextHeight, 1, 250);
        listOfInputFields.add(binding.editTextHeight);
        listOfInputFields.add(binding.editTextWidth);

        binding.addButton.setOnClickListener(event -> addWallToBasket());
        binding.doneButton.setOnClickListener(event -> displayBasketFragment() );

        model = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        model.getPriceEstimate().observe(requireActivity(), priceEstimate ->

                binding.priceValueTextfield.setText(getString(R.string.price, priceEstimate)
                ));
        model.calculatePriceEstimate(currentWall);

        return binding.getRoot();
    }

    public void addWallToBasket() {
        if (!allFieldsFilled()) {
            return;
        }
        model.addToBasket(currentWall);
    }



    public void displayBasketFragment() {

        BasketFragment basketFragment = BasketFragment.newInstance();
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();

        fragmentTransaction.replace(R.id.fragment_container,
                basketFragment).addToBackStack(null).commit();
    }

    public void calculatePriceEstimate() {
        setupWall();
        model.calculatePriceEstimate(currentWall);
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

    private void attachSeekBarListener(SeekBar seekBar, TextView seekBarTextField) {
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBarTextField.setText(String.valueOf(progress));
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
            if (inputFieldIsEmpty(inputfield)) {
                return false;
            }
        }
        return true;
    }
    private boolean inputFieldIsEmpty(EditText inputField) {
        return inputField.getText().toString().trim().length() == 0;
    }

    private void setupWall() {
        if (!inputFieldIsEmpty(binding.editTextNote)) {
            currentWall.setName(binding.editTextNote.getText().toString());
        } else {
            currentWall.setName("Væg");
        }
        currentWall.setName(binding.editTextNote.getText().toString());
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