package ca.umontreal.mpg.memepasgame.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import ca.umontreal.mpg.memepasgame.R;
import ca.umontreal.mpg.memepasgame.activities.MainActivity;
import ca.umontreal.mpg.memepasgame.activities.Modele;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Apercu.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Apercu#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Apercu extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static final String ARG_POSITION = "position";

    // TODO: Rename and change types of parameters
    public int position = 3;
    private OnFragmentInteractionListener mListener;

    public Apercu() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static Apercu newInstance(int position) {
        Apercu fragment = new Apercu();
        Bundle args = new Bundle();
        args.putInt(ARG_POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            position = getArguments().getInt(ARG_POSITION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_apercu, container, false);

        final ImageView imageApercu = (ImageView) view.findViewById(R.id.imageApercu);
        imageApercu.setImageBitmap(Modele.bitmapScreenshot);

        Button bShare = (Button) view.findViewById(R.id.bShare);
        bShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareImage(imageApercu);
            }
        });

        Button bEnregistrer = (Button) view.findViewById(R.id.bEnregistrer);
        bEnregistrer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveImage(view);
            }
        });

        return view;
    }


    private void shareImage (View view){

        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("image/*");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        view.buildDrawingCache();
        Bitmap imageApercuBitMap = view.getDrawingCache();

        imageApercuBitMap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        //File file = new File(Environment.getExternalStorageDirectory()+ File.separator+"ImageDemo.jpg");

        File root = Environment.getExternalStorageDirectory();
        File cachePath = new File(root.getAbsolutePath() + "/DCIM/Camera/image.jpg");

        try {
            cachePath.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(cachePath);
            fileOutputStream.write(byteArrayOutputStream.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
        shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(cachePath));
        startActivity(Intent.createChooser(shareIntent, "Share Image"));
    }

    private void saveImage(View view){

        String currentImage = "MPG_" + System.currentTimeMillis() + ".png";
        store(Modele.bitmapScreenshot, currentImage);
    }

    private void store(Bitmap bm, String fileName){
        String pathDossier = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString();
        File dossier = new File(pathDossier);

        if(!dossier.exists())
            dossier.mkdir();

        File file = new File(pathDossier, fileName);

        try{
            FileOutputStream fOut = null;
            fOut = new FileOutputStream(file);

            bm.compress(Bitmap.CompressFormat.PNG, 100, fOut);

            fOut.flush();
            fOut.close();

            Toast.makeText(getContext(), "Enregistré", Toast.LENGTH_SHORT).show();
        }
        catch (FileNotFoundException e) { e.printStackTrace(); }
        catch (IOException e) { e.printStackTrace(); }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(int position);
    }
}
