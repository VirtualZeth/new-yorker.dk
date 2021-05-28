package com.example.newyorkerdk.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.newyorkerdk.R;

public class ThirdActivity extends AppCompatActivity {


    ImageView i1;
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_basket);
        i1 = (ImageView) findViewById(R.id.imageView2);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {

            int resid = bundle.getInt("resId");
            i1.setImageResource(resid);
        }

    }


    public void backToWall(View view) {
        startActivity(new Intent(ThirdActivity.this,BuildWallActivity.class));
    }



    public void delete(View view) {
        setContentView(R.layout.activity_basket);
        i1 = (ImageView) findViewById(R.id.imageView2);
        Bundle bundle = getIntent().getExtras();

        int resid = bundle.getInt("resId");
        i1.setImageResource(resid);

        i1.setImageBitmap(null);

        textView = (TextView)findViewById((R.id.textView));

        textView.setText("");
        ImageView imgview = findViewById(R.id.imageView4);

        imgview.setImageDrawable(null);



    }
}

