package com.fourthstatelab.androidworkshop;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Start_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_);
        MyAsyncTask async=new MyAsyncTask();
        async.execute();

    }

    public void  switchact()
    {
        SharedPreferences shared=getApplicationContext().getSharedPreferences("sharedpreferences",Context.MODE_PRIVATE);
        String z=shared.getString("personaldetails","");
        if(z.equals("")) {
            startActivity(new Intent(Start_Activity.this,LoginDetailsAct.class));
            finish();
        }
        else {
            startActivity(new Intent(Start_Activity.this, MainActivity.class));
            finish();
        }
    }

    class MyAsyncTask extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            switchact();
            super.onPostExecute(aVoid);
        }
    }
}
