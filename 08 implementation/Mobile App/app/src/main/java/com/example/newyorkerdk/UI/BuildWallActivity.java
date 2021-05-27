package com.example.newyorkerdk.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.newyorkerdk.R;
import com.example.newyorkerdk.ThirdActivity;
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


    public void send(View v) {

        Intent i = new Intent(BuildWallActivity.this, ThirdActivity.class);
        i.putExtra("resId",R.drawable.newyorker);
        startActivity(i);




    }


}