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

public class SignUpActivity extends AppCompatActivity implements AsyncCallback<BackendlessUser> {
    EditText nameText,mailText,passText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        nameText=findViewById(R.id.nameText);
        mailText=findViewById(R.id.loginMail);
        passText=findViewById(R.id.loginPass);

        getSupportActionBar().setTitle("SIGN UP");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Transparent Action Bar
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
//        getSupportActionBar().hide();

    }

    public void register(View view) {
        BackendlessUser user=new BackendlessUser();
        user.setEmail(mailText.getText().toString());
        user.setPassword(passText.getText().toString());
        user.setProperty("name",nameText.getText().toString());

        Backendless.UserService.register(user,this);

    }

    @Override
    public void handleResponse(BackendlessUser response) {
        Toast.makeText(this, "user saved", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void handleFault(BackendlessFault fault) {
        if (fault.getCode().equals("3033"))
            Toast.makeText(this, "user exists", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "error in register", Toast.LENGTH_SHORT).show();

    }

    public void haveAccount(View view) {
        Intent intent=new Intent(this,LoginActivity.class);
        startActivity(intent);
    }
}