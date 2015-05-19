package com.example.DotaEasyPick;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;

public class MainActivity extends FragmentActivity {

    public static final int FRAGMENT_ONE = 0;
    public static final int FRAGMENT_TWO = 1;
    public static final int FRAGMENTS = 2;
    private FragmentPagerAdapter _fragmentPagerAdapter;
    private final List<Fragment> _fragments = new ArrayList<Fragment>();
    static DBHelper dbHelper;
    static ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        dbHelper = new DBHelper(this);
        _fragments.add(FRAGMENT_ONE, new MainFragment());
        _fragments.add(FRAGMENT_TWO, new GalleryFragment());

        _fragmentPagerAdapter = new FragmentPagerAdapter(
                getSupportFragmentManager()) {

            @Override
            public int getCount() {

                return FRAGMENTS;
            }

            @Override
            public Fragment getItem(final int position) {

                return _fragments.get(position);
            }

            @Override
            public CharSequence getPageTitle(final int position) {

                switch (position) {
                    case FRAGMENT_ONE:
                        return "Title One";
                    case FRAGMENT_TWO:
                        return "Title Two";
                    default:
                        return null;
                }
            }
        };
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(_fragmentPagerAdapter);
        viewPager.setCurrentItem(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context) {
            super(context, "easyPickDB", null, 1);
        }

        public void fillDB (SQLiteDatabase db) throws IOException {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    getResources().openRawResource(R.raw.hero_table_create)));
            String line = "", query = "";
            while ((line = reader.readLine()) != null) {
                query += line;
            }
            reader.close();
            db.execSQL(query);
            System.out.println("hero_table created");

            reader = new BufferedReader(new InputStreamReader(
                    getResources().openRawResource(R.raw.hero_table_insert)));
            query = "";
            while ((line = reader.readLine()) != null) {
                query += line;
            }
            reader.close();
            db.execSQL(query);
            System.out.println("hero_table filled");

            reader = new BufferedReader(new InputStreamReader(
                    getResources().openRawResource(R.raw.parameter_table_create)));
            query = "";
            while ((line = reader.readLine()) != null) {
                query += line;
            }
            reader.close();
            db.execSQL(query);
            System.out.println("parameter_table created");

            reader = new BufferedReader(new InputStreamReader(
                    getResources().openRawResource(R.raw.parameter_table_insert)));
            query = "";
            while ((line = reader.readLine()) != null) {
                query += line;
            }
            reader.close();
            db.execSQL(query);
            System.out.println("parameter_table filled");

            reader = new BufferedReader(new InputStreamReader(
                    getResources().openRawResource(R.raw.main_table_create)));
            query = "";
            while ((line = reader.readLine()) != null) {
                query += line;
            }
            reader.close();
            db.execSQL(query);
            System.out.println("main_table created");

            reader = new BufferedReader(new InputStreamReader(
                    getResources().openRawResource(R.raw.main_table_insert1)));
            query = "";
            while ((line = reader.readLine()) != null) {
                query += line;
            }
            reader.close();
            db.execSQL(query);
            System.out.println("main_table filled with 1st insert");

            reader = new BufferedReader(new InputStreamReader(
                    getResources().openRawResource(R.raw.main_table_insert2)));
            query = "";
            while ((line = reader.readLine()) != null) {
                query += line;
            }
            reader.close();
            db.execSQL(query);
            System.out.println("main_table filled with 2nd insert");
        }

        public void onCreate(SQLiteDatabase db) {
            try {
                System.out.println("trying to fill db");
                fillDB(db);
            } catch (IOException e) {
                System.err.println(e);
                System.out.println("peace decibal");
            }
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }

    }
}
