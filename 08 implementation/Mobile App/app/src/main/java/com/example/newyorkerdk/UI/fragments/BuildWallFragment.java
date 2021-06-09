package com.example.newyorkerdk.UI.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.newyorkerdk.R;
import com.example.newyorkerdk.UI.util.MinMaxInputFilter;
import com.example.newyorkerdk.databinding.FragmentBuildWallBinding;
import com.example.newyorkerdk.entities.Wall;
import com.example.newyorkerdk.viewmodels.SharedViewModel;

import static java.lang.Double.parseDouble;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BuildWallFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BuildWallFragment extends Fragment {

    private FragmentBuildWallBinding binding;
    private SharedViewModel model;
    private boolean updatingFields;


    public BuildWallFragment() {
        // Required empty public constructor
    }

    public static BuildWallFragment newInstance() {
        return new BuildWallFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        updatingFields = false;
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
        attachEditFieldListener(binding.editTextNote);
        binding.editTextHeight.setTag("editTextHeight");
        binding.editTextWidth.setTag("editTextWidth");
        binding.editTextNote.setTag("editTextNote");
        binding.seekBarWidth.setTag("seekBarWidth");
        binding.seekBarHeight.setTag("seekBarHeight");

        binding.addButton.setOnClickListener(event -> addWallToBasket());
        binding.doneButton.setOnClickListener(event -> displayBasketFragment());

        model = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        model.getCurrentWall().observe(requireActivity(), this::fillFieldsWithWallData);
        model.getPriceEstimate().observe(requireActivity(), priceEstimate ->
                binding.priceValueTextfield.setText(getString(R.string.price, priceEstimate)
                ));
        return binding.getRoot();
    }

    @Override
    public void onStop() {
        super.onStop();
        model.getBasket().removeObservers(requireActivity());
        model.getPriceEstimate().removeObservers(requireActivity());
    }

    public void addWallToBasket() {

        model.addToBasket(model.getCurrentWall().getValue());
        model.newCurrentWall();
    }

    private void attachEditFieldListener(EditText inputField) {
        inputField.setOnFocusChangeListener((v, hasFocus) -> {
            if (updatingFields || inputField.getText().toString().length() == 0) {
                return;
            }
            String tag = inputField.getTag().toString();
            switch (tag) {
                case "editTextHeight": model.setCurrentWallHeight(parseDouble(inputField.getText().toString())); break;
                case "editTextWidth": model.setCurrentWallWidth(parseDouble(inputField.getText().toString())); break;
                case "editTextNote": model.setCurrentWallNote(inputField.getText().toString()); break;
                default: break;
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
                String tag = seekBar.getTag().toString();

                switch (tag) {
                    case "seekBarHeight": model.setCurrentWallSeekBarHeight(seekBar.getProgress());break;
                    case "seekBarWidth": model.setCurrentWallSeekBarWidth(seekBar.getProgress()); break;
                    default: break;
                }

            }
        });
    }

    private void fillFieldsWithWallData(Wall wall) {

        updatingFields = true;
        binding.editTextHeight.setText(String.valueOf(wall.getHeight()));
        binding.editTextWidth.setText(String.valueOf(wall.getWidth()));
        binding.seekBarHeight.setProgress(wall.getNumberOfGlassFieldsHeight());
        binding.seekBarWidth.setProgress(wall.getNumberOfGlassFieldsWidth());
        binding.editTextNote.setText(wall.getName());
        updatingFields = false;
    }

    public void displayBasketFragment() {

        BasketFragment basketFragment = BasketFragment.newInstance();
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,
                basketFragment).addToBackStack(null).commit();
    }


}