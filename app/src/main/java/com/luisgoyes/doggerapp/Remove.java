package com.luisgoyes.doggerapp;


import android.app.ActionBar;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import java.util.ArrayList;
import java.util.List;


public class Remove extends Fragment {
    private ArrayList<Boolean> control = new ArrayList<Boolean>();
    public Remove() {}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_remove, container, false);
        final LinearLayout ll = (LinearLayout) rootView.findViewById(R.id.CheckBoxLayout);
        CheckBox cb;
        for(int i=0; i<MainActivity.getMasterDataBase().size(); i++){
            cb = new CheckBox(rootView.getContext());
            cb.setId(i);
            cb.setText(MainActivity.getMasterDataBase().get(i).getNome() + " (" + MainActivity.getMasterDataBase().get(i).getLatitude() + ", " + MainActivity.getMasterDataBase().get(i).getLogitude() + ")");
            cb.setTextSize(30);
            cb.setOnClickListener(onClickCheckBox(cb.getId()));
            ll.addView(cb);
            control.add(new Boolean(false));
        }
        Button remOk = new Button(rootView.getContext());
        remOk.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT,1f));
        Button remCancel = new Button(rootView.getContext());
        remCancel.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT,1f));

        LinearLayout ll2 = new LinearLayout(rootView.getContext());
        ll2.setOrientation(LinearLayout.HORIZONTAL);
        ll2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT,1f));

        ll2.addView(remOk);
        ll2.addView(remCancel);

        ll.addView(ll2);
        return rootView;
    }

    private View.OnClickListener onClickCheckBox(int id) {
        final int ID = id;
        return new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                control.set(ID,new Boolean(!(control.get(ID).booleanValue())));
            }
        };
    }

}
