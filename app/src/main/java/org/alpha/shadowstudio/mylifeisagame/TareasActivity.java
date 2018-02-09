package org.alpha.shadowstudio.mylifeisagame;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.Date;

public class TareasActivity extends AppCompatActivity {

    private EditText descrip;
    private TextView cat;
    private EditText fechaInicio;
    private EditText fechaFin;
    private Button edit;
    private boolean editPower;
    private Tarea tarea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer2);
        tarea = (Tarea) getIntent().getSerializableExtra("tarea");
        editPower=false;
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
        EditText titulo= findViewById(R.id.editTitulo);
        titulo.setText(tarea.getTitulo());
        descrip = findViewById(R.id.editDescip);
        descrip.setText(tarea.getDescripcion());
        cat = findViewById(R.id.textViewSpinnerCat);
        cat.setText(""+tarea.getCategoria());
        fechaInicio = findViewById(R.id.editTextFechaInicio);
        fechaInicio.setText(new Date(tarea.getDateInit()).toString());
        fechaFin = findViewById(R.id.editTextFechaFin);
        fechaFin.setText(new Date(tarea.getDateFin()).toString());
        onOff(false);
        Button usuarios =findViewById(R.id.buttonUsers);
        usuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(), UsuarioActivity.class);
                intent.putExtra("Tarea", tarea);
                startActivity(intent);
            }
        });
        edit = findViewById(R.id.buttonEdit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!editPower){
                    editPower=true;
                    edit.setText("Save");
                    onOff(true);
                }else{
                    editPower=false;
                    edit.setText("Edit");
                    onOff(false);
                    SQLiteGestor bdg = new SQLiteGestor(getApplicationContext(),"AppBDs.sqlite",null,1);
                    SQLiteDatabase bd = bdg.getReadableDatabase();
                    ContentValues content=new ContentValues();
                    content.put("descrip",descrip.getText().toString());
                    content.put("categoria",cat.getText().toString());
                    content.put("",fechaInicio.getText().toString());
                    content.put("",fechaFin.getText().toString());
                    tarea.getId();
                }
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        switch(item.getItemId()) {

            case android.R.id.home:

                //drawerLayout.openDrawer(GravityCompat.START);

                return true;

        }

        return super.onOptionsItemSelected(item);

    }
    public void onOff(boolean power){
        descrip.setFocusable(power);
        descrip.setClickable(power);
        cat.setFocusable(power);
        cat.setClickable(power);
        fechaInicio.setFocusable(power);
        fechaInicio.setClickable(power);
        fechaFin.setFocusable(power);
        fechaFin.setClickable(power);
    }
}

