package com.example.earzo.styles.adapters;

import android.annotation.TargetApi;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.earzo.styles.R;
import com.example.earzo.styles.models.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GridViewAdapter extends BaseAdapter {

    private Context mContext;

    public GridViewAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return productList.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    @TargetApi(21)
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView = null;

        if ((convertView == null) && (inflater != null)) {

                // get layout from xml file
                gridView = inflater.inflate(R.layout.custom_grid_item, parent, false);
        } else {
            gridView = (View) convertView;
        }

        TextView textView_gridItemName = (TextView) gridView.findViewById(R.id.textView_gridItemName);
        TextView textView_gridItemPrice = (TextView) gridView.findViewById(R.id.textView_gridItemPrice);
        ImageView imageView_gridImage = (ImageView) gridView.findViewById(R.id.imageView_gridImage);
        imageView_gridImage.setClipToOutline(true);

        String price = "LKR" + Double.toString(productList.get(position).getPrice());

        textView_gridItemName.setText(productList.get(position).getName());
        textView_gridItemPrice.setText(price);
        Picasso.get().load(productList.get(position).getScaledImage()).placeholder(R.drawable.app_launcher_icon).into(imageView_gridImage);

        return gridView;
    }

    // references to our images
    private List<Product> productList = Product.listAll(Product.class);

}
