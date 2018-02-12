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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        TextView textView = view.findViewById(R.id.titleTime);
        final Calendar cal = Calendar.getInstance();
        int dd = cal.get(Calendar.DAY_OF_MONTH);
        int mm = cal.get(Calendar.MONTH);
        int yy = cal.get(Calendar.YEAR);
        textView.setText(new StringBuilder()
                .append(yy).append(" ").append("-").append(mm + 1).append("-")
                .append(dd));
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, true);
        ArrayList<Tarea> arrayList = new ArrayList<>();

        SQLiteGestor bdg = new SQLiteGestor(getContext(), "AppBDs.sqlite", null, 1);
        SQLiteDatabase bd = bdg.getReadableDatabase();

        Cursor rs = bd.rawQuery("SELECT * FROM TAREA", null);
        if (rs != null) {
            int count=0;
            while (rs.moveToNext()) {
                if (count<3) {
                    Tarea tarea = new Tarea(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getLong(5), rs.getLong(6));
                    if (rs.getInt(7) > 0) {
                        tarea.setEncurso(true);
                    }
                    if (rs.getInt(8) > 0) {
                        tarea.setCanceladaOFallida(true);
                    }
                    arrayList.add(tarea);
                    count++;
                }else {
                    break;
                }
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
