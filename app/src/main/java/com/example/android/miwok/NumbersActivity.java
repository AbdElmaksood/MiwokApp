package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {
    private MediaPlayer mMediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);


        // Create an array list of type Word
        final ArrayList<Word> words = new ArrayList<Word>();


        words.add(new Word("واحد", "lutti", R.drawable.number_one, R.raw.number_one));
        words.add(new Word("اثنين", "ottiko", R.drawable.number_two, R.raw.number_two));
        words.add(new Word("ثلاثة", "tolookosu", R.drawable.number_three, R.raw.number_three));
        words.add(new Word("أربعة", "oyyisa", R.drawable.number_four, R.raw.number_four));
        words.add(new Word("خمسة", "massokka", R.drawable.number_five, R.raw.number_five));
        words.add(new Word("ستة", "temmokka", R.drawable.number_six, R.raw.number_six));
        words.add(new Word("سبعة", "kenekaku", R.drawable.number_seven, R.raw.number_seven));
        words.add(new Word("ثمانية", "kawinta", R.drawable.number_eight, R.raw.number_eight));
        words.add(new Word("تسعة", "wo'e", R.drawable.number_nine, R.raw.number_nine));
        words.add(new Word("عشرة", "na'aacha", R.drawable.number_ten, R.raw.number_ten));


// Create an {@link ArrayAdapter}, whose data source is a list of Words. The
        // adapter knows how to create layouts for each item in the list, using the
        // list_item.xml layout resource defined in the res-layout.
        // This list item layout contains a douple {@link TextView}, which the adapter will set to
// display two words(miwok and arabic).
        WordAdapter adapter = new WordAdapter(this, words, R.color.category_numbers);


        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
// word_list.xml file.
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
                mMediaPlayer = MediaPlayer.create(NumbersActivity.this, word.getAudio());
                mMediaPlayer.start();
            }
        });


    }
}
