package com.example.museos;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.museos.Model.Element;
import com.example.museos.Model.Museums;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterData extends RecyclerView.Adapter<AdapterData.ViewHolderData> {

    List<Element> listaMuseos;
    Context context;

    public AdapterData(List<Element> listaMuseos, Context context) {
        this.listaMuseos = listaMuseos;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,null,false);

        return new ViewHolderData(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterData.ViewHolderData holder, int position) {


        holder.asignarDatos(listaMuseos.get(position));
        Picasso.get().load(listaMuseos.get(position).getImatge().get(0)).into(holder.imagenMu);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, Museo_Info.class);
                intent.putExtra("descripcion",listaMuseos.get(position).getDescripcio());
                intent.putExtra("imagen",listaMuseos.get(position).getImatge().get(0));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return listaMuseos.size();
    }

    public class ViewHolderData extends RecyclerView.ViewHolder {

        ImageView imagenMu;
        TextView nombreMu;

        public ViewHolderData(@NonNull View itemView) {
            super(itemView);

            imagenMu = (ImageView) itemView.findViewById(R.id.imagenMu);
            nombreMu = (TextView) itemView.findViewById(R.id.nombreM);

        }
        public void asignarDatos(Element element){

            nombreMu.setText(element.getAdrecaNom());

        }
    }
}
