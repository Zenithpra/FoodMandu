package com.zenith.foodmandu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.zenith.foodmandu.api.UsersAPI;
import com.zenith.foodmandu.model.User;
import com.zenith.foodmandu.serverresponse.ImageResponse;
import com.zenith.foodmandu.serverresponse.SignUpResponse;
import com.zenith.foodmandu.strictmode.StrictModeClass;
import com.zenith.foodmandu.url.Url;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {

    ImageView ProfileImg;
    EditText etFirstname, etLastname, etUsername, etPassword, etconPassword;
    Button btnRegister;
    String imagePath="";
    private  String imageName = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        ProfileImg = findViewById(R.id.ProfileImg);
        etFirstname = findViewById(R.id.etFirstname);
        etFirstname = findViewById(R.id.etFirstname);
        etLastname = findViewById(R.id.etLastname);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        etconPassword = findViewById(R.id.etConPassword);
        btnRegister = findViewById(R.id.btnRegister);

        ProfileImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BrowseImage();
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etPassword.getText().toString().equals((etconPassword.getText().toString()))){
                    if(validate()){
                        saveImageOnly();
                        signUp();
                    }
                }else {
                    Toast.makeText(SignupActivity.this, "Password does not match", Toast.LENGTH_SHORT).show();
                    etPassword.requestFocus();
                    return;
                }
            }
        });
    }
    private boolean validate() {
        boolean status = true;
        if (etPassword.getText().toString().length() < 6) {
            etPassword.setError("Random Number");
            status = false;
        }
        return status;
    }
    private void BrowseImage() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,0);
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            if (data == null) {
                Toast.makeText(this, "Select an image ", Toast.LENGTH_SHORT).show();
            }
        }
        Uri uri = data.getData();
        ProfileImg.setImageURI(uri);
        imagePath = getRealPathFromUri(uri);
    }

    private String getRealPathFromUri(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(getApplicationContext(),
            uri, projection, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int colIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(colIndex);
        cursor.close();
        return result;
    }
    private void saveImageOnly() {
        File file = new File(imagePath);
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("imageFile",
                file.getName(), requestBody);

        UsersAPI usersAPI = Url.getInstance().create(UsersAPI.class);
        retrofit2.Call<ImageResponse> responseBodyCall = usersAPI.uploadImage(body);

        StrictModeClass.StrictMode();

        try {
            retrofit2.Response<ImageResponse> imageResponseResponse = responseBodyCall.execute();
            imageName = imageResponseResponse.body().getFilename();
            Toast.makeText(this, "Image inserted", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this, "Error" + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            e.getStackTrace();
        }
    }

    private void signUp() {
        String fname = etFirstname.getText().toString();
        String lname = etLastname.getText().toString();
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        String conpassword = etconPassword.getText().toString();

        User users = new User(fname, lname, username, password, conpassword, imageName);

        UsersAPI usersAPI = Url.getInstance().create(UsersAPI.class);
        retrofit2.Call<SignUpResponse> signUpCall = usersAPI.registerUser(users);

        signUpCall.enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(SignupActivity.this, "Registered", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(SignupActivity.this, "Registered", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                Toast.makeText(SignupActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
