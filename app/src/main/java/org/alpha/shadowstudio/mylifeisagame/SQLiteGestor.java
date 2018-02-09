package org.alpha.shadowstudio.mylifeisagame;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class SQLiteGestor extends SQLiteOpenHelper{
    public SQLiteGestor(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE TAREA("
                + "idtarea INTEGER PRIMARY KEY, "
                + "categoria INTEGER,"+"titulo TEXT, "+"descripcion TEXT,"
                + "dataInit REAL, " +"dataFin REAL,"+ "enCurs INTEGER, "
                + "canceladaOFallida INTEGER, "+ "idusuariotarea INTEGER," +
                "FOREIGN KEY (idusuariotarea) REFERENCES USUARIOTAREA(idusuariotarea)"+")";
        sqLiteDatabase.execSQL(sql);
        sqLiteDatabase.execSQL("INSERT INTO TAREA VALUES(1234, 1,'hola','hola',12222,11111,1,0,1)");
        sql = "CREATE TABLE USUARIO("
                + "correo TEXT UNIQUE  CONSTRAINT cp_emp PRIMARY KEY, "
                +"nick TEXT, "+ "nivel INTEGER "+")";
        sqLiteDatabase.execSQL(sql);
        sqLiteDatabase.execSQL("INSERT INTO USUARIO VALUES ('fran@gmail.com','fran',100)");
        sql = "CREATE TABLE USUARIOSTAREA("
                +"idusuariotarea INTEGER,"
                + "correo TEXT UNIQUE , "
                + "idtarea INTEGER ,"
                +"propietario INTEGER,"
                +"FOREIGN KEY (correo) REFERENCES USUARIO(correo)," +
                "FOREIGN KEY (idtarea) REFERENCES TAREA(ID)," +
                "PRIMARY KEY (idusuariotarea, correo, idtarea)"+")";
        sqLiteDatabase.execSQL(sql);
        sqLiteDatabase.execSQL("INSERT INTO USUARIOSTAREA VALUES (1345,'fran@gmail.com',1234,'fran@gmail.com')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

    }
}