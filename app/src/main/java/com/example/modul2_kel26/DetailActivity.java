package com.example.modul2_kel26;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    private TextView tvFirstName, tvEmail;
    private ImageView imgAvatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvFirstName = findViewById(R.id.tv_first_name);
        tvEmail = findViewById(R.id.tv_email);
        imgAvatar = findViewById(R.id.img_avatar);

        if (getIntent().hasExtra("name") && getIntent().hasExtra("email") && getIntent().hasExtra("avatar")) {
            String name = getIntent().getStringExtra("name");
            String email = getIntent().getStringExtra("email");
            String avatarUrl = getIntent().getStringExtra("avatar");

            tvFirstName.setText(name);
            tvEmail.setText(email);
            Glide.with(this).load(avatarUrl).into(imgAvatar);
        }
    }
}
