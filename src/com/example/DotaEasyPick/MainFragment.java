package com.example.DotaEasyPick;

/**
 * Created by Ильнур on 14.05.15.
 */
import java.util.ArrayList;
import java.util.HashMap;

import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;


public class MainFragment extends Fragment {

    public ArrayList<HeroButton> heroesList;
    private Button mPickButton;
    private Cursor cursor;
    private SQLiteDatabase db;
    static HeroButton currentButton;
    private Spinner spinner;
    String [] spinnerList;
    static int curHero;
    Algorithm algorithm;

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

        final View view = inflater.inflate(R.layout.main_layout, container, false);
        db = MainActivity.dbHelper.getWritableDatabase();
        GalleryFragment.setMainFragment(this);

        /*String query = "select * from hero_table";
        cursor = db.rawQuery(query, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                String str;
                do {
                    str = "";
                    for (String cn : cursor.getColumnNames()) {
                        str = str.concat(cn + " = "
                                + cursor.getString(cursor.getColumnIndex(cn)) + "; ");
                    }
                    System.out.println(str);
                } while (cursor.moveToNext());
            } else
                System.out.println("dsgft");
        }*/

        algorithm = new Algorithm();
        heroesList = new ArrayList<>();
        setButtons(view);
//		{
        spinnerList = new String[6];
        for (int i = 0; i < 6; i++) {
            spinnerList[i] = "" +
                    "";
        }
//    	}
        mPickButton = (Button)view.findViewById(R.id.pick_button);
        mPickButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                MainActivity.viewPager.setCurrentItem(1);
            }
        });
//		{
        spinner = (Spinner)view.findViewById(R.id.spinner1);
        CustomArrayAdapter adapter = new CustomArrayAdapter(getActivity(), R.layout.custom_spinner,
                spinnerList, ImageAdapter.mThumbIds, inflater);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int pos, long id) {
//        			int position = 0;
                for (int i = 0; i < ImageAdapter.mHeroesNames.length; i++) {
                    if (ImageAdapter.mHeroesNames[i].equals((String)spinner.getSelectedItem())) {
                        currentButton.button.setImageResource(ImageAdapter.mThumbIds[i]);
                        currentButton.heroId = i;
                        break;
                    }
                }
                spinnerFilling();
        			/*boolean flag = true;
					for (int j = 0; j < 10; j++) {
						try {
							if (allHeroes[j].equals(ImageAdapter.mHeroesNames[position]))
								flag = false;
						} catch (NullPointerException e) {

		        		}
					}
					System.out.println(flag + " " + position);
					if (flag) {*/
//					}
                /*String workRequestType = arg0.getItemAtPosition(pos)
                        .toString();

                if (pos != 0)
                    Toast.makeText(WorkOrderOpen.this, workRequestType,
                            Toast.LENGTH_LONG).show();*/
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }

        });
