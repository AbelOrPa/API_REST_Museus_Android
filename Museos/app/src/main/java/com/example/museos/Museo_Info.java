package com.example.museos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.museos.Model.Element;
import com.squareup.picasso.Picasso;

public class Museo_Info extends AppCompatActivity {


    private ImageView imageM;
    private TextView museoinfi;
    private Element element;
    private SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_museo__info);

        museoinfi = (TextView) findViewById(R.id.infoMus);
        imageM = (ImageView) findViewById(R.id.museoIm);


        Bundle bundle = getIntent().getExtras();
        String descripcion = bundle.getString("descripcion");
        String imagen = bundle.getString("imagen");

        //imageM.setImageResource(element.getImatge());
        museoinfi.setText(descripcion);
        Picasso.get().load(imagen).into(imageM);

    }

    public void btnClick(View view){

        preferences.edit().clear().apply();
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);

    }
}