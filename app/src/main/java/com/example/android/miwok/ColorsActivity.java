package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {
private MediaPlayer mMediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

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

            // variable i is the position of the view

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Word word = words.get(position);

                // word.getAudio() has the position of the view and the suitable audio for it
                // depending on that position
                mMediaPlayer = MediaPlayer.create(ColorsActivity.this, word.getAudio());
                mMediaPlayer.start();
            }
        });


    }
}

