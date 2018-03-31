package ca.umontreal.mpg.memepasgame.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import ca.umontreal.mpg.memepasgame.R;
import ca.umontreal.mpg.memepasgame.fragments.Modele1;
import ca.umontreal.mpg.memepasgame.fragments.Modele2;
import ca.umontreal.mpg.memepasgame.helpers.FragmentTags;
import ca.umontreal.mpg.memepasgame.helpers.MemeTags;

public class Modele extends FragmentActivity
    implements Modele1.OnFragmentInteractionListener {


    private static FragmentTags CURRENT_TAG = FragmentTags.M1;
    private static MemeTags MEME_TAG = null;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_modele1);

        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if (findViewById(R.id.contentframe) != null) {

            if (savedInstanceState != null)
                return;

            Modele1 firstFragment = new Modele1();
            firstFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentframe, firstFragment).commit();
        }

        loadModele1Elements();
    }

    private void loadModele1Elements(){

        final TextView modele_select = (TextView) findViewById(R.id.modele_select);

        final ImageButton brain_meme = (ImageButton) findViewById(R.id.img_brain_meme);
        brain_meme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(MEME_TAG == MemeTags.BRAIN)
                    MEME_TAG = null;

                else{
                    MEME_TAG = MemeTags.BRAIN;
                    modele_select.setText(MEME_TAG.toString());
                }
            }
        });

        final ImageButton drake_meme = (ImageButton) findViewById(R.id.img_drake_meme);
        drake_meme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(MEME_TAG == MemeTags.DRAKE)
                    MEME_TAG = null;

                else {
                    MEME_TAG = MemeTags.DRAKE;
                    modele_select.setText(MEME_TAG.toString());
                }
            }
        });

        final ImageButton twitter_meme = (ImageButton) findViewById(R.id.img_twitter_meme);
        twitter_meme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(MEME_TAG == MemeTags.TWITTER)
                    MEME_TAG = null;

                else {
                    MEME_TAG = MemeTags.TWITTER;
                    modele_select.setText(MEME_TAG.toString());
                }
            }
        });

        final ImageButton patrick_meme = (ImageButton) findViewById(R.id.img_patrick_meme);
        patrick_meme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(MEME_TAG == MemeTags.PATRICK)
                    MEME_TAG = null;

                else {
                    MEME_TAG = MemeTags.PATRICK;
                    modele_select.setText(MEME_TAG.toString());
                }
            }
        });


        final Button b_ContinuerM1 = (Button) findViewById(R.id.b_ContinuerM1);
        b_ContinuerM1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Valider qu'un choix à été fait.
                if(MEME_TAG == null)
                    Toast.makeText(getApplicationContext(), "Vous devez sélectionner un modèle avant de continuer", Toast.LENGTH_SHORT).show();

                else {
                    // Call fragement to Modele2
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                    fragmentTransaction.replace(R.id.contentframe, new Modele2(), CURRENT_TAG.toString());
                    fragmentTransaction.addToBackStack(CURRENT_TAG.toString());
                    fragmentTransaction.commitAllowingStateLoss();

                    CURRENT_TAG = FragmentTags.M2;
                }
            }
        });
    }

    private Runnable getFragmentRunnable(final Fragment fragment) {
        Runnable changeFragmentThread = new Runnable() {
            @Override
            public void run() {;
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                fragmentTransaction.replace(R.id.contentframe, fragment, CURRENT_TAG.toString());
                fragmentTransaction.addToBackStack(CURRENT_TAG.toString());
                fragmentTransaction.commitAllowingStateLoss();
            }
        };

        return changeFragmentThread;
    }

    @Override
    public void onFragmentInteraction(int position) {

    }

}
