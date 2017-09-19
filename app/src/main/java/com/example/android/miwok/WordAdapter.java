package com.example.android.miwok;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


// THIS IS A CUSTOM ARRAY ADAPTER
public class WordAdapter extends ArrayAdapter<Word> {


    private int mColorResorceID;


    public WordAdapter(@NonNull Context context, ArrayList<Word> resource, int categorycolor) {
        super(context, 0, resource);

        this.mColorResorceID = categorycolor;
    }

    // convertView = the recycled list item view
    // parent = the parent of all the list item views which is ListView

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused
        View listItemView = convertView;
        // if there is no view ro reuse, inflate (create) one from the List_item.xml layout
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {Word.java} object's at the correct position in the list (0,1,2,..)
        final Word currentview = getItem(position);


        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwokword);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the Miwok TextView
        miwokTextView.setText(currentview.getMiwokTranslation());

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.defaulthword);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the arabic translation TextView
        defaultTextView.setText(currentview.getDefaultTranslation());

        // Find the image
        ImageView miwokimage = (ImageView) listItemView.findViewById(R.id.image);


        if (currentview.getImageResult()) {
            // to show the image
            miwokimage.setImageResource(currentview.getimage());

        } else {
// to hide the image
            miwokimage.setVisibility(View.GONE);
        }

        // Set the theme color for the list item
        View textContainer = listItemView.findViewById(R.id.text_container);
        // Find the color that the resource ID maps to
        int color = ContextCompat.getColor(getContext(), mColorResorceID);
        // Set the background color of the text container View
        textContainer.setBackgroundColor(color);



        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;

    }

}