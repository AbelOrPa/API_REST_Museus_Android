package com.example.museos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private EditText user,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = (EditText)findViewById(R.id.userText);
        pass = (EditText)findViewById(R.id.pass);

    }


    public void btnLogin(View view){

        String password = pass.getText().toString();
        String username = user.getText().toString();

        if(password.equals("dsamola") && username.equals("user")){

            Intent intent = new Intent(this,showMuseosActivity.class);
            savePreferences();
            startActivity(intent);
            finish();

        }
        else{

            Toast.makeText(this,"Usuario o contraseña incorrectos",Toast.LENGTH_SHORT).show();

        }

    }

    private void savePreferences(){
        SharedPreferences preferences= getSharedPreferences("Login credentials", Context.MODE_PRIVATE);
        String nickname = user.getText().toString();
        String password = pass.getText().toString();
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("userNickname", nickname);
        editor.putString("userPassword", password);
        editor.apply();


    }


}