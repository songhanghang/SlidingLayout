package com.facefont.songhang.slidinglayout;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.facefont.songhang.slidinglayout.slidingview.HalfPlayCardFragment;
import com.facefont.songhang.slidinglayout.slidingview.SlidingListView;
import com.facefont.songhang.slidinglayout.slidingview.SlidingLayout;

public class MainActivity extends AppCompatActivity implements HalfPlayCardFragment.OnFragmentInteractionListener {
    HalfPlayCardFragment halfPlayCardFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FragmentManager fragmentManager = getSupportFragmentManager();
        halfPlayCardFragment = HalfPlayCardFragment.newInstance("", "");
        fragmentManager.beginTransaction().replace(R.id.fragment, halfPlayCardFragment, "test").commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void fireList(View view) {
        halfPlayCardFragment.openListSlidingLayout();
    }
    public void fireGrid(View view) {
        halfPlayCardFragment.openGridSlidingLayout();
    }
    public void close(View view) {
        halfPlayCardFragment.closeSlidingLayout();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
