package com.luisgoyes.doggerapp;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Edit extends Fragment {

    private RadioButton rdbtn;
    public Edit() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_edit, container, false);
        final RadioGroup rg = (RadioGroup) rootView.findViewById(R.id.radiobuttonsGroup);

        for(int i=0; i<MainActivity.getMasterDataBase().size(); i++){
            rdbtn = new RadioButton(rootView.getContext());
            rdbtn.setId(i);
            rdbtn.setText(MainActivity.getMasterDataBase().get(i).getNome() + " (" + MainActivity.getMasterDataBase().get(i).getLatitude() + ", " + MainActivity.getMasterDataBase().get(i).getLogitude() + ")");
            rdbtn.setTextSize(30);
            rg.addView(rdbtn);
        }
        Button bEdit = (Button) rootView.findViewById(R.id.EditEdit);
        Button bCancel = (Button) rootView.findViewById(R.id.EditCancel);
        bEdit.setOnClickListener(onClickEditButton(rootView,rg));
        bCancel.setOnClickListener(onClickCancelButton());
        return rootView;
    }

    private View.OnClickListener onClickEditButton(final View rootView, final RadioGroup rg){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rg.getCheckedRadioButtonId()==-1) {
                    Toast.makeText(rootView.getContext(),getResources().getString(R.string.sAskSelect), Toast.LENGTH_SHORT).show();
                }else{
                    EditFranchise f = new EditFranchise();
                    f.setID(rg.getCheckedRadioButtonId());
                    getFragmentManager().beginTransaction().replace(android.R.id.content, f).commit();
                }
            }
        };
    }

    private View.OnClickListener onClickCancelButton(){
        return new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        };
    }
}
