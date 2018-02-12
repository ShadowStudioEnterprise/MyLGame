package org.alpha.shadowstudio.mylifeisagame;

import android.app.*;
import android.content.*;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.*;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TareasActivity extends AppCompatActivity {

    private EditText descrip;
    private TextView cat;
    private EditText fechaInicio;
    private EditText fechaFin;
    private Button edit;
    private boolean editPower;
    private Tarea tarea;
    private String string;
    private EditText titulo;
    private Date date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer2);
        tarea = (Tarea) getIntent().getSerializableExtra("tarea");
        editPower = false;
        date=new Date();
        final DrawerLayout drawerLayout =  findViewById(R.id.drawer_layout);
        //Navview
        NavigationView navView =  findViewById(R.id.navview);
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
                if (fragmentTransaction) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.content, fragment).commit();
                    menuItem.setChecked(true);
                    getSupportActionBar().setTitle(menuItem.getTitle());
                }
                drawerLayout.closeDrawers();
                return true;
            }
        });
        titulo = findViewById(R.id.editTitulo);
        titulo.setText(tarea.getTitulo());
        descrip = findViewById(R.id.editDescip);
        descrip.setText(tarea.getDescripcion());
        cat = findViewById(R.id.textViewSpinnerCat);
        cat.setText("" + tarea.getCategoria());
        fechaInicio = findViewById(R.id.editTextFechaInicio);
        fechaInicio.setText(time(tarea.getDateInit()));
        hideKeyboard(fechaInicio);
        fechaFin = findViewById(R.id.editTextFechaFin);
        fechaFin.setText(time(tarea.getDateFin()));
        hideKeyboard(fechaFin);
        onOff(false);
        editFechas(false);
        Button usuarios = findViewById(R.id.buttonUsers);
        usuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), UsuarioActivity.class);
                intent.putExtra("Tarea", tarea);
                startActivity(intent);
            }
        });
        edit = findViewById(R.id.buttonEdit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!editPower) {
                    onOff(true);
                    editFechas(true);
                    editPower = true;
                    edit.setText("Save");

                } else {
                    editPower = false;
                    edit.setText("Edit");
                    onOff(false);
                    editFechas(false);
                    SQLiteGestor bdg = new SQLiteGestor(getApplicationContext(), "AppBDs.sqlite", null, 1);
                    SQLiteDatabase bd = bdg.getReadableDatabase();
                    ContentValues content = new ContentValues();
                    content.put("titulo", titulo.getText().toString());
                    content.put("descrip", descrip.getText().toString());
                    content.put("categoria", cat.getText().toString());
                    content.put("", fechaInicio.getText().toString());
                    content.put("", fechaFin.getText().toString());
                }
            }
        });
    }

    private void hideKeyboard(EditText et) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(et.getWindowToken(), 0);
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        switch (item.getItemId()) {

            case android.R.id.home:

                //drawerLayout.openDrawer(GravityCompat.START);

                return true;

        }

        return super.onOptionsItemSelected(item);

    }

    public void onOff(boolean power) {
        titulo.setFocusable(power);
        titulo.setClickable(power);
        descrip.setFocusable(power);
        descrip.setClickable(power);
        cat.setFocusable(power);
        cat.setClickable(power);
        fechaInicio.setFocusable(power);
        fechaInicio.setClickable(power);
        fechaFin.setFocusable(power);
        fechaFin.setClickable(power);
    }

    private void editFechas(boolean power) {
        if (power) {
            fechaInicio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    datePicker(true);
                }
            });
            fechaFin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    datePicker(false);
                }
            });
        } else {
            fechaInicio.setOnClickListener(null);
            fechaFin.setOnClickListener(null);
        }
    }

    private void datePicker(final boolean power) {
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        string = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                        date.setYear(year);
                        date.setMonth(monthOfYear);
                        date.setDate(dayOfMonth);
                        tiemPicker(power);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    private void tiemPicker(final Boolean editPower) {
        final Calendar c = Calendar.getInstance();
        final int[] mHour = {c.get(Calendar.HOUR_OF_DAY)};
        final int[] mMinute = {c.get(Calendar.MINUTE)};
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        date.setHours(hourOfDay);
                        date.setMinutes(minute);
                        date.setSeconds(00);
                        mHour[0] = hourOfDay;
                        mMinute[0] = minute;
                        if (editPower) {
                            String str=string + " " + hourOfDay + ":" + minute;
                            fechaInicio.setText(str);
                        } else {
                            String str=string + " " + hourOfDay + ":" + minute;

                            fechaFin.setText(str);
                        }

                    }
                }, mHour[0], mMinute[0], false);
        timePickerDialog.show();
    }
    private String time(long datelong){
        Date date =new Date(datelong);
        LocalDateTime dt = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String str = dt.format(fmt);
        return str;
    }
}

