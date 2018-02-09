package org.alpha.shadowstudio.mylifeisagame;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TareasFragment extends Fragment {


    public TareasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_tareas, container, false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, true);
        ArrayList<Tarea> arrayList = new ArrayList<>();

        SQLiteGestor bdg = new SQLiteGestor(getContext(), "AppBDs.sqlite", null, 1);
        SQLiteDatabase bd = bdg.getReadableDatabase();

        Cursor rs = bd.rawQuery("SELECT * FROM TAREA", null);
        if (rs != null) {
            while (rs.moveToNext()) {
                Tarea tarea = new Tarea(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getLong(5), rs.getLong(6));
                if (rs.getInt(7) > 0) {
                    tarea.setEncurso(true);
                }
                if (rs.getInt(8) > 0) {
                    tarea.setCanceladaOFallida(true);
                }
                arrayList.add(tarea);
            }
        }
        rs.close();
        bd.close();
        bdg.close();

        Adapter adapter = new Adapter(getContext(), arrayList);
        final RecyclerView myList = (RecyclerView) view.findViewById(R.id.recyclerView);
        myList.setLayoutManager(layoutManager);
        myList.setAdapter(adapter);
        return view;
    }

}
