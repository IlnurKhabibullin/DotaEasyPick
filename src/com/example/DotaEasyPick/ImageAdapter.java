package com.example.DotaEasyPick;

/**
 * Created by Ильнур on 14.05.15.
 */
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {

    private Context mContext;

    public ImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return mThumbIds[position];
    }

    public long getItemId(int position) {
        return position;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(120, 67));
            imageView.setBackgroundColor(999999999);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }

    // references to our images
    public	static Integer[] mThumbIds = { R.drawable.abaddon, R.drawable.anti_mage,
            R.drawable.broodmother, R.drawable.chaos_knight, R.drawable.chen,
            R.drawable.dark_seer, R.drawable.drow_ranger, R.drawable.invoker,
            R.drawable.kotl, R.drawable.meepo, R.drawable.puck,
            R.drawable.pudge, R.drawable.pugna, R.drawable.slardar,
            R.drawable.storm_spirit, R.drawable.templar_assassin, R.drawable.tidehunter,
            R.drawable.timbersaw, R.drawable.weaver, R.drawable.witch_doctor };
    public static String[] mHeroesNames = {"abaddon", "anti_mage","broodmother", "chaos_knight", "chen", "dark_seer",
            "drow_ranger", "invoker", "kotl", "meepo", "puck", "pudge", "pugna", "slardar", "storm_spirit", "templar_assassin",
            "tidehunter", "timbersaw", "weaver", "witch_doctor"};

}
