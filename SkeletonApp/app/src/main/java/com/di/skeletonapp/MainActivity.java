package com.di.skeletonapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.di.skeletonapp.fragments.CalculatorFragment;
import com.di.skeletonapp.fragments.HomeFragment;
import com.di.skeletonapp.fragments.RecyclerFragment;
import com.di.skeletonapp.fragments.TabsFragment;
import com.di.skeletonapp.model.Calculator;
import com.digintent.framework.FlowActivity;
import com.digintent.framework.FlowScreen;
import com.digintent.framework.FlowStack;

public class MainActivity extends FlowActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar mToolbar;
    private ActionBarDrawerToggle mToggle;
    private boolean mBackArrowShown;
    private boolean mUseAsBackButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
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
                } else
                    drawer.openDrawer(GravityCompat.START);
            }
        });

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // On first start of the activity, display home fragment. Otherwise continue where
        // we left.
        if (savedInstanceState == null)
            FlowStack.setBackstackRoot(this, HomeFragment.newInstance());
        else
            syncBackButton();
    }

    void showBackArrow(boolean yes) {
        if (yes == mBackArrowShown)
            return;
        mBackArrowShown = yes;
        ActionBar bar = getSupportActionBar();
        if (bar == null)
            return;
        if (yes) {
            bar.setDisplayHomeAsUpEnabled(true);
        } else {
            bar.setDisplayHomeAsUpEnabled(false);
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
            FlowStack.setBackstackRoot(this, HomeFragment.newInstance());
        } else if (id == R.id.nav_tabs) {
            FlowStack.setBackstackRoot(this, TabsFragment.newInstance());
        } else if (id == R.id.nav_recycler) {
            FlowStack.setBackstackRoot(this, RecyclerFragment.newInstance());
        } else if (id == R.id.nav_calculator) {
            FlowStack.setBackstackRoot(this, CalculatorFragment.newInstance(new Calculator(0.0)));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void showCurrentFragment() {
        FlowScreen position = getHistory().getCurrent();
        if (position == null)
            return;
        Fragment fragment = position.getFragment();

        if (fragment != null) {
            if (mToolbar != null) {
                syncBackButton();
                mToolbar.setTitle(fragment.getClass().getSimpleName());
            }

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.container, fragment);
            transaction.commit();
        }

    }

    private void syncBackButton() {
        if (mToolbar != null) {
            FlowScreen position = getHistory().getCurrent();
            if (position != null) {

                if (position.getParent() != null) {
                    showBackArrow(true);
                } else {
                    showBackArrow(false);
                }
            }
        }
    }


}
