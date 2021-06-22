package com.example.newyorkerdk.UI.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        attachSeekBarListener(binding.seekBarHeight, binding.seekbarHeightTextfield);
        attachSeekBarListener(binding.seekBarWidth, binding.seekbarWidthTextfield);
        attachEditFieldListenerHeight(binding.editTextHeight);
        attachEditFieldListenerWidth(binding.editTextWidth);
        binding.seekBarWidth.setTag("seekBarWidth");
        binding.seekBarHeight.setTag("seekBarHeight");

        binding.addButton.setOnClickListener(event -> addWallToBasket());
        binding.doneButton.setOnClickListener(event -> displayBasketFragment());
        model = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        new ViewModelProvider.NewInstanceFactory().create(SharedViewModel.class);
        model.getCurrentWall().observe(requireActivity(), this::fillFieldsWithWallData);
        model.getPriceEstimate().observe(requireActivity(), priceEstimate ->
                binding.priceValueTextfield.setText(getString(R.string.price, priceEstimate)));
        model.getAdditions().observe(requireActivity(), this::buildAdditions);
        model.getMutableSuggestedFieldsHeight().observe(requireActivity(),
                this::setSeekbarHeight);
        model.getMutableSuggestedFieldsWidth().observe(requireActivity(),
                this::setSeekbarWidth);

        return binding.getRoot();
    }

    private void setSeekbarWidth(Integer suggestedFieldsWidth) {

        binding.seekBarWidth.setProgress(suggestedFieldsWidth);
        model.setCurrentWallSeekBarWidth(suggestedFieldsWidth);
    }

    private void setSeekbarHeight(Integer suggestedFieldsHeight) {

        binding.seekBarHeight.setProgress(suggestedFieldsHeight);
        model.setCurrentWallSeekBarHeight(suggestedFieldsHeight);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        model.getCurrentWall().removeObservers(requireActivity());
        model.getPriceEstimate().removeObservers(requireActivity());
        model.getAdditions().removeObservers(requireActivity());
    }

    private void buildAdditions(HashMap<String, ArrayList<Addition>> additions) {
        expandableListView = binding.expandableListView;
        expandableListTitle = new ArrayList<>(additions.keySet());
        expandableListAdapter = new AdditionsExpandableListAdapter(requireActivity(), expandableListTitle, additions);
        expandableListView.setAdapter(expandableListAdapter);

        expandableListView.setOnChildClickListener((parent, v, groupPosition, childPosition, id) -> {

                model.toggleAddition(
                        additions.get(
                                expandableListTitle.get(groupPosition))
                                .get(childPosition));

            return false;
        });
    }

    public void addWallToBasket() {

        model.setCurrentWallNote(binding.editTextNote.getText().toString());
        model.addToBasket(model.getCurrentWall().getValue());
    }

    private void attachEditFieldListenerHeight(EditText inputfield) {

        inputfield.setOnFocusChangeListener((v, hasFocus) -> {
            if (!updatingFields || inputfield.getText().toString().length() == 0) {
                model.setCurrentWallHeight(parseDouble(inputfield.getText().toString()));
            }
        });
    }
    private void attachEditFieldListenerWidth(EditText inputfield) {


        inputfield.setOnFocusChangeListener((v, hasFocus) -> {
            if (!updatingFields || inputfield.getText().toString().length() == 0) {
                model.setCurrentWallWidth(parseDouble(inputfield.getText().toString()));
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