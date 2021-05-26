package com.example.newyorkerdk.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.os.Bundle;

import com.example.newyorkerdk.R;
import com.example.newyorkerdk.databinding.ActivityBuildWallBinding;

public class BuildWallActivity extends AppCompatActivity {

    private ActivityBuildWallBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBuildWallBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);


    }
}