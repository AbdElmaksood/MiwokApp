package com.example.android.miwok;

import android.media.MediaPlayer;
import android.widget.ImageView;

/**
 * Created by mohamed on 7/18/2017.
 */

public class Word {

    // the audio file
    private int maudio;

    // default translation for the word
    private String mdefaultTranslation;

    // Miwok translation for the word
    private String mmiwokTranslation;

    // image for each list item view (m is for member variable)
// if there is an image, the mImage will be changed to (R.id.source)
    // if there is no image it will stay = -1
    private int mImage = mnoImageFound;


    private static final int mnoImageFound = -1;

    public Word(String defaultTranslation, String miwokTranslation, int image, int audio) {
        this.mdefaultTranslation = defaultTranslation;
        this.mmiwokTranslation = miwokTranslation;
        this.maudio = audio;

        // mImage value will change
        this.mImage = image;

    }


    //constructor for the phrase activity (Without images)
    public Word(String mdefaultTranslation, String mmiwokTranslation,int audio) {

        this.mdefaultTranslation = mdefaultTranslation;
        this.mmiwokTranslation = mmiwokTranslation;
        this.maudio = audio;


    }

    // get the audio
    public int  getAudio() {

        return maudio;

    }

    // get default translation
    public String getDefaultTranslation() {
        return mdefaultTranslation;
    }

    // get Miwok translation
    public String getMiwokTranslation() {
        return mmiwokTranslation;
    }

    // get the image
    public int getimage() {
        return mImage;
    }

    public boolean getImageResult() {
// this should return true in case of there is an image
        return mImage != mnoImageFound;

    }
}
