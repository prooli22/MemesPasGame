package ca.umontreal.mpg.memepasgame.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import ca.umontreal.mpg.memepasgame.R;

public class MainActivity extends AppCompatActivity {
    private static final int CAMERA_REQUEST = 1888;
    private static final int IMAGE_GALLERY_REQUEST = 1;
    private ImageView imageView;
    private static final int SELECTED_PICTURE = 1;
    private ImageView imgImageChosen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Avoir accès à l'image View
        imgImageChosen = (ImageView) findViewById(R.id.imageChosen);

        Button bCreation = findViewById(R.id.bCreation);
        bCreation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Creation.class));
            }
        });

        Button bModele = findViewById(R.id.bModele);
        bModele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Modele.class));
            }
        });

        Button bCamera = findViewById(R.id.bCamera);
        Button bGallerie = findViewById(R.id.bGallerie);

    }

    public void bCameraClicked(View v) {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }

    public void bGalClicked(View v){
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);

        //On veut chercher data ou?
        File pictureDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        String pictureDirectoryPath = pictureDirectory.getPath();
        //Get a URI representation
        Uri data = Uri.parse(pictureDirectoryPath);

        photoPickerIntent.setDataAndType(data, "image/*" );

        startActivityForResult(photoPickerIntent, IMAGE_GALLERY_REQUEST);

    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(resultCode, resultCode, data);

        //Est-ce que c'est vraiment la caméra que l'on veut ouvrir?
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap cameraImage = (Bitmap) data.getExtras().get("data");



            imgImageChosen.setImageBitmap(cameraImage);
        }

        //Est-ce que c'est vraiment la gallerie que l'ont veut ouvrir?
        if(requestCode == IMAGE_GALLERY_REQUEST && resultCode == Activity.RESULT_OK){
            //L'adresse sur la carte SD
            Uri imageUri = data.getData();

            InputStream inputStream;

            //On prend un input basé sur le Uri de l'image
            try {
                inputStream = getContentResolver().openInputStream(imageUri);
                Bitmap imageGalChosen = BitmapFactory.decodeStream(inputStream);

                //modifyOrientation(imageGalChosen, imageUri.toString());
                imgImageChosen.setImageBitmap(imageGalChosen);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
                //Toast.makeText("Impossible d'ouvrir cette photo");
            }
        }
    }

    public static Bitmap modifyOrientation(Bitmap bitmap, String image_absolute_path) throws IOException {
        ExifInterface ei = new ExifInterface(image_absolute_path);
        int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

        switch (orientation) {
            case ExifInterface.ORIENTATION_ROTATE_90:
                return rotate(bitmap, 90);

            case ExifInterface.ORIENTATION_ROTATE_180:
                return rotate(bitmap, 180);

            case ExifInterface.ORIENTATION_ROTATE_270:
                return rotate(bitmap, 270);

            case ExifInterface.ORIENTATION_FLIP_HORIZONTAL:
                return flip(bitmap, true, false);

            case ExifInterface.ORIENTATION_FLIP_VERTICAL:
                return flip(bitmap, false, true);

            default:
                return bitmap;
        }
    }

    public static Bitmap rotate(Bitmap bitmap, float degrees) {
        Matrix matrix = new Matrix();
        matrix.postRotate(degrees);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public static Bitmap flip(Bitmap bitmap, boolean horizontal, boolean vertical) {
        Matrix matrix = new Matrix();
        matrix.preScale(horizontal ? -1 : 1, vertical ? -1 : 1);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }
}








