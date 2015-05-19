package com.example.DotaEasyPick;

/**
 * Created by Ильнур on 14.05.15.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomArrayAdapter extends ArrayAdapter<String> {

    LayoutInflater inflater;
    String[] spinnerValues;
    Integer[] images;

    public CustomArrayAdapter(Context ctx, int txtViewResourceId, String[] spinValues, Integer[] images, LayoutInflater infl) {
        super(ctx, txtViewResourceId, spinValues);
        inflater = infl;
        this.images = images;
        spinnerValues = spinValues;
    }

    @Override public View getDropDownView(int position, View cnvtView, ViewGroup prnt) {
        return getCustomView(position, cnvtView, prnt, inflater);
    }

    @Override public View getView(int pos, View cnvtView, ViewGroup prnt) {
        return getCustomView(pos, cnvtView, prnt, inflater);
    }

    public View getCustomView(int position, View convertView, ViewGroup parent, LayoutInflater inflater) {
        View mySpinner = inflater.inflate(R.layout.custom_spinner, parent, false);
        TextView main_text = (TextView) mySpinner.findViewById(R.id.text_main_seen);
        main_text.setText(spinnerValues[position]);
//			could add decription of hero (like "carry, melee...") but then need to add in spinner layout this:

/*		<TextView android:id="@+id/sub_text_seen"
	        android:layout_width="wrap_content"
	        android:layout_height="wrap_content"
	        android:layout_below="@+id/text_main_seen"
	        android:layout_marginLeft="5dip"
	        android:layout_toRightOf="@+id/left_pic"
	        android:padding="2dp"
	        android:textColor="#777777" />*/

//		TextView subSpinner = (TextView) mySpinner .findViewById(R.id.sub_text_seen);
//		subSpinner.setText(spinnerSubs[position]);
        ImageView left_icon = (ImageView) mySpinner .findViewById(R.id.left_pic);
        for (int i = 0; i < ImageAdapter.mHeroesNames.length; i++) {
            if (ImageAdapter.mHeroesNames[i].equals(spinnerValues[position])) {
                left_icon.setImageResource(images[i]);
            }
        }
        return mySpinner;
    }

}