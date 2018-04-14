package ca.umontreal.mpg.memepasgame.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import ca.umontreal.mpg.memepasgame.R;
import ca.umontreal.mpg.memepasgame.activities.Modele;
import ca.umontreal.mpg.memepasgame.helpers.FragmentTags;
import ca.umontreal.mpg.memepasgame.helpers.MemeTags;

//import ca.umontreal.mpg.memepasgame.activities.Creation;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Modele1.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Modele1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Modele1 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static final String ARG_POSITION = "position";

    // TODO: Rename and change types of parameters
    public int position = 1;
    private OnFragmentInteractionListener mListener;

    public Modele1() {
        // Required empty public constructor
    }


    public static Modele1 newInstance(int position) {
        Modele1 fragment = new Modele1();
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
        final View view =  inflater.inflate(R.layout.fragment_modele1, container, false);

        final ImageButton brain_meme = (ImageButton) view.findViewById(R.id.img_brain_meme);
        final ImageButton drake_meme = (ImageButton) view.findViewById(R.id.img_drake_meme);
        final ImageButton twitter_meme = (ImageButton) view.findViewById(R.id.img_twitter_meme);
        final ImageButton patrick_meme = (ImageButton) view.findViewById(R.id.img_patrick_meme);

        final ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.progressBar);

        if(Modele.MEME_TAG != null)
            progressBar.setProgress(25);

        if(Modele.MEME_TAG == MemeTags.BRAIN)
            brain_meme.setImageResource(R.drawable.brain_meme_click);
        else if(Modele.MEME_TAG == MemeTags.DRAKE)
            drake_meme.setImageResource(R.drawable.drake_click);
        else if(Modele.MEME_TAG == MemeTags.TWITTER)
            twitter_meme.setImageResource(R.drawable.twitter_meme_click);
        else if(Modele.MEME_TAG == MemeTags.PATRICK)
            patrick_meme.setImageResource(R.drawable.patrick_meme_click);

        brain_meme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Modele.MEME_TAG == MemeTags.BRAIN){
                    Modele.MEME_TAG = null;
                    brain_meme.setImageResource(R.drawable.brain_meme);

                    progressBar.setProgress(5);
                }

                else{
                    drake_meme.setImageResource(R.drawable.drake_meme);
                    twitter_meme.setImageResource(R.drawable.twitter_meme);
                    patrick_meme.setImageResource(R.drawable.patrick_meme);

                    Modele.MEME_TAG = MemeTags.BRAIN;

                    brain_meme.setImageResource(R.drawable.brain_meme_click);

                    progressBar.setProgress(25);
                }
            }
        });

        drake_meme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Modele.MEME_TAG == MemeTags.DRAKE){
                    Modele.MEME_TAG = null;
                    drake_meme.setImageResource(R.drawable.drake_meme);

                    progressBar.setProgress(5);
                }

                else {
                    brain_meme.setImageResource(R.drawable.brain_meme);
                    twitter_meme.setImageResource(R.drawable.twitter_meme);
                    patrick_meme.setImageResource(R.drawable.patrick_meme);

                    Modele.MEME_TAG = MemeTags.DRAKE;

                    drake_meme.setImageResource(R.drawable.drake_click);

                    progressBar.setProgress(25);
                }
            }
        });

        twitter_meme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Modele.MEME_TAG == MemeTags.TWITTER){
                    Modele.MEME_TAG = null;
                    twitter_meme.setImageResource(R.drawable.twitter_meme);

                    progressBar.setProgress(5);
                }

                else {
                    drake_meme.setImageResource(R.drawable.drake_meme);
                    brain_meme.setImageResource(R.drawable.brain_meme);
                    patrick_meme.setImageResource(R.drawable.patrick_meme);

                    Modele.MEME_TAG = MemeTags.TWITTER;

                    twitter_meme.setImageResource(R.drawable.twitter_meme_click);

                    progressBar.setProgress(25);
                }
            }
        });

        patrick_meme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Modele.MEME_TAG == MemeTags.PATRICK){
                    Modele.MEME_TAG = null;
                    patrick_meme.setImageResource(R.drawable.patrick_meme);

                    progressBar.setProgress(5);
                }


                else {
                    drake_meme.setImageResource(R.drawable.drake_meme);
                    twitter_meme.setImageResource(R.drawable.twitter_meme);
                    brain_meme.setImageResource(R.drawable.brain_meme);

                    Modele.MEME_TAG = MemeTags.PATRICK;

                    patrick_meme.setImageResource(R.drawable.patrick_meme_click);

                    progressBar.setProgress(25);
                }
            }
        });


        final Button b_ContinuerM1 = (Button) view.findViewById(R.id.b_ContinuerM1);
        b_ContinuerM1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Valider qu'un choix à été fait.
                if(Modele.MEME_TAG == null)
                    //Toast.makeText(getContext(), "Vous devez sélectionner un modèle avant de continuer", Toast.LENGTH_SHORT).show();
                    Toast.makeText(getContext(), getString(R.string.veuillezSelect), Toast.LENGTH_SHORT).show();


                else {
                    Modele.changerFragment(Modele2.newInstance(2));
                    Modele.CURRENT_TAG = FragmentTags.M2;
                }
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
        void onFragmentInteraction(int position);
    }

}
