package ca.umontreal.mpg.memepasgame.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.net.Uri;
import android.opengl.Visibility;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import ca.umontreal.mpg.memepasgame.R;
import ca.umontreal.mpg.memepasgame.activities.Modele;
import ca.umontreal.mpg.memepasgame.helpers.FragmentTags;
import ca.umontreal.mpg.memepasgame.helpers.MemeTags;
import ca.umontreal.mpg.memepasgame.helpers.MemeText;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Modele2.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Modele2#newInstance} factory method to
 * create an instance of this fragment.
 */


public class Modele2 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static final String ARG_POSITION = "position";

    // TODO: Rename and change types of parameters
    public int position = 2;
    private OnFragmentInteractionListener mListener;
    private ArrayList<MemeText> tabMemeText;
    private RelativeLayout imageLayout;

    public Modele2() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static Modele2 newInstance(int position) {
        Modele2 fragment = new Modele2();
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
        View view = inflater.inflate(R.layout.fragment_modele2, container, false);

        imageLayout = view.findViewById(R.id.imageLayout);
        ImageView imageModele = view.findViewById(R.id.imageModele);
        tabMemeText = new ArrayList<>();

        if(Modele.MEME_TAG == MemeTags.BRAIN) {
            imageModele.setImageResource(R.drawable.brain_meme);

            EditText texte1 = (EditText) view.findViewById(R.id.text1);
            EditText texte2 = (EditText) view.findViewById(R.id.text2);
            EditText texte3 = (EditText) view.findViewById(R.id.text3);
            EditText texte4 = (EditText) view.findViewById(R.id.text4);

            TextView textBrain1 = (TextView) view.findViewById(R.id.textBrain1);
            TextView textBrain2 = (TextView) view.findViewById(R.id.textBrain2);
            TextView textBrain3 = (TextView) view.findViewById(R.id.textBrain3);
            TextView textBrain4 = (TextView) view.findViewById(R.id.textBrain4);

            texte2.setVisibility(View.VISIBLE);
            texte3.setVisibility(View.VISIBLE);
            texte4.setVisibility(View.VISIBLE);

            textBrain1.setVisibility(View.VISIBLE);
            textBrain2.setVisibility(View.VISIBLE);
            textBrain3.setVisibility(View.VISIBLE);
            textBrain4.setVisibility(View.VISIBLE);

            tabMemeText.add(new MemeText(texte1, textBrain1));
            tabMemeText.add(new MemeText(texte2, textBrain2));
            tabMemeText.add(new MemeText(texte3, textBrain3));
            tabMemeText.add(new MemeText(texte4, textBrain4));
        }

        else if(Modele.MEME_TAG == MemeTags.DRAKE) {
            imageModele.setImageResource(R.drawable.drake_meme);

            EditText texte1 = (EditText) view.findViewById(R.id.text1);
            EditText texte2 = (EditText) view.findViewById(R.id.text2);
            texte2.setVisibility(View.VISIBLE);

            TextView textDrake1 = (TextView) view.findViewById(R.id.textDrake1);
            textDrake1.setVisibility(View.VISIBLE);
            TextView textDrake2 = (TextView) view.findViewById(R.id.textDrake2);
            textDrake2.setVisibility(View.VISIBLE);

            tabMemeText.add(new MemeText(texte1, textDrake1));
            tabMemeText.add(new MemeText(texte2, textDrake2));
        }

        else if(Modele.MEME_TAG == MemeTags.TWITTER) {
            imageModele.setImageResource(R.drawable.twitter_meme_template);

            EditText texte1 = (EditText) view.findViewById(R.id.text1);

            TextView textTwitter = (TextView) view.findViewById(R.id.textTwitter);
            textTwitter.setVisibility(View.VISIBLE);

            tabMemeText.add(new MemeText(texte1, textTwitter));

            Button bCameraM2 = (Button) view.findViewById(R.id.bCameraM2);
            bCameraM2.setVisibility(View.VISIBLE);
            bCameraM2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            Button bGallerieM2 = (Button) view.findViewById(R.id.bGallerieM2);
            bGallerieM2.setVisibility(View.VISIBLE);
            bGallerieM2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

        else if(Modele.MEME_TAG == MemeTags.PATRICK) {
            imageModele.setImageResource(R.drawable.patrick_meme_template);

            EditText texte1 = (EditText) view.findViewById(R.id.text1);

            TextView textPatrick = (TextView) view.findViewById(R.id.textPatrick);
            textPatrick.setVisibility(View.VISIBLE);

            tabMemeText.add(new MemeText(texte1, textPatrick));
        }

        Button bAjouter = (Button) view.findViewById(R.id.bAjouter);
        bAjouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (MemeText mt: tabMemeText) {
                    mt.getTextView().setText(mt.getEditText().getText().toString());
                }
            }
        });

        Button bContinuer = (Button) view.findViewById(R.id.b_ContinuerM2);
        bContinuer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                screenshot();
                Modele.changerFragment(Apercu.newInstance(3));
                Modele.CURRENT_TAG = FragmentTags.APERCU;
            }
        });

        return view;
    }

    private void screenshot(){

        imageLayout.setDrawingCacheEnabled(true);
        Modele.bitmapScreenshot = Bitmap.createBitmap(imageLayout.getDrawingCache());
        imageLayout.setDrawingCacheEnabled(false);
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


    public void ajouterTextImage(String text, int xpos, int ypos, int textSize){

        //Modele.imageModele.setDrawingCacheEnabled(true);
        //Modele.imageModele.buildDrawingCache();
        //Bitmap bitmap = Bitmap.createBitmap(Modele.imageModele.getDrawingCache());

        //Canvas canvas = new Canvas(bitmap);
        //Paint paint = new Paint();
        //paint.setColor(Color.BLACK);
        //paint.setTextSize(textSize);
        //Typeface arial = Typeface.createFromAsset(getContext().getAssets(), "fonts/Arial.ttf");
        //paint.setTypeface(arial);
        //canvas.drawText(text, xpos, ypos, paint);

        //Modele.imageModele.setImageBitmap(bitmap);
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(int position);
    }
}
