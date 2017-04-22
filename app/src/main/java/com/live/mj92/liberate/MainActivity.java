package com.live.mj92.liberate;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.estimote.coresdk.common.requirements.SystemRequirementsChecker;
import com.estimote.coresdk.observation.region.beacon.BeaconRegion;
import com.estimote.coresdk.recognition.packets.Beacon;
import com.estimote.coresdk.service.BeaconManager;
import com.live.mj92.liberate.fragments.DashboardFragment;
import com.live.mj92.liberate.fragments.HomeFragment;
import com.live.mj92.liberate.fragments.NotificationsFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    public static BeaconManager mBeaconManager;
    public static final BeaconRegion BEACON_REGION = new BeaconRegion("RR", UUID.fromString("B9407F30-F5F8-466E-AFF9-25556B57FE6D"), null, null);


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            boolean flag = false;
            FragmentTransaction mFragmentTransaction = getSupportFragmentManager().beginTransaction();
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mFragmentTransaction.replace(R.id.content, new HomeFragment());
                    flag = true;
                    break;
                case R.id.navigation_dashboard:
                    mFragmentTransaction.replace(R.id.content, new DashboardFragment());
                    flag = true;
                    break;
                case R.id.navigation_notifications:
                    mFragmentTransaction.replace(R.id.content, new NotificationsFragment());
                    flag = true;
                    break;
            }
            mFragmentTransaction.commit();
            return flag;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBeaconManager = new BeaconManager(getApplicationContext());

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_notifications);

    }

    public static void searchForBeacons(final OnBeaconFoundCallback callback) {
        mBeaconManager.setMonitoringListener(new BeaconManager.BeaconMonitoringListener() {
            @Override
            public void onEnteredRegion(BeaconRegion beaconRegion, List<Beacon> list) {
                callback.onBeaconFound(list);
            }

            @Override
            public void onExitedRegion(BeaconRegion beaconRegion) {
                callback.onBeaconExit(beaconRegion);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        SystemRequirementsChecker.checkWithDefaultDialogs(this);
        mBeaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                mBeaconManager.startMonitoring(BEACON_REGION);
            }
        });

    }

    @Override
    public void onPause() {
        mBeaconManager.disconnect();
        super.onPause();
    }
}
