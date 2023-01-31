package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;
public class PizzaAdapter extends ArrayAdapter<Pizza> {
    private List<Pizza> pizzaList;
    private Context context;
    private int layoutResourceId;
    private double totalPrice = 0;

    public PizzaAdapter(Context context, int layoutResourceId, List<Pizza> pizzaList) {
        super(context, layoutResourceId, pizzaList);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.pizzaList = pizzaList;
    }

    public void addPrice(double price) {
        totalPrice += price;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(layoutResourceId, parent, false);

            holder = new ViewHolder();
            holder.pizzaImage = (ImageView) convertView.findViewById(R.id.pizza_image_view);
            holder.pizzaName = (TextView) convertView.findViewById(R.id.pizza_name_text_view);
            holder.pizzaDescription = (TextView) convertView.findViewById(R.id.pizza_description_text_view);
            holder.pizzaPrice = (TextView) convertView.findViewById(R.id.pizza_price_text_view);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final Pizza pizza = pizzaList.get(position);
        holder.pizzaName.setText(pizza.getName());
        holder.pizzaDescription.setText(pizza.getDescription());
        holder.pizzaPrice.setText(String.format("%.2f"+"RON", pizza.getPrice()));
        holder.pizzaImage.setImageResource(pizza.getImageResourceId());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, pizza.getName() + " pizza hozzaadva", Toast.LENGTH_SHORT).show();
                addPrice(pizza.getPrice());
            }
        });

        return convertView;
    }

    static class ViewHolder {
        ImageView pizzaImage;
        TextView pizzaName;
        TextView pizzaDescription;
        TextView pizzaPrice;
    }
}
