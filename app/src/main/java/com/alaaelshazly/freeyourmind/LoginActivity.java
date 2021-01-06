package com.alaaelshazly.freeyourmind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

public class LoginActivity extends AppCompatActivity {
    EditText loginMail,loginPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginMail=findViewById(R.id.loginMail);
        loginPass=findViewById(R.id.loginPass);

        getSupportActionBar().setTitle("LOG IN");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Backendless.initApp(this,"67D6ED81-C5CB-A3BF-FFA9-F6619DACA800","1AFA5BF7-95B3-42C3-90F3-66816C1435F0");
    }

    public void createAccount(View view) {
        Intent in=new Intent(this,SignUpActivity.class);
        startActivity(in);
    }

    public void login(View view) {
        String mail=loginMail.getText().toString();
        String pass=loginPass.getText().toString();

        Backendless.UserService.login(mail, pass, new AsyncCallback<BackendlessUser>() {
            @Override
            public void handleResponse(BackendlessUser response) {
                Intent in=new Intent(LoginActivity.this,BookActivity.class);
                startActivity(in);
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                if (fault.getCode().equals("3003"))
                    Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(LoginActivity.this, "error in login", Toast.LENGTH_SHORT).show();
            }
        });
    }
}