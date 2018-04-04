package ca.umontreal.mpg.memepasgame.activities;


import android.content.Intent;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;


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


    public static FragmentTags CURRENT_TAG = FragmentTags.M1;
    public static MemeTags MEME_TAG = null;
    public static ImageView imageModele;

    private Handler handler;
    private static FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modele);

        handler = new Handler();
        manager = getSupportFragmentManager();

        changerFragment(Modele1.newInstance(1));
    }


    private Runnable getFragmentRunnable(final Fragment fragment) {
        Runnable changeFragmentThread = new Runnable() {
            @Override
            public void run() {;
                FragmentTransaction fragmentTransaction = manager.beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                fragmentTransaction.replace(R.id.contentframe, fragment, CURRENT_TAG.toString());
                //fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        };

        return changeFragmentThread;
    }

    public static void changerFragment(Fragment fragment){

        //handler.post(getFragmentRunnable(fragment));
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        fragmentTransaction.replace(R.id.contentframe, fragment, CURRENT_TAG.toString());
        //fragmentTransaction.addToBackStack(CURRENT_TAG.toString());
        fragmentTransaction.commit();
        manager.popBackStack();
    }

    @Override
    public void onBackPressed() {

        int fragments = getSupportFragmentManager().getBackStackEntryCount();
        if (fragments == 0) {
            finish();
            return;
        }

        super.onBackPressed();
    }

    @Override
    public void onFragmentInteraction(int position) {

        //handler.post(getFragmentRunnable(fragment));
    }

}
