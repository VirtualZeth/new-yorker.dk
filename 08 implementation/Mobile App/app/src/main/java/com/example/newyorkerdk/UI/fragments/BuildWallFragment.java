package com.example.newyorkerdk.UI.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.newyorkerdk.R;
import com.example.newyorkerdk.UI.adapters.AdditionsExpandableListAdapter;
import com.example.newyorkerdk.databinding.FragmentBuildWallBinding;
import com.example.newyorkerdk.entities.Addition;
import com.example.newyorkerdk.entities.Wall;
import com.example.newyorkerdk.viewmodels.SharedViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.lang.Double.parseDouble;

/**
 * Benyt {@link BuildWallFragment#newInstance} factory metode til
 * at skabe en ny instans af dette fragment som er ansvarlig for håndtere byg væg UI'en
 * @author Mike
 */
public class BuildWallFragment extends Fragment {

    private FragmentBuildWallBinding binding;
    private SharedViewModel model;
    private boolean updatingFields;

    private ExpandableListView expandableListView;
    private ExpandableListAdapter expandableListAdapter;
    private List<String> expandableListTitle;
    private HashMap<String, ArrayList<Addition>> expandableListDetail;

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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        binding = FragmentBuildWallBinding.inflate(getLayoutInflater());
        binding.editTextHeight.setTag("editTextHeight");
        binding.editTextWidth.setTag("editTextWidth");
        binding.editTextNote.setTag("editTextNote");
        binding.seekBarWidth.setTag("seekBarWidth");
        binding.seekBarHeight.setTag("seekBarHeight");
        attachSeekBarListener(binding.seekBarHeight, binding.seekbarHeightTextfield);
        attachSeekBarListener(binding.seekBarWidth, binding.seekbarWidthTextfield);
        attachEditFieldListener(binding.editTextHeight);
        attachEditFieldListener(binding.editTextWidth);
       attachEditFieldListener(binding.editTextNote);



        binding.addButton.setOnClickListener(event -> addWallToBasket());
        binding.doneButton.setOnClickListener(event -> displayBasketFragment());
        model = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        new ViewModelProvider.NewInstanceFactory().create(SharedViewModel.class);
        model.getCurrentWall().observe(requireActivity(), this::fillFieldsWithWallData);
        model.getPriceEstimate().observe(requireActivity(), priceEstimate ->
                binding.priceValueTextfield.setText(getString(R.string.price, priceEstimate)));
        model.getAdditions().observe(requireActivity(), this::buildAdditions);

        return binding.getRoot();
    }

    private void buildAdditions(HashMap<String, ArrayList<Addition>> additions) {
        Log.d("expand", "inside build additions");
        expandableListView = binding.expandableListView;
        expandableListTitle = new ArrayList<>(additions.keySet());
        expandableListAdapter = new AdditionsExpandableListAdapter(requireActivity(), expandableListTitle, additions);
        expandableListView.setAdapter(expandableListAdapter);

        expandableListView.setOnChildClickListener((parent, v, groupPosition, childPosition, id) -> {

                model.addAdditionToWall(
                        additions.get(
                                expandableListTitle.get(groupPosition))
                                .get(childPosition));

            return false;
        });
    }

    public void addWallToBasket() {

        model.addToBasket(model.getCurrentWall().getValue());
    }

    private void attachEditFieldListener(EditText inputField) {

        if (!inputField.getTag().equals("editTextNote")) {
            inputField.setOnFocusChangeListener((v, hasFocus) -> {
                if (updatingFields || inputField.getText().toString().length() == 0) {
                    return;
                }
                String tag = inputField.getTag().toString();
                switch (tag) {
                    case "editTextHeight":
                        model.setCurrentWallHeight(parseDouble(inputField.getText().toString()));
                        break;
                    case "editTextWidth":
                        model.setCurrentWallWidth(parseDouble(inputField.getText().toString()));
                        break;
                    default:
                        break;
                }
            });
        } else {
            inputField.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (!updatingFields) {
                        model.setCurrentWallNote(s.toString());
                    }
                }
            });
        }
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