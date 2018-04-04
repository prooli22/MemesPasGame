package ca.umontreal.mpg.memepasgame.helpers;

/**
 * Created by olivier on 18-03-31.
 */

public enum MemeTags {
    BRAIN("brain_meme"),
    DRAKE("drake_meme"),
    TWITTER("twitter_meme"),
    PATRICK("patrick_meme");

    private String tag;

    MemeTags(String tag) { this.tag = tag; }

    @Override
    public String toString() { return this.tag; }
}
