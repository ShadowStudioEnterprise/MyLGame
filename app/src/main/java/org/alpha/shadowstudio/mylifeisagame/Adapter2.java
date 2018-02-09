package org.alpha.shadowstudio.mylifeisagame;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.sql.Date;
import java.util.ArrayList;

public class Adapter2 extends RecyclerView.Adapter<Adapter2.ViewHolder>{

    private Context context;
    private ArrayList<Usuario> usuarios;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private final TextView nick;
        private final TextView nivel;

        public ViewHolder(View view){
            super(view);
            nick = (TextView) view.findViewById(R.id.textTitle);
            nivel = (TextView) view.findViewById(R.id.textFecha1);
        }

        @Override
        public void onClick(View view) {
            Usuario usuario= usuarios.get(getLayoutPosition());
            //Intent intent=new Intent(context,TareasActivity.class);
            //intent.putExtra("tarea",tarea);
        }

    }

   public Adapter2(Context context, ArrayList<Usuario> usuarios) {
        super();
        this.context=context;
        this.usuarios = usuarios;
   }

    public int getCount() {
        return usuarios.size();
    }
    public Usuario getItem(int position) {
        return usuarios.get(position);
    }

    @Override
    public Adapter2.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item2, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.nick.setText((usuarios.get(position).getNick()));
        holder.nivel.setText(usuarios.get(position).getNivel());
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return usuarios.size();
    }


}
