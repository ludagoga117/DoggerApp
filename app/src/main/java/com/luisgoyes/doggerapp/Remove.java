package com.luisgoyes.doggerapp;


import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

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
        remOk.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1f));
        remOk.setText(getResources().getString(R.string.sRemove));
        remOk.setTextSize(30);
        remOk.setOnClickListener(onClickOkButton(rootView));

        Button remCancel = new Button(rootView.getContext());
        remCancel.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1f));
        remCancel.setText(getResources().getString(R.string.sCancel));
        remCancel.setTextSize(30);
        remCancel.setOnClickListener(onClickCancelButton());

        LinearLayout ll2 = new LinearLayout(rootView.getContext());
        ll2.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
        params.gravity = Gravity.CENTER_HORIZONTAL;
        ll2.setLayoutParams(params);

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

    private View.OnClickListener onClickOkButton(View rootView){
        final View r = rootView;
        View.OnClickListener h = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                for(int i = control.size()-1; i>=0; i--){
                    if(control.get(i).booleanValue()==true){
                        MainActivity.getMasterDataBase().remove(i);
                    }
                }
                Toast.makeText(r.getContext(), getResources().getString(R.string.tRemoveSuccess), Toast.LENGTH_SHORT).show();
                getActivity().onBackPressed();
            }
        };
        return h;
    }

    private View.OnClickListener onClickCancelButton(){
        View.OnClickListener h = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        };
        return h;
    }
}
