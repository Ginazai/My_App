package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;

public class Activity2 extends AppCompatActivity {
    TextView nameF;
    TextView dateF;
    TextView phoneF;
    TextView emailF;
    TextView descF;
    TextView back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        Bundle param = getIntent().getExtras();
        String nombre = param.getString(getResources().getString(R.string.nombre_completo));
        String mail = param.getString(getResources().getString(R.string.correo_electronico));
        String phone = param.getString(getResources().getString(R.string.telefono));
        String desc = param.getString(getResources().getString(R.string.descripcion));
        String myDate = param.getString(getResources().getString(R.string.fecha));

        nameF = (TextView) findViewById(R.id.nameField);
        emailF = (TextView) findViewById(R.id.emailF);
        dateF = (TextView) findViewById(R.id.dateF);
        descF = (TextView) findViewById(R.id.descF);
        phoneF = (TextView) findViewById(R.id.phoneF);
        back = (TextView) findViewById(R.id.textPrev);

        nameF.setText("Nombre: " + nombre);
        emailF.setText("Correo Electronico: " + mail);
        dateF.setText("Fecha: " + myDate);
        phoneF.setText("Telefono: " + phone);
        descF.setText("Descripcion: \n" + desc);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}