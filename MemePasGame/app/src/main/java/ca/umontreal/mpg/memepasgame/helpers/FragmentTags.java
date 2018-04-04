package ca.umontreal.mpg.memepasgame.helpers;

/**
 * Created by olivier on 18-03-31.
 */

public enum FragmentTags {
    M1("modele1"),
    M2("modele2"),
    APERCU("apercu");

    private String tag;

    FragmentTags(String tag) { this.tag = tag; }

    @Override
    public String toString() { return this.tag; }
}
