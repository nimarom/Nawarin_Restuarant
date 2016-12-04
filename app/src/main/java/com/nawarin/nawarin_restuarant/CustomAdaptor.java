package com.nawarin.nawarin_restuarant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Narrin on 23/11/2559.
 */
public class CustomAdaptor extends BaseAdapter{

    private Context context;

    String[] strIcon,strTitle,strPrice;

    ImageView iconFood;

    TextView txtFood,txtPrice;

    public CustomAdaptor(Context context, String[] strIcon, String[] strTitle, String[] strPrice) {
        this.context = context;
        this.strIcon = strIcon;
        this.strTitle = strTitle;
        this.strPrice = strPrice;
    }


    @Override
    public int getCount() {
        return strIcon.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = inflater.inflate(R.layout.listviewfood,parent,false);

        iconFood = (ImageView) view.findViewById(R.id.iconfood);

        txtFood = (TextView) view.findViewById(R.id.txt_food_name);

        txtPrice = (TextView) view.findViewById(R.id.txt_food_price);

        Picasso.with(context).load(strIcon[position]).into(iconFood);

        txtFood.setText(strTitle[position]);

        txtPrice.setText(strPrice[position]);


        return view;
    }
}
