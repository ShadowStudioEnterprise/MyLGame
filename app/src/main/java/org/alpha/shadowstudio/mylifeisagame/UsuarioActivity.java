package org.alpha.shadowstudio.mylifeisagame;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class UsuarioActivity extends AppCompatActivity {

    private Tarea tarea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,true);
        ArrayList<Usuario> arrayList=new ArrayList<>();
        SQLiteGestor bdg = new SQLiteGestor(getApplicationContext(),"AppBDs.sqlite",null,1);
        SQLiteDatabase bd = bdg.getReadableDatabase();
        tarea = (Tarea) getIntent().getSerializableExtra("Tarea");
        Cursor rs = bd.rawQuery("SELECT * FROM USUARIOSTAREA WHERE idtarea="+tarea.getId(),null);
        while (rs.moveToNext()){
            Cursor rs2 = bd.rawQuery("SELECT * FROM USUARIO WHERE correo ="+rs.getInt(2),null);
            while (rs2.moveToNext()) {
                Usuario usuario = new Usuario(rs2.getString(1), rs2.getString(2), rs2.getInt(3));
                arrayList.add(usuario);
            }
        }
        Button add= findViewById(R.id.buttonAddUser);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),UsuarioAddActivity.class);
                intent.putExtra("tarea",tarea);
                startActivity(intent);
            }
        });
        Adapter2 adapter= new Adapter2(getApplicationContext(),arrayList);
        RecyclerView myList = findViewById(R.id.recyclerView2);
        myList.setLayoutManager(layoutManager);
        myList.setAdapter(adapter);
    }
}
