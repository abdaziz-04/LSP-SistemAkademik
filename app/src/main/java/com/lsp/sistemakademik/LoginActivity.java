package com.lsp.sistemakademik;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    EditText ETUsername, ETPassword;
    Button BTNLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        ETUsername = findViewById(R.id.ed_username);
        ETPassword = findViewById(R.id.ed_password);
        BTNLogin = findViewById(R.id.btn_login);

        BTNLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkLogin();
            }
        });
    }

    private void checkLogin() {
        String username = ETUsername.getText().toString();
        String pw = ETPassword.getText().toString();

        if (username.equals("abdaziz04") && pw.equals("aziz123")) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            Toast.makeText(this, "Login Berhasil!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Username/Password salah!", Toast.LENGTH_LONG).show();
        }
    }
}