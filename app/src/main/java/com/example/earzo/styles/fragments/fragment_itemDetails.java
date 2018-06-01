package com.example.earzo.styles.fragments;


import android.annotation.TargetApi;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.earzo.styles.R;
import com.example.earzo.styles.models.Product;
import com.squareup.picasso.Picasso;

public class fragment_itemDetails extends Fragment {

    public fragment_itemDetails() {
        // Required empty public constructor
    }


    @Override
    @TargetApi(21)
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_item_details, container, false);

        Long productID = getArguments().getLong("PID");
        Product product = Product.findById(Product.class, productID);

        ImageView imageView = (ImageView) view.findViewById(R.id.imageView_itemDetails);
        TextView textView1 = (TextView) view.findViewById(R.id.textView_itemDetails_itemName);
        TextView textView2 = (TextView) view.findViewById(R.id.textView_itemDetails_itemPrice);

        String price = "LKR" + Double.toString(product.getPrice());

        textView1.setText(product.getName());
        textView2.setText(price);
        Picasso.get().load(product.getScaledImage()).placeholder(R.drawable.app_launcher_icon).into(imageView);

        return view;
    }

    @Override
    public void onResume(){
        super.onResume();

        getActivity().setTitle(R.string.app_name);
    }

}
