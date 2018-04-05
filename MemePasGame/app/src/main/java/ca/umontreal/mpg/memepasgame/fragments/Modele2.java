package ca.umontreal.mpg.memepasgame.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.net.Uri;
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

        Modele.imageModele = view.findViewById(R.id.imageModele);
        tabMemeText = new ArrayList<>();

        if(Modele.MEME_TAG == MemeTags.BRAIN) {
            Modele.imageModele.setImageResource(R.drawable.brain_meme);
            ajouterChampsTexts(view, 4, false);

            tabMemeText.get(0).setXpos(130);
            tabMemeText.get(0).setYpos(160);
            tabMemeText.get(0).setTextSize(60);

            tabMemeText.get(1).setXpos(130);
            tabMemeText.get(1).setYpos(160);
            tabMemeText.get(1).setTextSize(60);

            tabMemeText.get(2).setXpos(130);
            tabMemeText.get(2).setYpos(160);
            tabMemeText.get(2).setTextSize(60);

            tabMemeText.get(3).setXpos(130);
            tabMemeText.get(3).setYpos(160);
            tabMemeText.get(3).setTextSize(60);
        }
        else if(Modele.MEME_TAG == MemeTags.DRAKE) {
            Modele.imageModele.setImageResource(R.drawable.drake_meme);
            ajouterChampsTexts(view, 2, false);

            tabMemeText.get(0).setXpos(130);
            tabMemeText.get(0).setYpos(160);
            tabMemeText.get(0).setTextSize(60);

            tabMemeText.get(1).setXpos(130);
            tabMemeText.get(1).setYpos(160);
            tabMemeText.get(1).setTextSize(60);
        }

        else if(Modele.MEME_TAG == MemeTags.TWITTER) {
            Modele.imageModele.setImageResource(R.drawable.twitter_meme_template);
            ajouterChampsTexts(view, 1, true);

            tabMemeText.get(0).setXpos(130);
            tabMemeText.get(0).setYpos(160);
            tabMemeText.get(0).setTextSize(60);
        }
        else if(Modele.MEME_TAG == MemeTags.PATRICK) {
            Modele.imageModele.setImageResource(R.drawable.patrick_meme);
            ajouterChampsTexts(view, 1, false);

            tabMemeText.get(0).setXpos(130);
            tabMemeText.get(0).setYpos(160);
            tabMemeText.get(0).setTextSize(60);
        }

        Button bAjouter = (Button) view.findViewById(R.id.bAjouter);
        bAjouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (MemeText mt: tabMemeText) {
                    ajouterTextImage(mt.getEditText().getText().toString(), mt.getXpos(), mt.getYpos(), mt.getTextSize());
                }
            }
        });

        Button bContinuer = (Button) view.findViewById(R.id.b_ContinuerM2);
        bContinuer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Modele.changerFragment(Apercu.newInstance(3));
                Modele.CURRENT_TAG = FragmentTags.APERCU;
            }
        });

        return view;
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

    public void ajouterChampsTexts(View view, int nbTexte, boolean ajouterBoutons){

        RelativeLayout layout = (RelativeLayout) view.findViewById(R.id.imageLayout);
        RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        p.setMargins(20, 0, 20, 0);

        View v = Modele.imageModele;

        for(int i = 0; i < nbTexte; i++) {

            EditText et = new EditText(getContext());
            et.setLayoutParams(p);
            et.setText("Texte " + (i + 1));
            et.setId(i + 1);

            p.addRule(RelativeLayout.BELOW, v.getId());
            p.addRule(RelativeLayout.ALIGN_BOTTOM);

            layout.addView(et);

            tabMemeText.add(new MemeText(et));
            v = et;
        }

        if(ajouterBoutons) {

            Button bCamera = new Button(getContext());
            bCamera.setText("Caméra");
            bCamera.setId(R.id.bCamera);
            bCamera.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

            Button bGallerie = new Button(getContext());
            bGallerie.setText("Gallerie");
            bGallerie.setId(R.id.bGallerie);
            bGallerie.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

            RelativeLayout.LayoutParams pCam = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            pCam.setMargins(20, 0, 0, 0);
            pCam.addRule(RelativeLayout.ALIGN_BOTTOM);
            pCam.addRule(RelativeLayout.BELOW, v.getId());
            bCamera.setLayoutParams(pCam);
            layout.addView(bCamera);

            RelativeLayout.LayoutParams pGal = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            pGal.setMargins(0, 0, 0, 20);
            pGal.addRule(RelativeLayout.ALIGN_BOTTOM);
            pGal.addRule(RelativeLayout.BELOW, v.getId());
            pGal.addRule(RelativeLayout.RIGHT_OF, bCamera.getId());
            bGallerie.setLayoutParams(pGal);
            layout.addView(bGallerie);
        }

    }

    public void ajouterTextImage(String text, int xpos, int ypos, int textSize){

        Modele.imageModele.setDrawingCacheEnabled(true);
        Modele.imageModele.buildDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(Modele.imageModele.getDrawingCache());

        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(textSize);
        Typeface arial = Typeface.createFromAsset(getContext().getAssets(), "fonts/Arial.ttf");
        paint.setTypeface(arial);
        canvas.drawText(text, xpos, ypos, paint);

        Modele.imageModele.setImageBitmap(bitmap);
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(int position);
    }
}
