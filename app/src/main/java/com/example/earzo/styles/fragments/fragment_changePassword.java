package com.example.earzo.styles.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.earzo.styles.R;


public class fragment_changePassword extends Fragment {


    public fragment_changePassword() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable  ViewGroup container, @Nullable
            Bundle savedInstanceState) {

        /*UserSession userSession = new UserSession(getActivity());

        if(container != null){
            container.removeAllViews();
        }*/

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_change_password, container, false);

        /*if(userSession.getLogInStatus()) {
            EditText editText_username = (EditText) view.findViewById(R.id.editText_userEmail);
            editText_username.setVisibility(View.GONE);
        }

        return view;*/
    }

    @Override
    public void onResume(){
        super.onResume();

        getActivity().setTitle(R.string.fragmentTitle_changePassword);
    }

}
