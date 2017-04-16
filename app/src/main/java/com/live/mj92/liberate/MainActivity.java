package com.live.mj92.liberate;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.estimote.coresdk.common.requirements.SystemRequirementsChecker;
import com.live.mj92.liberate.fragments.DashboardFragment;
import com.live.mj92.liberate.fragments.HomeFragment;
import com.live.mj92.liberate.fragments.NotificationsFragment;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            boolean flag = false;
            FragmentTransaction mFragmentTransaction = getSupportFragmentManager().beginTransaction();
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mFragmentTransaction.replace(R.id.content, new HomeFragment());
//                    mFragmentTransaction.addToBackStack("Home");
                    flag = true;
                    break;
                case R.id.navigation_dashboard:
                    mFragmentTransaction.replace(R.id.content, new DashboardFragment());
//                    mFragmentTransaction.addToBackStack("Dashboard");
                    flag = true;
                    break;
                case R.id.navigation_notifications:
                    mFragmentTransaction.replace(R.id.content, new NotificationsFragment());
//                    mFragmentTransaction.addToBackStack("Notification");
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

        SystemRequirementsChecker.checkWithDefaultDialogs(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_dashboard);
    }

}