//		}
        return view;
    }

    private void setButtons (View view) {
        int [] images = new int [] {
                R.drawable.empty_slot_1, R.drawable.empty_slot_1_selected,
                R.drawable.empty_slot_2, R.drawable.empty_slot_1_selected,
                R.drawable.empty_slot_3, R.drawable.empty_slot_1_selected,
                R.drawable.empty_slot_4, R.drawable.empty_slot_1_selected,
                R.drawable.empty_slot_5, R.drawable.empty_slot_1_selected,
                R.drawable.empty_slot_6, R.drawable.empty_slot_1_selected,
                R.drawable.empty_slot_7, R.drawable.empty_slot_1_selected,
                R.drawable.empty_slot_8, R.drawable.empty_slot_1_selected,
                R.drawable.empty_slot_9, R.drawable.empty_slot_1_selected,
                R.drawable.empty_slot_10, R.drawable.empty_slot_1_selected};

        ImageButton [] views = new ImageButton [] {
                (ImageButton)view.findViewById(R.id.radiantHero1),
                (ImageButton)view.findViewById(R.id.radiantHero2),
                (ImageButton)view.findViewById(R.id.radiantHero3),
                (ImageButton)view.findViewById(R.id.radiantHero4),
                (ImageButton)view.findViewById(R.id.radiantHero5),
                (ImageButton)view.findViewById(R.id.direHero1),
                (ImageButton)view.findViewById(R.id.direHero2),
                (ImageButton)view.findViewById(R.id.direHero3),
                (ImageButton)view.findViewById(R.id.direHero4),
                (ImageButton)view.findViewById(R.id.direHero5)
        };

        for (int i = 0; i < 10; i++) {
            HeroButton hb = new HeroButton(views[i], this, images[i*2], images[i*2 + 1]);
            heroesList.add(hb);
        }

    }

    void selectButton (HeroButton hb) {
//        System.out.println("selecting button");
        for (HeroButton button: heroesList) {
            if (button.isSelected) {
                button.isSelected = false;
                button.button.setBackgroundResource(button.unselectedImage);
            }
        }
        hb.isSelected = true;
        currentButton = hb;
        hb.button.setBackgroundResource(hb.selectedImage);
        if (heroesList.indexOf(hb) < 5) {
            for (int i = 5; i < 10; i++) {
                if (heroesList.get(i).heroId >= 0) {
                    spinner.setVisibility(spinner.VISIBLE);
                    spinnerFilling();
                }
            }
        } else
            spinner.setVisibility(spinner.INVISIBLE);
//		need to deal with setting "curHero" number of selected button
    }
    //	{
    void spinnerFilling() {
//        System.out.println("spinnerFilling invoked");
        boolean flag = (spinner.getVisibility() == spinner.VISIBLE);
        String enemyHeroes = "";
//        System.out.println("1flag is " + flag);
        if (flag) {
            flag = false;
            for (int i = 5; i < 10; i++) {
                if (heroesList.get(i) != currentButton && heroesList.get(i).heroId >= 0) {
                    enemyHeroes += "," + heroesList.get(i).heroId;
                    flag = true;
                }
            }
        }
        if (flag) {
//            System.out.println("2flag is true");
            enemyHeroes = enemyHeroes.substring(1);
            String query = "select * from main_table"; //where hero_id not in (" + enemyHeroes + ")";
            /*"select hero_name, count(hero_name) as hero_freq from ability where effect in (select anti_effect" +
                    " from effect where effect_name in (select effect from ability where hero_name in (" + enemyHeroes
                    + "))) group by hero_name order by hero_freq desc";*/
            cursor = db.rawQuery(query, null);

            if (cursor != null) {
//                System.out.println("cursor not empty");
//				это просто вывод данных с вернувшегося из базы кортежа
                /*if (cursor.moveToFirst()) {
                    String str;
                    do {
                        str = "";
                        for (String cn : cursor.getColumnNames()) {
                            str = str.concat(cn + " = "
                                    + cursor.getString(cursor.getColumnIndex(cn)) + "; ");
                        }
                        System.out.println(str);
                    } while (cursor.moveToNext());
                }*/
//                cursor.close();
                int[] rParams = new int[63];
                int[] dParams = new int[63];
                for (int i = 0; i < rParams.length; i++) {
                    rParams[i] = 0;
                    dParams[i] = 0;
                }
                int rCount = 0, dCount = 0;
                for (int i = 0; i < 5; i++) {
                    int id = heroesList.get(i).heroId;
                    if (id > -1) {
                        rParams = getHeroParams(cursor, id, rParams);
                        rCount++;
                    }
                    id = heroesList.get(i + 5).heroId;
                    if (id > -1) {
                        dParams = getHeroParams(cursor, id, dParams);
                        dCount++;
                    }
                }
//                System.out.println("params got, rCount= " + rCount + ", dCount= " + dCount);
                ArrayList<Integer> radiantParams = new ArrayList<>();
                ArrayList<Integer> direParams = new ArrayList<>();
                for (int i = 0; i < rParams.length; i++) {
                    radiantParams.add(rParams[i]);
                    direParams.add(dParams[i]);
                }
                algorithm.updateParams(radiantParams, direParams);
                algorithm.updateCounts(rCount, dCount);
                int[] heroRate = new int[ImageAdapter.mHeroesNames.length];
//                System.out.println("going to set heroRates!");
                if (cursor.moveToFirst()) {
//                    System.out.println("cursor has elements!");
                    for (int i = 0; i < heroRate.length; i++) {
                        ArrayList<Integer> list = new ArrayList<>();
                        for (int j = 0; j < 63; j++) {
//                            System.out.print("j= " + j + ", param= ");
                            Boolean hasNext = false;
                            try {
                                hasNext = (cursor.getInt(2) == j);
                            } catch (CursorIndexOutOfBoundsException e) {
                                System.err.println(e);
                            }
                            if (hasNext) {
//                                System.out.println(cursor.getInt(2) + " " + cursor.getInt(3));
                                list.add(cursor.getInt(3));
                                try {
                                    cursor.moveToNext();
                                } catch (CursorIndexOutOfBoundsException e) {
                                    System.err.println(e);
                                }
                            } else
                                list.add(0);
                        }
//                        System.out.println("invokation checkForHero");
                        heroRate[i] = algorithm.checkForHero(list);
//                        System.out.println(i + "'s hero rate= " + heroRate[i]);
                    }
//                    System.out.println("sorting");
                    for (int i: heroRate) {
                        System.out.println("hero's rate = " + i);
                    }
                    ArrayList<Integer> indexes = new ArrayList<>();
                    for (int i = 0; i < 6; i++) {
                           indexes.add(-1);
                    }
                    for (int j = 0; j < 6; j++) {
                        int rate = -100;
                        for (int i = 0; i < heroRate.length; i++) {
                            if (rate < heroRate[i] && !indexes.contains(i)) {
                                rate = heroRate[i];
                                indexes.set(j, i);
                            }
                        }
                    }
                    for (int i = 0; i < 6; i++) {
                        spinnerList[i] = ImageAdapter.mHeroesNames[indexes.get(i)];
                    }
                }
            }
        }
    }
//	}
    int[] getHeroParams (Cursor cursor, int heroId, int[] params) {
        if (cursor.moveToFirst()) {
//            System.out.println("cursor has elems");
            while (cursor.getInt(1) != heroId) {
                cursor.moveToNext();
            }
            for (int j = 0; j < 63; j++) {
                if (cursor.getInt(2) == j) {
                    params[cursor.getInt(2)] += (cursor.getInt(3));
                    cursor.moveToNext();
                }
            }
        }

        return params;
    }
}