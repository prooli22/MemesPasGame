package ca.umontreal.mpg.memepasgame.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.media.ExifInterface;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
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
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import ca.umontreal.mpg.memepasgame.R;

public class MainActivity extends AppCompatActivity {
    private static final int CAMERA_REQUEST = 1888;
    private static final int IMAGE_GALLERY_REQUEST = 1;
    private static final int IMAGE_SAVED_REQUEST = 8;
    private static final int SELECTED_PICTURE = 1;
    private ImageView imageChosen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Avoir accès à l'image View
        imageChosen = (ImageView) findViewById(R.id.imageChosen);

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



        Button bEnregistrer = findViewById(R.id.bEnregistrer);
        bEnregistrer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nameFolder = "/MPG";
                String nameFile = "imgMPG";

                imageChosen.setDrawingCacheEnabled(true);
                imageChosen.buildDrawingCache();

                Bitmap imageToSaved = Bitmap.createBitmap(imageChosen.getDrawingCache());

                String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + nameFolder;
                String currentDate = getCurrentDate();
                File dir = new File(filePath);
                if(!dir.exists()){
                    dir.mkdirs();
                }

                File file = new File(dir, nameFile + currentDate + ".jpeg");

                try{
                    FileOutputStream fOut = new FileOutputStream(file);
                    imageToSaved.compress(Bitmap.CompressFormat.JPEG, 85, fOut);
                    fOut.flush();
                    fOut.close();
                    testCreatedFile(file);
                    Toast.makeText(getApplicationContext(), "picture saved", Toast.LENGTH_SHORT).show();
                }
                catch (FileNotFoundException e ){
                    Toast.makeText(getApplicationContext(), "picture failed caca", Toast.LENGTH_SHORT).show();
                }
                catch(IOException e){
                    Toast.makeText(getApplicationContext(), "picture failed", Toast.LENGTH_SHORT).show();
                }

            }


        });

    }

    private void testCreatedFile(File file){
        MediaScannerConnection.scanFile(getApplicationContext(),
                new String[]{file.toString()}, null,
                new MediaScannerConnection.OnScanCompletedListener() {
                    @Override
                    public void onScanCompleted(String path, Uri uri) {
                        Log.e("External Storage ", "Scanned : " + path);
                        Log.e("External Storage ", "uri : " + uri);
                    }
                });

    }



    private static String getCurrentDate(){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        return df.format(c.getTime());
    }

    private static void scanFile(Context context, Uri imageUri){
        Intent scanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        scanIntent.setData(imageUri);
        context.sendBroadcast(scanIntent);

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

            imageChosen.setImageBitmap(cameraImage);
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
                imageChosen.setImageBitmap(imageGalChosen);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
                //Toast.makeText("Impossible d'ouvrir cette photo");
            }

        }
    }

    public String saveImageFile(Bitmap bitmap) {
        FileOutputStream out = null;
        String filename = getFilename();
        try {
            out = new FileOutputStream(filename);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return filename;
    }

    private String getFilename() {
        File file = new File(Environment.getExternalStorageDirectory()
                .getPath(), "TestFolder");
        if (!file.exists()) {
            file.mkdirs();
        }
        String uriSting = (file.getAbsolutePath() + "/"
                + System.currentTimeMillis() + ".jpg");
        return uriSting;
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








