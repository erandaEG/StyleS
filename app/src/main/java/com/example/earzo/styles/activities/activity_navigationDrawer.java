package com.example.earzo.styles.activities;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.example.earzo.styles.R;
import com.example.earzo.styles.extra.UserSession;
import com.example.earzo.styles.fragments.fragment_changePassword;
import com.example.earzo.styles.fragments.fragment_productMenu;
import com.example.earzo.styles.models.User;

public class activity_navigationDrawer extends activity_base
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        UserSession userSession = UserSession.getUserSession(this);

        User LoggedInUser = User.findById(User.class, userSession.getUID());

        View headerView = navigationView.getHeaderView(0);
        TextView navHeaderUserName = (TextView) headerView.findViewById(R.id.textView_loggedUser);
        String user = LoggedInUser.getFirstName() + " " + LoggedInUser.getLastName();
        navHeaderUserName.setText(user);

        TextView navHeaderUserEmail = (TextView) headerView.findViewById(R.id.textView_loggedUserEmail);
        navHeaderUserEmail.setText(LoggedInUser.getUserEmail());

        int position = getIntent().getIntExtra("position", R.id.content_frame);

        if(getFragmentManager().findFragmentById(R.id.content_frame) == null) {
            if(position == R.id.content_frame) {
                setTitle("Home");
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.content_frame, new fragment_productMenu());
                transaction.commit();
            }
        }
    }

    @Override
    public void onResume(){
        super.onResume();

        setTitle(R.string.app_name);
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
        getMenuInflater().inflate(R.menu.activity_navigation_drawer, menu);
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

            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            setTitle(R.string.fragmentTitle_changePassword);
            Fragment newFragment = new fragment_changePassword();
            transaction.replace(R.id.content_frame, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
            return true;
        }else if (id == R.id.action_logout){

            UserSession userSession = UserSession.getUserSession(this);
            userSession.logout();
            Intent intent = new Intent(this, activity_login.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);

            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment newFragment;
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        if (id == R.id.nav_home) {
            setTitle(R.string.fragmentTitle_Home);
            newFragment = new fragment_productMenu();
            transaction.replace(R.id.content_frame, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();

        } else if (id == R.id.nav_shoppingCart) {

        } else if (id == R.id.nav_favourites) {

        } else if(id == R.id.nav_categories){

        } else if (id == R.id.nav_share) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
