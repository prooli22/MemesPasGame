package ca.umontreal.mpg.memepasgame.activities;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.os.Bundle;
import android.os.LocaleList;
import android.support.v13.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;


import java.util.Locale;

import ca.umontreal.mpg.memepasgame.R;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_PERMISSIONS = 20;
    private static Button bModele;
    public Switch switchLang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bModele = findViewById(R.id.bModele);
        bModele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Modele.class));
            }
        });


        // On doit demander les permissions à l'utilisateur.
        demanderPermissions(Manifest.permission.READ_EXTERNAL_STORAGE);
        demanderPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        demanderPermissions(Manifest.permission.CAMERA);

        


        switchLang = (Switch)findViewById(R.id.switchLang);

        switchLang.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if(isChecked == true){
                    //On veut mettre la langue à anglais
                    Toast.makeText(getBaseContext(), "Anglais", Toast.LENGTH_SHORT).show();


                    Locale locale = new Locale("en-rCA");
                    Locale.setDefault(locale);
                    Configuration config = getBaseContext().getResources().getConfiguration();
                    config.locale = locale;
                    getBaseContext().getResources().updateConfiguration(config,
                            getBaseContext().getResources().getDisplayMetrics());


                }else{
                    Toast.makeText(getBaseContext(), "Français", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }


    private void demanderPermissions(String persmission){

        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this, persmission) != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, persmission)) {


            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this, new String[]{persmission}, REQUEST_PERMISSIONS);
            }
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISSIONS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {


                } else {

                    // permission denied
                    bModele.setActivated(false);

                    Toast.makeText(MainActivity.this, "Vous n'avez pas les permissions nécessaires ...", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

}








