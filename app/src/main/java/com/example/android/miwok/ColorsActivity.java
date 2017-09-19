package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import static android.media.AudioManager.AUDIOFOCUS_GAIN_TRANSIENT;
import static android.media.AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK;

public class ColorsActivity extends AppCompatActivity {
    // new media player instance to start, pause, ... the audio
    private MediaPlayer mMediaPlayer;


    // new on completion listener instance to perform resourc release when the audio stops playing
    // to free up memory resources
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {

        // override the on completion method
        @Override

        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();


        }


    };


    // new Audio manager instance to REQUEST,MANAGE,ABANDON audio focus
    // audio focus means you have the mic to play the audio
    private Context mContext;
    private AudioManager mAudioManager;


    //2- onAudioFocusChange instance, to MANAGE the audio focus different states
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {


        @Override
        public void onAudioFocusChange(int focusChange) {

            // when we have the mic to speak
            if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {

                mMediaPlayer.start();

            }
            // when someone else's got the mic
            else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                mMediaPlayer.stop();
                releaseMediaPlayer();
            }
            // when we lose the mic for short time, or we lose it but we still can sing in low voice
            else if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                // pause it and start over because the user must listen carefully to the word
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        // Create an array list of type Word
        final ArrayList<Word> words = new ArrayList<Word>();


        words.add(new Word("أحمر", "weṭeṭṭi", R.drawable.color_red, R.raw.color_red));
        words.add(new Word("أخضر", "chokokki", R.drawable.color_green, R.raw.color_green));
        words.add(new Word("بني", "ṭakaakki", R.drawable.color_brown, R.raw.color_brown));
        words.add(new Word("رمادي", "ṭopoppi", R.drawable.color_gray, R.raw.color_gray));
        words.add(new Word("اسود", "kululli", R.drawable.color_black, R.raw.color_black));
        words.add(new Word("ابيض", "kelelli", R.drawable.color_white, R.raw.color_white));
        words.add(new Word("اصفر باهت", "ṭopiisә", R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow));
        words.add(new Word("اصفر لامع", "chiwiiṭә", R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow));


// Create an {@link ArrayAdapter}, whose data source is a list of Words. The
        // adapter knows how to create layouts for each item in the list, using the
        // list_item.xml layout resource defined in the res-layout.
        // This list item layout contains a douple {@link TextView}, which the adapter will set to
// display two words(miwok and arabic).
        WordAdapter adapter = new WordAdapter(this, words, R.color.category_colors);


        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
// word_listyout file.
        ListView listView = (ListView) findViewById(R.id.list);

        // Make the {@link ListView} use the {@link ArrayAdapter} we created above, so that the
        // {@link ListView} will display list items for each word in the list of words.
        // Do this by calling the setAdapter method on the {@link ListView} object and pass in
// 1 argument, which is the {@link ArrayAdapter} with the variable name itemsAdapter.
        listView.setAdapter(adapter);

// play the audio when clicking on the view
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {


                //get the word object at the given position the user clicked on
                Word word = words.get(position);

                // to check on every object and it's contents
                Log.v("PhraseActivity", "current word" + word);


                // release the media player resources before playing another sound
                releaseMediaPlayer();

                // AudioManager API to REQUEST, MANAGE, and ABANDON audio focus
                // 1- audio focus REQUEST
                int status = mAudioManager.requestAudioFocus(
                        // use the on audio focus change listener to keep track of the
                        // state of the music playing
                        mOnAudioFocusChangeListener,
                        // i want to play music type of sound
                        AudioManager.STREAM_MUSIC,
                        // it will be played for a short time
                        AUDIOFOCUS_GAIN_TRANSIENT);

                /*
                if the requestAudioFocus method returns (GRANTED), then we play the sound
                 */
                if (status == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // create mediaplayer object
                    // word.getAudio() has the position of the view and the suitable audio for it
                    // depending on that position
                    mMediaPlayer = MediaPlayer.create(ColorsActivity.this, word.getAudio());

                    //start the audio
                    mMediaPlayer.start();

                    // set up listener when finish the song to release it
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);

                    // if not (GRANTED) then the user will click again

                }

                // hint: step 2 is at the very begining of the code
                // step 3 is in the releaseMediaplayer method


            }


        });


    }


    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    /**
     * Clean up the media player by releasing its resources.
     */
// when the app stops , we need to clean up the resources to prevent
    //consuming battery or memory, so we use the release method
    // after the sound starts
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;

            // 3- ABANDON the audio focus
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);

        }
    }

}

