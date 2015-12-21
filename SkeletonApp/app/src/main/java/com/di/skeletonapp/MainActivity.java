package com.di.skeletonapp;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.di.skeletonapp.fragments.DetailFragment;
import com.di.skeletonapp.fragments.HomeFragment;
import com.di.skeletonapp.fragments.RecyclerFragment;
import com.di.skeletonapp.fragments.TabPageFragment;
import com.di.skeletonapp.fragments.TabsFragment;
import com.di.skeletonapp.framework.FlowActivity;
import com.di.skeletonapp.framework.FlowScreen;

public class MainActivity extends FlowActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        HomeFragment.OnFragmentInteractionListener,
        TabsFragment.OnFragmentInteractionListener,
        RecyclerFragment.OnFragmentInteractionListener,
        TabPageFragment.OnFragmentInteractionListener,
        DetailFragment.OnFragmentInteractionListener {

    private Toolbar mToolbar;
    private ActionBarDrawerToggle mToggle;
    private boolean mBackArrowShown;
    private boolean mUseAsBackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, getBackStackDescription(), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        mToggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(mToggle);
        mToggle.syncState();

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBackArrowShown) {
                    if (mUseAsBackButton)
                        onBackPressed();
                    else
                        onUpPressed();
                }
                else
                    drawer.openDrawer(GravityCompat.START);
            }
        });

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        setFlowRoot("home", "test", "test");
        //autoLaunchFragment(HomeFragment.newInstance("test", "test"));
    }

    void showBackArrow(boolean yes) {
        if (yes == mBackArrowShown)
            return;
        mBackArrowShown = yes;
        if (yes) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        else {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            mToggle.syncState();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
            mUseAsBackButton = !mUseAsBackButton;
            item.setChecked(mUseAsBackButton);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            setFlowRoot("home", "test", "test");
            //autoLaunchFragment(HomeFragment.newInstance("test", "test"));
        } else if (id == R.id.nav_tabs) {
            setFlowRoot("tabs", "test", "test");
            //autoLaunchFragment(TabsFragment.newInstance("test", "test"));
        } else if (id == R.id.nav_recycler) {
            setFlowRoot("recycler", "test", "test");
            //autoLaunchFragment(RecyclerFragment.newInstance("test", "test"));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void showFragment(FlowScreen position) {
        Fragment fragment = null;
        if (mToolbar != null) {
            if (position.uri.equals("detail")) {
                showBackArrow(true);
            } else {
                showBackArrow(false);
            }
            mToolbar.setTitle(position.uri);
        }

        if (position.uri.equals("home"))
            fragment = HomeFragment.newInstance(position.parameters);
        else if (position.uri.equals("tabs"))
            fragment = TabsFragment.newInstance(position.parameters);
        else if (position.uri.equals("recycler"))
            fragment =  RecyclerFragment.newInstance(position.parameters);
        else if (position.uri.equals("detail"))
            fragment = DetailFragment.newInstance(position.parameters);

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.container, fragment);
            transaction.commit();
        }

    }

    //public void autoLaunchFragment(Fragment fragment) {
    //FragmentManager fragmentManager = getSupportFragmentManager();
    //FragmentTransaction transaction = fragmentManager.beginTransaction();
    //transaction.replace(R.id.container, fragment);
    //transaction.commit();

    //setFlow(fragment.toString());
    //}

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onHomeButtonClicked(String param1, String param2) {
        //autoLaunchFragment(DetailFragment.newInstance(param1, param2));
        setFlowUp("detail", param1, param2);
    }

    @Override
    public void onDetailButton1Clicked(String param1, String param2) {
        //autoLaunchFragment(DetailFragment.newInstance(param1, param2));
        setFlowUp("detail", param1, param2);
    }

    @Override
    public void onDetailButton2Clicked(String param1, String param2) {
        //autoLaunchFragment(DetailFragment.newInstance(param1, param2));
        setFlowBack("detail", param1, param2);
    }

}
