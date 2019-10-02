package com.nested.user.myapplication;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import com.nested.user.myapplication.HelperClass.BottomNavigationViewHelper;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private AdView mAdView;
    private FirebaseDatabase database;
    Button livecmtry;
    Button detailedscore;
    private ActionBar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        MobileAds.initialize(this, "ca-app-pub-1971543375558505~8033435599");
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        database = FirebaseDatabase.getInstance();


        toolbar = getSupportActionBar();
        toolbar.setTitle("CricBangla");

        loadFragment(new HomeFragment());

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        BottomNavigationViewHelper.disableShiftMode(navigation);




    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_fixture:
                    toolbar.setTitle("Tap to See Scorecard");


                    fragment = new FixtureFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_squard:
                    toolbar.setTitle("Squad");

                    fragment = new SquardFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_home:
                    toolbar.setTitle("CricBangla");

                    fragment = new HomeFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_match_list:
                    toolbar.setTitle("Fixture");
                    fragment = new MatchListFragment();
                    loadFragment(fragment);
                    return true;

            }
            return false;
        }
    };
    @Override
    public void onBackPressed() {

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        int seletedItemId = bottomNavigationView.getSelectedItemId();
        if (R.id.navigation_match_list == seletedItemId) {
            bottomNavigationView.setSelectedItemId(R.id.navigation_squard);
        } else if (R.id.navigation_squard == seletedItemId) {
            bottomNavigationView.setSelectedItemId(R.id.navigation_fixture);
        }
        else if (R.id.navigation_fixture == seletedItemId) {
            bottomNavigationView.setSelectedItemId(R.id.navigation_home);
        }
        else {
            super.onBackPressed();
        }


    }
}
