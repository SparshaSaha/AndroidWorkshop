package com.fourthstatelab.androidworkshop;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    Button takephoto;
    ImageView imageview;
    Bitmap bit;
    Button save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        takephoto=(Button)findViewById(R.id.takephoto);
        imageview=(ImageView)findViewById(R.id.imageView) ;
        save=(Button) findViewById(R.id.save);
        save.setVisibility(View.INVISIBLE);


        takephoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, 1067);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveFile(getApplication(),bit,"mypic");
                Toast.makeText(MainActivity.this, "Saved", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==1067 && resultCode!=Activity.RESULT_CANCELED){
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageview.setImageBitmap(photo);
            save.setVisibility(View.VISIBLE);
            bit=photo;

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public static void saveFile(Context context, Bitmap b, String picName){
        FileOutputStream fos= null;
        try {
            fos = context.openFileOutput(picName, Context.MODE_PRIVATE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            fos = context.openFileOutput(picName, Context.MODE_PRIVATE);
            b.compress(Bitmap.CompressFormat.PNG, 100, fos);
        }
        catch (FileNotFoundException e) {
            Log.d("tag", "file not found");
            e.printStackTrace();
        }
        catch (IOException e) {
            Log.d("tag", "io exception");
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
