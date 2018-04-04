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
    private ImageView imageModele;
    private ArrayList<EditText> tabEditText;

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

        imageModele = view.findViewById(R.id.imageModele);
        tabEditText = new ArrayList<>();

        if(Modele.MEME_TAG == MemeTags.BRAIN) {
            imageModele.setImageResource(R.drawable.brain_meme);
            ajouterTexte(view, 4);
        }
        else if(Modele.MEME_TAG == MemeTags.DRAKE) {
            imageModele.setImageResource(R.drawable.drake_meme);
            ajouterTexte(view, 2);
        }

        else if(Modele.MEME_TAG == MemeTags.TWITTER) {
            imageModele.setImageResource(R.drawable.twitter_meme_template);
            ajouterTexte(view, 1);
        }
        else if(Modele.MEME_TAG == MemeTags.PATRICK) {
            imageModele.setImageResource(R.drawable.patrick_meme);
            ajouterTexte(view, 1);
        }

        Button bAjouter = (Button) view.findViewById(R.id.bAjouter);
        bAjouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                imageModele.setDrawingCacheEnabled(true);
                imageModele.buildDrawingCache();
                Bitmap bitmap = Bitmap.createBitmap(imageModele.getDrawingCache());

                Canvas canvas = new Canvas(bitmap);
                Paint paint = new Paint();
                paint.setColor(Color.BLACK);
                paint.setTextSize(60);
                Typeface arial = Typeface.createFromAsset(getContext().getAssets(), "fonts/Arial.ttf");
                paint.setTypeface(arial);
                canvas.drawText(tabEditText.get(0).getText().toString(), 130, 160, paint);

                imageModele.setImageBitmap(bitmap);
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

    public void ajouterTexte(View view, int nbTexte){

        ConstraintLayout layout = (ConstraintLayout) view.findViewById(R.id.imageLayout);
        ConstraintSet c = new ConstraintSet();

        ConstraintLayout.LayoutParams p = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        p.setMargins(20, 0, 20, 0);

        View v = imageModele;

        for(int i = 0; i < nbTexte; i++) {

            EditText et = new EditText(getContext());
            et.setLayoutParams(p);
            et.setText("Texte " + (i + 1));
            et.setId(i + 1);

            layout.addView(et);
            c.clone(layout);

            if(v == imageModele)
                c.connect(et.getId(), ConstraintSet.TOP, v.getId(), ConstraintSet.BOTTOM, 10);
            else
                c.connect(et.getId(), ConstraintSet.TOP, v.getId(), ConstraintSet.BOTTOM, 0);

            tabEditText.add(et);
            v = et;
        }

        c.applyTo(layout);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(final Fragment fragment);
    }
}
