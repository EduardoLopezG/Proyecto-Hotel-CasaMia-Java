package com.example.eduardo.hotelcasamia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class GalleryAdapter extends BaseAdapter{

    /* Declare this */
    private List<Gallery> galleryList;
    private Context mContext;

    /* Create this constructor */
    public GalleryAdapter(List<Gallery> galleryList, Context mContext) {
        this.galleryList = galleryList;
        this.mContext = mContext;
    }

    /* Implements this methods */
    @Override
    public int getCount() {
        return galleryList.size();//<-----
    }

    @Override
    public Object getItem(int i) {
        return galleryList.get(i);//<-----
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View rootView = view;
        if (rootView == null) {
            rootView = LayoutInflater.from(mContext).inflate(R.layout.layout_item,null);
            TextView name = (TextView)rootView.findViewById(R.id.label_gallery_information);
            ImageView image = (ImageView)rootView.findViewById(R.id.image_gallery);

            /* Set Data */
            Picasso.with(mContext).load(galleryList.get(i).getImageURL()).into(image);
            name.setText(galleryList.get(i).getName());
        }
        return rootView;
    }
}//End class
