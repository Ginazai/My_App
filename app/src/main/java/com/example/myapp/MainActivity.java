package com.example.myapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    String fname, fdate, fphone, fdesc, femail, sfdate;
    CalendarView calendar;
    TextInputEditText name;
    EditText phone;
    EditText emailField;
    EditText desc;
    TextView next;
    boolean oncePress;

    int day = 1;
    int month = 1;
    int year = 1970;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = (TextInputEditText) findViewById(R.id.nameField);
        calendar = (CalendarView) findViewById(R.id.calendarField);
        phone = (EditText) findViewById(R.id.phoneField);
        emailField = (EditText) findViewById(R.id.emailField);
        desc = (EditText) findViewById(R.id.descField);
        next = (TextView) findViewById(R.id.nextActivity);

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendar, int cYear, int cMonth, int cDay) {
                day = cDay;
                month = cMonth + 1;
                year = cYear;
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fname = name.getText().toString();
                femail = emailField.getText().toString();
                fdesc = desc.getText().toString();
                fphone = phone.getText().toString();
                fdate = day + "/" + month + "/" + year;
                boolean isComplete = false;

                if (TextUtils.isEmpty(fname) || TextUtils.isEmpty(femail) ||
                        TextUtils.isEmpty(fdesc) || TextUtils.isEmpty(fphone)) {
                    if (TextUtils.isEmpty(fname)) {
                        name.setError("campo requerido");
                    }
                    if (TextUtils.isEmpty(femail)) {
                        emailField.setError("campo requerido");
                    }
                    if (TextUtils.isEmpty(fdesc)) {
                        desc.setError("campo requerido");
                    }
                    if (TextUtils.isEmpty(fphone)) {
                        phone.setError("campo requerido");
                    }
                } else {
                    isComplete = true;
                }

                if (isComplete) {
                    Intent commit = new Intent(MainActivity.this, Activity2.class);
                    commit.putExtra(getResources().getString(R.string.nombre_completo), fname);
                    commit.putExtra(getResources().getString(R.string.correo_electronico), femail);
                    commit.putExtra(getResources().getString(R.string.telefono), fphone);
                    commit.putExtra(getResources().getString(R.string.descripcion), fdesc);
                    commit.putExtra(getResources().getString(R.string.fecha), fdate);
                    startActivity(commit);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (oncePress) {
            super.onBackPressed();
            return;
        }

        this.oncePress = true;
        showToast("Realmente quieres salir?");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                oncePress = false;
            }
        }, 2000);
    }

    private void showToast(String text) {
        Toast.makeText(MainActivity.this, text, Toast.LENGTH_LONG).show();
    }
}