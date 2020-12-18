package com.example.sofra.adapter.SaleAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.sofra.R;
import com.example.sofra.data.model.itemFood.ItemFoodData;

import java.util.List;

import butterknife.ButterKnife;

public class ItemsListOrderAdapter extends BaseAdapter {

    Context context;
    LayoutInflater layoutInflater;
    List<ItemFoodData> spinnnerArrayList;

    public ItemsListOrderAdapter(Context context, List<ItemFoodData> spinnerArrayList) {
        this.context = context;

        this.spinnnerArrayList = spinnerArrayList;
    }

    @Override
    public int getCount() {
        return spinnnerArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return spinnnerArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    class ViewHolder {
        TextView setAdapterListTvNameItemsOrder, setAdapterListTvPriceItemsOrder;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.set_adapter_list_items, parent, false);

            viewHolder = new ViewHolder(convertView);

            viewHolder.setAdapterListTvNameItemsOrder = convertView.findViewById(R.id.setAdapterListTvNameItemsOrder);
            viewHolder.setAdapterListTvPriceItemsOrder = convertView.findViewById(R.id.setAdapterListTvPriceItemsOrder);

            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.setAdapterListTvNameItemsOrder.setText(spinnnerArrayList.get(position).getName());
        double total = Double.parseDouble((spinnnerArrayList.get(position).getQuantity()) + (spinnnerArrayList.get(position).getPrice()));
        viewHolder.setAdapterListTvPriceItemsOrder.setText(total + " LE");

        return convertView;
    }


}
