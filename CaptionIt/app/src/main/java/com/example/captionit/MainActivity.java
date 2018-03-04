package com.example.captionit;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);



        mDrawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView= findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                // set item as selected to persist highlight
                menuItem.setChecked(true);
                // close drawer when item is tapped
                mDrawerLayout.closeDrawers();

                // Add code here to update the UI based on the item selected
                // For example, swap UI fragments here


                switch(menuItem.getItemId()) {
                    case R.id.nav_howToUse:
                        Intent howToUseIntent = new Intent(MainActivity.this, HowToUse.class);
                        startActivity(howToUseIntent);
                        break;
                    case R.id.nav_faq:
                        Intent faqIntent = new Intent(MainActivity.this, Faq.class);
                        startActivity(faqIntent);
                        break;
                    case R.id.about:
                        Intent aboutIntent = new Intent(MainActivity.this, About.class);
                        startActivity(aboutIntent);
                        break;

                }
                return true;

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void takePhoto(View view) {
        Intent i = new Intent(this, TakePhoto.class);
        startActivity(i);
    }

    public void browsePhoto(View view) {

        Intent i = new Intent(this, BrowsePhoto.class);
        startActivity(i);

    }


}
