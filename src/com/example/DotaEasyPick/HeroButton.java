package com.example.DotaEasyPick;

/**
 * Created by Ильнур on 14.05.15.
 */

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class HeroButton {

    MainFragment fragment;
    final ImageButton button;
    final int selectedImage;
    final int unselectedImage;
    boolean isSelected;
    int heroId;

    public HeroButton(ImageButton butt, MainFragment mf,
                      int unselImage, int selImage) {
        fragment = mf;
        selectedImage = selImage;
        unselectedImage = unselImage;
        isSelected = false;
        heroId = -1;
        button = butt;
        button.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                fragment.selectButton(HeroButton.this);
            }
        });
    }
}
