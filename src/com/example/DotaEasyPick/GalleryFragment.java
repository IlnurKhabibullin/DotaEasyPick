package com.example.DotaEasyPick;

/**
 * Created by Ильнур on 14.05.15.
 */
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

public class GalleryFragment extends Fragment {

    private GridView gridview;
    private Context context;
    private static MainFragment fragment;


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.gallery_layout, container, false);
        context = view.getContext();
        gridview = (GridView)view.findViewById(R.id.gridView1);
        ImageAdapter ia = new ImageAdapter(context);
        gridview.setAdapter(ia);

        gridview.setOnItemClickListener(new GridView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position,
                                    long id) {
                boolean flag = true;
                try {
                    for (HeroButton hb: fragment.heroesList) {
                        if (hb.heroId == position)
                            flag = false;
                    }
                } catch (NullPointerException e) {
                    System.err.println(e);
                }
                if (flag) {
                    System.out.println("ready to setHero in gallery");
                    fragment.currentButton.setHero(position, ImageAdapter.mThumbIds[position]);
                    System.out.println("invoking spinnerFilling from gallery");
                    fragment.spinnerFilling();
                    System.out.println("spinnerFilling finished from gallery");
                    MainActivity.viewPager.setCurrentItem(0);
                }
            }
        });

        return view;
    }

    public static void setMainFragment (MainFragment mf) {
        fragment = mf;
    }

	/*private GridView.OnItemClickListener gridviewOnItemClickListener = new GridView.OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View v, int position,
				long id) {
			boolean flag = true;
			try {
				for (int i = 0; i < 10; i++) {
					if (MainFragment.allHeroes[i].equals(ImageAdapter.mHeroesNames[position]))
						flag = false;
				}
			} catch (NullPointerException e) {

			}
			if (flag) {
				MainFragment.currentButton.setImageResource(ImageAdapter.mThumbIds[position]);
			}
			MainFragment.allHeroes[MainFragment.curHero] = ImageAdapter.mHeroesNames[position];
			MainActivity.viewPager.setCurrentItem(0);
		}
	};*/

}
