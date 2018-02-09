package org.alpha.shadowstudio.mylifeisagame;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class UsuarioAddActivity extends AppCompatActivity {
    private Tarea tarea;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_add);
        tarea = (Tarea) getIntent().getSerializableExtra("Tarea");
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,true);
        ArrayList<Usuario> arrayList=new ArrayList<>();
        SQLiteGestor bdg = new SQLiteGestor(getApplicationContext(),"AppBDs.sqlite",null,1);
        SQLiteDatabase bd = bdg.getReadableDatabase();
        Cursor rs = bd.rawQuery("SELECT * FROM USUARIOSTAREA WHERE idtarea="+tarea.getId(),null);
        String correo= rs.getString(4);
        Cursor rs2 = bd.rawQuery("SELECT * FROM USUARIO WHERE correo LIKE NOT"+correo,null);
        while (rs2.moveToNext()) {
            Usuario usuario = new Usuario(rs2.getString(1), rs2.getString(2), rs2.getInt(3));
            if (rs2.getInt(4) < 0) {
                arrayList.add(usuario);
            }


        }
        Adapter2 adapter= new Adapter2(getApplicationContext(),arrayList);
        RecyclerView myList = findViewById(R.id.recyclerView2);
        myList.setLayoutManager(layoutManager);
        myList.setAdapter(adapter);

    }
}
