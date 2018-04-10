package ca.umontreal.mpg.memepasgame.fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

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

    private RelativeLayout imageLayout;
    private static ImageView imageModele;
    private static ImageView imageTwitter;
    private static Bitmap bmChoosen;


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
        imageTwitter = view.findViewById(R.id.imageTwitter);
        imageModele = view.findViewById(R.id.imageModele);

        if(Modele.MEME_TAG == MemeTags.BRAIN) {
            imageModele.setImageResource(R.drawable.brain_meme);

            EditText textBrain1 = (EditText) view.findViewById(R.id.textBrain1);
            EditText textBrain2 = (EditText) view.findViewById(R.id.textBrain2);
            EditText textBrain3 = (EditText) view.findViewById(R.id.textBrain3);
            EditText textBrain4 = (EditText) view.findViewById(R.id.textBrain4);

            textBrain1.setVisibility(View.VISIBLE);
            textBrain2.setVisibility(View.VISIBLE);
            textBrain3.setVisibility(View.VISIBLE);
            textBrain4.setVisibility(View.VISIBLE);
        }

        else if(Modele.MEME_TAG == MemeTags.DRAKE) {
            imageModele.setImageResource(R.drawable.drake_meme);

            EditText textDrake1 = (EditText) view.findViewById(R.id.textDrake1);
            textDrake1.setVisibility(View.VISIBLE);
            EditText textDrake2 = (EditText) view.findViewById(R.id.textDrake2);
            textDrake2.setVisibility(View.VISIBLE);
        }

        else if(Modele.MEME_TAG == MemeTags.TWITTER) {
            imageModele.setImageResource(R.drawable.twitter_meme_template);

            imageTwitter.setVisibility(View.VISIBLE);

            EditText textTwitter = (EditText) view.findViewById(R.id.textTwitter);
            textTwitter.setVisibility(View.VISIBLE);

            ImageButton bCameraM2 = (ImageButton) view.findViewById(R.id.bCameraM2);
            bCameraM2.setVisibility(View.VISIBLE);
            bCameraM2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Modele.cameraClick(getActivity());
                }
            });

            ImageButton bGalerieM2 = (ImageButton) view.findViewById(R.id.bGalerieM2);
            bGalerieM2.setVisibility(View.VISIBLE);
            bGalerieM2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Modele.galerieClick(getActivity());
                }
            });

            ImageButton bOrientationM2 = (ImageButton) view.findViewById(R.id.bOrientationM2);
            bOrientationM2.setVisibility(View.VISIBLE);
            bOrientationM2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(bmChoosen == null)
                        Toast.makeText(getContext(), "Vous devez sélectionner une image avant de changer l'orientation.", Toast.LENGTH_SHORT).show();

                    else {
                        bmChoosen = Modele.rotate(bmChoosen, 90);
                        imageTwitter.setImageBitmap(bmChoosen);
                    }
                }
            });
        }

        else if(Modele.MEME_TAG == MemeTags.PATRICK) {
            imageModele.setImageResource(R.drawable.patrick_meme);

            EditText textPatrick = (EditText) view.findViewById(R.id.textPatrick);
            textPatrick.setVisibility(View.VISIBLE);
        }


        Button bContinuer = (Button) view.findViewById(R.id.bContinuerM2);
        bContinuer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // On enlève le focus des views pour avoir un bon screenshot.
                imageLayout.requestFocus();
                screenshot();

                // On change de fragment.
                Modele.changerFragment(Apercu.newInstance(3));
                Modele.CURRENT_TAG = FragmentTags.APERCU;
            }
        });


        // Si l'image twitter a déjà été créée, on l'affiche (onBackPress).
        if(bmChoosen != null && Modele.MEME_TAG == MemeTags.TWITTER) {
            imageTwitter.setVisibility(View.VISIBLE);
            imageTwitter.setImageBitmap(bmChoosen);
        }

        return view;
    }


    private void screenshot(){

        // On prend un screenshot du view.
        imageLayout.setDrawingCacheEnabled(true);
        Modele.bitmapScreenshot = Bitmap.createBitmap(imageLayout.getDrawingCache());
        imageLayout.setDrawingCacheEnabled(false);
    }


    public static void ajouterImage(Bitmap bm){

        // On ajoute l'image au Twitter Memes
        bmChoosen = bm;
        imageTwitter.setImageBitmap(bm);
        imageTwitter.setVisibility(View.VISIBLE);
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
