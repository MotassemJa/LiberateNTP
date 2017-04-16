package com.live.mj92.liberate;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

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

    private TextView mTextMessage;
    private List<BeaconRegion> mRegions = new ArrayList<>();

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
    protected void onResume() {
        super.onResume();

        fillBeacons();

        SystemRequirementsChecker.checkWithDefaultDialogs(this);

        App.getmBeaconManager().connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
//                App.getmBeaconManager().startRanging(mRegions.get(0));
                App.getmBeaconManager().startMonitoring(mRegions.get(0));
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_dashboard);

//        App.getmBeaconManager().setRangingListener(new BeaconManager.BeaconRangingListener() {
//            @Override
//            public void onBeaconsDiscovered(BeaconRegion beaconRegion, List<Beacon> list) {
//                if (!list.isEmpty()) {
//                    Toast.makeText(getApplicationContext(), "Beacon Found", Toast.LENGTH_LONG).show();
//                    Log.i("BeaconsAround", list.size() + " Beacons found");
//                }
//            }
//        });

        App.getmBeaconManager().setMonitoringListener(new BeaconManager.BeaconMonitoringListener() {
            @Override
            public void onEnteredRegion(BeaconRegion beaconRegion, List<Beacon> list) {
                if (!list.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Enter", Toast.LENGTH_SHORT).show();
                    Log.i("BeaconsAround", list.size() + " Beacons found - Enter");
                }
            }

            @Override
            public void onExitedRegion(BeaconRegion beaconRegion) {
//                if (beaconRegion != null) {
                    Toast.makeText(getApplicationContext(), "Exit", Toast.LENGTH_SHORT).show();
                    Log.i("BeaconsAround", "Exit");
//                }
            }
        });

    }

    private void fillBeacons() {
        mRegions.add(new BeaconRegion("RR", UUID.fromString("B9407F30-F5F8-466E-AFF9-25556B57FE6D"), null, null));
    }

    @Override
    protected void onPause() {
        App.getmBeaconManager().stopRanging(mRegions.get(0));
        super.onPause();
    }
}
