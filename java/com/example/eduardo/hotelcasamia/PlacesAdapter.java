package com.example.eduardo.hotelcasamia;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class PlacesAdapter extends RecyclerView.Adapter<PlacesAdapter.PlacesViewHolder> implements View.OnClickListener{

    ArrayList<PlacesVO> listPlaces;
    private View.OnClickListener listener;

    public PlacesAdapter(ArrayList<PlacesVO> listPlaces) {
        this.listPlaces = listPlaces;
    }

    @Override
    public PlacesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,null,false);
        view.setOnClickListener(this);
        return new PlacesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PlacesViewHolder holder, int position) {
        holder.txtName.setText(listPlaces.get(position).getName());
        holder.txtInformation.setText(listPlaces.get(position).getInfo());
        holder.photo.setImageResource(listPlaces.get(position).getFoto());
    }

    @Override
    public int getItemCount() {
        return listPlaces.size();//<------
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if (listener!=null){
            listener.onClick(view);
        }
    }

    public class PlacesViewHolder extends RecyclerView.ViewHolder {

        TextView txtName, txtInformation;
        ImageView photo;

        public PlacesViewHolder(View itemView) {
            super(itemView);
            txtName = (TextView) itemView.findViewById(R.id.name_item_list);
            txtInformation = (TextView) itemView.findViewById(R.id.info_item_list);
            photo = (ImageView) itemView.findViewById(R.id.image_item_list);
        }
    }
}//End class
