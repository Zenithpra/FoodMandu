package com.zenith.foodmandu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zenith.foodmandu.bll.LoginBLL;
import com.zenith.foodmandu.strictmode.StrictModeClass;

public class Login extends AppCompatActivity {

    private EditText etUser, etPass;
    private Button btnLogin;
    private Button btnClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUser = findViewById(R.id.etUser);
        etPass = findViewById(R.id.etPass);
        btnLogin = findViewById(R.id.btnLogin);
        btnClick = findViewById(R.id.btnClick);

        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Login.this, "Wait", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Login.this, SignupActivity.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   login();
//                Toast.makeText(Login.this, "", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(Login.this,MainActivity.class);
//                startActivity(intent);
            }
            private void login(){
                String username = etUser.getText().toString();
                String password = etPass.getText().toString();

                LoginBLL loginBLL = new LoginBLL();

                StrictModeClass.StrictMode();
                if (loginBLL.checkUser(username, password)) {
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(Login.this, "Username or password is incorrect", Toast.LENGTH_SHORT).show();
                    etUser.requestFocus();
                }
            }
        });
    }
}
