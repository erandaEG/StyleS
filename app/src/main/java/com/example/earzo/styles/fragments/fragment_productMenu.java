package com.example.earzo.styles.fragments;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.earzo.styles.R;
import com.example.earzo.styles.adapters.GridViewAdapter;
import com.example.earzo.styles.models.Product;

import java.util.List;

public class fragment_productMenu extends Fragment {

    public fragment_productMenu() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    @TargetApi(23)
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_menu, container, false);

        final List<Product> productList = Product.listAll(Product.class);

        GridViewAdapter gridAdapter = new GridViewAdapter(getContext());
        GridView gridview = view.findViewById(R.id.gridview);
        gridview.setAdapter(gridAdapter);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                Product product = productList.get(position);
                Long productID = product.getId();

                /*SharedPreferences spForItems = getActivity().getSharedPreferences("Product Details", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = spForItems.edit();
                editor.putLong("Product ID", productID);
                editor.apply();*/

                Bundle bundle = new Bundle(2);
                bundle.putLong("PID", productID);

                Fragment newFragment = new fragment_itemDetails();
                newFragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.content_frame, newFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return view;
    }

    @Override
    public void onResume(){
        super.onResume();

        getActivity().setTitle(R.string.fragmentTitle_Home);
    }

    //call this method anywhere to create a crash by stackOverflow error and get a Crashlytics report
    public void stackOverflow(){
        this.stackOverflow();
    }
}
