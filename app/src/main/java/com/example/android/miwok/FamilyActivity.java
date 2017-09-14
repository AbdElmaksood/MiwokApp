package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {
private MediaPlayer mMediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        // Create an array list of type Word
      final  ArrayList<Word> words = new ArrayList<Word>();


        words.add(new Word("أب", "әpә",R.drawable.family_father,R.raw.family_father));
        words.add(new Word("أم","әṭa",R.drawable.family_mother,R.raw.family_mother));
        words.add(new Word("ابن" ,"angsi",R.drawable.family_son,R.raw.family_son));
        words.add(new Word("ابنة", "tune",R.drawable.family_daughter,R.raw.family_daughter));
        words.add(new Word("أخ اكبر" ,"taachi",R.drawable.family_older_brother,R.raw.family_older_brother));
        words.add(new Word("أخ اصغر", "chalitti",R.drawable.family_younger_brother,R.raw.family_younger_brother));
        words.add(new Word("أخت كبيرة", "teṭe",R.drawable.family_older_sister,R.raw.family_older_sister));
        words.add(new Word("أخت صغيرة" ,"kolliti",R.drawable.family_younger_sister,R.raw.family_younger_sister));
        words.add(new Word("جدة" ,"ama",R.drawable.family_grandmother,R.raw.family_grandmother));
        words.add(new Word("جد" ,"paapa",R.drawable.family_grandfather,R.raw.family_grandfather));


// Create an {@link ArrayAdapter}, whose data source is a list of Words. The
        // adapter knows how to create layouts for each item in the list, using the
        // list_item.xml layout resource defined in the res-layout.
        // This list item layout contains a douple {@link TextView}, which the adapter will set to
// display two words(miwok and arabic).
        WordAdapter adapter = new WordAdapter(this,words,R.color.category_family);


        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
// activity_numbers.xml layout file.
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
                mMediaPlayer = MediaPlayer.create(FamilyActivity.this, word.getAudio());
                mMediaPlayer.start();
            }
        });



    }
    }

