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
    public	static Integer[] mThumbIds = { R.drawable.abaddon, R.drawable.alchemist,
            R.drawable.antimage, R.drawable.ancient_apparition, R.drawable.axe,
            R.drawable.bane, R.drawable.batrider, R.drawable.beastmaster,
            R.drawable.bloodseeker, R.drawable.bounty_hunter, R.drawable.brewmaster,
            R.drawable.bristleback, R.drawable.broodmother, R.drawable.centaur,
            R.drawable.chaos_knight, R.drawable.chen, R.drawable.clinkz,
            R.drawable.clockwerk, R.drawable.crystal_maiden, R.drawable.dark_seer };
    public static String[] mHeroesNames = {"abaddon", "alchemist", "antimage", "ancient_apparition", "axe", "bane",
            "batrider", "beastmaster", "bloodseeker", "bounty_hunter", "brewmaster", "bristleback", "broodmother",
            "centaur", "chaos_knight", "chen", "clinkz", "clockwerk", "crystal_maiden", "dark_seer"
            /*, "drow_ranger", "invoker", "kotl", "meepo", "puck", "pudge", "pugna", "slardar", "storm_spirit",
             "templar_assassin", "tidehunter", "timbersaw", "weaver", "witch_doctor"*/};

}
