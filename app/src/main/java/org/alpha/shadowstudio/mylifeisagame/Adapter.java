package org.alpha.shadowstudio.mylifeisagame;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{

    private Context context;
    private ArrayList<Tarea> tareas;

    public class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView hNombre;
        private final TextView horaInit;
        private final TextView horaFin;

        public ViewHolder(View view){
            super(view);
            hNombre = (TextView) view.findViewById(R.id.textTitle);
            horaInit = (TextView) view.findViewById(R.id.textFecha1);
            horaFin = (TextView) view.findViewById(R.id.textFecha2);
        }



    }

   public Adapter(Context context, ArrayList<Tarea> tareas) {
        super();
        this.context=context;
        this.tareas = tareas;
   }

    public int getCount() {
        return tareas.size();
    }
    public Tarea getItem(int position) {
        return tareas.get(position);
    }

    @Override
    public Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.hNombre.setText((tareas.get(position).getTitulo()));
        holder.horaInit.setText( ""+new Date(tareas.get(position).getDateInit()));
        holder.horaFin.setText((""+new Date(tareas.get(position).getDateFin())));
        final int num=position;
        holder.hNombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Tarea tarea=tareas.get(num);
                Intent intent=new Intent(context,TareasActivity.class);
                intent.putExtra("tarea",tarea);
                context.startActivity(intent);
            }
        });
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return tareas.size();
    }


}
