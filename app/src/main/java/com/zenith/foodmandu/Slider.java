package com.zenith.foodmandu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.zenith.foodmandu.api.ImageAPI;
import com.zenith.foodmandu.url.Url;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Slider extends AppCompatActivity {

    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider);

        imageView = findViewById(R.id.imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               addslider();
            }

            private void addslider() {
                ImageAPI imageAPI = Url.getInstance().create(ImageAPI.class);

                Call<Void> voidCall = imageAPI.addimage(Url.token,imageView.toString());

                voidCall.enqueue(new Callback<Void>() {
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (!response.isSuccessful()) {
                            Toast.makeText(Slider.this, "Code", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Toast.makeText(Slider.this, "Added", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(Slider.this, "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });}
        });
}
}
