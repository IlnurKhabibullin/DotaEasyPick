package com.example.DotaEasyPick;

/**
 * Created by Ильнур on 14.05.15.
 */
import java.util.ArrayList;

import android.database.Cursor;
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

    private ArrayList<HeroButton> heroesList;
    private Button mPickButton;
    private Cursor cursor;
    private SQLiteDatabase db;
    static HeroButton currentButton;
    private Spinner spinner;
    String [] spinnerList;
    static int curHero;
    static String [] allHeroes;

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
		/*db.execSQL("drop table if exists main_table");
		db.execSQL("create table main_table ( id integer primary key autoincrement, hero_id integer," +
				" parameter_id integer, effectiveness integer);");
		try {
//			db.execSQL(queryForHeroTable());
//			db.execSQL(queryForParamTable());
			int i = 0;
			String buffer = "";
			for (String param: queryForMainTable()) {
				if (i < 450) {
					buffer += param + ",";
					i++;
				} else {
					buffer += param;
					db.execSQL("insert into main_table ('hero_id', 'parameter_id', 'effectiveness') values "
							+ buffer);
					i = 0;
					buffer = "";
				}
			}
			if (buffer != "") {
				db.execSQL("insert into main_table ('hero_id', 'parameter_id', 'effectiveness') values "
						+ buffer.substring(0, buffer.length() - 1));
			}
		} catch (IOException e) {
			System.err.println(e);
		}

		String query = "select * from main_table";
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
			}
		}*/

        heroesList = new ArrayList<HeroButton>();
        setButtons(view);
//		{
        spinnerList = new String[6];
        for (int i = 0; i < 6; i++) {
            spinnerList[i] = "not chosen";
        }
        allHeroes = new String[10];
        for (String a: allHeroes) {
            a = "";
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
                        allHeroes[curHero] = (String)spinner.getSelectedItem();
                        break;
                    }
                }
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

    void setButtons (View view) {
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
                }
            }
        } else
            spinner.setVisibility(spinner.INVISIBLE);
        SpinnerFilling();
//		need to deal with setting "curHero" number of selected button
    }
    //	{
    void SpinnerFilling() {
        boolean flag = (spinner.getVisibility() == spinner.VISIBLE);
        String enemyHeroes = "";
//		после изменения базы надо будет делать запросы с индексами героев, а не их именами
		/*if (flag) {
			for (int i = 5; i < 10; i++) {
				if (heroesList.get(i).heroId >= 0) {
					enemyHeroes += ", " + heroesList.get(i).heroId + "";
					flag = true;
				}
			}
		}*/
        System.out.println("1 flag is " + flag);
        if (flag) {
            flag = false;
            for (int i = 5; i < 10; i++) {
                if (!(allHeroes[i] != null && allHeroes[i].equals(""))) {
                    enemyHeroes += ",'" + allHeroes[i] + "'";
                    flag = true;
                    System.out.println(i + " flag is " + flag);
                }
            }
        }
        if (flag) {
            System.out.println(flag);
            enemyHeroes = enemyHeroes.substring(1);
            String query = "select hero_name, count(hero_name) as hero_freq from ability where effect in (select anti_effect" +
                    " from effect where effect_name in (select effect from ability where hero_name in (" + enemyHeroes
                    + "))) group by hero_name order by hero_freq desc";
            cursor = db.rawQuery(query, null);

            if (cursor != null) {
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
                int count = 6;
                if (cursor.getCount() < count)
                    count = cursor.getCount();
                cursor.moveToFirst();
                for (int i = 0; i < count; i++) {
                    spinnerList[i] = cursor.getString(0);
                    cursor.moveToNext();
                }
                cursor.close();
            }
        }
    }
//	}
}