package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class PhraseActivity extends AppCompatActivity {
private MediaPlayer mMediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        // Create an array list of type Word
      final  ArrayList<Word> words = new ArrayList<Word>();


        words.add(new Word("الي اين تذهب؟", "minto wuksus",R.raw.phrase_where_are_you_going));
        words.add(new Word("ما اسمك؟", "tinnә oyaase'nә",R.raw.phrase_what_is_your_name));
        words.add(new Word("اسمي هو...", "oyaaset...",R.raw.phrase_my_name_is));
        words.add(new Word("كيف تشعر؟", "michәksәs?",R.raw.phrase_how_are_you_feeling));
        words.add(new Word("انا بأفضل حال", "kuchi achit",R.raw.phrase_im_feeling_good));
        words.add(new Word("هل انت قادم؟", "әәnәs'aa?",R.raw.phrase_are_you_coming));
        words.add(new Word("نعم انا قادم", "hәә’ әәnәm",R.raw.phrase_yes_im_coming));
        words.add(new Word("انا قادم", "әәnәm",R.raw.phrase_im_coming));
        words.add(new Word("هيا بنا", "yoowutis",R.raw.phrase_lets_go));
        words.add(new Word("تعال هنا", "әnni'nem",R.raw.phrase_come_here));


// Create an {@link ArrayAdapter}, whose data source is a list of Words. The
        // adapter knows how to create layouts for each item in the list, using the
        // list_item.xml layout resource defined in the res-layout.
        // This list item layout contains a douple {@link TextView}, which the adapter will set to
// display two words(miwok and arabic).
        WordAdapter adapter = new WordAdapter(this, words, R.color.category_phrases);


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


            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Word word = words.get(position);

                // word.getAudio() has the position of the view and the suitable audio for it
                // depending on that position
                mMediaPlayer = MediaPlayer.create(PhraseActivity.this, word.getAudio());
                mMediaPlayer.start();
            }
        });



    }
}

