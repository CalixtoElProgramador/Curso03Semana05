package com.example.curso03semana03;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //private ArrayList<Pets> pets;
    private TabLayout tabLayoutMain;
    private ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayoutMain = findViewById(R.id.tabLayout_mainActivity);
        viewPager = findViewById(R.id.viewPager_mainActivity);
        MaterialToolbar topAppBar = findViewById(R.id.topAppBar_main);
        setSupportActionBar(topAppBar);

        setUpViewPager();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.top_app_bar, menu);

        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.contact: {
                Intent intentContact = new Intent(MainActivity.this, ContactActivity.class);
                startActivity(intentContact);
                break;
            }

            case R.id.aboutOf: {
                Intent intentAboutOf = new Intent(MainActivity.this, AboutOfActivity.class);
                startActivity(intentAboutOf);
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public void activityFavorites(MenuItem item) {
        Intent intent = new Intent(MainActivity.this, FavoritesActivity.class);
        startActivity(intent);
    }


    private ArrayList<Fragment> addFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new MainFragment());
        fragments.add(new ProfileFragment());

        return fragments;
    }

    private void setUpViewPager() {

        viewPager.setAdapter(new PagerAdapter(this, addFragments()));
        new TabLayoutMediator(tabLayoutMain, viewPager, (tab, position) -> {
            switch (position){
                case 0: {
                    tab.setText(getString(R.string.text_label_01));
                    break;
                }
                case 1: {
                    tab.setText(getString(R.string.text_label_02));
                    break;
                }
            }
        }).attach();
    }


}