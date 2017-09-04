package com.fourthstatelab.androidworkshop;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

public class LoginDetailsAct extends AppCompatActivity {

    EditText name,pin,repin;
    Button okay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_details);

        SharedPreferences shared=getApplicationContext().getSharedPreferences("sharedpreferences", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor=shared.edit();

        name=(EditText)findViewById(R.id.name);
        pin=(EditText)findViewById(R.id.pin);
        repin=(EditText)findViewById(R.id.re_enterpin);

        okay=(Button)findViewById(R.id.okay);

        okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name.getText().toString()!=null && pin.getText().toString()!=null && repin.getText().toString()!=null){
                    if(name.getText().toString().equals("") || pin.getText().toString().equals("") || repin.getText().toString().equals(""))
                    {
                        Toast.makeText(LoginDetailsAct.this, "None of fields can be empty", Toast.LENGTH_SHORT).show();
                    }
                    else if (pin.getText().toString().equals(repin.getText().toString())==false){
                        Toast.makeText(LoginDetailsAct.this, "Both pins must match", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        PersonalDetails person=new PersonalDetails(name.getText().toString(),pin.getText().toString());
                        editor.putString("personaldetails",new Gson().toJson(person));
                        Toast.makeText(LoginDetailsAct.this, "Details Saved", Toast.LENGTH_SHORT).show();
                        editor.apply();

                        startActivity(new Intent(LoginDetailsAct.this,MainActivity.class));
                        finish();
                    }
                }
            }
        });


    }
}
