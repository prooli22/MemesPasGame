package ca.umontreal.mpg.memepasgame.activities;


import android.app.Activity;
import android.content.Intent;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import ca.umontreal.mpg.memepasgame.R;
import ca.umontreal.mpg.memepasgame.fragments.Apercu;
import ca.umontreal.mpg.memepasgame.fragments.Modele1;
import ca.umontreal.mpg.memepasgame.fragments.Modele2;
import ca.umontreal.mpg.memepasgame.helpers.FragmentTags;
import ca.umontreal.mpg.memepasgame.helpers.MemeTags;

public class Modele extends AppCompatActivity
    implements Modele1.OnFragmentInteractionListener,
               Modele2.OnFragmentInteractionListener,
               Apercu.OnFragmentInteractionListener {

    private static final int CAMERA_REQUEST = 1888;
    private static final int IMAGE_GALLERY_REQUEST = 1;

    public static FragmentTags CURRENT_TAG = FragmentTags.M1;
    public static MemeTags MEME_TAG = null;
    public static Bitmap bitmapScreenshot;

    private static FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modele);

        manager = getSupportFragmentManager();

        changerFragment(Modele1.newInstance(1));
    }

    public static void changerFragment(Fragment fragment){

        //handler.post(getFragmentRunnable(fragment));
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.contentframe, fragment, CURRENT_TAG.toString());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        //manager.popBackStack();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(resultCode, resultCode, data);

        //Est-ce que c'est vraiment la caméra que l'on veut ouvrir?
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap cameraImage = (Bitmap) data.getExtras().get("data");

            Modele2.ajouterImage(cameraImage, findViewById(R.id.imageTwitter));
        }

        //Est-ce que c'est vraiment la gallerie que l'ont veut ouvrir?
        if(requestCode == IMAGE_GALLERY_REQUEST && resultCode == Activity.RESULT_OK){
            //L'adresse sur la carte SD
            Uri imageUri = data.getData();

            InputStream inputStream;

            //On prend un input basé sur le Uri de l'image
            try {
                inputStream = getContentResolver().openInputStream(imageUri);
                Bitmap imageGal = BitmapFactory.decodeStream(inputStream);

                //modifyOrientation(imageGalChosen, imageUri.toString());
                //imageChosen.setImageBitmap(imageGalChosen);
                Modele2.ajouterImage(imageGal, findViewById(R.id.imageTwitter));

            } catch (FileNotFoundException e) {
                e.printStackTrace();
                //Toast.makeText("Impossible d'ouvrir cette photo");
            }

        }
    }

    public static void cameraClick(Activity activity){

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        activity.startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }

    public static void galerieClick(Activity activity){

        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);

        //On veut chercher data ou?
        File pictureDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        String pictureDirectoryPath = pictureDirectory.getPath();
        //Get a URI representation
        Uri data = Uri.parse(pictureDirectoryPath);

        photoPickerIntent.setDataAndType(data, "image/*" );

        activity.startActivityForResult(photoPickerIntent, IMAGE_GALLERY_REQUEST);
    }

    @Override
    public void onBackPressed() {

        if (manager.getBackStackEntryCount() < 1) {
            finish();
            super.onBackPressed();
        }

        else 
            manager.popBackStack();
    }

    @Override
    public void onFragmentInteraction(int position) {

    }

}
