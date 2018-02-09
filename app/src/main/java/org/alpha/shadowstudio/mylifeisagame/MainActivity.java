package org.alpha.shadowstudio.mylifeisagame;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer);
        mAuth = FirebaseAuth.getInstance();
        Toolbar myToolbar =findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        final DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        //Navview
        NavigationView navView = (NavigationView)findViewById(R.id.navview);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                boolean fragmentTransaction = false;
                Fragment fragment = null;
                switch (menuItem.getItemId()) {
                    case R.id.menu_seccion_1:
                        //fragment = new HomeFragment();
                        fragmentTransaction = true;
                        break;
                    case R.id.menu_seccion_2:
                        //fragment = new Fragment2();
                        fragmentTransaction = true;
                        break;
                    case R.id.menu_seccion_3:
                        //fragment = new Fragment3();
                        fragmentTransaction = true;
                        break;
                }
                if(fragmentTransaction) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.content, fragment).commit();
                    menuItem.setChecked(true);
                    getSupportActionBar().setTitle(menuItem.getTitle());
                }
                drawerLayout.closeDrawers();
                return true;
            }
        });
        //Tab layout
        TabLayout tabLayout = findViewById(R.id.tabs);
        getSupportFragmentManager().beginTransaction().replace(R.id.content, new HomeFragment()).commit();
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            boolean fragmentTransaction = false;
            Fragment fragment = null;
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                switch (tab.getPosition()) {
                    case 0:
                        fragment = new HomeFragment();
                        fragmentTransaction = true;
                        break;
                    case 1:
                        fragment = new CalendarFragment();
                        fragmentTransaction = true;
                        break;
                    case 2:
                        fragment = new TareasFragment();
                        fragmentTransaction = true;
                        break;
                    case 3:
                        fragment = new NuevasTareasFragment();
                        fragmentTransaction = true;
                        break;
                }
                if(fragmentTransaction) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.content, fragment).commit();
                    getSupportActionBar().setTitle(tab.getText());
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });

    }
    @Override
    public void onStart() {
        super.onStart();
        /*FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser==null){
            Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
            startActivity(intent);
        }else{
            Intent intent= new Intent(getApplicationContext(), CargaActivity.class);
            startActivity(intent);
        }*/

    }
    @Override

    public boolean onOptionsItemSelected(MenuItem item) {

        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        switch(item.getItemId()) {

            case android.R.id.home:

                //drawerLayout.openDrawer(GravityCompat.START);

                return true;

        }

        return super.onOptionsItemSelected(item);

    }
}
