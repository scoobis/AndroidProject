package com.example.serviceapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;

import com.example.serviceapp.R;

public class LoginView extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private EditText editTextEmail;
    private EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_view);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
    }

    public void loginClicked(View view) {
        Intent startIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(startIntent);
        overridePendingTransition(0, 0);

        // TODO check if password is correct!
        editor.putString(getString(R.string.email1), editTextEmail.getText().toString());
        editor.commit();

    }
}
